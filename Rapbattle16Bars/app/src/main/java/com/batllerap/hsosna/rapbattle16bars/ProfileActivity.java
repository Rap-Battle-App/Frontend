package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    //Widgets Deklarieren und Initialisieren

    //TextView
    private TextView txtvUsername = null;
    private TextView txtvLocation = null;
    private TextView txtvAboutMe = null;
    private TextView txtvWins = null;
    private TextView txtvLooses = null;

    //ImageView
    private ImageView imgvProfilePicture = null;

    //Button
    private Button btnEditProfile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //TextView
        this.txtvUsername = (TextView) findViewById(R.id.txtvUsername);
        this.txtvLocation = (TextView) findViewById(R.id.txtvLocation);
        this.txtvAboutMe = (TextView) findViewById(R.id.txtvAboutMe);
        this.txtvWins = (TextView) findViewById(R.id.txtvWins);
        this.txtvLooses = (TextView) findViewById(R.id.txtvLooses);

        //ImageView
        this.imgvProfilePicture = (ImageView) findViewById(R.id.imgvProfilePicture);

        //Button
        this.btnEditProfile = (Button) findViewById(R.id.btnEditProfile);

        //Listener
       /* btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    public void editProfil(View v) {

        startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));

    }
}
