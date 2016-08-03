package croma.com.foody.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import croma.com.foody.R;
import croma.com.foody.Util.ActivitySwitcher;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getWindow().addFlags(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        ActivitySwitcher.switchActivity(SplashActivity.this,LoginActivity.class,true);

    };
}

