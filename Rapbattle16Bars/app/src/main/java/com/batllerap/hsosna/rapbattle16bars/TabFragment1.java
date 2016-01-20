package com.batllerap.hsosna.rapbattle16bars;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TabFragment1 extends Fragment implements CustomAdapter.ClickListener {
    private RecyclerView tList;
    private RecyclerView oList;
    private WrappingRecyclerViewLayoutManager wrvLayoutManager;
    private WrappingRecyclerViewLayoutManager wrv2LayoutManager;
    private CustomAdapter tAdapter;
    private CustomAdapter oAdapter;
    private User aktUser;
    private List<BattleOverview> trendingBattlesList = new ArrayList<>();
    private List<BattleOverview> openForVotesBattlesList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.tab_fragment_1, container, false);
        tList = (RecyclerView) layout.findViewById(R.id.profileClosedBattlesList);
        oList = (RecyclerView) layout.findViewById(R.id.openList);
        TextView tview = (TextView) layout.findViewById(R.id.txtvClosedBattles);
        TextView oView = (TextView) layout.findViewById(R.id.txtvOpenBattles);
        aktUser = (User) getActivity().getIntent().getSerializableExtra("User");


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
                myIntent.putExtra("User", aktUser);
                startActivity(myIntent);
            }
        });

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        tList.setHasFixedSize(true);
        oList.setHasFixedSize(true);

        wrvLayoutManager = new WrappingRecyclerViewLayoutManager(getActivity());
        wrv2LayoutManager = new WrappingRecyclerViewLayoutManager(getActivity());

        tList.setLayoutManager(wrvLayoutManager);
        oList.setLayoutManager(wrv2LayoutManager);

        tAdapter = new CustomAdapter(getActivity(), trendingBattlesList);
        oAdapter = new CustomAdapter(getActivity(), openForVotesBattlesList);

        if(aktUser != null){
            TabFragment1AsyncTasks asyncTrendigBattles = new TabFragment1AsyncTasks(this.getContext());
            asyncTrendigBattles.execute("trending", trendingBattlesList, tAdapter);
            TabFragment1AsyncTasks asyncOpenForVotes = new TabFragment1AsyncTasks(this.getContext());
            asyncOpenForVotes.execute("open", openForVotesBattlesList, oAdapter);
        }

        tAdapter.setClickListener(this);
        oAdapter.setClickListener(this);

        tList.setAdapter(tAdapter);
        oList.setAdapter(oAdapter);
        return layout;
    }


    public List<BattleOverview> getTrendingList() {

        List<BattleOverview> data = new ArrayList<>();
        BattleOverview[] bla = new BattleOverview[0];
        try {
            if (aktUser != null) {
                bla = BattleController.getTrendingBattles(0, 50).getData();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        data.addAll(Arrays.asList(bla));

        return data;
    }

    public List<BattleOverview> getOpenForVotesBattlesList() {

        List<BattleOverview> data =  new ArrayList<>();
        BattleOverview[] bla = new BattleOverview[0];
        try {
            if (aktUser != null) {
                bla = BattleController.getOpenForVotingBattles( 0, 50).getData();
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
                Battle battle = BattleController.getBattle(trendingBattlesList.get(position).getBattle_id());

                Intent intent = new Intent("com.batllerap.hsosna.rapbattle16bars.ClosedBattleActivity");
                intent.putExtra("battle", battle);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (view.getParent() == oList) {

            try {
                Battle battle = BattleController.getBattle(openForVotesBattlesList.get(position).getBattle_id());

                Intent intent = new Intent("com.batllerap.hsosna.rapbattle16bars.OpenforVotesBattleActivity");
                intent.putExtra("battle", battle);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}


