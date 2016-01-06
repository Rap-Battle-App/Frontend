package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.batllerap.hsosna.rapbattle16bars.Model.response.BattleListResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class TabFragment3 extends Fragment implements CustomAdapter.ClickListener {

    //aktueller User
    private User aktUser = null;

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
    private Button btnEditProfile = null;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.activity_profile, container, false);


        final Toolbar toolbar = (Toolbar) layout.findViewById(R.id.profileToolbar);
        toolbar.setVisibility(View.GONE);

        aktUser = (User) getActivity().getIntent().getSerializableExtra("User");

        //TextView
        this.txtvUsername = (TextView) layout.findViewById(R.id.txtvUsername);
        this.txtvLocation = (TextView) layout.findViewById(R.id.txtvLocation);
        this.txtvAboutMe = (TextView) layout.findViewById(R.id.txtvAboutMe);
        this.txtvWins = (TextView) layout.findViewById(R.id.txtvWins);
        this.txtvLooses = (TextView) layout.findViewById(R.id.txtvLooses);
        this.txtvWinsValue = (TextView) layout.findViewById(R.id.txtvWinsValue);
        this.txtvLoosesValue = (TextView) layout.findViewById(R.id.txtvLoosesValue);
        this.txtvClosedBattles = (TextView) layout.findViewById(R.id.txtvClosedBattles);
        this.txtvOpenBattles = (TextView) layout.findViewById(R.id.txtvOpenBattles);

        //View
        this.profileDivider = (View) layout.findViewById(R.id.profileDivider);

        //ImageView
        this.imgvProfilePicture = (ImageView) layout.findViewById(R.id.imgvProfilePicture);

        //Button
        this.btnEditProfile = (Button) layout.findViewById(R.id.btnEditProfile);

        this.btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), EditProfileActivity.class);
                myIntent.putExtra("User", aktUser);
                getActivity().startActivity(myIntent);
            }
        });

        this.btnHerausfordern = (Button) layout.findViewById(R.id.btnHerausfordern);
        this.btnHerausfordern.setVisibility(View.INVISIBLE);

        this.txtvUsername.setText(aktUser.getUserName());
        this.txtvLocation.setText(aktUser.getLocation());
        this.txtvAboutMe.setText(aktUser.getAboutMe());
        if(aktUser.getProfilePicture() != null) {
            this.imgvProfilePicture.setImageURI(Uri.parse(aktUser.getProfilePicture()));
        }else {
            this.imgvProfilePicture.setImageResource(R.drawable.default_profile_pic);
        }

        if(aktUser.isRapper()){
            this.txtvWinsValue.setText(Integer.toString(aktUser.getRapper().getWins()));
            this.txtvLoosesValue.setText(Integer.toString(aktUser.getRapper().getLooses()));

            //Battles des Rappers
            tList = (RecyclerView) layout.findViewById(R.id.profileClosedBattlesList);
            oList = (RecyclerView) layout.findViewById(R.id.profileOpenBattlesList);
            TextView tview = (TextView) layout.findViewById(R.id.txtvClosedBattles);
            TextView oView = (TextView) layout.findViewById(R.id.txtvOpenBattles);


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

            wrvLayoutManager = new WrappingRecyclerViewLayoutManager(getActivity());
            wrv2LayoutManager = new WrappingRecyclerViewLayoutManager(getActivity());

            tList.setLayoutManager(wrvLayoutManager);
            oList.setLayoutManager(wrv2LayoutManager);

            tAdapter = new CustomAdapter(getActivity(),getCompletedBattles());
            oAdapter = new CustomAdapter(getActivity(),getOpenforVotingBattlesList());

            tAdapter.setClickListener(this);
            oAdapter.setClickListener(this);

            tList.setAdapter(tAdapter);
            oList.setAdapter(oAdapter);
        }else{
            this.txtvWins.setVisibility(View.INVISIBLE);
            this.txtvLooses.setVisibility(View.INVISIBLE);
            this.txtvWinsValue.setVisibility(View.INVISIBLE);
            this.txtvLoosesValue.setVisibility(View.INVISIBLE);
            this.txtvClosedBattles.setVisibility(View.INVISIBLE);
            this.txtvOpenBattles.setVisibility(View.INVISIBLE);
            this.profileDivider.setVisibility(View.INVISIBLE);
        }

        return layout;
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
    public  List<BattleOverview> getCompletedBattles(){

        List<BattleOverview> data = Collections.emptyList();
        BattleOverview[] bla= new BattleOverview[0];
        try {
            if(aktUser != null) {
                bla = BattleController.getCompletedBattles(aktUser.getId(), 0, 50).getData();
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
                bla = BattleController.getOpenForVotingBattles(aktUser.getId(),0, 50).getData();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        data.addAll(Arrays.asList(bla));

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