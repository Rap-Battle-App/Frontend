package com.batllerap.hsosna.rapbattle16bars;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.batllerap.hsosna.rapbattle16bars.Controller.SearchController;
import com.batllerap.hsosna.rapbattle16bars.Model.ProfilePreview;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;

import org.json.JSONException;

import java.io.IOException;

public class SearchActivity extends AppCompatActivity {

    //aktueller User
    private User aktUser = null;

    //Widgets Deklarieren und Initialisieren

    //EditText
    private EditText etxtSearchTextField = null;

    //Button
    private Button btnStartSearch = null;

    //ProfilePreview
    private ProfilePreview[] searchResults = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        aktUser = (User) getIntent().getSerializableExtra("User");

        //EditText
        this.etxtSearchTextField = (EditText) findViewById(R.id.etxtSearchTextField);

        //Button
        this.btnStartSearch = (Button) findViewById(R.id.btnStartSearch);

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

                    searchResults = new ProfilePreview[1];
                    searchResults[0] = new ProfilePreview(0, "testRapper", "@drawable/default_profile_pic");
                    /*searchResults[1] = new ProfilePreview(1, "testViewer", "@drawable/default_profile_pic");
                    searchResults[2] = new ProfilePreview(2, "testViewer1", "@drawable/default_profile_pic");
                    searchResults[3] = new ProfilePreview(3, "testViewer2", "@drawable/default_profile_pic");
                    searchResults[4] = new ProfilePreview(4, "testViewer3", "@drawable/default_profile_pic");*/

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
                        /*texts[i] = new TextView(getApplicationContext());
                        texts[i].setText(searchResults[i].getUsername().toString());
                        texts[i].setId(i+1+searchResults.length);
                        RelativeLayout.LayoutParams lpTxt = new RelativeLayout.LayoutParams((int)RelativeLayout.LayoutParams.WRAP_CONTENT, (int)RelativeLayout.LayoutParams.WRAP_CONTENT);
                        lpTxt.addRule(RelativeLayout.BELOW, images[i].getId());
                        lpTxt.topMargin = 40;
                        lpTxt.leftMargin = 30;
                        rl.addView(texts[i], lpTxt);*/
                    }
                }
            }
        });
    }
}
