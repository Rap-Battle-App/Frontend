package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.OpenBattle;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

public class OpenBattleActivity extends AppCompatActivity {

    private OpenBattle battle;
    private RadioButton beat1, beat2, beat3, beat4;
    private MediaPlayer mPlayer;
    private Button vidButton;
    private int auswahl;
    private RadioGroup beatgroup;
    private User aktUser;
    private VideoView myRound1;
    private VideoView enemyRound1;
    private TextView myRound1Text;
    private TextView enemyRound1Text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        battle = (OpenBattle) getIntent().getSerializableExtra("Battle");
        aktUser = (User) getIntent().getSerializableExtra("User");


        switch (battle.getPhase()){
            case 1:

                setContentView(R.layout.activity_open_battle);
                final Toolbar toolbar = (Toolbar) findViewById(R.id.openBattleToolbar);
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle("Battle");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                beatgroup = (RadioGroup) findViewById(R.id.beatgroup);
                beat1 =(RadioButton) findViewById(R.id.beat1);
                beat2 =(RadioButton) findViewById(R.id.beat2);
                beat3 =(RadioButton) findViewById(R.id.beat3);
                beat4 =(RadioButton) findViewById(R.id.beat4);

                vidButton = (Button) findViewById(R.id.capture_first_round);

                beat1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try{
                            mPlayer.stop();
                            mPlayer.release();
                        }catch (Exception e){

                        }
                        mPlayer = MediaPlayer.create(OpenBattleActivity.this,R.raw.beat1);
                        mPlayer.start();
                        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mPlayer.release();
                            }
                        });
                    }
                });
                beat2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            mPlayer.stop();
                            mPlayer.release();
                        } catch (Exception e) {

                        }
                        mPlayer = MediaPlayer.create(OpenBattleActivity.this, R.raw.beat2);
                        mPlayer.start();
                        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mPlayer.release();
                            }
                        });
                    }
                });
                beat3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            mPlayer.stop();
                            mPlayer.release();
                        } catch (Exception e) {

                        }
                        mPlayer = MediaPlayer.create(OpenBattleActivity.this, R.raw.beat3);
                        mPlayer.start();
                        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mPlayer.release();
                            }
                        });
                    }
                });
                beat4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            mPlayer.stop();
                            mPlayer.release();
                        } catch (Exception e) {

                        }
                        mPlayer = MediaPlayer.create(OpenBattleActivity.this, R.raw.beat4);
                        mPlayer.start();
                        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mPlayer.release();
                            }
                        });
                    }
                });

                vidButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),VideoCapture.class);
                        int radioButtonID = beatgroup.getCheckedRadioButtonId();
                        View radioButton = beatgroup.findViewById(radioButtonID);
                        auswahl = beatgroup.indexOfChild(radioButton);
                        System.out.println(auswahl);
                        intent.putExtra("Beat", auswahl+1);
                        intent.putExtra("BattleID", battle.getId());
                        try {
                            mPlayer.stop();
                            mPlayer.release();
                        } catch (Exception e) {

                        }

                        startActivity(intent);
                    }
                });


                return;

            case 2:
                setContentView(R.layout.activity_open_battle_phase1);
                final Toolbar toolbar1 = (Toolbar) findViewById(R.id.openBattleToolbarphase1);
                setSupportActionBar(toolbar1);
                getSupportActionBar().setTitle("Battle");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                vidButton = (Button) findViewById(R.id.capture_second_round);


                myRound1Text =(TextView) findViewById(R.id.rapper1_round1text);
                myRound1Text.setText(aktUser.getUserName());
                enemyRound1Text = (TextView) findViewById(R.id.rapper2_round1text);
                enemyRound1Text.setText(battle.getOpponent().getUsername());
                myRound1 = (VideoView) findViewById(R.id.firstround_rapperx);
                enemyRound1 = (VideoView) findViewById (R.id.firstrounde_rappery);

                MediaController mc = new MediaController(this);
                mc.setAnchorView(myRound1);
                mc.setMediaPlayer(myRound1);
                Uri videolink = Uri.parse(battle.getInfo().getRound1_url());
                myRound1.setMediaController(mc);
                myRound1.setVideoURI(videolink);
                myRound1.requestFocus();

                MediaController mc2 = new MediaController(this);
                mc2.setAnchorView(myRound1);
                mc2.setMediaPlayer(myRound1);
                Uri videolink2 = Uri.parse(battle.getInfo().getOpponent_round1_rl());
                enemyRound1.setMediaController(mc);
                enemyRound1.setVideoURI(videolink2);
                enemyRound1.requestFocus();

                vidButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), VideoCapture.class);
                        intent.putExtra("Beat", battle.getInfo().getBeat_id());
                        intent.putExtra("BattleID", battle.getId());
                        intent.putExtra("Phase", battle.getPhase());
                        try {
                            mPlayer.stop();
                            mPlayer.release();
                        } catch (Exception e) {

                        }

                        startActivity(intent);
                    }
                });




                return;



            default:
                return;
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mPlayer != null){

            mPlayer.release();
            mPlayer = null;


        }
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("User", aktUser);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        User tuser = (User) intent.getSerializableExtra("User");
        startActivity(intent);
        return;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (mPlayer != null){
            mPlayer.release();
            mPlayer = null;


        }


        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        myIntent.putExtra("User", aktUser);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityForResult(myIntent, 0);
        return true;
    }


}
