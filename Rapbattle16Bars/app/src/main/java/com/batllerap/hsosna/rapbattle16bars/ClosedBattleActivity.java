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

    private ImageView imgRapper1;
    private ImageView imgRapper2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed_battle);

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
            String LINK = battle.getVideo_url();
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("");System.out.println(" ");
            System.out.println(LINK);
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");

            MediaController mc = new MediaController(this);
            mc.setAnchorView(video);
            mc.setMediaPlayer(video);
            Uri videolink = Uri.parse(LINK);
            video.setMediaController(mc);
            video.setVideoURI(videolink);
            video.requestFocus();
            video.start();

            imgRapper1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        User rapper = UserController.getUser(battle.getRapper1().getUser_id());
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.putExtra("Searchuser", rapper);
                        startActivity(intent);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            imgRapper2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User rapper = null;
                    try {
                        rapper = UserController.getUser(battle.getRapper2().getUser_id());
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.putExtra("Searchuser", rapper);
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

        }



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
