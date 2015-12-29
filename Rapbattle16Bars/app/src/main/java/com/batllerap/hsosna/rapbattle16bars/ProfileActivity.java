package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.batllerap.hsosna.rapbattle16bars.Model.response.BattleListResponse;

import java.io.IOException;
import java.util.ArrayList;
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
    private BattleController bController;
    private  static BattleListResponse trending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        //Button
        this.editProfile = (Button) findViewById(R.id.btnEditProfile);
        this.editProfile.setVisibility(View.INVISIBLE);
        this.btnHerausfordern = (Button) findViewById(R.id.btnHerausfordern);
        this.btnHerausfordern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Dennis fragen wie Herausforderung funktioniert
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
            this.txtvWinsValue.setText(Integer.toString(searchUser.getRapper().getWins()));
            this.txtvLoosesValue.setText(Integer.toString(searchUser.getRapper().getLooses()));

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

            tAdapter = new CustomAdapter(this,getTrendingList());
            oAdapter = new CustomAdapter(this,getOpenBattlesList());

            tAdapter.setClickListener(this);
            oAdapter.setClickListener(this);

            tList.setAdapter(tAdapter);
            oList.setAdapter(oAdapter);
        }else{
            this.txtvWins.setVisibility(View.INVISIBLE);
            this.txtvLooses.setVisibility(View.INVISIBLE);
            this.txtvWinsValue.setVisibility(View.INVISIBLE);
            this.txtvLoosesValue.setVisibility(View.INVISIBLE);
        }
    }

    public static List<ListElement> getTrendingList(){

        List<ListElement> data = new ArrayList<>();
        try {
            BattleListResponse trending = BattleController.getTrendingBattles(1, 5);
        }catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0;i <5; i++ ){

            ListElement current = new ListElement();
            current.imgRapper1 =R.mipmap.ic_launcher;
            current.imgRapper2= R.mipmap.ic_launcher;
            current.name1="john";
            current.name2 = "peter";

            data.add(current);
        }

        return data;
    }

    public static List<ListElement> getOpenBattlesList(){

        List<ListElement> data = new ArrayList<>();

        try {
            BattleListResponse trending = BattleController.getTrendingBattles(1, 5);
        }catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=0;i <5; i++ ){

            ListElement current = new ListElement();
            current.imgRapper1 =R.mipmap.ic_launcher;
            current.imgRapper2= R.mipmap.ic_launcher;
            current.name1="john";
            current.name2 = "peter";

            data.add(current);
        }

        return data;
    }

    @Override
    public void itemClicked(View view, int position) {
        View v =view;
        System.out.println(v.getParent());
        if(v.getParent()== tList){
            System.out.println("Trending List Angeklickt");
            Intent intent = new Intent("com.batllerap.hsosna.rapbattle16bars.ClosedBattleActivity");
            startActivity(intent);
            //
            //Works after Controllers are finished
                /*
                try{
                    Intent intent = new Intent("com.albert.testbattle.ClosedBattleActivity");
                    Battle battle = bController.getBattle(trending[position].getBattleId());
                    intent.putExtra("battle",battle);
                    startActivity(intent);
                }catch(org.json.JSONException exception) {
                    exception.printStackTrace();
                }*/

        }else if(v.getParent()== oList){
            System.out.println("Open for Votes List Angeklickt");
            Intent intent = new Intent("com.batllerap.hsosna.rapbattle16bars.OpenforVotesBattleActivity");
            startActivity(intent);

            //
            //Works after Controllers are finished

               /* try{
                    Intent intent = new Intent("com.albert.testbattle.OpenforVotesBattleActivity");
                    Battle battle = bController.getBattle(trending[position].getBattleId());
                    intent.putExtra("battle",battle);
                    startActivity(intent);
                }catch(org.json.JSONException exception) {
                    exception.printStackTrace();
                }
                */

        }

    }

}