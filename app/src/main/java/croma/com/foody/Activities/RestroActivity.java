package croma.com.foody.Activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import croma.com.foody.Adapters.CustomListAdapter;
import croma.com.foody.Constants.AppConstants;
import croma.com.foody.Constants.ServiceConfig;
import croma.com.foody.R;
import croma.com.foody.Util.NetUtil;
import croma.com.foody.Util.SharedPrefUtil;
import croma.com.foody.application.MyApplicationClass;
import croma.com.foody.interfaces.initInterface;
import croma.com.foody.io.geometry;

/**
 * @author Shambhavi.Thakuar
 *         Restrorent Activity Class
 */
public class RestroActivity extends AppCompatActivity implements initInterface {


    private static final String TAG = RestroActivity.class.getSimpleName();
    public static final int REQUEST_TIMEOUT_MS = 10000;
    private ArrayList<geometry> mArrayList = new ArrayList<>();
    private ListView restroListView;
    CustomListAdapter adapter;
    public RestroActivity restroActivity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restro);
        restroActivity = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        findViewById();
        applyFont();
        setOnClickListener();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(NetUtil.isNetworkAvailable(RestroActivity.this)) {
            String url = ServiceConfig.URL + "&location="+ SharedPrefUtil.getString(
                    AppConstants.LOCATION_LATTITUDE,"100",RestroActivity.this
            )+","+SharedPrefUtil.getString(AppConstants.LOCATION_LONGITUDE,"100",RestroActivity.this)+"&type=restaurant";
            Log.v(TAG,"URL--->>>"+url);
            jsonRequestWithGet(url);
        }else{
            Toast.makeText(RestroActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
        }
    }

    // json request with get
    public void jsonRequestWithGet(final String url) {

        JsonObjectRequest jsonObjectRequestWithGet = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.v(TAG, response.toString());
                mArrayList = new ArrayList<geometry>();
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    Log.v(TAG, "Json Array Size" + jsonArray.length());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        geometry geometry = new Gson().fromJson(jsonArray.getJSONObject(i).toString(), geometry.class);
                        mArrayList.add(geometry);
                        Log.v(TAG, "size-->>>" + mArrayList.size());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Resources res = getResources();
                            adapter = new CustomListAdapter(restroActivity, mArrayList, res);
                            restroListView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(RestroActivity.this, "There is some problem while getting restaurent", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v(TAG, "Exception: " + error);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map headerMap = new HashMap();
                return headerMap;
            }
        };
        jsonObjectRequestWithGet.setPriority(Request.Priority.IMMEDIATE);
        jsonObjectRequestWithGet.setShouldCache(false);
        jsonObjectRequestWithGet.setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplicationClass.getInstance().getRequestQueue().add(jsonObjectRequestWithGet);
    }


    @Override
    public void findViewById() {
        restroListView = (ListView) findViewById(R.id.restroListView);
        Resources res = getResources();
        /**************** Create Custom Adapter *********/
        adapter = new CustomListAdapter(restroActivity, mArrayList, res);
        restroListView.setAdapter(adapter);
    }

    @Override
    public void applyFont() {

    }

    @Override
    public void setOnClickListener() {

    }

    /*****************
     * This function used by adapter
     ****************/
    public void onItemClick(int mPosition) {
        geometry tempValues = (geometry) mArrayList.get(mPosition);
    }
}
