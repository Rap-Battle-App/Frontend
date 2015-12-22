package com.batllerap.hsosna.rapbattle16bars;

import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.TabLayout;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;

        import com.batllerap.hsosna.rapbattle16bars.Controller.AuthentificationController;
        import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;


public class MainActivity extends AppCompatActivity {
    private User aktUser;
    private AuthentificationController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Holt sich den User über das Login
         aktUser = (User) getIntent().getSerializableExtra("User");
         //System.out.print(aktUser.getUserName());



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Creating Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setContentDescription("HOME").setIcon(R.mipmap.ic_home_white_48dp)); //setText("Home")); //.setIcon(R.mipmap.ic_home_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setContentDescription("BATTLE").setIcon(R.mipmap.ic_settings_voice_white_48dp));
        tabLayout.addTab(tabLayout.newTab().setContentDescription("PROFILE").setIcon(R.mipmap.ic_account_circle_white_48dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        getSupportActionBar().setTitle("HOME");

        // Creating Viewpager
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter (getSupportFragmentManager(), tabLayout.getTabCount());
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

        if(getIntent().getExtras() != null){
            if(getIntent().getExtras().getInt("Tab") == 3){
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
                 if(aktUser != null){
                     try{
                         AuthentificationController.logout();
                     }catch (java.io.IOException exception){

                     }


                     //TODO WIEDER EINKOMMENTIEREN
                     //authController.logout(aktUser.getUserName());

                 }

                startActivity(d);
                return true;

            case R.id.action_search:
                Intent s = new Intent(this, SearchActivity.class);
                s.putExtra("User", aktUser);
                startActivity(s);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}