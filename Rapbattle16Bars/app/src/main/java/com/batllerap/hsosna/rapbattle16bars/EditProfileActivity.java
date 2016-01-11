package com.batllerap.hsosna.rapbattle16bars;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.batllerap.hsosna.rapbattle16bars.Controller.UserController;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;


import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Set up Toolbar for Navigation
        final Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PROFIL");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        if(aktUser.getProfilePicture() != null) {
            this.imgvEditProfilePicture.setImageURI(Uri.parse(aktUser.getProfilePicture()));
        }else {
            this.imgvEditProfilePicture.setImageResource(R.drawable.default_profile_pic);
        }

        txteNewUsername.setText(aktUser.getUserName());
        txteNewLocation.setText(aktUser.getLocation());
        txteNewAboutMe.setText(aktUser.getAboutMe());

        //OnClickListener zum Speichern der Daten
        this.btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent("com.batllerap.hsosna.rapbattle16bars.MainActivity");
                //myIntent.setAction("TabFragment3");


                myIntent.putExtra("User", aktUser);
                myIntent.putExtra("Tab", 3);

                if (aktUser.getUserName().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Benutzername darf nicht leer sein", Toast.LENGTH_LONG).show();
                } else {
                    //Benutzerdaten speichern
                    try {
                        UserController.setUsername(aktUser, txteNewUsername.getText().toString());
                        UserController.setProfileInformation(aktUser, txteNewLocation.getText().toString(), txteNewAboutMe.getText().toString());
                        InputStream iStream =   getContentResolver().openInputStream(Uri.parse(aktUser.getProfilePicture()));
                        byte[] inputData = getBytes(iStream);
                        UserController.setProfilPicture(inputData, getMimeType(getApplicationContext(), Uri.parse(aktUser.getProfilePicture())));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    startActivity(myIntent);
                }
            }
        });

        //OnClickListener um Profilbild zu ändern
        this.btnChangeProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent, "Select Profile Picture"), 1);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onActivityResult(int reqCode, int resCode, Intent data) {
        if (resCode == RESULT_OK) {
            if (reqCode == 1) {
                imgvEditProfilePicture.setImageURI(data.getData());
                aktUser.setProfilePicture(data.getData().toString());
            }
        }
    }

    public static String getMimeType(Context context, Uri uriImage)
    {
        String strMimeType = null;

        Cursor cursor = context.getContentResolver().query(uriImage,
                new String[] { MediaStore.MediaColumns.MIME_TYPE },
                null, null, null);

        if (cursor != null && cursor.moveToNext())
        {
            strMimeType = cursor.getString(0);
        }
        assert strMimeType != null;
        String[] seperated = strMimeType.split("/");
        return seperated[1];
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int next = inputStream.read();
        while (next > -1) {
            bos.write(next);
            next = inputStream.read();
        }
        bos.flush();
        byte[] result = bos.toByteArray();
        return result;
    }
    /* public String getRealPathFromURI(Uri contentUri){
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        cursor.close();
        System.out.println(s);
        return s;
    }*/

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "EditProfile Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.batllerap.hsosna.rapbattle16bars/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "EditProfile Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.batllerap.hsosna.rapbattle16bars/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        myIntent.putExtra("User", aktUser);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
