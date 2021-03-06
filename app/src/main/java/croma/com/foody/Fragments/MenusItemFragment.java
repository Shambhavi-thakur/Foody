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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import croma.com.foody.Activities.FirstActivity;
import croma.com.foody.Activities.NavigationActivity;
import croma.com.foody.Constants.AppConstants;
import croma.com.foody.R;
import croma.com.foody.interfaces.initInterface;
import croma.com.foody.io.geometry;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenusItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenusItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenusItemFragment extends Fragment implements initInterface, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static final String TAG = MenusItemFragment.class.getSimpleName();
    private View mView;
    private LinearLayout experssLinearLayout,deliveryMenuLinearLayout;
    private geometry geometry;
    private TextView restroNameTextView,restroAddressTextView;
    private ImageView restroImageView;


    public MenusItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenusItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenusItemFragment newInstance(String param1, String param2) {
        MenusItemFragment fragment = new MenusItemFragment();
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
        mView = inflater.inflate(R.layout.fragment_menus_item, container, false);
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
        ((NavigationActivity)getActivity()).getSupportActionBar().setTitle("Menus");
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
    public void findViewById() {

        experssLinearLayout          =       (LinearLayout)mView.findViewById(R.id.experssLinearLayout);
        deliveryMenuLinearLayout     =       (LinearLayout)mView.findViewById(R.id.deliveryMenuLinearLayout);
        restroNameTextView           =       (TextView)mView.findViewById(R.id.restroNameTextView);
        restroAddressTextView        =       (TextView)mView.findViewById(R.id.restroAddressTextView);
        restroImageView              =       (ImageView)mView.findViewById(R.id.restroImageView);

    }

    @Override
    public void applyFont() {

    }

    @Override
    public void setOnClickListener() {

        experssLinearLayout.setOnClickListener(this);
        deliveryMenuLinearLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.experssLinearLayout:{
                Bundle bundle = new Bundle();
                bundle.putSerializable(AppConstants.BUNDLE_GEOMTERTY_MODEL,geometry);
                ExpressMenuListFragment expressMenuListFragment = new ExpressMenuListFragment();
                expressMenuListFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,expressMenuListFragment).addToBackStack(null).commit();
                break;
            }
            case R.id.deliveryMenuLinearLayout:{
                Bundle bundle = new Bundle();
                bundle.putSerializable(AppConstants.BUNDLE_GEOMTERTY_MODEL,geometry);
                DeliverMenuListFragment deliverMenuListFragment = new DeliverMenuListFragment();
                deliverMenuListFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,deliverMenuListFragment).addToBackStack(null).commit();
                break;
            }
        }
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
