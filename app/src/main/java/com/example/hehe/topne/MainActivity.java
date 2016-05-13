package com.example.hehe.topne;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import  fragment.*;
public class MainActivity extends Fragment implements TabLayout.OnTabSelectedListener {
    private TabLayout t;
    private ViewPager v;
    private List<String> list=new ArrayList<>();
    private  List<Fragment> fragments=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.activity_main,container,false);

        t=(TabLayout)view.findViewById(R.id.tl);
        v=(ViewPager)view.findViewById(R.id.viewpager) ;
        //v.setOffscreenPageLimit(6);
        init();
        // net();
        fragment f=new fragment(getFragmentManager(),list,fragments,getActivity());
        v.setAdapter(f);

        t.setupWithViewPager(v);
        t.setTabsFromPagerAdapter(f);
        t.setScrollPosition(0,1f,false);
        t.setOnTabSelectedListener(this);
        return view;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        net();
//        t=(TabLayout)findViewById(R.id.tl);
//        v=(ViewPager)findViewById(R.id.viewpager) ;
//        v.setOffscreenPageLimit(6);
//        init();
//       // net();
//        fragment f=new fragment(getSupportFragmentManager(),list,fragments);
//        v.setAdapter(f);
//        t.setupWithViewPager(v);
//        t.setTabsFromPagerAdapter(f);
//
//    }
    private void init(){
        list.add("国际");
        list.add("国内");
        list.add("军事");
        list.add("社会");
        list.add("台海");
        list.add("互联网");
        t.addTab(t.newTab().setText(list.get(0)));
        t.addTab(t.newTab().setText(list.get(1)));
        t.addTab(t.newTab().setText(list.get(2)));
        t.addTab(t.newTab().setText(list.get(3)));
        t.addTab(t.newTab().setText(list.get(4)));
        t.addTab(t.newTab().setText(list.get(5)));
        fragments.add(new fr1());
        fragments.add(new fr2());
        fragments.add(new fr3());
        fragments.add(new fr4());
        fragments.add(new fr5());
        fragments.add(new fr6());
        //Toast.makeText(getActivity(),"hahaha",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //点击tab标签跳转到指定位置
        v.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
//    private void net(){
//        Context c=getApplicationContext();
//        ConnectivityManager connectivityManager = (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
//       NetworkInfo info=connectivityManager.getActiveNetworkInfo();
//        if(info!=null){
//        if(info.isAvailable() || info.isConnected()){
//            Toast.makeText(this,"have net",Toast.LENGTH_LONG).show();
//            }
//        }
//        else{
//            Toast.makeText(this,"not net",Toast.LENGTH_LONG).show();
//        }
//        }



}
