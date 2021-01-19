package com.example.lulutong.ui.BottomNavActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.lulutong.R;
import com.example.lulutong.ui.BottomNavActivity.BusinessHall.BusinessHallFragment;
import com.example.lulutong.ui.BottomNavActivity.HomePage.HomePageFragment;
import com.example.lulutong.ui.BottomNavActivity.MyBusiness.MyBusinessFragment;
import com.example.lulutong.ui.BottomNavActivity.PersonalCenter.PersonalCenterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class ButtomNavActivity extends AppCompatActivity {
    ViewPager viewPager;
    MenuItem menuItem;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttom_nav);

        viewPager = findViewById(R.id.viewpager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.homepage:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.business_hall:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.my_business:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.personal_center:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new HomePageFragment());
        adapter.addFragment(new BusinessHallFragment());
        adapter.addFragment(new MyBusinessFragment());
        adapter.addFragment(new PersonalCenterFragment());
        viewPager.setAdapter(adapter);
    }


}