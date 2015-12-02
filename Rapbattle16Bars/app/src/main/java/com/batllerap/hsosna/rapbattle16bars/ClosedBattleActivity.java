package com.batllerap.hsosna.rapbattle16bars;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;

public class ClosedBattleActivity extends AppCompatActivity {
    private Battle battle;
    private TextView rapper1;
    private TextView rapper2;
    private VideoView video;
    private ProgressBar pBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed_battle);
        battle = (Battle) getIntent().getSerializableExtra("battle");
        if(battle != null){
            rapper1 = (TextView) findViewById(R.id.closedBattleRapper1);
            rapper2 =(TextView) findViewById(R.id.closedBattleRapper2);
            pBar= (ProgressBar) findViewById(R.id.progressBar);
            rapper1.setText(battle.getRapper1());
            rapper2.setText(battle.getRapper2());
            video = (VideoView) findViewById(R.id.video);
            Uri vUri =Uri.parse(battle.getVideoUrl());
            video.setVideoURI(vUri);

            pBar.setMax(battle.getVotes1() + battle.getVotes2());
            pBar.setProgress(battle.getVotes1());


        }


    }
}
