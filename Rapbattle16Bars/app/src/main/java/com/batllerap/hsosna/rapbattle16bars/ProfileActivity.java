package com.batllerap.hsosna.rapbattle16bars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;

import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.batllerap.hsosna.rapbattle16bars.Model.response.BattleListResponse;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ProfileActivity extends AppCompatActivity implements CustomAdapter.ClickListener {


    //aktueller User
    private User aktUser = null;

    //gesuchter User
    private User searchUser = null;

    //Widgets Deklarieren und Initialisieren

    //TextView
    private TextView txtvUsername = null;
    private TextView txtvLocation = null;
    private TextView txtvAboutMe = null;
    private TextView txtvWins = null;
    private TextView txtvLooses = null;
    private TextView txtvLoosesValue = null;
    private TextView txtvWinsValue = null;
    private TextView txtvClosedBattles = null;
    private TextView txtvOpenBattles = null;

    //View
    private View profileDivider = null;

    //ImageView
    private ImageView imgvProfilePicture = null;
    private ImageView imgvEditProfile = null;

    //Button
    private Button btnHerausfordern = null;

    //Battles
    private RecyclerView tList;
    private RecyclerView oList;
    private WrappingRecyclerViewLayoutManager wrvLayoutManager;
    private WrappingRecyclerViewLayoutManager wrv2LayoutManager;
    private CustomAdapter tAdapter;
    private CustomAdapter oAdapter;
    private List<BattleOverview> myBattlesList = new ArrayList<>();
    private List<BattleOverview> myOpenforVotesBattlesList = new ArrayList<>();

    private List<BattleOverview> trendingBattlesList = new ArrayList<>();
    private List<BattleOverview> openForVotesBattlesList = new ArrayList<>();
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
        // Set up Toolbar for Navigation
        final Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PROFIL");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aktUser = (User) getIntent().getSerializableExtra("User");
        searchUser = (User) getIntent().getSerializableExtra("Searchuser");

        //TextView
        this.txtvUsername = (TextView) findViewById(R.id.txtvUsername);
        this.txtvLocation = (TextView) findViewById(R.id.txtvLocation);
        this.txtvAboutMe = (TextView) findViewById(R.id.txtvAboutMe);
        this.txtvWins = (TextView) findViewById(R.id.txtvWins);
        this.txtvLooses = (TextView) findViewById(R.id.txtvLooses);
        this.txtvWinsValue = (TextView) findViewById(R.id.txtvWinsValue);
        this.txtvLoosesValue = (TextView) findViewById(R.id.txtvLoosesValue);
        this.txtvClosedBattles = (TextView) findViewById(R.id.txtvClosedBattles);
        this.txtvOpenBattles = (TextView) findViewById(R.id.txtvOpenBattles);

        //View
        this.profileDivider = (View) findViewById(R.id.profileDivider);

        //Button

        this.btnHerausfordern = (Button) findViewById(R.id.btnHerausfordern);
        this.btnHerausfordern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BattleController.sendRequest(searchUser.getId());
                    btnHerausfordern.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Herausgefordert!", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        if (aktUser.getUserName().equals(searchUser.getUserName())) {
            btnHerausfordern.setVisibility(View.INVISIBLE);
        }

        //ImageView
        this.imgvProfilePicture = (ImageView) findViewById(R.id.imgvProfilePicture);
        this.imgvEditProfile = (ImageView) findViewById(R.id.imgvEditProfile);
        this.imgvEditProfile.setVisibility(View.GONE);

        this.txtvUsername.setText(searchUser.getUserName());
        this.txtvLocation.setText(searchUser.getLocation());
        this.txtvAboutMe.setText(searchUser.getAboutMe());

        if (searchUser.getProfilePicture() != null) {
            Picasso.with(getApplicationContext()).load(searchUser.getProfilePicture()).into(imgvProfilePicture);
        } else {
            this.imgvProfilePicture.setImageResource(R.drawable.default_profile_pic);
        }
        this.imgvProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(imgvProfilePicture);
            }
        });

        if (searchUser.isRapper()) {
            this.txtvWinsValue.setText(String.valueOf(searchUser.getRapper().getWins()));
            this.txtvLoosesValue.setText(String.valueOf(searchUser.getRapper().getLooses()));

            //Battles des Rappers
            tList = (RecyclerView) findViewById(R.id.profileClosedBattlesList);
            oList = (RecyclerView) findViewById(R.id.profileOpenBattlesList);
            TextView tview = (TextView) findViewById(R.id.txtvClosedBattles);
            TextView oView = (TextView) findViewById(R.id.txtvOpenBattles);


            tview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent("com.batllerap.hsosna.rapbattle16bars.TrendingActivity");
                    startActivity(myIntent);

                }
            });

            oView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent("com.batllerap.hsosna.rapbattle16bars.OpenforvotesActivity");
                    startActivity(myIntent);
                }
            });


            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            tList.setHasFixedSize(true);

            wrvLayoutManager = new WrappingRecyclerViewLayoutManager(this);
            wrv2LayoutManager = new WrappingRecyclerViewLayoutManager(this);

            tList.setLayoutManager(wrvLayoutManager);
            oList.setLayoutManager(wrv2LayoutManager);

            tAdapter = new CustomAdapter(this, trendingBattlesList);
            oAdapter = new CustomAdapter(this, openForVotesBattlesList);

            if (aktUser != null) {
                TabFragment3AsyncTasks asyncTrendigBattles = new TabFragment3AsyncTasks();
                asyncTrendigBattles.execute("complete", aktUser.getId(), trendingBattlesList, tAdapter);
                TabFragment3AsyncTasks asyncOpenForVotes = new TabFragment3AsyncTasks();
                asyncOpenForVotes.execute("open", aktUser.getId(), openForVotesBattlesList, oAdapter);
            }

            tAdapter.setClickListener(this);
            oAdapter.setClickListener(this);

            tList.setAdapter(tAdapter);
            oList.setAdapter(oAdapter);
        } else {
            this.txtvWins.setVisibility(View.INVISIBLE);
            this.txtvLooses.setVisibility(View.INVISIBLE);
            this.txtvWinsValue.setVisibility(View.INVISIBLE);
            this.txtvLoosesValue.setVisibility(View.INVISIBLE);
            this.btnHerausfordern.setVisibility(View.INVISIBLE);
            this.txtvClosedBattles.setVisibility(View.INVISIBLE);
            this.txtvOpenBattles.setVisibility(View.INVISIBLE);
            this.profileDivider.setVisibility(View.INVISIBLE);
        }

    }

    public List<BattleOverview> getCompletedBattles() {

        List<BattleOverview> data = new ArrayList<>();
        BattleOverview[] bla = new BattleOverview[0];
        try {
            if (searchUser != null) {
                bla = BattleController.getCompletedBattles(searchUser.getId(), 0, 50).getData();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        data.addAll(Arrays.asList(bla));

        return data;
    }

    public List<BattleOverview> getOpenforVotingBattlesList() {

        List<BattleOverview> data = new ArrayList<>();
        BattleOverview[] bla = new BattleOverview[0];
        try {
            if (searchUser != null) {
                bla = BattleController.getOpenForVotingBattles(searchUser.getId(), 0, 50).getData();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        data.addAll(Arrays.asList(bla));

        return data;
    }

    @Override
    public void itemClicked(View view, int position) {
        if (view.getParent() == tList) {
            System.out.println("Trending List Angeklickt");
            try {
                Battle battle = BattleController.getBattle(trendingBattlesList.get(position).getBattle_id());

                Intent intent = new Intent("com.batllerap.hsosna.rapbattle16bars.ClosedBattleActivity");
                intent.putExtra("battle", battle);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (view.getParent() == oList) {

            try {
                Battle battle = BattleController.getBattle(openForVotesBattlesList.get(position).getBattle_id());

                Intent intent = new Intent("com.batllerap.hsosna.rapbattle16bars.OpenforVotesBattleActivity");
                intent.putExtra("battle", battle);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
            /*Intent myIntent = new Intent(getApplicationContext(), SearchActivity.class);
            myIntent.putExtra("User", aktUser);
            myIntent.putExtra("Suche", getIntent().getSerializableExtra("Suche"));
            startActivityForResult(myIntent, 0);
            return true;*/
        finish();
        return true;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private void zoomImageFromThumb(final View thumbView) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
        expandedImageView.setImageBitmap(((BitmapDrawable) imgvProfilePicture.getDrawable()).getBitmap());

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
        findViewById(R.id.container)
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
        btnHerausfordern.setVisibility(View.INVISIBLE);

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
                        if (!aktUser.getUserName().equals(searchUser.getUserName())) {
                            btnHerausfordern.setVisibility(View.VISIBLE);
                        }
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        if (aktUser.getUserName().equals(searchUser.getUserName())) {
                            btnHerausfordern.setVisibility(View.VISIBLE);
                        }
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}