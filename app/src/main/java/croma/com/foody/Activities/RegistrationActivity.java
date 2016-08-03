package croma.com.foody.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import croma.com.foody.Constants.AppConstants;
import croma.com.foody.R;
import croma.com.foody.Util.ActivitySwitcher;
import croma.com.foody.Util.NetUtil;
import croma.com.foody.Util.SharedPrefUtil;


/**
 * @author shambhavi
 */
public class RegistrationActivity extends AppCompatActivity implements initInterface, View.OnClickListener {


    private static final String TAG = RegistrationActivity.class.getSimpleName();
    private EditText newUserName_editText, password_editText, confirmPassword_editText, email_editText, contactNo_editText, address_editText;
    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById();
        applyFont();
        setOnClickListener();
    }

    @Override
    public void findViewById() {

        newUserName_editText = (EditText) findViewById(R.id.newUserName_editText);
        password_editText = (EditText) findViewById(R.id.password_editText);
        confirmPassword_editText = (EditText) findViewById(R.id.confirmPassword_editText);
        email_editText = (EditText) findViewById(R.id.email_editText);
        contactNo_editText = (EditText) findViewById(R.id.contactNo_editText);
        address_editText = (EditText) findViewById(R.id.address_editText);
        submitButton = (Button) findViewById(R.id.submitButton);

    }

    @Override
    public void applyFont() {

    }

    @Override
    public void setOnClickListener() {
        submitButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitButton: {
                if (NetUtil.isNetworkAvailable(RegistrationActivity.this)) {
                    if (!TextUtils.isEmpty(newUserName_editText.getText().toString())) {
                        if (!TextUtils.isEmpty(password_editText.getText().toString())) {

                            SharedPrefUtil.putString(AppConstants.KEY_REGISTRATION_USERNAME, newUserName_editText.getText().toString(), RegistrationActivity.this);
                            SharedPrefUtil.putString(AppConstants.KEY_REGISTRATION_PASSWORD, password_editText.getText().toString(), RegistrationActivity.this);
                            Toast.makeText(RegistrationActivity.this, "Thanx for Registration", Toast.LENGTH_SHORT).show();
                            ActivitySwitcher.switchActivity(RegistrationActivity.this, LoginActivity.class, true);
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, "Password Cannot be Empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegistrationActivity.this, "Username Cannot be Empty", Toast.LENGTH_SHORT).show();
                        }
                    if (password_editText.getText().toString().equals(confirmPassword_editText.getText().toString())) {

                                Toast.makeText(RegistrationActivity.this,"Password Matched",Toast.LENGTH_SHORT).show();
                            }
                    else {
                        Toast.makeText(RegistrationActivity.this,"Password Mismatch, Please Retype",Toast.LENGTH_SHORT).show();
                            }
                    if (!TextUtils.isEmpty(email_editText.getText().toString())) {

                        SharedPrefUtil.putString(AppConstants.KEY_REGISTRATION_EMAIL, email_editText.getText().toString(), RegistrationActivity.this);
                                }
                    else {
                        Toast.makeText(RegistrationActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                                }
                    if (!TextUtils.isEmpty(contactNo_editText.getText().toString())) {

                        SharedPrefUtil.putString(AppConstants.KEY_REGISTRATION_CONTACTNUMBER, contactNo_editText.getText().toString(),RegistrationActivity.this);
                                    }
                    else {
                        Toast.makeText(RegistrationActivity.this, "contact number cannot be empty", Toast.LENGTH_SHORT).show();
                                    }
                    if (!TextUtils.isEmpty(address_editText.getText().toString())) {

                        SharedPrefUtil.putString(AppConstants.KEY_REGISTRATION_ADDRESS, address_editText.getText().toString(), RegistrationActivity.this);
                    }
                    else {
                        Toast.makeText(RegistrationActivity.this, "Address cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegistrationActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
                break;

            }

                }
            }

        }




