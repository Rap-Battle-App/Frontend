package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.OpenBattle;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

public class OpenBattleActivity extends AppCompatActivity {

    private OpenBattle battle;
    private RadioButton beat1, beat2, beat3, beat4;
    MediaPlayer mPlayer;
    Button vidButton;
    int auswahl;
    private RadioGroup beatgroup;
    private User aktUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_battle);

        // Set up Toolbar for Navigation
        final Toolbar toolbar = (Toolbar) findViewById(R.id.openBattleToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Battle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        battle = (OpenBattle) getIntent().getSerializableExtra("Battle");
        aktUser = (User) getIntent().getSerializableExtra("User");

        // Set up Toolbar for Navigation
    /*    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("EINSTELLUNGEN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
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
                intent.putExtra("BattleID",battle.getId() );
                try {
                    mPlayer.stop();
                    mPlayer.release();
                } catch (Exception e) {

                }

                startActivity(intent);
            }
        });


/*        battle =(OpenBattle) getIntent().getSerializableExtra("Battle");

        switch (battle.getPhase()){
            case 0:

                setContentView(R.layout.activity_open_battle);

                return;

            case 1:
                setContentView(R.layout.activity_open_battle_phase1);
                return;




        }

       */
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
        startActivityForResult(myIntent, 0);
        return true;
    }


}
