package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark on 10.02.2016.
 */
public class CompletedBattlesUser extends AppCompatActivity implements MyAdapter.ClickListener{
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private final List<BattleOverview> myDataset = new ArrayList<>();
    private Handler handler;
    private int page;
    private User aktUser;
    private User searchUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);

        // Set up Toolbar for Navigation
        aktUser = (User)getIntent().getSerializableExtra("User");
        searchUser = (User) getIntent().getSerializableExtra("Searchuser");
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

        mAdapter = new MyAdapter<String>(myDataset, mRecyclerView,this);
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
                        page++;
                        //remove progress item
                        myDataset.remove(myDataset.size() - 1);
                        mAdapter.notifyItemRemoved(myDataset.size());
                        //add items one by one
                        BattleOverview[] tmp = new BattleOverview[0];
                        try {
                            if(searchUser != null){
                                tmp = BattleController.getCompletedBattles(searchUser.getId(), page, 25).getData();
                            }else {
                                tmp = BattleController.getCompletedBattles(aktUser.getId(), page, 25).getData();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        myDataset.addAll(Arrays.asList(tmp));

                        mAdapter.notifyDataSetChanged();
                        // mAdapter.notifyItemInserted(myDataset.size());

                        mAdapter.setLoaded();
                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
                    }
                }, 2000);
                System.out.println("load");
            }
        });
    }

    public void getTrendingList()  {

        BattleOverview[] tmp = new BattleOverview[0];
        try {
            if(searchUser != null){
                tmp = BattleController.getCompletedBattles(searchUser.getId(), 0, 25).getData();
            }else {
                tmp = BattleController.getCompletedBattles(aktUser.getId(), 0, 25).getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.myDataset.addAll(Arrays.asList(tmp));

    }


    @Override
    public void itemClicked(View view, int position) {
        try {
            Battle battle = BattleController.getBattle(myDataset.get(position).getBattle_id());

            Intent intent = new Intent("com.batllerap.hsosna.rapbattle16bars.ClosedBattleActivity");
            intent.putExtra("battle", battle);

            intent.putExtra("User", aktUser);
            startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            intent.putExtra("Tab", 3);
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
            intent.putExtra("Tab", 3);
            startActivity(intent);
        }
        return true;
    }
}
