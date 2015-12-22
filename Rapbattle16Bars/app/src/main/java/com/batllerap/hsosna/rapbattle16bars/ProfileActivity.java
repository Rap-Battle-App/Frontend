
package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    public final static String OLD_USERNAME = "com.batllerap.hsosna.rapbattle16bars.USERNAME";
    public final static String OLD_LOCATION = "com.batllerap.hsosna.rapbattle16bars.LOCATION";
    public final static String OLD_ABOUT_ME = "com.batllerap.hsosna.rapbattle16bars.ABOUTME";

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

        Intent intent = getIntent();

        // Musste ich eben auskommentieren, hat mir die ganze Zeit Fehler geworden - Robert
        // User rapper = UserController.getUser("testRapper");

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

        //TODO sichtbarkeit, wenn leer
        /*if ((intent.getExtras() != null)) {
            if(!intent.getStringExtra(EditProfileActivity.NEW_USERNAME).equals("")) {
                this.txtvUsername.setText(intent.getStringExtra(EditProfileActivity.NEW_USERNAME));
            }
            if(!intent.getStringExtra(EditProfileActivity.NEW_LOCATION).equals("")) {
                this.txtvLocation.setText(intent.getStringExtra(EditProfileActivity.NEW_LOCATION));
                this.txtvLocation.setVisibility(1);
            }else{
                if(txtvLocation.equals("")) {
                    this.txtvLocation.setVisibility(1);
                }
            }
            if(!intent.getStringExtra(EditProfileActivity.NEW_ABOUT_ME).equals("")) {
                this.txtvAboutMe.setText(intent.getStringExtra(EditProfileActivity.NEW_ABOUT_ME));
            }
        }*/
    }

    public void editProfil(View v) {

        Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);

        //Zu übergebene Strings
        String userName = txtvUsername.getText().toString();
        String location = txtvLocation.getText().toString();
        String aboutMe = txtvAboutMe.getText().toString();

        intent.putExtra(OLD_USERNAME, userName);
        intent.putExtra(OLD_LOCATION, location);
        intent.putExtra(OLD_ABOUT_ME, aboutMe);

        startActivity(intent);

    }
}

