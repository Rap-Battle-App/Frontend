package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Controller.SearchController;
<<<<<<< HEAD
import com.batllerap.hsosna.rapbattle16bars.Controller.UserController;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.ProfilePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.Profile.User;
=======
import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
>>>>>>> 85c830b1bc2cdcc200f27de296d80cee4209a6da

import org.json.JSONException;

import java.io.IOException;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.ClickListener {

    //aktueller User
    private User aktUser = null;

    //Widgets Deklarieren und Initialisieren

    //EditText
    private EditText etxtSearchTextField = null;

    //Button
    private Button btnStartSearch = null;

    //ProfilePreview
    private ProfilePreview[] searchResults = {};

    //RecyclerView
    private RecyclerView searchView;

    //LayoutManager
    private WrappingRecyclerViewLayoutManager wrvLayoutManager;

    //SearchAdapter
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        aktUser = (User) getIntent().getSerializableExtra("User");

        //EditText
        this.etxtSearchTextField = (EditText) findViewById(R.id.etxtSearchTextField);

        //Button
        this.btnStartSearch = (Button) findViewById(R.id.btnStartSearch);

        this.searchView = (RecyclerView) findViewById(R.id.searchResultView);
        this.wrvLayoutManager = new WrappingRecyclerViewLayoutManager(this);
        this.searchView.setLayoutManager(this.wrvLayoutManager);
        adapter = new SearchAdapter(getApplicationContext(), searchResults);
        adapter.setClickListener(SearchActivity.this);
        searchView.setAdapter(adapter);

        this.btnStartSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etxtSearchTextField.getText().toString().isEmpty()) {

                    //TODO Benutzen, wenn SearchController tut
                    /*try {
                        //searchResults = SearchController.profileSearch(etxtSearchTextField.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                    searchResults = new ProfilePreview[5];
                    searchResults[0] = new ProfilePreview(0, "testRapper", "http://thz-salzburg.at/cms/uploads/Prof-im-Profil-690x1024.jpg");
                    searchResults[1] = new ProfilePreview(1, "testViewer", "http://thz-salzburg.at/cms/uploads/Prof-im-Profil-690x1024.jpg");
                    searchResults[2] = new ProfilePreview(1, "testViewer1", "http://thz-salzburg.at/cms/uploads/Prof-im-Profil-690x1024.jpg");
                    searchResults[3] = new ProfilePreview(1, "testViewer2", "http://thz-salzburg.at/cms/uploads/Prof-im-Profil-690x1024.jpg");
                    searchResults[4] = new ProfilePreview(1, "testViewer3", "http://thz-salzburg.at/cms/uploads/Prof-im-Profil-690x1024.jpg");

                    adapter.notifyDataSetChanged();

                    /*
                    ImageView[] images = new ImageView[searchResults.length];
                    TextView[] texts = new TextView[searchResults.length];
                    for (int i = 0; i < searchResults.length; i++) {
                        //Imageview
                        images[i] = new ImageView(getApplicationContext());
                        //iv.setImageURI(Uri.parse(searchResults[i].getProfilePicture()));
                        images[i].setImageResource(R.drawable.default_profile_pic);
                        images[i].setId(i+1);
                        RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayoutSearch);
                        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(100,100);
                        if(i == 0){
                            lp.addRule(RelativeLayout.BELOW, R.id.btnStartSearch);
                            lp.topMargin = 30;
                        }else{
                            lp.addRule(RelativeLayout.BELOW, images[i-1].getId());
                            lp.topMargin = 30;
                        }
                        rl.addView(images[i], lp);


                        //TODO  Text anzeigen
                        //TextView
                        texts[i] = new TextView(getApplicationContext());
                        texts[i].setText(searchResults[i].getUsername().toString());
                        texts[i].setId(i+1+searchResults.length);
                        RelativeLayout.LayoutParams lpTxt = new RelativeLayout.LayoutParams((int)RelativeLayout.LayoutParams.WRAP_CONTENT, (int)RelativeLayout.LayoutParams.WRAP_CONTENT);
                        lpTxt.addRule(RelativeLayout.BELOW, images[i].getId());
                        lpTxt.topMargin = 40;
                        lpTxt.leftMargin = 30;
                        rl.addView(texts[i], lpTxt);
                    }*/
                }
            }
        });
    }

    @Override
    public void searchProfileClicked(View view, int position) {
        Intent intent = new Intent(this, ProfileActivity.class);
        User user = null;
        try {
            user = UserController.getUser(searchResults[position].getUserId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
