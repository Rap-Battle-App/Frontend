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

import java.util.ArrayList;
import java.util.List;

public class TabFragment2 extends Fragment implements CustomAdapter.ClickListener {

    private RecyclerView oList;
    private WrappingRecyclerViewLayoutManager wrvLayoutManager;
    private CustomAdapter oAdapter;
    private User aktUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.tab_fragment_2, container, false);

        oList = (RecyclerView) layout.findViewById(R.id.openBattlesList);
        oList.setHasFixedSize(true);

        wrvLayoutManager = new WrappingRecyclerViewLayoutManager(getActivity());

        oList.setLayoutManager(wrvLayoutManager);
        oAdapter = new CustomAdapter(getActivity(),getMyOpenBattlesList());

        oAdapter.setClickListener(this);

        oList.setAdapter(oAdapter);





        return layout;
    }

    public static List<ListElement> getMyOpenBattlesList(){

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

    }
}