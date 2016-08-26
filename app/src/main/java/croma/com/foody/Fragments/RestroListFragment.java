package croma.com.foody.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import croma.com.foody.Activities.NavigationActivity;
import croma.com.foody.Activities.RestroActivity;
import croma.com.foody.Adapters.CustomListAdapter;
import croma.com.foody.Constants.AppConstants;
import croma.com.foody.Constants.ServiceConfig;
import croma.com.foody.R;
import croma.com.foody.Fragments.MenusItemFragment;
import croma.com.foody.Util.NetUtil;
import croma.com.foody.Util.ProgressUtils;
import croma.com.foody.Util.SharedPrefUtil;
import croma.com.foody.application.MyApplicationClass;
import croma.com.foody.interfaces.initInterface;
import croma.com.foody.io.geometry;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RestroListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RestroListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestroListFragment extends Fragment implements initInterface, View.OnClickListener, MenusItemFragment.OnFragmentInteractionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public static final String TAG = RestroListFragment.class.getSimpleName();
    public static final int REQUEST_TIMEOUT_MS = 10000;
    public ArrayList<geometry> mArrayList = new ArrayList<>();
    private ListView restroListView;
    public CustomListAdapter adapter;
    private View mView;
    public NavigationActivity restroActivity = null;




    public RestroListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestroListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RestroListFragment newInstance(String param1, String param2) {
        RestroListFragment fragment = new RestroListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        restroActivity = (NavigationActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView   =    inflater.inflate(R.layout.fragment_restro_list, container, false);
        findViewById();
        applyFont();
        setOnClickListener();
        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(NetUtil.isNetworkAvailable(getActivity())) {
            ProgressUtils.showSimpleProgressDialog(getActivity(),"","",false);
            String url = ServiceConfig.URL + "&location="+ SharedPrefUtil.getString(
                    AppConstants.LOCATION_LATTITUDE,"100",getActivity()
            )+","+SharedPrefUtil.getString(AppConstants.LOCATION_LONGITUDE,"100",getActivity())+"&type=restaurant";
            Log.v(TAG,"URL--->>>"+url);
            jsonRequestWithGet(url);
        }else{
            Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void findViewById() {

        restroListView = (ListView) mView.findViewById(R.id.restroListView);
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

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Resources res = getResources();
                            adapter = new CustomListAdapter(restroActivity, mArrayList, res);
                            restroListView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            ProgressUtils.removeSimpleProgressDialog();
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "There is some problem while getting restaurent", Toast.LENGTH_SHORT).show();
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


    /*****************
     * This function used by adapter
     ****************/
    public void onItemClick(int mPosition) {
        ArrayList<geometry> arrayList = ((MenusItemFragment)getSupportFragmentManager().findFragmentByTag(MenusItemFragment.TAG)).mArrayList;
        geometry tempValues = (geometry) mArrayList.get(mPosition);




    }
}
