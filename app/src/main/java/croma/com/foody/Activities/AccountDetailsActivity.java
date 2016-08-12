package croma.com.foody.Activities;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import croma.com.foody.Constants.AppConstants;
import croma.com.foody.R;
import croma.com.foody.Util.NetUtil;
import croma.com.foody.Util.SharedPrefUtil;
import croma.com.foody.interfaces.initInterface;

public class AccountDetailsActivity extends AppCompatActivity implements initInterface, View.OnClickListener {


    private static final String TAG = AccountDetailsActivity.class.getSimpleName();
    private TextView accountdetails_txtview, edit_txtview, changepassword_txtview;
    private EditText firstname_edittext, lastname_edittext, emailid_edittext, mobile_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        findViewById();
        applyFont();
        setOnClickListener();
    }

    @Override
    public void findViewById() {

        accountdetails_txtview  = (TextView) findViewById(R.id.accountdetails_txtview);
        edit_txtview            = (TextView) findViewById(R.id.edit_txtview);
        changepassword_txtview  = (TextView) findViewById(R.id.changepassword_txtview);
        firstname_edittext      = (EditText) findViewById(R.id.firstname_edittext);
        lastname_edittext       = (EditText) findViewById(R.id.lastname_edittext);
        emailid_edittext        = (EditText) findViewById(R.id.emailid_edittext);
        mobile_edittext         = (EditText) findViewById(R.id.mobile_edittext);

        firstname_edittext.setEnabled(false);
        lastname_edittext.setEnabled(false);
        emailid_edittext.setEnabled(false);
        mobile_edittext.setEnabled(false);


    }

    @Override
    public void applyFont() {

    }

    @Override
    public void setOnClickListener() {

        edit_txtview.setOnClickListener(this);
        changepassword_txtview.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_txtview: {
                firstname_edittext.setEnabled(true);
                lastname_edittext.setEnabled(true);
                emailid_edittext.setEnabled(true);
                mobile_edittext.setEnabled(true);
                edit_txtview.setText("Apply");

                if(edit_txtview.getText().equals("Apply")) {
                    if (NetUtil.isNetworkAvailable(AccountDetailsActivity.this)) {
                        if (!TextUtils.isEmpty(firstname_edittext.getText().toString())) {
                            if (!TextUtils.isEmpty(lastname_edittext.getText().toString())) {
                                if (!TextUtils.isEmpty(emailid_edittext.getText().toString())) {
                                    if (!TextUtils.isEmpty(mobile_edittext.getText().toString())) {

                                        SharedPrefUtil.putString(AppConstants.KEY_ACCOUNTDETAILS_FIRSTNAME, firstname_edittext.getText().toString(), AccountDetailsActivity.this);
                                        SharedPrefUtil.putString(AppConstants.KEY_ACCOUNTDETAILS_MOBILENUMBER, mobile_edittext.getText().toString(), AccountDetailsActivity.this);
                                        SharedPrefUtil.putString(AppConstants.KEY_ACCOUNTDETAILS_EMAIL, emailid_edittext.getText().toString(), AccountDetailsActivity.this);
                                        SharedPrefUtil.putString(AppConstants.KEY_ACCOUNTDETAILS_LASTNAME, lastname_edittext.getText().toString(), AccountDetailsActivity.this);

                                    } else {
                                        Toast.makeText(AccountDetailsActivity.this, "This Field is mandatory", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(AccountDetailsActivity.this, "This Field is mandatory", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(AccountDetailsActivity.this, "This Field is mandatory", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(AccountDetailsActivity.this, "This Field is mandatory", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AccountDetailsActivity.this, "No Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
            case R.id.changepassword_txtview:{

                final Dialog dialog = new Dialog(AccountDetailsActivity.this); // Context, this, etc.
                dialog.setContentView(R.layout.activity_changepassword);
                dialog.setTitle("Forgot Password");
                dialog.show();
                break;

            }
        }
    }
}








