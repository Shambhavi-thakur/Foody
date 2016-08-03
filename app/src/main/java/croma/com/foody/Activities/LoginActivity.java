package croma.com.foody.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import croma.com.foody.Constants.AppConstants;
import croma.com.foody.R;
import croma.com.foody.Util.ActivitySwitcher;
import croma.com.foody.Util.NetUtil;
import croma.com.foody.Util.SharedPrefUtil;

public class LoginActivity extends AppCompatActivity implements initInterface, View.OnClickListener{



    private static final String TAG  = LoginActivity.class.getSimpleName();
    private EditText loginUserName , loginPassword;
    private Button loginButton;
    private TextView signupLink, forgotPasswordLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById();
        setOnClickListener();
        applyFont();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.loginButton)
        {
            String userName = loginUserName.getText().toString();
            String password = loginPassword.getText().toString();
            String userKey = SharedPrefUtil.getString(AppConstants.KEY_REGISTRATION_USERNAME,"Shambhavi",LoginActivity.this);
            String userPassword = SharedPrefUtil.getString(AppConstants.KEY_REGISTRATION_PASSWORD,"Shambhavi",LoginActivity.this);
            if(NetUtil.isNetworkAvailable(LoginActivity.this)) {
                if (userName.equals(userKey) && password.equals(userPassword)) {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    ActivitySwitcher.switchActivity(LoginActivity.this,FirstActivity.class,true);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(LoginActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
            }
        }
        if (view.getId()== R.id.signupLink)
        {
            ActivitySwitcher.switchActivity(LoginActivity.this, RegistrationActivity.class);
        }
        if(view.getId()== R.id.forgotPasswordLink)
        {
            ActivitySwitcher.switchActivity(LoginActivity.this,ForgotPasswordActivity.class);
        }

        }

    @Override
    public void findViewById() {

        loginUserName     =       (EditText) findViewById(R.id.loginUserName);
        loginPassword      =       (EditText) findViewById(R.id.loginPassword);
        loginButton        =       (Button) findViewById(R.id.loginButton);
        signupLink          =       (TextView) findViewById(R.id.signupLink);
        forgotPasswordLink =       (TextView) findViewById(R.id.forgotPasswordLink);
    }

    @Override
    public void applyFont() {

    }

    @Override
    public void setOnClickListener() {

        loginButton.setOnClickListener(this);
        signupLink.setOnClickListener(this);
        forgotPasswordLink.setOnClickListener(this);

    }

}
