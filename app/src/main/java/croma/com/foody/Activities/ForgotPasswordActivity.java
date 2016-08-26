package croma.com.foody.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import croma.com.foody.R;
import croma.com.foody.Util.ActivitySwitcher;
import croma.com.foody.Util.NetUtil;
import croma.com.foody.interfaces.initInterface;

/**
 * @author Shambahvi.Thakur
 *          Forgot Password Activity 
 */
public class ForgotPasswordActivity extends AppCompatActivity implements initInterface, View.OnClickListener {


    private EditText emailLink;
    private Button submit_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        findViewById();
        applyFont();
        setOnClickListener();
    }

    @Override
    public void findViewById() {

        emailLink = (EditText) findViewById(R.id.emailLink);
        submit_button = (Button) findViewById(R.id.submit_button);

    }

    @Override
    public void applyFont() {

    }

    @Override
    public void setOnClickListener() {

        submit_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (NetUtil.isNetworkAvailable(ForgotPasswordActivity.this)) {


        }
        else {
            Toast.makeText(ForgotPasswordActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        ActivitySwitcher.switchActivityWithoutHandler(this,LoginActivity.class,true);
    }
}
