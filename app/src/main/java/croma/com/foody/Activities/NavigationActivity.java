package croma.com.foody.Activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import croma.com.foody.Constants.AppConstants;
import croma.com.foody.R;
import croma.com.foody.Util.ActivitySwitcher;
import croma.com.foody.Util.SharedPrefUtil;
import croma.com.foody.interfaces.initInterface;


/**
 * @author Shambhavi.Thakur
 *         Navigation Activity Class
 */
public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener,initInterface {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        findViewById();
        applyFont();
        setOnClickListener();
    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPrefUtil.putBoolean(AppConstants.KEY_USER_LOGGED_IN,true,NavigationActivity.this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.addressid:{
                break;
            }
            case R.id.restro_id:{
                break;
            }
            case R.id.orderid:{
                break;
            }
            case R.id.account_id:{
                break;
            }
            case R.id.addressbook_id:{
                break;
            }
            case R.id.settings_id:{
                break;
            }
            case R.id.info_id:{
                break;
            }
            case R.id.logoutid:{
                SharedPrefUtil.remove(AppConstants.KEY_USER_LOGGED_IN,NavigationActivity.this);
                ActivitySwitcher.switchActivityWithoutHandler(this,LoginActivity.class,true);
                break;
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void findViewById() {

    }

    @Override
    public void applyFont() {

    }

    @Override
    public void setOnClickListener() {

    }
}
