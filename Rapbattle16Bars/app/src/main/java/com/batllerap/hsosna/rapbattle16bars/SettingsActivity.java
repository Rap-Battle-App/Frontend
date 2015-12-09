package com.batllerap.hsosna.rapbattle16bars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.batllerap.hsosna.rapbattle16bars.Controller.UserController;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.User;

import org.json.JSONException;

import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {
    private Switch switch1;
    private Switch switch2;
    private User aktUser;
    private UserController uController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        aktUser =(User) getIntent().getSerializableExtra("User");

        switch1 = (Switch) findViewById(R.id.battles_switch);
        switch2 = (Switch) findViewById(R.id.notifications_switch);

        if(aktUser != null){
            switch1.setChecked(aktUser.getNotifications());
            switch2.setChecked(aktUser.getIsRapper());
        }


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try{
                        uController.setIsRapper(aktUser, aktUser.getIsRapper());
                    }catch (java.io.IOException exception){


                    } catch (JSONException e) {

                    }
                } else {
                    try {
                        uController.setIsRapper(aktUser,aktUser.getIsRapper());
                    }catch (java.io.IOException exception){


                    } catch (JSONException e) {

                    }
                }
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && aktUser != null){
                    try{
                        uController.setNotifications(aktUser, aktUser.getNotifications());
                    }catch (java.io.IOException exception){


                    } catch (JSONException e) {

                    }
                } else if (aktUser != null){
                    try {
                        uController.setNotifications(aktUser,aktUser.getNotifications());
                    }catch (JSONException exception){

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
