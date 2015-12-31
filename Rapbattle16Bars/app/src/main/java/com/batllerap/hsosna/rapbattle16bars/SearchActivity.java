package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Controller.SearchController;
import com.batllerap.hsosna.rapbattle16bars.Controller.UserController;
import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

import org.json.JSONException;

import java.io.IOException;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.ClickListener {

    //aktueller User
    private User aktUser = null;

    //Widgets Deklarieren und Initialisieren

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

        // Set up Toolbar for Navigation
        final Toolbar toolbar = (Toolbar) findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SUCHE");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            searchResults = SearchController.profileSearch((String) getIntent().getSerializableExtra("Suche"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*searchResults = new ProfilePreview[5];
        searchResults[0] = new ProfilePreview(0, "testRapper", "http://thz-salzburg.at/cms/uploads/Prof-im-Profil-690x1024.jpg");
        searchResults[1] = new ProfilePreview(1, "testViewer", "http://thz-salzburg.at/cms/uploads/Prof-im-Profil-690x1024.jpg");
        searchResults[2] = new ProfilePreview(1, "testViewer1", "http://thz-salzburg.at/cms/uploads/Prof-im-Profil-690x1024.jpg");
        searchResults[3] = new ProfilePreview(1, "testViewer2", "http://thz-salzburg.at/cms/uploads/Prof-im-Profil-690x1024.jpg");
        searchResults[4] = new ProfilePreview(1, "testViewer3", "http://thz-salzburg.at/cms/uploads/Prof-im-Profil-690x1024.jpg");*/


        aktUser = (User) getIntent().getSerializableExtra("User");

        this.searchView = (RecyclerView) findViewById(R.id.searchResultView);
        this.wrvLayoutManager = new WrappingRecyclerViewLayoutManager(this);
        this.searchView.setLayoutManager(this.wrvLayoutManager);
        adapter = new SearchAdapter(getApplicationContext(), searchResults);
        adapter.setClickListener(SearchActivity.this);
        searchView.setAdapter(adapter);


    }

    @Override
    public void searchProfileClicked(View view, int position) {
        Intent intent = new Intent(this, ProfileActivity.class);
        User user = null;
        try {
            user = UserController.getUser(searchResults[position].getUser_id());
        } catch (IOException e) {
            e.printStackTrace();
        }
        intent.putExtra("Searchuser", user);
        intent.putExtra("User", aktUser);
        startActivity(intent);
    }
}
