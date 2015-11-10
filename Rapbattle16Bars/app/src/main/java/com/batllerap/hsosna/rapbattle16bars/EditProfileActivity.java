package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {

    public final static String NEW_USERNAME = "com.batllerap.hsosna.rapbattle16bars.USERNAME";
    public final static String NEW_LOCATION = "com.batllerap.hsosna.rapbattle16bars.LOCATION";
    public final static String NEW_ABOUT_ME = "com.batllerap.hsosna.rapbattle16bars.ABOUTME";

    //Widgets Deklarieren und Initialisieren

    //TextView
    private TextView txtvEditUsername = null;
    private TextView txtvEditLocation = null;
    private TextView txtvEditAboutMe = null;

    //EditText
    private EditText txteNewUsername = null;
    private EditText txteNewLocation = null;
    private EditText txteNewAboutMe = null;

    //ImageView
    private ImageView imgvEditProfilePicture = null;

    //Button
    private Button btnSaveChanges = null;
    private Button btnChangeProfilePicture = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();

        //TextView
        this.txtvEditUsername = (TextView) findViewById(R.id.txtvEditUsername);
        this.txtvEditLocation = (TextView) findViewById(R.id.txtvEditLocation);
        this.txtvEditAboutMe = (TextView) findViewById(R.id.txtvEditAboutMe);

        //EditText
        this.txteNewUsername = (EditText) findViewById(R.id.txteNewUsername);
        this.txteNewLocation = (EditText) findViewById(R.id.txteNewLocation);
        this.txteNewAboutMe = (EditText) findViewById(R.id.txteNewAboutMe);

        //ImageView
        this.imgvEditProfilePicture = (ImageView) findViewById(R.id.imgvEditProfilePicture);

        //Button
        this.btnSaveChanges = (Button) findViewById(R.id.btnSaveChanges);
        this.btnChangeProfilePicture = (Button) findViewById(R.id.btnChangeProfilePicture);

        txteNewUsername.setHint(intent.getStringExtra(EditProfileActivity.NEW_USERNAME));
        txteNewLocation.setHint(intent.getStringExtra(EditProfileActivity.NEW_LOCATION));
        txteNewAboutMe.setHint(intent.getStringExtra(EditProfileActivity.NEW_ABOUT_ME));
    }

    public void saveChanges(View v) {
        Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);

        //Zu übergebene Strings
        String userName = txteNewUsername.getText().toString();
        String location = txteNewLocation.getText().toString();
        String aboutMe = txteNewAboutMe.getText().toString();

        if (userName.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Benutzername darf nicht leer sein", Toast.LENGTH_LONG).show();
        } else {
            intent.putExtra(NEW_USERNAME, userName);
            intent.putExtra(NEW_LOCATION, location);
            intent.putExtra(NEW_ABOUT_ME, aboutMe);

            startActivity(intent);
        }
    }
}
