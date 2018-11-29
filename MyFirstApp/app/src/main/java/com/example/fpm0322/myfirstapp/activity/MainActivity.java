package com.example.fpm0322.myfirstapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.fpm0322.myfirstapp.R;
import com.example.fpm0322.myfirstapp.adapter.MyFragmentPagerAdapter;
import com.example.fpm0322.myfirstapp.fragment.main.HomeFragment;
import com.example.fpm0322.myfirstapp.fragment.main.HotFragment;
import com.example.fpm0322.myfirstapp.fragment.main.MessageFragment;
import com.example.fpm0322.myfirstapp.fragment.main.UserFragment;
import com.example.fpm0322.myfirstapp.utils.ViewPagerSlide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    @BindView(R.id.ll_fragments)
    LinearLayout llFragments;

    private HomeFragment homeFragment;
    private HotFragment hotFragment;
    private MessageFragment messageFragment;
    private UserFragment userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initFragment();
    }

    public void initView() {
        TextBadgeItem textBadgeItem = new TextBadgeItem();
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.shouye, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.hot, "热门"))
                .addItem(new BottomNavigationItem(R.mipmap.message, "消息").setBadgeItem(textBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.my, "我的"))
                .setFirstSelectedPosition(0);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar.setBarBackgroundColor(R.color.color_light_gray);
        bottomNavigationBar.setActiveColor(R.color.color_green);
        bottomNavigationBar.setInActiveColor(R.color.color_black);
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        addFragment(homeFragment, "HomeFragment");
                        showFragment(homeFragment);
                        break;
                    case 1:
                        addFragment(hotFragment, "HotFragment");
                        showFragment(hotFragment);
                        break;
                    case 2:
                        addFragment(messageFragment, "MessageFragment");
                        showFragment(messageFragment);
                        break;
                    case 3:
                        addFragment(userFragment, "UserFragment");
                        showFragment(userFragment);
                        break;
                    default:
                        addFragment(homeFragment, "HomeFragment");
                        showFragment(homeFragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
            }
        });
        bottomNavigationBar.initialise();

        textBadgeItem.setText("9") //显示的文本
                .setBackgroundColor(R.color.color_green) //背景色
                .setTextColor("#FFFFFF") //文本颜色
                .setBorderColor(R.color.color_green) //border颜色
                .setBorderWidth(5) //border宽度px
                .setBackgroundColorResource(R.color.colorPrimaryDark) //背景色，资源文件获取
                .setBorderColorResource(R.color.colorPrimary) //border颜色，资源文件获取
                .setTextColorResource(R.color.colorAccent) //文本颜色，资源文件获取
                .setAnimationDuration(30) //隐藏和展示的动画速度，单位毫秒,和setHideOnSelect一起使用
                .setGravity(Gravity.RIGHT | Gravity.TOP) //位置，默认右上角
                .setHideOnSelect(true); //true：当选中状态时消失，非选中状态显示,moren false

    }

    public void initFragment() {
        homeFragment = new HomeFragment();
        hotFragment = new HotFragment();
        messageFragment = new MessageFragment();
        userFragment = new UserFragment();
        addFragment(homeFragment, "HomeFragment");
        showFragment(homeFragment);
    }

    /**
     * 添加所有Fragment
     *
     * @param fragment
     */
    private void addFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.ll_fragments, fragment, tag);
        ft.commitAllowingStateLoss();
    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (hotFragment != null) {
            ft.hide(hotFragment);
        }
        if (messageFragment != null) {
            ft.hide(messageFragment);
        }
        if (userFragment != null) {
            ft.hide(userFragment);
        }
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }
}
