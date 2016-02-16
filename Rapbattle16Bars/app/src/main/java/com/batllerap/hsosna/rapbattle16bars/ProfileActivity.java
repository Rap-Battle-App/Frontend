package com.batllerap.hsosna.rapbattle16bars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;

import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.batllerap.hsosna.rapbattle16bars.Model.response.BattleListResponse;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ProfileActivity extends AppCompatActivity implements CustomAdapter.ClickListener {


    //aktueller User
    private User aktUser = null;

    //gesuchter User
    private User searchUser = null;

    //Widgets Deklarieren und Initialisieren

    //TextView
    private TextView txtvUsername = null;
    private TextView txtvLocation = null;
    private TextView txtvAboutMe = null;
    private TextView txtvWins = null;
    private TextView txtvLooses = null;
    private TextView txtvLoosesValue = null;
    private TextView txtvWinsValue = null;
    private TextView txtvClosedBattles = null;
    private TextView txtvOpenBattles = null;

    //View
    private View profileDivider = null;

    //ImageView
    private ImageView imgvProfilePicture = null;
    private ImageView imgvEditProfile = null;

    //Button
    private Button btnHerausfordern = null;

    //Battles
    private RecyclerView tList;
    private RecyclerView oList;
    private WrappingRecyclerViewLayoutManager wrvLayoutManager;
    private WrappingRecyclerViewLayoutManager wrv2LayoutManager;
    private CustomAdapter tAdapter;
    private CustomAdapter oAdapter;
    private List<BattleOverview> myBattlesList = new ArrayList<>();
    private List<BattleOverview> myOpenforVotesBattlesList = new ArrayList<>();

    private List<BattleOverview> trendingBattlesList = new ArrayList<>();
    private List<BattleOverview> openForVotesBattlesList = new ArrayList<>();
    private Animator mCurrentAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // Set up Toolbar for Navigation
        final Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PROFIL");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aktUser = (User) getIntent().getSerializableExtra("User");
        searchUser = (User) getIntent().getSerializableExtra("Searchuser");

        //TextView
        this.txtvUsername = (TextView) findViewById(R.id.txtvUsername);
        this.txtvLocation = (TextView) findViewById(R.id.txtvLocation);
        this.txtvAboutMe = (TextView) findViewById(R.id.txtvAboutMe);
        this.txtvWins = (TextView) findViewById(R.id.txtvWins);
        this.txtvLooses = (TextView) findViewById(R.id.txtvLooses);
        this.txtvWinsValue = (TextView) findViewById(R.id.txtvWinsValue);
        this.txtvLoosesValue = (TextView) findViewById(R.id.txtvLoosesValue);
        this.txtvClosedBattles = (TextView) findViewById(R.id.txtvClosedBattles);
        this.txtvOpenBattles = (TextView) findViewById(R.id.txtvOpenBattles);

        //View
        this.profileDivider = (View) findViewById(R.id.profileDivider);

        //Button

        this.btnHerausfordern = (Button) findViewById(R.id.btnHerausfordern);
        this.btnHerausfordern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BattleController.sendRequest(searchUser.getId());
                    btnHerausfordern.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Herausgefordert!", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        if (aktUser.getUserName().equals(searchUser.getUserName())) {
            btnHerausfordern.setVisibility(View.INVISIBLE);
        }
        if(!searchUser.isRapper()){
            btnHerausfordern.setVisibility(View.INVISIBLE);
        }

        //ImageView
        this.imgvProfilePicture = (ImageView) findViewById(R.id.imgvProfilePicture);
        this.imgvEditProfile = (ImageView) findViewById(R.id.imgvEditProfile);
        this.imgvEditProfile.setVisibility(View.GONE);

        this.txtvUsername.setText(searchUser.getUserName());
        this.txtvLocation.setText(searchUser.getLocation());
        this.txtvAboutMe.setText(searchUser.getAboutMe());

        if (searchUser.getProfilePicture() != null) {
            Picasso.with(getApplicationContext()).load(searchUser.getProfilePicture()).into(imgvProfilePicture);
        } else {
            this.imgvProfilePicture.setImageResource(R.drawable.default_profile_pic);
        }
        this.imgvProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZoomImage zi = new ZoomImage();
                zi.zoomImageFromThumb(imgvProfilePicture, ProfileActivity.this, ((BitmapDrawable) imgvProfilePicture.getDrawable()).getBitmap()); // imgvProfilePicture);
            }
        });

        this.txtvWinsValue.setText(String.valueOf(searchUser.getRapper().getWins()));
        this.txtvLoosesValue.setText(String.valueOf(searchUser.getRapper().getLooses()));

        //Battles des Rappers
        tList = (RecyclerView) findViewById(R.id.profileClosedBattlesList);
        oList = (RecyclerView) findViewById(R.id.profileOpenBattlesList);
        TextView tview = (TextView) findViewById(R.id.txtvClosedBattles);
        TextView oView = (TextView) findViewById(R.id.txtvOpenBattles);

        tList.setItemViewCacheSize(0);
        oList.setItemViewCacheSize(0);


        tview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), CompletedBattlesUser.class);
                myIntent.putExtra("User", aktUser);
                myIntent.putExtra("Searchuser", searchUser);
                startActivity(myIntent);

            }
        });

        oView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), OpenForVotesUser.class);
                myIntent.putExtra("User", aktUser);
                myIntent.putExtra("Searchuser", searchUser);
                startActivity(myIntent);
            }
        });


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        tList.setHasFixedSize(true);

        wrvLayoutManager = new WrappingRecyclerViewLayoutManager(this);
        wrv2LayoutManager = new WrappingRecyclerViewLayoutManager(this);

        tList.setLayoutManager(wrvLayoutManager);
        oList.setLayoutManager(wrv2LayoutManager);

        tAdapter = new CustomAdapter(this, trendingBattlesList);
        oAdapter = new CustomAdapter(this, openForVotesBattlesList);

        if (aktUser != null) {
            TabFragment3AsyncTasks asyncTrendigBattles = new TabFragment3AsyncTasks();
            asyncTrendigBattles.execute("complete", aktUser.getId(), trendingBattlesList, tAdapter);
            TabFragment3AsyncTasks asyncOpenForVotes = new TabFragment3AsyncTasks();
            asyncOpenForVotes.execute("open", aktUser.getId(), openForVotesBattlesList, oAdapter);
        }

        tAdapter.setClickListener(this);
        oAdapter.setClickListener(this);

        tList.setAdapter(tAdapter);
        oList.setAdapter(oAdapter);


    }

    public List<BattleOverview> getCompletedBattles() {

        List<BattleOverview> data = new ArrayList<>();
        BattleOverview[] temp = new BattleOverview[0];
        try {
            if (searchUser != null) {
                temp = BattleController.getCompletedBattles(searchUser.getId(), 0, 50).getData();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        data.addAll(Arrays.asList(temp));

        return data;
    }

    public List<BattleOverview> getOpenforVotingBattlesList() {

        List<BattleOverview> data = new ArrayList<>();
        BattleOverview[] temp = new BattleOverview[0];
        try {
            if (searchUser != null) {
                temp = BattleController.getOpenForVotingBattles(searchUser.getId(), 0, 50).getData();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        data.addAll(Arrays.asList(temp));

        return data;
    }

    @Override
    public void itemClicked(View view, int position) {
        if (view.getParent() == tList) {
            System.out.println("Trending List Angeklickt");
            try {
                Battle battle = BattleController.getBattle(trendingBattlesList.get(position).getBattle_id());
                Intent intent = new Intent("com.batllerap.hsosna.rapbattle16bars.ClosedBattleActivity");
                intent.putExtra("battle", battle);
                intent.putExtra("User", aktUser);
                intent.putExtra("Searchuser", searchUser);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (view.getParent() == oList) {

            try {
                Battle battle = BattleController.getBattle(openForVotesBattlesList.get(position).getBattle_id());

                Intent intent = new Intent("com.batllerap.hsosna.rapbattle16bars.OpenforVotesBattleActivity");
                intent.putExtra("battle", battle);
                intent.putExtra("User", aktUser);
                intent.putExtra("Searchuser", searchUser);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}