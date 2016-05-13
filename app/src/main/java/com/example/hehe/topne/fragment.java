package com.example.hehe.topne;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import java.util.List;

/**
 * Created by hehe on 2016/4/22.
 */
public class fragment extends FragmentPagerAdapter{
    private List<String> list;
    private List<Fragment> fragments;
    public fragment(FragmentManager f, List<String> list, List<Fragment> fragments, Context context){
        super(f);
       // Toast.makeText(context,"123context",Toast.LENGTH_LONG).show();
        this.list=list;
        this.fragments=fragments;
    }
    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
