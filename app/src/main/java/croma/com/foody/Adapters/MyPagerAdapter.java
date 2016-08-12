package croma.com.foody.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import croma.com.foody.Fragments.LetsStartFragment;
import croma.com.foody.Fragments.LocatemeFragment;

/**
 * @author Shambhavi.Thakur
 *          MyPagerAdapter
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {



    public MyPagerAdapter(FragmentManager fm) { super(fm); }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LetsStartFragment tab1 = new LetsStartFragment();
                return tab1;
            case 1:
                LocatemeFragment tab2 = new LocatemeFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
