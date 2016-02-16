package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.VideoView;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Controller.UserController;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class OpenforVotesBattleActivity extends AppCompatActivity {
    private Battle battle;
    private TextView rapper1;
    private TextView rapper2;
    private ImageView video;
    private ProgressBar pBar;
    private ToggleButton lButton;
    private ToggleButton rButton;
    private ImageView imgRapper1;
    private ImageView imgRapper2;
    private User aktUser;
    private User searchUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openfor_votes_battle);

        aktUser = (User) getIntent().getSerializableExtra("User");
        searchUser = (User) getIntent().getSerializableExtra("Searchuser");

        // Set up Toolbar for Navigation
        final Toolbar toolbar = (Toolbar) findViewById(R.id.openforvotesToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Battle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lButton = (ToggleButton) findViewById(R.id.openforvotes_votebutton1);
        rButton = (ToggleButton) findViewById(R.id.openforvotes_votebutton2);

        battle = (Battle) getIntent().getSerializableExtra("battle");
        if (battle != null) {

            imgRapper1 = (ImageView) findViewById(R.id.openforvotes_battleRapper1img);
            imgRapper2 = (ImageView) findViewById(R.id.openforvotes_battleRapper2img);
            rapper1 = (TextView) findViewById(R.id.openforvotes_Rapper1);
            rapper2 =(TextView) findViewById(R.id.openforvotes_Rapper2);

            pBar= (ProgressBar) findViewById(R.id.progressBar1);
            rapper1.setText(battle.getRapper1().getUsername());
            rapper2.setText(battle.getRapper2().getUsername());
            video = (ImageView) findViewById(R.id.openforvotes_video);


            if (battle.getVoting() != null){
                pBar.setMax(battle.getVoting().getVotes_rapper1() + battle.getVoting().getVotes_rapper2());
                pBar.setProgress(battle.getVoting().getVotes_rapper1());
            }else {
                pBar.setMax(2);
                pBar.setProgress(1);
            }
            if (searchUser.getProfilePicture() != null) {
                Picasso.with(getApplicationContext()).load(battle.getRapper1().getProfile_picture()).into(imgRapper1);
            } else {
                this.imgRapper1.setImageResource(R.drawable.default_profile_pic);
            }
            /*if (battle.getRapper1().getProfile_picture() != null) {
                Picasso.with(this.getApplicationContext()).load(battle.getRapper1().getProfile_picture()).networkPolicy(NetworkPolicy.NO_CACHE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE).fit().into(imgRapper1);
            } else {
                this.imgRapper1.setImageResource(R.drawable.default_profile_pic);
            }*/
            if (battle.getRapper2().getProfile_picture() != null) {
                Picasso.with(this.getApplicationContext()).load(battle.getRapper2().getProfile_picture()).networkPolicy(NetworkPolicy.NO_CACHE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE).fit().into(imgRapper2);
            } else {
                this.imgRapper1.setImageResource(R.drawable.default_profile_pic);
            }
            video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), VideoPlayerActivity.class);
                    intent.putExtra("url", battle.getVideo_url());
                    startActivity(intent);
                }
            });



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
        if(searchUser != null){
            super.onBackPressed();
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("Searchuser", searchUser);
            intent.putExtra("User", aktUser);
        }else {
            super.onBackPressed();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("User", aktUser);
          //  intent.putExtra("Tab", 3);
            startActivity(intent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(searchUser != null){
            super.onBackPressed();
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("Searchuser", searchUser);
            intent.putExtra("User", aktUser);
        }else {
            super.onBackPressed();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("User", aktUser);
            //intent.putExtra("Tab", 3);
            startActivity(intent);
        }
        return true;
    }
}
