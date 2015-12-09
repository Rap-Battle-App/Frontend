package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.batllerap.hsosna.rapbattle16bars.Controller.UserController;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.User;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;

public class EditProfileActivity extends AppCompatActivity {

  /*  public final static String NEW_USERNAME = "com.batllerap.hsosna.rapbattle16bars.USERNAME";
    public final static String NEW_LOCATION = "com.batllerap.hsosna.rapbattle16bars.LOCATION";
    public final static String NEW_ABOUT_ME = "com.batllerap.hsosna.rapbattle16bars.ABOUTME";*/

    //aktueller User
    private User aktUser;

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

        aktUser = (User) getIntent().getSerializableExtra("User");

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

        txteNewUsername.setText(aktUser.getUserName());
        txteNewLocation.setText(aktUser.getLocation());
        txteNewAboutMe.setText(aktUser.getAboutMe());

        //OnClickListener zum Speichern der Daten
        this.btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent("com.batllerap.hsosna.rapbattle16bars.MainActivity");
                //myIntent.setAction("TabFragment3");
                //Benutzerdaten speichern
                try {
                    UserController.setUsername(aktUser, txteNewUsername.getText().toString());
                    if(!txteNewLocation.getText().toString().isEmpty() && !txteNewAboutMe.getText().toString().isEmpty()) {
                        UserController.setProfileInformation(aktUser, txteNewLocation.getText().toString(), txteNewAboutMe.getText().toString());
                    }else if(txteNewAboutMe.getText().toString().isEmpty()){
                        UserController.setLocation(aktUser, txteNewLocation.getText().toString());
                    } else if(txteNewLocation.getText().toString().isEmpty()){
                        UserController.setLocation(aktUser, txteNewAboutMe.getText().toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*aktUser.setUserName(txteNewUsername.getText().toString());
                aktUser.setLocation(txteNewLocation.getText().toString());
                aktUser.setAboutMe(txteNewAboutMe.getText().toString());*/

                myIntent.putExtra("User", aktUser);
                myIntent.putExtra("Tab", 3);

                if (aktUser.getUserName().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Benutzername darf nicht leer sein", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(myIntent);
                }
            }
        });

        //OnClickListener um Profilbild zu ändern
        this.btnChangeProfilePicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent, "Select Profile Picture"), 1);
            }
        });
    }

    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode == RESULT_OK){
            if(reqCode == 1){
                imgvEditProfilePicture.setImageURI(data.getData());
                aktUser.setProfilePicture(data.getData().toString());
            }
        }
    }
}
