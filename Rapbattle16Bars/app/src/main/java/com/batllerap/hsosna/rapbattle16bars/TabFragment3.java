package com.batllerap.hsosna.rapbattle16bars;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.batllerap.hsosna.rapbattle16bars.Model.Profile.User;


public class TabFragment3 extends Fragment {
    User aktUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile, container, false);

    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            aktUser =(User) bundle.getSerializable("User");
        }
    }
}