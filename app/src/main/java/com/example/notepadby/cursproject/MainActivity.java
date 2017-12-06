package com.example.notepadby.cursproject;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.notepadby.cursproject.constants.Constants;
import com.example.notepadby.cursproject.constants.ConstantsClass;
import com.example.notepadby.cursproject.entity.ListElement;
import com.example.notepadby.cursproject.fragments.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentManager mFragmentManager;

    public static String[] tabTitles;
    public static List<ListElement> currentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstantsClass.addElements();

        tabTitles = new String[3];
        tabTitles[0] = getString(R.string.past);
        tabTitles[1] = getString(R.string.today);
        tabTitles[2] = getString(R.string.soon);
        mFragmentManager = getSupportFragmentManager();

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(mFragmentManager) {
            @Override
            public Fragment getItem(int position) {
                List<ListElement> elements = new ArrayList<>();
                switch (position) {
                    case 0:
                        elements = Constants.pastList;
                        break;
                    case 1:
                        elements = Constants.todaysList;
                        break;
                    case 2:
                        elements = Constants.soonList;
                        break;
                }
                RecyclerViewFragment fragment = new RecyclerViewFragment();
                fragment.setElements(elements);
                return fragment;
            }

            @Override
            public int getCount() {
                return tabTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitles[position];
            }
        });
        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
