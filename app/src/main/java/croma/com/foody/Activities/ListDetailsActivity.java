package croma.com.foody.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import croma.com.foody.R;
import croma.com.foody.Util.SharedPrefUtil;
import croma.com.foody.interfaces.initInterface;


public class ListDetailsActivity extends AppCompatActivity implements initInterface, View.OnClickListener {

    private double latitude;
    private double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);
        findViewById();
        applyFont();
        setOnClickListener();
        double latitude = Double.longBitsToDouble(SharedPrefUtil.getLong("CurrentLatitude",1L, getActivity()));
        double longitude = Double.longBitsToDouble(SharedPrefUtil.getLong("CurrentLongitude",1L, getActivity()));

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
    public void onClick(View view) {

    }
}
