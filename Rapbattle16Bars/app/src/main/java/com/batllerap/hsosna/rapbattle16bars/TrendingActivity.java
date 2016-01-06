package com.batllerap.hsosna.rapbattle16bars;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrendingActivity extends AppCompatActivity  implements MyAdapter.ClickListener{
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private final List<BattleOverview> myDataset = new ArrayList<>();
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);

        // Set up Toolbar for Navigation
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("TRENDING");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handler = new Handler();
        getTrendingList();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter<String>(myDataset, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setClickListener(this);

        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnLoadMoreListener(new MyAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //add progress item
                myDataset.add(null);
                mAdapter.notifyItemInserted(myDataset.size() - 1);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //remove progress item
                        myDataset.remove(myDataset.size() - 1);
                        mAdapter.notifyItemRemoved(myDataset.size());
                        //add items one by one
                        for (int i = 0; i < 15; i++) {
                            ListElement current = new ListElement();
                            current.imgRapper1 =R.mipmap.ic_launcher;
                            current.imgRapper2= R.mipmap.ic_launcher;
                            current.name1="john";
                            current.name2 = "peter";

                            mAdapter.notifyItemInserted(myDataset.size());
                        }
                        mAdapter.setLoaded();
                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
                    }
                }, 2000);
                System.out.println("load");
            }
        });
    }

    public void getTrendingList()  {

        BattleOverview[] bla= new BattleOverview[0];
        try {
            bla = BattleController.getTrendingBattles(0, 50).getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.myDataset.addAll(Arrays.asList(bla));

    }


    @Override
    public void itemClicked(View view, int position) {
        View v = view;


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
    }
}