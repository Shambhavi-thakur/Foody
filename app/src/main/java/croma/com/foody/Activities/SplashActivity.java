package croma.com.foody.Activities;

import android.app.Activity;
import android.os.Bundle;

import croma.com.foody.Constants.AppConstants;
import croma.com.foody.R;
import croma.com.foody.Util.ActivitySwitcher;
import croma.com.foody.Util.SharedPrefUtil;


/**
 * @author Shambhavi.Thakur
 *         Splash Activity Class
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(SharedPrefUtil.getBoolean(AppConstants.KEY_USER_LOGGED_IN,false,SplashActivity.this)){
            ActivitySwitcher.switchActivity(SplashActivity.this,NavigationActivity.class,true);
        }else{
            ActivitySwitcher.switchActivity(SplashActivity.this,LoginActivity.class,true);
        }
    };
}

