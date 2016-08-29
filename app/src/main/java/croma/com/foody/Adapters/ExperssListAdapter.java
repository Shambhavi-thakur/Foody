package croma.com.foody.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import croma.com.foody.R;

/**
 * Created by muadmin on 8/29/16.
 */
public class ExperssListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> mArrayList;
    private LayoutInflater inflter;

    public ExperssListAdapter(Context applicationContext, ArrayList<String> mArrayList) {
        this.context = applicationContext;
        this.mArrayList = mArrayList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.express_list_row, null);
        TextView textView = (TextView) view.findViewById(R.id.experssMenu);
        textView.setText(mArrayList.get(i));
        return view;
    }
}
