package com.example.lenovo.imitatewechatnavigation;

import android.animation.ArgbEvaluator;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.imitatewechatnavigation.fragment.CategoryFragment;
import com.example.lenovo.imitatewechatnavigation.fragment.FindFragment;
import com.example.lenovo.imitatewechatnavigation.fragment.HomeFragment;
import com.example.lenovo.imitatewechatnavigation.fragment.MineFragment;
import com.example.lenovo.imitatewechatnavigation.view.MyImageView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mViewPager;
    private MyImageView mIvHome;//消息的imageview
    private TextView mTvHome;//消息的textview

    private MyImageView mIvCategory;//通讯录的imageview
    private TextView mTvCategory;

    private MyImageView mIvFind;//发现的imageview
    private TextView mTvFind;

    private MyImageView mIvMine;//我的imageview
    private TextView mTvMine;

    private ArrayList<Fragment> mFragments;
    private ArgbEvaluator mColorEvaluator;

    private int mTextNormalColor;//未选中的字体颜色
    private int mTextSelectedColor;//选中的字体颜色
    private LinearLayout mLinearLayoutHome;
    private LinearLayout mLinearLayoutCategory;
    private LinearLayout mLinearLayoutFind;
    private LinearLayout mLinearLayoutMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initColor();//选中未选中的textview的color
        initView();//初始化控件
        initData();//初始化数据
        initSelectImage();//初始化渐变的图片
        aboutViewpager();//关于viewpager
        setListener();//viewpager设置滑动监听
    }

    private void initSelectImage(){
        mIvHome.setImages(R.drawable.home_normal,R.drawable.home_selected);
        mIvCategory.setImages(R.drawable.category_normal,R.drawable.category_selected);
        mIvFind.setImages(R.drawable.find_normal,R.drawable.find_selected);
        mIvMine.setImages(R.drawable.mine_normal,R.drawable.mine_selected);
    }

    private void initColor(){//初始化导航图标的颜色
        mTextNormalColor=getResources().getColor(R.color.main_bottom_tab_textcolor_normal);//未被选中的颜色
        mTextSelectedColor=getResources().getColor(R.color.main_bottom_tab_textcolor_selected);//被选中的颜色
    }

    private void setListener(){//设置点击监听器
        mLinearLayoutHome.setOnClickListener(this);
        mLinearLayoutCategory.setOnClickListener(this);
        mLinearLayoutFind.setOnClickListener(this);
        mLinearLayoutMine.setOnClickListener(this);



        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){//被点击的导航选项颜色改变

            public void onPageScrolled(int position,float positionOffset,int positionOffsetPs){
                setTabTextColorAndImageView(position,positionOffset);//更改text的颜色还有图片
            }

            public void onPageSelected(int position){
            }

            public void onPageScrollStateChanged(int state){}
        });
    }

    private void setTabTextColorAndImageView(int position,float positionOffset){
        mColorEvaluator=new ArgbEvaluator();//根据偏移量来得到
        int evaluateCurrent=(int)mColorEvaluator.evaluate(positionOffset,mTextSelectedColor,mTextNormalColor);
        //当前的颜色值

        int evaluateThe=(int)mColorEvaluator.evaluate(positionOffset,mTextNormalColor,mTextSelectedColor);
        //将要的颜色值

        switch (position){
            case 0:
                mTvHome.setTextColor(evaluateCurrent);//设置消息的字体的颜色
                mTvCategory.setTextColor(evaluateThe);//设置通讯录的字体颜色

                mIvHome.transformPage(positionOffset);//设置消息的图片
                mIvCategory.transformPage(1-positionOffset);//设置通讯录的图片
                break;
            case 1:
                mTvCategory.setTextColor(evaluateCurrent);
                mTvFind.setTextColor(evaluateThe);

                mIvCategory.transformPage(positionOffset);
                mIvFind.transformPage(1-positionOffset);
                break;
            case 2:
                mTvFind.setTextColor(evaluateCurrent);
                mTvMine.setTextColor(evaluateThe);

                mIvFind.transformPage(positionOffset);
                mIvMine.transformPage(1-positionOffset);
                break;
        }
    }

    private void initData(){//初始化“碎片”内容显示
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new CategoryFragment());
        mFragments.add(new FindFragment());
        mFragments.add(new MineFragment());
    }

    private void aboutViewpager(){
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(), mFragments);// 初始化adapter
        mViewPager.setAdapter(myAdapter); // 设置adapter
    }

    private void initView(){
        mLinearLayoutHome = (LinearLayout) findViewById(R.id.ll_home);
        mLinearLayoutCategory = (LinearLayout) findViewById(R.id.ll_category);
        mLinearLayoutFind = (LinearLayout) findViewById(R.id.ll_find);
        mLinearLayoutMine = (LinearLayout) findViewById(R.id.ll_mine);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mIvHome = (MyImageView) findViewById(R.id.iv1);  //  微信 imageview
        mTvHome = (TextView) findViewById(R.id.rb1);  //    微信的字

        mIvCategory = (MyImageView) findViewById(R.id.iv2); //  通信录 imageview
        mTvCategory = (TextView) findViewById(R.id.rb2);  //    通信录 字

        mIvFind = (MyImageView) findViewById(R.id.iv3); //  发现 imageview
        mTvFind = (TextView) findViewById(R.id.rb3);   //    发现 字

        mIvMine = (MyImageView) findViewById(R.id.iv4);   //  我 imageview
        mTvMine = (TextView) findViewById(R.id.rb4);    //    我 字
    }

    public void onClick(View view){//导航栏的点击事件
        switch (view.getId()) {
            case R.id.ll_home:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.ll_category:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.ll_find:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.ll_mine:
                mViewPager.setCurrentItem(3);
                break;
        }
    }
}
