package com.batllerap.hsosna.rapbattle16bars;

import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.OpenBattle;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Request;
import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabFragment2 extends Fragment implements CustomAdapter.ClickListener,ChallengeAdapter.ClickListener {

    private RecyclerView oList;
    private RecyclerView cList;
    private Button cButton;
    private WrappingRecyclerViewLayoutManager wrvLayoutManager;
    private WrappingRecyclerViewLayoutManager wrvLayoutManager2;
    private CustomAdapter oAdapter;
    private ChallengeAdapter cAdapter;
    private List<Request> challengeList;
    private List<BattleOverview> myOpenBattlesList;
    private DialogFragment challengeAlert;

    private User aktUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.tab_fragment_2, container, false);

        challengeAlert = new ChallengeAlertDialogFragment();

        oList = (RecyclerView) layout.findViewById(R.id.openBattlesList);
        cList = (RecyclerView) layout.findViewById(R.id.challengeList);
        cButton = (Button) layout.findViewById(R.id.challenge_random_opponent);

        oList.setHasFixedSize(true);
        cList.setHasFixedSize(true);
        aktUser = (User) getActivity().getIntent().getSerializableExtra("User");

        wrvLayoutManager = new WrappingRecyclerViewLayoutManager(getActivity());
        wrvLayoutManager2 = new WrappingRecyclerViewLayoutManager(getActivity());

        oList.setLayoutManager(wrvLayoutManager);
        cList.setLayoutManager(wrvLayoutManager2);
        myOpenBattlesList = getMyOpenBattlesList();

        oAdapter = new CustomAdapter(getActivity(),myOpenBattlesList);

        challengeList =getMyOpenChallengeList();
        cAdapter = new ChallengeAdapter(getActivity(),challengeList);

        cAdapter.setClickListener(this);
        oAdapter.setClickListener(this);

        oList.setAdapter(oAdapter);
        cList.setAdapter(cAdapter);

        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                challengeAlert.show(getFragmentManager(), "123");

            }
        });





        return layout;
    }

    public  List<BattleOverview> getMyOpenBattlesList(){

        List<BattleOverview> data = new ArrayList<>();
        BattleOverview[] bla= new BattleOverview[0];
        try {
            if(aktUser != null) {
                bla = BattleController.getOpenBattles(0).getData();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        data.addAll(Arrays.asList(bla));

        return data;
    }

    public  List<Request> getMyOpenChallengeList(){


        List<Request> data = new ArrayList<>();
        Request[] bla= new Request[0];
        try {
            if(aktUser != null) {
                bla = BattleController.getRequestList(aktUser.getUserName()).getOpponent_requests();
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
        try {
            OpenBattle oBattle= BattleController.getOpenBattle(myOpenBattlesList.get(position).getBattle_id());
            Intent intent = new Intent(getActivity(), OpenBattleActivity.class);
            intent.putExtra("Battle", oBattle);
            startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void ChallengeClicked(View view, int position) {

        Intent intent = new Intent(getActivity(), ProfileActivity.class);

    }

    @Override
    public void itemAccepted(View view, int position) {


        try {
            BattleController.answerRequest(challengeList.get(position).getId(),true);
            Context context = getActivity().getApplicationContext();
            CharSequence text = "Challenge angenommen! ;)";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void itemDeclined(View view, int position) {

        try {
            BattleController.answerRequest(challengeList.get(position).getId(),false);
            Context context = getActivity().getApplicationContext();
            CharSequence text = "Challenge abgelehnt! ;)";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}