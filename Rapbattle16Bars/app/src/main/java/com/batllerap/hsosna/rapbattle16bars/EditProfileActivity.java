package com.batllerap.hsosna.rapbattle16bars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EditProfileActivity extends AppCompatActivity {

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

        //TextView
        this.txtvEditUsername = (TextView)findViewById(R.id.txtvEditUsername);
        this.txtvEditLocation = (TextView)findViewById(R.id.txtvEditLocation);
        this.txtvEditAboutMe = (TextView)findViewById(R.id.txtvEditAboutMe);

        //EditText
        this.txteNewUsername  = (EditText)findViewById(R.id.txteNewUsername);
        this.txteNewLocation  = (EditText)findViewById(R.id.txteNewLocation);
        this.txteNewAboutMe  = (EditText)findViewById(R.id.txteNewAboutMe);

        //ImageView
        this.imgvEditProfilePicture  = (ImageView)findViewById(R.id.imgvEditProfilePicture);

        //Button
        this.btnSaveChanges  = (Button)findViewById(R.id.btnSaveChanges);
        this.btnChangeProfilePicture  = (Button)findViewById(R.id.btnChangeProfilePicture);
    }
}
