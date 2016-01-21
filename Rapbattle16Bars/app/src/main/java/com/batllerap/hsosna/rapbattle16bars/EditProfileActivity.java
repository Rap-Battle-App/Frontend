package com.batllerap.hsosna.rapbattle16bars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
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
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;

    private boolean pictureChanged = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
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
            Picasso.with(getApplicationContext()).load(aktUser.getProfilePicture()).into(imgvEditProfilePicture);
        } else {
            this.imgvEditProfilePicture.setImageResource(R.drawable.default_profile_pic);
        }

        this.imgvEditProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(imgvEditProfilePicture);
            }
        });

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
                        System.out.println(pictureChanged);
                        if (pictureChanged) {
                            String path = null;
                            Picasso.with(getApplicationContext()).load(Uri.parse(aktUser.getProfilePicture())).into(new Target() {
                                @Override
                                public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                                    new Thread(new Runnable() {

                                        @Override
                                        public void run() {

                                            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/tmp.jpg");
                                            try {
                                                file.createNewFile();
                                                FileOutputStream ostream = new FileOutputStream(file);
                                                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                                                Uri tmpUri = getImageUri(getApplicationContext(), bitmap);
                                                File f = new File(getRealPathFromURI(tmpUri));
                                                ImageUploadController up = new ImageUploadController(getContextFromActivity());
                                                up.execute(f);
                                                ostream.flush();
                                                ostream.close();
                                                file.delete();
                                            } catch (IOException e) {
                                                Log.e("IOException", e.getLocalizedMessage());
                                            }
                                        }
                                    }).start();
                                }

                                @Override
                                public void onBitmapFailed(Drawable drawable) {

                                }

                                @Override
                                public void onPrepareLoad(Drawable drawable) {

                                }
                            });
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
                startActivityForResult(pickPhoto, FROM_GALLERY);
            }
        });
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onActivityResult(int reqCode, int resCode, final Intent data) {
        switch (reqCode) {
            case FROM_GALLERY: {
                if (resCode == Activity.RESULT_OK) {
                    Uri selectedImage = data.getData();
                    Intent cropIntent = new Intent("com.android.camera.action.CROP");
                    cropIntent.setDataAndType(selectedImage, "image/*");
                    cropIntent.putExtra("crop", "true");
                    cropIntent.putExtra("aspectX", 1);
                    cropIntent.putExtra("aspectY", 1);
                    startActivityForResult(cropIntent, PICTURE_CROP);
                }
                break;
            }
            case PICTURE_CROP: {
                if (resCode == Activity.RESULT_OK) {
                    imgvEditProfilePicture.setImageURI(data.getData());
                    aktUser.setProfilePicture(data.getDataString());
                    pictureChanged = true;
                }
                break;
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
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
        startActivityForResult(myIntent, 0);
        return true;
    }

    private void zoomImageFromThumb(final View thumbView) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image_edit);
        expandedImageView.setImageBitmap(((BitmapDrawable) imgvEditProfilePicture.getDrawable()).getBitmap());

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container_edit)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);
        expandedImageView.bringToFront();
        btnChangeProfilePicture.setVisibility(View.INVISIBLE);
        btnSaveChanges.setVisibility(View.INVISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        btnChangeProfilePicture.setVisibility(View.VISIBLE);
                        btnSaveChanges.setVisibility(View.VISIBLE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        btnChangeProfilePicture.setVisibility(View.VISIBLE);
                        btnSaveChanges.setVisibility(View.VISIBLE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }

    public EditProfileActivity getContextFromActivity() {
        return this;
    }
}
