package croma.com.foody.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import croma.com.foody.Activities.FirstActivity;
import croma.com.foody.Activities.NavigationActivity;
import croma.com.foody.Adapters.ExperssListAdapter;
import croma.com.foody.Constants.AppConstants;
import croma.com.foody.R;
import croma.com.foody.interfaces.initInterface;
import croma.com.foody.io.geometry;


public class ExpressMenuListFragment extends Fragment implements initInterface, View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View mView;
    private ListView expressListView;
    private ArrayList<String> mArraList = new ArrayList<>();
    private ExperssListAdapter mExperssListAdapter;
    private geometry geometry;
    private TextView restroAddressTextView,restroNameTextView;
    private ImageView restroImageView;

    public ExpressMenuListFragment() {
        // Required empty public constructor
    }


    public static ExpressMenuListFragment newInstance(String param1, String param2) {
        ExpressMenuListFragment fragment = new ExpressMenuListFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_express_menu_list, container, false);
        findViewById();
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
        ((NavigationActivity)getActivity()).getSupportActionBar().setTitle("Express Menus");
        Bundle b = getArguments();
        if(b!=null && b.containsKey(AppConstants.BUNDLE_GEOMTERTY_MODEL)){
            geometry    =   (geometry) b.getSerializable(AppConstants.BUNDLE_GEOMTERTY_MODEL);
            if(!TextUtils.isEmpty(geometry.name)){
                restroNameTextView.setText(geometry.name);
            }
            if(!TextUtils.isEmpty(geometry.vicinity)){
                restroAddressTextView.setText(geometry.vicinity);
            }
            if(!TextUtils.isEmpty(geometry.icon)){
                Picasso.with(getActivity()).load(geometry.icon).resize(150,150).into(restroImageView);
            }
        }



    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void findViewById() {

        expressListView     =       (ListView)mView.findViewById(R.id.expressListView);
        restroNameTextView           =       (TextView)mView.findViewById(R.id.restroNameTextView);
        restroAddressTextView        =       (TextView)mView.findViewById(R.id.restroAddressTextView);
        restroImageView              =       (ImageView)mView.findViewById(R.id.restroImageView);
        mExperssListAdapter =       new ExperssListAdapter(getActivity(),mArraList);
        expressListView.setAdapter(mExperssListAdapter);
        mArraList.add("Express Shani Wraps");
        mArraList.add("Express Down South");
        mArraList.add("Express Biryanis");
        mArraList.add("Express Not Just Parathas");
        mArraList.add("Express Value Meals");
        mArraList.add("Express Platters");
        mArraList.add("Express Beverages");
       mExperssListAdapter.notifyDataSetChanged();
    }

    @Override
    public void applyFont() {

    }

    @Override
    public void setOnClickListener() {

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
}
