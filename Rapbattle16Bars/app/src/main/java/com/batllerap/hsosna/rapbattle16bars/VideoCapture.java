package com.batllerap.hsosna.rapbattle16bars;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;


import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class VideoCapture extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback {

    public static final String LOGTAG = "VIDEOCAPTURE";

    private MediaRecorder recorder;
    private MediaPlayer player;
    private SurfaceHolder holder;
    private CamcorderProfile camcorderProfile;
    private TextView redDot;
    private TextView rec;
    private TextView maxTime;
    private static Camera camera;
    private File newFile;
    private DialogFragment VideoAlert;
    private Chronometer timer;
    private int phase;
  //  private AsyncVideoCaptureUI myTask;

    private User aktUser = null;

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

        setContentView(R.layout.activity_video_capture);

        redDot = (TextView) findViewById(R.id.rec_dot);
        rec = (TextView) findViewById(R.id.rec);
        maxTime =(TextView) findViewById(R.id.maxTime);
        beat=  getIntent().getIntExtra("Beat",0);
        id = getIntent().getIntExtra("BattleID",999999);
        phase = getIntent().getIntExtra("Phase", 99990);
        aktUser = (User) getIntent().getSerializableExtra("User");

        beatstr = "beat"+beat;
        Log.v("DAMN", beatstr);
        //TODO: Blinkendes Icon fixen, wenn Zeit vorhanden.
        // myTask = new AsyncVideoCaptureUI(getApplicationContext(),findViewById(android.R.id.content),timer);
        VideoAlert = new VideoUploadAlert();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        camcorderProfile = CamcorderProfile.get(currentCameraId,CamcorderProfile.QUALITY_LOW);


        timer = (Chronometer) findViewById(R.id.timer);
      //  myTask = new AsyncVideoCaptureUI(getApplicationContext(),findViewById(android.R.id.content),timer);


        SurfaceView cameraView = (SurfaceView) findViewById(R.id.CameraView);
        holder = cameraView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        cameraView.setClickable(true);
        cameraView.setOnClickListener(this);

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
        recorder.setOrientationHint(270);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setVideoSize(320,240);
        recorder.setVideoFrameRate(camcorderProfile.videoFrameRate);
        recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        /*recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);


        recorder.setProfile(camcorderProfile);*/


        try{
            newFile = new File(Environment.getExternalStorageDirectory(), "Video" + ".mp4" );
            recorder.setOutputFile(newFile.getAbsolutePath());
        } catch (Exception e) {
        Log.v(LOGTAG,"Couldn't create file");
        e.printStackTrace();
        finish();
    }
       /* // Altes Funktionen
        if (camcorderProfile.fileFormat == MediaRecorder.OutputFormat.THREE_GPP) {
            try {
                newFile = new File(Environment.getExternalStorageDirectory(), "Video" + ".3gp" );
                recorder.setOutputFile(newFile.getAbsolutePath());
            } catch (Exception e) {
                Log.v(LOGTAG,"Couldn't create file");
                e.printStackTrace();
                finish();
            }
        } else if (camcorderProfile.fileFormat == MediaRecorder.OutputFormat.MPEG_4) {
            try {
                newFile = new File(Environment.getExternalStorageDirectory(), "Video" + ".mp4" );
                recorder.setOutputFile(newFile.getAbsolutePath());
                System.out.println("CAPTURE" + newFile.getName());
                System.out.println("CAPTURE"+newFile.getAbsolutePath());
            } catch (Exception e) {
                Log.v(LOGTAG,"Couldn't create file");
                e.printStackTrace();
                finish();
            }
        } else {
            try {
                newFile = new File(Environment.getExternalStorageDirectory(), "Video" + ".mp4" );
                recorder.setOutputFile(newFile.getAbsolutePath());
            } catch (Exception e) {
                Log.v(LOGTAG,"Couldn't create file");
                e.printStackTrace();
                finish();
            }

        }*/
        recorder.setMaxDuration(60000); // 60 seconds
        recorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
            @Override
            public void onInfo(MediaRecorder mr, int what, int extra) {
                if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
                    Log.v("VIDEOCAPTURE", "Maximum Duration Reached");
                    if (recording) {
                        recorder.stop();

                        //  myTask.cancel(true);

                        if (player != null) {
                            player.stop();
                            player.release();
                        }
                        timer.stop();
                        timer.setVisibility(View.GONE);
                        rec.setVisibility(View.GONE);
                        redDot.setVisibility(View.GONE);
                        maxTime.setVisibility(View.GONE);
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
                        VideoAlert = VideoUploadAlert.newInstance(beat, id, "mp4", newFile, aktUser);
                        VideoAlert.show(getSupportFragmentManager(), "123");
                    }
                }

            }
        });

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

          //  myTask.cancel(true);

            if (player != null){
                player.stop();
                player.release();
            }
            timer.stop();
            timer.setVisibility(View.GONE);
            rec.setVisibility(View.GONE);
            redDot.setVisibility(View.GONE);
            maxTime.setVisibility(View.GONE);
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
            VideoAlert = VideoUploadAlert.newInstance(beat, id, "mp4", newFile, aktUser);
            VideoAlert.show(getSupportFragmentManager(), "123");
        } else {
            recording = true;
            if(beat != 4){
                player = MediaPlayer.create(this, getResources().getIdentifier(beatstr,"raw", getPackageName()));
                player.start();
            }

            recorder.start();

            timer.setVisibility(View.VISIBLE);
            redDot.setVisibility(View.VISIBLE);
            rec.setVisibility(View.VISIBLE);
            maxTime.setVisibility(View.VISIBLE);
            timer.setBase(SystemClock.elapsedRealtime());
            timer.start();
          //  myTask.execute();
            Log.v(LOGTAG, "Recording Started");
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Log.v(LOGTAG, "surfaceCreated");

        if (usecamera) {
            camera = Camera.open(currentCameraId);
            camera.setDisplayOrientation(90);
            // Debug Help
           /* final List<Camera.Size> mSupportedVideoSizes = getSupportedVideoSizes(camera);
            for (Camera.Size str : mSupportedVideoSizes)
                Log.e("VideoAUFNAHME", "mSupportedVideoSizes "+str.width + ":" + str.height + " ... "
                        + ((float) str.width / str.height));

*/
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

                p.setPreviewSize(camcorderProfile.videoFrameWidth,camcorderProfile.videoFrameHeight);
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
        if (player != null){
            player.release();
        }
        timer.stop();
        timer.setVisibility(View.GONE);
        rec.setVisibility(View.GONE);
        redDot.setVisibility(View.GONE);
        maxTime.setVisibility(View.GONE);
       // myTask.cancel(true);

        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }


    //DEBUG INFO
    public List<Camera.Size> getSupportedVideoSizes(Camera camera) {
        if (camera.getParameters().getSupportedVideoSizes() != null) {
            return camera.getParameters().getSupportedVideoSizes();
        } else {
            // Video sizes may be null, which indicates that all the supported
            // preview sizes are supported for video recording.
            return camera.getParameters().getSupportedPreviewSizes();
        }
    }
}