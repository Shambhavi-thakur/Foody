package croma.com.foody.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import croma.com.foody.Activities.NavigationActivity;
import croma.com.foody.Activities.RestroActivity;
import croma.com.foody.R;
import croma.com.foody.io.geometry;


/***
 * @author Shambhavi.thakur
 */
public class CustomListAdapter extends BaseAdapter implements View.OnClickListener {

    /***********
     * Declare Used Variables
     *********/
    private Activity activity;
    private ArrayList<geometry> data;
    private static LayoutInflater inflater = null;
    public Resources res;
    geometry tempValues = null;
    int i = 0;

    /*************
     * CustomAdapter Constructor
     *****************/
    public CustomListAdapter(Activity a, ArrayList d, Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data = d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /********
     * What is the size of Passed Arraylist Size
     ************/
    public int getCount() {

        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /*********
     * Create a holder Class to contain inflated xml file elements
     *********/
    public static class ViewHolder {

        public ImageView profilePic;
        public TextView restroNameTextView;
        public TextView restroAddressTextView;


    }

    /******
     * Depends upon data size called for each row , Create each ListView row
     *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.list_row, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.restroNameTextView = (TextView) vi.findViewById(R.id.restroNameTextView);
            holder.restroAddressTextView = (TextView) vi.findViewById(R.id.restroAddressTextView);
            holder.profilePic = (ImageView) vi.findViewById(R.id.profilePic);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if(data.size()>0){
            /***** Get each Model object from Arraylist ********/
            tempValues = null;
            tempValues = data.get(position);

            /************  Set Model values in Holder elements ***********/
            holder.restroNameTextView.setText(tempValues.name);
            holder.restroAddressTextView.setText(tempValues.vicinity);
            /******** Set Item Click Listner for LayoutInflater for each row *******/
            Picasso.with(activity).load(tempValues.icon).resize(150,150).into(holder.profilePic);
            vi.setOnClickListener(new OnItemClickListener(position));
        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

    /*********
     * Called when Item click in ListView
     ************/
    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {

            NavigationActivity sct = (NavigationActivity) activity;
            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
            sct.onItemClick(mPosition);
        }
    }
}
