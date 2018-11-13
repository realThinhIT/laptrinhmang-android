package com.example.nttungg.livestream;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nttungg.livestream.adapter.ViewPagerAdapter;

public class HomeActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public static Intent getHomeIntent(Context context) {
        Intent mIntent = new Intent(context, HomeActivity.class);
        return mIntent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
    }

    public void initUI() {
        mTabLayout = findViewById(R.id.tablayout_home);
        mViewPager = findViewById(R.id.viewpager_home);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.drawable.icon_homepage_click);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.icon_direction_click);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.icon_storage_click);
                        break;
                    case 3:
                        tab.setIcon(R.drawable.icon_profile_click);
                        break;
                }
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.drawable.icon_homepage);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.icon_direction);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.icon_storage);
                        break;
                    case 3:
                        tab.setIcon(R.drawable.icon_profile);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setOffscreenPageLimit(4);
    }
}
