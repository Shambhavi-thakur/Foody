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

public class DeliverMenuListFragment extends Fragment implements initInterface, View.OnClickListener {

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

    public DeliverMenuListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeliverMenuListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeliverMenuListFragment newInstance(String param1, String param2) {
        DeliverMenuListFragment fragment = new DeliverMenuListFragment();
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
        mView =  inflater.inflate(R.layout.fragment_deliver_menu_list, container, false);
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
        ((NavigationActivity)getActivity()).getSupportActionBar().setTitle("Delivery Menus");
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

        expressListView         =       (ListView)mView.findViewById(R.id.expressListView);
        restroNameTextView           =       (TextView)mView.findViewById(R.id.restroNameTextView);
        restroAddressTextView        =       (TextView)mView.findViewById(R.id.restroAddressTextView);
        restroImageView              =       (ImageView)mView.findViewById(R.id.restroImageView);
        mExperssListAdapter     =       new ExperssListAdapter(getActivity(),mArraList);
        expressListView.setAdapter(mExperssListAdapter);
        mArraList.add("Popular products");
        mArraList.add("Indian Rolls");
        mArraList.add("Indian Tandoor: Veg");
        mArraList.add("Indian Tandoor: Non-Veg");
        mArraList.add("Indian Tangri Kebab & Wings");
        mArraList.add("Indian Main Course: Veg");
        mArraList.add("Indian Main Course: Non-Veg");
        mArraList.add("Indian Rice & Biryanis");
        mArraList.add("Indian Breads");
        mArraList.add("Chinese Soups");
        mArraList.add("Chinese Tandoori Momos");
        mArraList.add("Chinese Special Momos");
        mArraList.add("Chinese Cripsy Fry Chicken");
        mArraList.add("Chinese Fish Varities");
        mArraList.add("Chinese Main Course: Veg");
        mArraList.add("Chinese Main Course: Non-Veg");
        mArraList.add("Chinese Chow Mein");
        mArraList.add("Chinese Chinese Suey");
        mArraList.add("Desserts");
        mArraList.add("Beverages");
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
