package com.batllerap.hsosna.rapbattle16bars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.batllerap.hsosna.rapbattle16bars.Controller.UserController;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {
    private Switch switch1;
    private Switch switch2;
    private User aktUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        aktUser =(User) getIntent().getSerializableExtra("User");

        // Set up Toolbar for Navigation
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("EINSTELLUNGEN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        switch1 = (Switch) findViewById(R.id.battles_switch);
        switch2 = (Switch) findViewById(R.id.notifications_switch);

        if(aktUser != null){
            switch1.setChecked(aktUser.getNotifications());
            switch2.setChecked(aktUser.isRapper());
        }


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try{
                        UserController.setIsRapper(aktUser, false);

                    }catch (java.io.IOException exception){


                    }
                } else {
                    try {
                        UserController.setIsRapper(aktUser,true);

                    }catch (java.io.IOException exception){


                    }
                }
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && aktUser != null){
                    try{
                        UserController.setNotifications(aktUser, false);

                    }catch (java.io.IOException exception){


                    }
                } else if (aktUser != null){
                    try {
                        UserController.setNotifications(aktUser, true );

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
