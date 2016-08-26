package croma.com.foody.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import croma.com.foody.Constants.AppConstants;
import croma.com.foody.Fragments.RestroListFragment;
import croma.com.foody.Fragments.SetLocationFragment;
import croma.com.foody.R;
import croma.com.foody.Util.ActivitySwitcher;
import croma.com.foody.Util.SharedPrefUtil;
import croma.com.foody.interfaces.initInterface;
import croma.com.foody.io.geometry;


/**
 * @author Shambhavi.Thakur
 *         Navigation Activity Class
 */
public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener,initInterface ,
        SetLocationFragment.OnFragmentInteractionListener,RestroListFragment.OnFragmentInteractionListener{

private TextView NavigationName, NavigationEmail;

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
        NavigationName = (TextView)findViewById(R.id.name_textView);
        NavigationEmail = (TextView)findViewById(R.id.email_textView);
        navigationView.getMenu().getItem(1).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);

        findViewById();
        applyFont();
        setOnClickListener();
        if(savedInstanceState==null){
            boolean isManual = SharedPrefUtil.getBoolean("comeFromManual",false,NavigationActivity.this);
            if(isManual){
                getSupportActionBar().setTitle("Address");
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new SetLocationFragment(), SetLocationFragment.TAG).commit();
            }else{
                getSupportActionBar().setTitle("Restaurants");
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new RestroListFragment(),RestroListFragment.TAG).commit();
            }


        }
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
                ActivitySwitcher.switchActivityWithoutHandler(NavigationActivity.this,SetLocationFragment.class);
                break;
            }
            case R.id.restro_id:{
                ActivitySwitcher.switchActivityWithoutHandler(NavigationActivity.this,RestroActivity.class);
                break;
            }
            case R.id.orderid:{
                break;
            }
            case R.id.account_id:{
                ActivitySwitcher.switchActivityWithoutHandler(NavigationActivity.this,AccountDetailsActivity.class,false);
                break;
            }
            case R.id.addressbook_id:{
                 ActivitySwitcher.switchActivityWithoutHandler(NavigationActivity.this, AddressBookActivity.class);
                break;
            }
            case R.id.settings_id:{
                ActivitySwitcher.switchActivityWithoutHandler(NavigationActivity.this, SettingsActivity.class);
                break;
            }
            case R.id.info_id:{
                ActivitySwitcher.switchActivityWithoutHandler(NavigationActivity.this, InfoActivity.class);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /*****************
     * This function used by adapter
     ****************/
    public void onItemClick(int mPosition) {

        ArrayList<geometry> arrayList = ((RestroListFragment)getSupportFragmentManager().findFragmentByTag(RestroListFragment.TAG)).mArrayList;
        geometry tempValues = (geometry) arrayList.get(mPosition);

    }
}
