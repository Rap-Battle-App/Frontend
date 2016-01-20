package com.batllerap.hsosna.rapbattle16bars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Model.Battle.Battle;
import com.batllerap.hsosna.rapbattle16bars.Model.BattleOverview;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.batllerap.hsosna.rapbattle16bars.Model.response.BattleListResponse;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TabFragment3 extends Fragment implements CustomAdapter.ClickListener {

    //aktueller User
    private User aktUser = null;

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

    //Button
    private Button btnEditProfile = null;
    private Button btnHerausfordern = null;

    //Battles
    private RecyclerView tList;
    private RecyclerView oList;
    private WrappingRecyclerViewLayoutManager wrvLayoutManager;
    private WrappingRecyclerViewLayoutManager wrv2LayoutManager;
    private CustomAdapter tAdapter;
    private CustomAdapter oAdapter;
    private BattleController bController;
    private  static BattleListResponse trending;

    private List<BattleOverview> trendingBattlesList = new ArrayList<>();
    private List<BattleOverview> openForVotesBattlesList = new ArrayList<>();

    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.activity_profile, container, false);

        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);

        final Toolbar toolbar = (Toolbar) layout.findViewById(R.id.profileToolbar);
        toolbar.setVisibility(View.GONE);

        aktUser = (User) getActivity().getIntent().getSerializableExtra("User");

        //TextView
        this.txtvUsername = (TextView) layout.findViewById(R.id.txtvUsername);
        this.txtvLocation = (TextView) layout.findViewById(R.id.txtvLocation);
        this.txtvAboutMe = (TextView) layout.findViewById(R.id.txtvAboutMe);
        this.txtvWins = (TextView) layout.findViewById(R.id.txtvWins);
        this.txtvLooses = (TextView) layout.findViewById(R.id.txtvLooses);
        this.txtvWinsValue = (TextView) layout.findViewById(R.id.txtvWinsValue);
        this.txtvLoosesValue = (TextView) layout.findViewById(R.id.txtvLoosesValue);
        this.txtvClosedBattles = (TextView) layout.findViewById(R.id.txtvClosedBattles);
        this.txtvOpenBattles = (TextView) layout.findViewById(R.id.txtvOpenBattles);

        //View
        this.profileDivider = (View) layout.findViewById(R.id.profileDivider);

        //ImageView
        this.imgvProfilePicture = (ImageView) layout.findViewById(R.id.imgvProfilePicture);

        //Button
        this.btnEditProfile = (Button) layout.findViewById(R.id.btnEditProfile);

        this.btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), EditProfileActivity.class);
                myIntent.putExtra("User", aktUser);
                getActivity().startActivity(myIntent);
            }
        });

        this.btnHerausfordern = (Button) layout.findViewById(R.id.btnHerausfordern);
        this.btnHerausfordern.setVisibility(View.INVISIBLE);

        this.txtvUsername.setText(aktUser.getUserName());
        this.txtvLocation.setText(aktUser.getLocation());
        this.txtvAboutMe.setText(aktUser.getAboutMe());
        System.out.println("Profilbild: " + aktUser.getProfilePicture());
        if(aktUser.getProfilePicture() != null) {
            Picasso.with(getActivity().getApplicationContext()).load(aktUser.getProfilePicture()).into(imgvProfilePicture);
        }else {
            this.imgvProfilePicture.setImageResource(R.drawable.default_profile_pic);
        }

        this.imgvProfilePicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                zoomImageFromThumb(imgvProfilePicture);
            }
        });

        if(aktUser.isRapper()){
            this.txtvWinsValue.setText(Integer.toString(aktUser.getRapper().getWins()));
            this.txtvLoosesValue.setText(Integer.toString(aktUser.getRapper().getLooses()));

            //Battles des Rappers
            tList = (RecyclerView) layout.findViewById(R.id.profileClosedBattlesList);
            oList = (RecyclerView) layout.findViewById(R.id.profileOpenBattlesList);
            TextView tview = (TextView) layout.findViewById(R.id.txtvClosedBattles);
            TextView oView = (TextView) layout.findViewById(R.id.txtvOpenBattles);


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

            wrvLayoutManager = new WrappingRecyclerViewLayoutManager(getActivity());
            wrv2LayoutManager = new WrappingRecyclerViewLayoutManager(getActivity());

            tList.setLayoutManager(wrvLayoutManager);
            oList.setLayoutManager(wrv2LayoutManager);

            tAdapter = new CustomAdapter(getActivity(),trendingBattlesList);
            oAdapter = new CustomAdapter(getActivity(),openForVotesBattlesList);

            if(aktUser != null){
                TabFragment3AsyncTasks asyncTrendigBattles = new TabFragment3AsyncTasks();
                asyncTrendigBattles.execute("complete", aktUser.getId(), trendingBattlesList, tAdapter);
                TabFragment3AsyncTasks asyncOpenForVotes = new TabFragment3AsyncTasks();
                asyncOpenForVotes.execute("open", aktUser.getId(), openForVotesBattlesList, oAdapter);
            }

            tAdapter.setClickListener(this);
            oAdapter.setClickListener(this);

            tList.setAdapter(tAdapter);
            oList.setAdapter(oAdapter);

        }else{
            this.txtvWins.setVisibility(View.INVISIBLE);
            this.txtvLooses.setVisibility(View.INVISIBLE);
            this.txtvWinsValue.setVisibility(View.INVISIBLE);
            this.txtvLoosesValue.setVisibility(View.INVISIBLE);
            this.txtvClosedBattles.setVisibility(View.INVISIBLE);
            this.txtvOpenBattles.setVisibility(View.INVISIBLE);
            this.profileDivider.setVisibility(View.INVISIBLE);
        }

        return layout;
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
    private void zoomImageFromThumb(final View thumbView) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) getActivity().findViewById(R.id.expanded_image);
        expandedImageView.setImageBitmap(((BitmapDrawable)imgvProfilePicture.getDrawable()).getBitmap());

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
        getActivity().findViewById(R.id.container)
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
        btnEditProfile.setVisibility(View.INVISIBLE);

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
                                        View.Y,startBounds.top))
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
                        btnEditProfile.setVisibility(View.VISIBLE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        btnEditProfile.setVisibility(View.VISIBLE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}

