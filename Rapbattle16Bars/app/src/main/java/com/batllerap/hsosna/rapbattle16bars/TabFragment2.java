package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.BattlePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabFragment2 extends Fragment implements CustomAdapter.ClickListener,ChallengeAdapter.ClickListener {

    private RecyclerView oList;
    private RecyclerView cList;
    private WrappingRecyclerViewLayoutManager wrvLayoutManager;
    private WrappingRecyclerViewLayoutManager wrvLayoutManager2;
    private CustomAdapter oAdapter;
    private ChallengeAdapter cAdapter;

    private User aktUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.tab_fragment_2, container, false);

        oList = (RecyclerView) layout.findViewById(R.id.openBattlesList);
        cList = (RecyclerView) layout.findViewById(R.id.challengeList);
        oList.setHasFixedSize(true);
        cList.setHasFixedSize(true);

        wrvLayoutManager = new WrappingRecyclerViewLayoutManager(getActivity());
        wrvLayoutManager2 = new WrappingRecyclerViewLayoutManager(getActivity());

        oList.setLayoutManager(wrvLayoutManager);
        cList.setLayoutManager(wrvLayoutManager2);

        oAdapter = new CustomAdapter(getActivity(),getMyOpenBattlesList());
        cAdapter = new ChallengeAdapter(getActivity(),getMyOpenChallengeList());

        cAdapter.setClickListener(this);
        oAdapter.setClickListener(this);

        oList.setAdapter(oAdapter);
        cList.setAdapter(cAdapter);





        return layout;
    }

    public static List<ListElement> getMyOpenBattlesList(){

        List<ListElement> data = new ArrayList<>();

        try {
            BattlePreview[] trending = BattleController.getTrendingBattles(1, 5);
        }catch(org.json.JSONException exception){
            // how you handle the exception
            exception.printStackTrace();
        } catch (IOException e) {
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

    public static List<ChallengeElement> getMyOpenChallengeList(){

        List<ChallengeElement> data = new ArrayList<>();


        for (int i=0;i <5; i++ ){

            ChallengeElement current = new ChallengeElement();
            current.imgRapper =R.mipmap.ic_launcher;
            current.imgAccepted= R.mipmap.challenge_accepted;
            current.imgDeclined= R.mipmap.challenge_declined;
            current.rapper="john";


            data.add(current);
        }

        return data;
    }

    @Override
    public void itemClicked(View view, int position) {

    }

    @Override
    public void ChallengeClicked(View view, int position) {

    }

    @Override
    public void itemAccepted(View view, int position) {

    }

    @Override
    public void itemDeclined(View view, int position) {

    }
}