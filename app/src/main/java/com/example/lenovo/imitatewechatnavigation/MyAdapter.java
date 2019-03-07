package com.example.lenovo.imitatewechatnavigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> mFragments;
    public MyAdapter(FragmentManager fm,ArrayList<Fragment> fragments){
        super(fm);
        mFragments=fragments;//传递碎片内容
    }

    public Fragment getItem(int position){return mFragments.get(position);}

    public int getCount(){return mFragments.size();}
}
