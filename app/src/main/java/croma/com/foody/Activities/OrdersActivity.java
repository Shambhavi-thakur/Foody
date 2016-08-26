package croma.com.foody.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import croma.com.foody.R;

public class OrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        getSupportActionBar().setTitle("Orders");
    }
}
