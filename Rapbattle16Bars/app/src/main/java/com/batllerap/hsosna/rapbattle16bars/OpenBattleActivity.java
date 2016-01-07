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

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.OpenBattle;

public class OpenBattleActivity extends AppCompatActivity {

    private OpenBattle battle;
    private RadioButton beat1, beat2, beat3, beat4;
    MediaPlayer mPlayer;
    Button vidButton;
    int auswahl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_battle);

         battle = (OpenBattle) getIntent().getSerializableExtra("Battle");

        // Set up Toolbar for Navigation
    /*    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("EINSTELLUNGEN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

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
                intent.putExtra("Beat", auswahl);
                intent.putExtra("BeatID",battle.getId() );
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
        if (mPlayer != null) {
            mPlayer.release();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.v("qqq", "DIGGAAA");
                onBackPressed();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayer != null) {
            mPlayer.pause();
            mPlayer.release();
        }
    }

}
