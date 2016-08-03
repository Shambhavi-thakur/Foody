package croma.com.foody.Activities;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import croma.com.foody.Adapters.MyPagerAdapter;
import croma.com.foody.Fragments.LetsStartFragment;
import croma.com.foody.Fragments.OurLocationFragment;
import croma.com.foody.R;

public class FirstActivity extends AppCompatActivity implements initInterface, View.OnClickListener,LetsStartFragment.OnFragmentInteractionListener, OurLocationFragment.OnFragmentInteractionListener{


    public ViewPager vPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        findViewById();
    }

    @Override
    public void findViewById() {
        vPager = (ViewPager) findViewById(R.id.vPager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager()) ;
        vPager.setAdapter(pagerAdapter);
    }

    @Override
    public void applyFont() {

    }

    @Override
    public void setOnClickListener() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
