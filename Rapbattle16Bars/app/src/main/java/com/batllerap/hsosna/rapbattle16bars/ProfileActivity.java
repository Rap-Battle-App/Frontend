package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;

import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.batllerap.hsosna.rapbattle16bars.Model.response.BattleListResponse;

import java.io.IOException;
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

    //Button
    private Button editProfile = null;
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
        this.editProfile = (Button) findViewById(R.id.btnEditProfile);
        this.editProfile.setVisibility(View.INVISIBLE);
        this.btnHerausfordern = (Button) findViewById(R.id.btnHerausfordern);
        this.btnHerausfordern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BattleController.sendRequest(searchUser.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            btnHerausfordern.setVisibility(View.INVISIBLE);
            }
        });

        //ImageView
        this.imgvProfilePicture = (ImageView) findViewById(R.id.imgvProfilePicture);

        this.txtvUsername.setText(searchUser.getUserName());
        this.txtvLocation.setText(searchUser.getLocation());
        this.txtvAboutMe.setText(searchUser.getAboutMe());
        if(searchUser.getProfilePicture() != null) {
            this.imgvProfilePicture.setImageURI(Uri.parse(searchUser.getProfilePicture()));
        }else {
            this.imgvProfilePicture.setImageResource(R.drawable.default_profile_pic);
        }
        if(searchUser.isRapper()){
            this.txtvWinsValue.setText(String.valueOf(searchUser.getRapper().getWins()));
            this.txtvLoosesValue.setText(String.valueOf(searchUser.getRapper().getLooses()));

            //Battles des Rappers
            tList = (RecyclerView) findViewById(R.id.profileClosedBattlesList);
            oList = (RecyclerView) findViewById(R.id.profileOpenBattlesList);
            TextView tview = (TextView) findViewById(R.id.txtvClosedBattles);
            TextView oView = (TextView) findViewById(R.id.txtvOpenBattles);


            tview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent("com.batllerap.hsosna.rapbattle16bars.TrendingActivity");
                    startActivity(myIntent);

                }
            });

            oView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent("com.batllerap.hsosna.rapbattle16bars.OpenforvotesActivity");
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

            myBattlesList = getCompletedBattles();

            myOpenforVotesBattlesList = getOpenforVotingBattlesList();

            tAdapter = new CustomAdapter(this,myBattlesList);
            oAdapter = new CustomAdapter(this,myOpenforVotesBattlesList);

            tAdapter.setClickListener(this);
            oAdapter.setClickListener(this);

            tList.setAdapter(tAdapter);
            oList.setAdapter(oAdapter);
        }else{
            this.txtvWins.setVisibility(View.INVISIBLE);
            this.txtvLooses.setVisibility(View.INVISIBLE);
            this.txtvWinsValue.setVisibility(View.INVISIBLE);
            this.txtvLoosesValue.setVisibility(View.INVISIBLE);
            this.btnHerausfordern.setVisibility(View.INVISIBLE);
            this.txtvClosedBattles.setVisibility(View.INVISIBLE);
            this.txtvOpenBattles.setVisibility(View.INVISIBLE);
            this.profileDivider.setVisibility(View.INVISIBLE);
        }

    }

    public  List<BattleOverview> getCompletedBattles(){

        List<BattleOverview> data = Collections.emptyList();
        BattleOverview[] bla= new BattleOverview[0];
        try {
            if(aktUser != null) {
                bla = BattleController.getCompletedBattles(searchUser.getId(), 0, 50).getData();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        data.addAll(Arrays.asList(bla));

        return data;
    }

    public  List<BattleOverview> getOpenforVotingBattlesList(){

        List<BattleOverview> data = Collections.emptyList();
        BattleOverview[] bla= new BattleOverview[0];
        try {
            if(aktUser != null) {
                bla = BattleController.getOpenForVotingBattles(searchUser.getId(),0, 50).getData();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        data.addAll(Arrays.asList(bla));

        return data;
    }
    @Override
    public void itemClicked(View view, int position) {

        if (view.getParent() == tList) {
            System.out.println("Trending List Angeklickt");
            try {
                Battle battle = BattleController.getBattle(myBattlesList.get(position).getBattle_id());

                Intent intent = new Intent("com.batllerap.hsosna.rapbattle16bars.ClosedBattleActivity");
                intent.putExtra("battle", battle);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (view.getParent() == oList) {

            try {
                Battle battle = BattleController.getBattle(myOpenforVotesBattlesList.get(position).getBattle_id());

                Intent intent = new Intent("com.batllerap.hsosna.rapbattle16bars.OpenForVotesBattleActivity");
                intent.putExtra("battle", battle);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
