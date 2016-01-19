package com.batllerap.hsosna.rapbattle16bars;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.io.File;
import java.io.IOException;



public class VideoCapture extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback {

    public static final String LOGTAG = "VIDEOCAPTURE";

    private MediaRecorder recorder;
    private MediaPlayer player;
    private SurfaceHolder holder;
    private CamcorderProfile camcorderProfile;
    private static Camera camera;
    private File newFile;
    private DialogFragment VideoAlert;

    int beat;
    int id;
    String beatstr;
    boolean recording = false;
    boolean usecamera = true;
    boolean previewRunning = false;

    private static int currentCameraId = 1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        beat=  getIntent().getIntExtra("Beat",0);
        id = getIntent().getIntExtra("BattleID",999999);

        beatstr = "beat"+beat;
        Log.v("DAMN", beatstr);

        VideoAlert = new VideoUploadAlert();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        camcorderProfile = CamcorderProfile.get(currentCameraId,CamcorderProfile.QUALITY_HIGH);

        setContentView(R.layout.activity_video_capture);
        Button othercam = (Button) findViewById(R.id.switchcam);



        SurfaceView cameraView = (SurfaceView) findViewById(R.id.CameraView);
        holder = cameraView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        cameraView.setClickable(true);
        cameraView.setOnClickListener(this);
        othercam.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(recording){
                    recorder.stop();
                    if (player != null){
                        player.stop();
                        player.release();
                    }




                }
                if (previewRunning){
                    Log.v(LOGTAG, "Stop PreVIEW");
                    camera.stopPreview();
                }

                //camera.release();

                if(currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK){
                    currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
                }
                else {
                    currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
                }

                recreate();
                //camera = Camera.open(currentCameraId);
              // camera.unlock();
              //  recorder.setCamera(camera);

           /*     try{
                    camera.setPreviewDisplay(holder);
                }catch (IOException e){
                    e.printStackTrace();
                }
                camera.startPreview();*/
            }
        });
    }

    private void prepareRecorder() {
        recorder = new MediaRecorder();
        recorder.setPreviewDisplay(holder.getSurface());

        if (usecamera) {
            camera.unlock();
            recorder.setCamera(camera);
        }

        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
        recorder.setOrientationHint(90);

        recorder.setProfile(camcorderProfile);

        // This is all very sloppy
        if (camcorderProfile.fileFormat == MediaRecorder.OutputFormat.THREE_GPP) {
            try {
                File newFile = File.createTempFile("videocapture", ".3gp", Environment.getExternalStorageDirectory());
                recorder.setOutputFile(newFile.getAbsolutePath());
            } catch (IOException e) {
                Log.v(LOGTAG,"Couldn't create file");
                e.printStackTrace();
                finish();
            }
        } else if (camcorderProfile.fileFormat == MediaRecorder.OutputFormat.MPEG_4) {
            try {
                 newFile = File.createTempFile("videocapture", ".mp4", Environment.getExternalStorageDirectory());
                recorder.setOutputFile(newFile.getAbsolutePath());
            } catch (IOException e) {
                Log.v(LOGTAG,"Couldn't create file");
                e.printStackTrace();
                finish();
            }
        } else {
            try {
                File newFile = File.createTempFile("videocapture", ".mp4", Environment.getExternalStorageDirectory());
                recorder.setOutputFile(newFile.getAbsolutePath());
            } catch (IOException e) {
                Log.v(LOGTAG,"Couldn't create file");
                e.printStackTrace();
                finish();
            }

        }
        recorder.setMaxDuration(90000); // 90 seconds

        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            finish();
        }
    }

    public void onClick(View v) {
        if (recording) {
            recorder.stop();

            if (player != null){
                player.stop();
                player.release();
            }
            if (usecamera) {
                try {
                    camera.reconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // recorder.release();
            recording = false;
            Log.v(LOGTAG, "Recording Stopped");
            // Let's prepareRecorder so we can record again
            prepareRecorder();
            VideoAlert = VideoUploadAlert.newInstance(beat, id, "mp4", newFile);
            VideoAlert.show(getSupportFragmentManager(), "123");
        } else {
            recording = true;
            player = MediaPlayer.create(this, getResources().getIdentifier(beatstr,"raw", getPackageName()));
            recorder.start();
            player.start();
            Log.v(LOGTAG, "Recording Started");
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Log.v(LOGTAG, "surfaceCreated");

        if (usecamera) {
            camera = Camera.open(currentCameraId);
            camera.setDisplayOrientation(90);


            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
                previewRunning = true;
            }
            catch (IOException e) {
                Log.e(LOGTAG,e.getMessage());
                e.printStackTrace();
            }
        }

    }


    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.v(LOGTAG, "surfaceChanged");

        if (!recording && usecamera) {
            if (previewRunning){
                camera.stopPreview();
            }

            try {
                Camera.Parameters p = camera.getParameters();

                p.setPreviewSize(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
                p.setPreviewFrameRate(camcorderProfile.videoFrameRate);

                camera.setParameters(p);

                camera.setPreviewDisplay(holder);
                camera.startPreview();
                previewRunning = true;
            }
            catch (IOException e) {
                Log.e(LOGTAG,e.getMessage());
                e.printStackTrace();
            }

            prepareRecorder();
        }
    }


    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.v(LOGTAG, "surfaceDestroyed");
        if (recording) {
            recorder.stop();
            recording = false;
        }
        recorder.release();
        if (usecamera) {
            previewRunning = false;
            //camera.lock();
            camera.release();
        }
        finish();
    }
}