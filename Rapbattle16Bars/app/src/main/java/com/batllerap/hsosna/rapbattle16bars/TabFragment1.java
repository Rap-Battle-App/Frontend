package com.batllerap.hsosna.rapbattle16bars;


import android.content.Intent;
import android.os.Bundle;
        import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
        import android.view.ViewGroup;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.BattlePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.User;

import java.util.ArrayList;
import java.util.List;

public class TabFragment1 extends Fragment implements CustomAdapter.ClickListener {
    private RecyclerView tList;
    private RecyclerView oList;
    private WrappingRecyclerViewLayoutManager wrvLayoutManager;
    private WrappingRecyclerViewLayoutManager wrv2LayoutManager;
    private CustomAdapter tAdapter;
    private CustomAdapter oAdapter;
    private BattleController bController;
    private User aktUser;
    private  static BattlePreview[] trending ={};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.tab_fragment_1, container, false);
        tList = (RecyclerView) layout.findViewById(R.id.trendingList);
        oList = (RecyclerView) layout.findViewById(R.id.openList);
        TextView tview = (TextView) layout.findViewById(R.id.trending_text);
        TextView oView = (TextView) layout.findViewById(R.id.open_text);

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

        tAdapter = new CustomAdapter(getActivity(),getTrendingList());
        oAdapter = new CustomAdapter(getActivity(),getOpenBattlesList());

        tAdapter.setClickListener(this);
        oAdapter.setClickListener(this);

        tList.setAdapter(tAdapter);
        oList.setAdapter(oAdapter);
        return layout;
    }





    public static List<ListElement> getTrendingList(){

        List<ListElement> data = new ArrayList<>();

        try {
            BattlePreview[] trending = BattleController.getTrendingBattles(1, 5);
        }catch(org.json.JSONException exception){
            // how you handle the exception
            exception.printStackTrace();
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
            BattlePreview[] trending = BattleController.getTrendingBattles(1, 5);
        }catch(org.json.JSONException exception){
            // how you handle the exception
            exception.printStackTrace();
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


