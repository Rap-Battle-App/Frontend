package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Model.Profile.Rapper;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.User;

public class TabFragment3 extends Fragment {

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

    //ImageView
    private ImageView imgvProfilePicture = null;

    //Button
    private Button btnEditProfile = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.activity_profile, container, false);


        aktUser = (User) getActivity().getIntent().getSerializableExtra("User");

        //TextView
        this.txtvUsername = (TextView) layout.findViewById(R.id.txtvUsername);
        this.txtvLocation = (TextView) layout.findViewById(R.id.txtvLocation);
        this.txtvAboutMe = (TextView) layout.findViewById(R.id.txtvAboutMe);
        this.txtvWins = (TextView) layout.findViewById(R.id.txtvWins);
        this.txtvLooses = (TextView) layout.findViewById(R.id.txtvLooses);
        this.txtvWinsValue = (TextView) layout.findViewById(R.id.txtvWinsValue);
        this.txtvLoosesValue = (TextView) layout.findViewById(R.id.txtvLoosesValue);

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

        this.txtvUsername.setText(aktUser.getUserName());
        this.txtvLocation.setText(aktUser.getLocation());
        this.txtvAboutMe.setText(aktUser.getAboutMe());
        if(aktUser.getIsRapper()){
            this.txtvWinsValue.setText(Integer.toString(aktUser.getRapper().getWins()));
            this.txtvLoosesValue.setText(Integer.toString(aktUser.getRapper().getLooses()));
        }

        return layout;
    }
}