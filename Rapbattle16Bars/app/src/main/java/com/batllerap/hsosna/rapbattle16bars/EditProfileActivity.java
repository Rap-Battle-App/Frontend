package com.batllerap.hsosna.rapbattle16bars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.batllerap.hsosna.rapbattle16bars.Controller.ImageUploadController;
import com.batllerap.hsosna.rapbattle16bars.Controller.UserController;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import android.Manifest;

public class EditProfileActivity extends AppCompatActivity {
    private static final int FROM_GALLERY = 80;
    private static final int PICTURE_CROP = 90;

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

    //Permission
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private Animator mCurrentAnimator;

    private boolean pictureChanged = false;

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission(){
        System.out.println("Checking Permission:");
        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Ask Permission:");
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        checkPermission();
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

        if (aktUser.getProfilePicture() != null) {
            Picasso.with(getApplicationContext()).load(aktUser.getProfilePicture()).networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE).fit().into(imgvEditProfilePicture);
        } else {
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
                myIntent.putExtra("User", aktUser);
                myIntent.putExtra("Tab", 3);
                if (aktUser.getUserName().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Benutzername darf nicht leer sein", Toast.LENGTH_LONG).show();
                } else {
                    //Benutzerdaten speichern
                    try {
                        UserController.setUsername(aktUser, txteNewUsername.getText().toString());
                        UserController.setProfileInformation(aktUser, txteNewLocation.getText().toString(), txteNewAboutMe.getText().toString());
                        System.out.println(pictureChanged);
                        if (pictureChanged) {
                            Bitmap bitmap = ((BitmapDrawable) imgvEditProfilePicture.getDrawable()).getBitmap();
                            Uri tmpUri = getImageUri(getApplicationContext(), bitmap);
                            File f = new File(getRealPathFromURI(tmpUri));
                            ImageUploadController up = new ImageUploadController(getContextFromActivity());
                            up.execute(f);
                            pictureChanged = false;
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
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
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                pickPhoto.putExtra("crop", "true");
                pickPhoto.putExtra("aspectX", 1);
                pickPhoto.putExtra("aspectY", 1);

                startActivityForResult(pickPhoto, FROM_GALLERY);
            }
        });
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        System.out.println("Permission Rsult:");
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    System.out.println("Permission Granted");
                } else {
                    // Permission Denied
                    System.out.println("Permission Denied");
                    Toast.makeText(this, "WRITE_EXTERNAL_STORAGE Denied", Toast.LENGTH_SHORT)
                            .show();
                    this.btnChangeProfilePicture.setVisibility(View.GONE);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void onActivityResult(int reqCode, int resCode, final Intent data) {
        if (reqCode == FROM_GALLERY) {
            if (resCode == Activity.RESULT_OK) {
                if(data.getData()!=null){
                    imgvEditProfilePicture.setImageURI(data.getData());
                }else{
                    imgvEditProfilePicture.setImageBitmap((Bitmap)data.getExtras().get("data"));
                }

                aktUser.setProfilePicture(data.getDataString());
                pictureChanged = true;
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        Uri uri = Uri.parse("content://media/external/images/media");

        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

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

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        myIntent.putExtra("User", aktUser);
        // myIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public EditProfileActivity getContextFromActivity() {
        return this;
    }
}
