package com.batllerap.hsosna.rapbattle16bars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.batllerap.hsosna.rapbattle16bars.Model.response.BattleListResponse;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private BattleController bController;
    private BattleListResponse trending;

    private List<BattleOverview> trendingBattlesList = new ArrayList<>();
    private List<BattleOverview> openForVotesBattlesList = new ArrayList<>();

    private Animator mCurrentAnimator;

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

        //Button
        this.btnHerausfordern = (Button) layout.findViewById(R.id.btnHerausfordern);
        this.btnHerausfordern.setVisibility(View.GONE);

        //ImageView
        this.imgvProfilePicture = (ImageView) layout.findViewById(R.id.imgvProfilePicture);
        this.imgvEditProfile = (ImageView) layout.findViewById(R.id.imgvEditProfile);

        this.imgvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), EditProfileActivity.class);
                myIntent.putExtra("User", aktUser);
                getActivity().startActivity(myIntent);
            }
        });

        this.txtvUsername.setText(aktUser.getUserName());
        this.txtvLocation.setText(aktUser.getLocation());
        this.txtvAboutMe.setText(aktUser.getAboutMe());
        if(aktUser.getProfilePicture() != null) {
            Picasso.with(getActivity().getApplicationContext()).load(aktUser.getProfilePicture()).networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE).fit().into(imgvProfilePicture);
        }else {
            this.imgvProfilePicture.setImageResource(R.drawable.default_profile_pic);
        }

        this.imgvProfilePicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ZoomImage zi = new ZoomImage();
                zi.zoomImageFromThumb(imgvProfilePicture, getActivity(), ((BitmapDrawable)imgvProfilePicture.getDrawable()).getBitmap());//imgvProfilePicture);
            }
        });

        if(aktUser.isRapper()){
            this.txtvWinsValue.setText(Integer.toString(aktUser.getRapper().getWins()));
            this.txtvLoosesValue.setText(Integer.toString(aktUser.getRapper().getLooses()));

            //Battles des Rappers
            tList = (RecyclerView) layout.findViewById(R.id.profileClosedBattlesList);
            oList = (RecyclerView) layout.findViewById(R.id.profileOpenBattlesList);
            tList.setItemViewCacheSize(0);
            oList.setItemViewCacheSize(0);
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

            tAdapter = new CustomAdapter(getActivity(),trendingBattlesList);
            oAdapter = new CustomAdapter(getActivity(),openForVotesBattlesList);

            MyBus.getInstance().register(this);
            if(aktUser != null){
                TabFragment3AsyncTasks asyncTrendigBattles = new TabFragment3AsyncTasks();
                asyncTrendigBattles.execute("complete", aktUser.getId(), trendingBattlesList);
                TabFragment3AsyncTasks asyncOpenForVotes = new TabFragment3AsyncTasks();
                asyncOpenForVotes.execute("open", aktUser.getId(), openForVotesBattlesList);
            }

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
    @Override
    public void onDestroy(){
        super.onDestroy();
        MyBus.getInstance().unregister(this);
    }

    @Subscribe
    public  void onAsyncTaskResult(AsyncTaskResult event){
        if(event.getResult() == 1) {
            tAdapter.notifyDataSetChanged();
        }else{
            oAdapter.notifyDataSetChanged();
        }
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

