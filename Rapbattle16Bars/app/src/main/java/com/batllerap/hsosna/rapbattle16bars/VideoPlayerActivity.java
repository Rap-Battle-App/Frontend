package com.batllerap.hsosna.rapbattle16bars;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.OpenBattle;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

import java.io.File;


public class VideoPlayerActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    User aktUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        aktUser = (User) getIntent().getSerializableExtra("User");

        setContentView(R.layout.activity_video_player);

        VideoView v = (VideoView) findViewById(R.id.videoPlayer);

        String url = null;
        if (getIntent().getExtras() != null) {
            url = getIntent().getExtras().getString("url");

            if (url != null) {
                v.setMediaController(new MediaController(this));
                v.setOnCompletionListener(this);
                v.setVideoURI(Uri.parse(url));
                v.start();
            }
        }

        if (url == null) {
            throw new IllegalArgumentException("Must set url extra paremeter in intent.");
        }
    }

    @Override
    public void onCompletion(MediaPlayer v) {
        if (getIntent().hasExtra("beatID")) {
            DialogFragment VideoAlert = new VideoUploadAlert();
            VideoAlert = VideoUploadAlert.newInstance((int) getIntent().getSerializableExtra("beatID"), (int) getIntent().getSerializableExtra("battleID"), "mp4", (File) getIntent().getSerializableExtra("video"), aktUser);
            VideoAlert.show(getSupportFragmentManager(), "123");
        } else {
            finish();
        }
    }

    //Convenience method to show a video
    public static void showRemoteVideo(Context ctx, String url) {
        Intent i = new Intent(ctx, VideoPlayerActivity.class);

        i.putExtra("url", url);
        ctx.startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getIntent().hasExtra("beatID")) {
            DialogFragment VideoAlert = new VideoUploadAlert();
            VideoAlert = VideoUploadAlert.newInstance((int) getIntent().getSerializableExtra("beatID"), (int) getIntent().getSerializableExtra("battleID"), "mp4", (File) getIntent().getSerializableExtra("video"), aktUser);
            VideoAlert.show(getSupportFragmentManager(), "123");
        } else {
            finish();
        }
    }
}