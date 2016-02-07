package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Controller.UserController;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

import java.io.IOException;

public class ClosedBattleActivity extends AppCompatActivity {
    private Battle battle;
    private TextView rapper1;
    private TextView rapper2;
    private VideoView video;
    private ProgressBar pBar;
    private User aktUser;

    private ImageView imgRapper1;
    private ImageView imgRapper2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed_battle);

        aktUser = (User) getIntent().getSerializableExtra("User");
        // Set up Toolbar for Navigation
        final Toolbar toolbar = (Toolbar) findViewById(R.id.closedBattleToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Battle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        battle = (Battle) getIntent().getSerializableExtra("battle");
        if(battle != null){
            rapper1 = (TextView) findViewById(R.id.closedBattleRapper1);
            rapper2 =(TextView) findViewById(R.id.closedBattleRapper2);
            imgRapper1 = (ImageView) findViewById(R.id.battleRapper1);
            imgRapper2 = (ImageView) findViewById(R.id.battleRapper2);



            pBar= (ProgressBar) findViewById(R.id.progressBar);
            rapper1.setText(battle.getRapper1().getUsername());
            rapper2.setText(battle.getRapper2().getUsername());
            video= (VideoView) findViewById(R.id.video);

            if (battle.getVoting() != null){
//                pBar.setMax(battle.getVoting().getVotes_rapper1() + battle.getVoting().getVotes_rapper2());
               // pBar.setProgress(battle.getVoting().getVotes_rapper1());
            }else {
                pBar.setMax(2);
                pBar.setProgress(1);
            }
            MediaController mc = new MediaController(this);
            mc.setAnchorView(video);
            mc.setMediaPlayer(video);
            Uri videolink = Uri.parse(battle.getVideo_url());
            video.setMediaController(mc);
            video.setVideoURI(videolink);
            video.requestFocus();
            video.start();




        }




        imgRapper1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.putExtra("Searchuser", UserController.getUser(battle.getRapper1().getUser_id()));
                    intent.putExtra("User",aktUser);
                    startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        imgRapper2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.putExtra("Searchuser", UserController.getUser(battle.getRapper2().getUser_id()));
                    intent.putExtra("User", aktUser);
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("User", aktUser);
       // intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        return;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        myIntent.putExtra("User", aktUser);
      //  myIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
