package com.batllerap.hsosna.rapbattle16bars;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.VideoView;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;

import java.io.IOException;

public class OpenforVotesBattleActivity extends AppCompatActivity {
    private Battle battle;
    private TextView rapper1;
    private TextView rapper2;
    private VideoView video;
    private ProgressBar pBar;
    private ToggleButton lButton;
    private ToggleButton rButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openfor_votes_battle);

        lButton = (ToggleButton) findViewById(R.id.openforvotes_votebutton1);
        rButton = (ToggleButton) findViewById(R.id.openforvotes_votebutton2);

        battle = (Battle) getIntent().getSerializableExtra("battle");
        if (battle != null) {


            rapper1 = (TextView) findViewById(R.id.closedBattleRapper1);
            rapper2 =(TextView) findViewById(R.id.closedBattleRapper2);
            pBar= (ProgressBar) findViewById(R.id.progressBar);
            rapper1.setText(battle.getRapper1().getUsername());
            rapper2.setText(battle.getRapper2().getUsername());
            video = (VideoView) findViewById(R.id.video);
            Uri vUri =Uri.parse(battle.getVideo_url());
            video.setVideoURI(vUri);

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




        }


        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BattleController.voteBattle(battle.getId(),1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (rButton.isChecked()) {
                    rButton.setChecked(false);
                } else {

                }


            }

        });

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BattleController.voteBattle(battle.getId(),2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (lButton.isChecked()) {
                    lButton.setChecked(false);
                } else {

                }

            }

        });
    }

}
