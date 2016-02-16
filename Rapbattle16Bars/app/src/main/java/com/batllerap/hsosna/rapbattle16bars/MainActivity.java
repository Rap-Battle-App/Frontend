package com.batllerap.hsosna.rapbattle16bars;

import android.app.Service;

import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Build;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;


import com.batllerap.hsosna.rapbattle16bars.Controller.AuthentificationController;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;


public class MainActivity extends AppCompatActivity {
    private User aktUser;
    private AuthentificationController authController;
    private EditText etxtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Holt sich den User über das Login
        aktUser = (User) getIntent().getSerializableExtra("User");
        //System.out.print(aktUser.getUserName());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_relativelayout); // You must use the layout root
        InputMethodManager im = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);


        SoftKeyboard softKeyboard;
        softKeyboard = new SoftKeyboard(mainLayout, im);
        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged() {

            @Override
            public void onSoftKeyboardHide() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        etxtSearch.setVisibility(View.GONE);
                        etxtSearch.getText().clear();
                    }
                });
            }

            @Override
            public void onSoftKeyboardShow() {
            }
        });

        //Search EditText
        etxtSearch = (EditText) findViewById(R.id.etxtSearch);
        etxtSearch.setVisibility(View.GONE);
        etxtSearch.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) && !etxtSearch.getText().toString().isEmpty()) {
                    etxtSearch.setVisibility(View.INVISIBLE);
                    etxtSearch.setVisibility(View.GONE);
                    Intent s = new Intent(MainActivity.this, SearchActivity.class);
                    s.putExtra("User", aktUser);
                    s.putExtra("Suche", etxtSearch.getText().toString());
                    etxtSearch.getText().clear();
                    startActivity(s);
                    return true;
                }
                return false;
            }
        });

        // Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Creating Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setContentDescription("HOME").setIcon(R.mipmap.ic_home_white_48dp)); //setText("Home")); //.setIcon(R.mipmap.ic_home_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setContentDescription("BATTLE").setIcon(R.mipmap.ic_settings_voice_white_48dp));
        tabLayout.addTab(tabLayout.newTab().setContentDescription("PROFILE").setIcon(R.mipmap.ic_account_circle_white_48dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("HOME");
        }

        // Creating Viewpager
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                getSupportActionBar().setTitle(tab.getContentDescription());
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getInt("Tab") == 3) {
                viewPager.setCurrentItem(2);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent("com.batllerap.hsosna.rapbattle16bars.SettingsActivity");
                i.putExtra("User", aktUser);
                startActivity(i);
                return true;

            case R.id.action_info:
                Intent e = new Intent("com.batllerap.hsosna.rapbattle16bars.InfoActivity");
                startActivity(e);
                return true;

            case R.id.action_logout:
                Intent d = new Intent(this, Login.class);
                if (aktUser != null) {
                    try {
                        SharedPreferences login = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = login.edit();
                        editor.remove("username");
                        editor.remove("passwort");
                        editor.apply();
                        authController.logout();

                        AuthentificationController.logout();
                    } catch (java.io.IOException exception) {

                    }


                    //TODO WIEDER EINKOMMENTIEREN
                    //authController.logout(aktUser.getUserName());

                }

                startActivity(d);
                return true;

            case R.id.action_search:
                etxtSearch.setVisibility(View.VISIBLE);
                etxtSearch.setEnabled(true);
                etxtSearch.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etxtSearch, InputMethodManager.SHOW_IMPLICIT);
                etxtSearch.bringToFront();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}