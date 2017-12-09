package com.example.notepadby.cursproject;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.notepadby.cursproject.constants.Constants;
import com.example.notepadby.cursproject.constants.ListOperations;
import com.example.notepadby.cursproject.entity.ListElement;
import com.example.notepadby.cursproject.fragments.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentManager mFragmentManager;

    public List<String> tabTitles;
    public List<RecyclerViewFragment> fragments;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case Constants.REQUEST_CODE_LIST_UPDATED:
                Log.i(Constants.APP_LOGS, getString(R.string.ad_created_callback));
                ListOperations.updateLists();
                fragments.forEach(RecyclerViewFragment::changeData);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListOperations.addTestElements();
        ListOperations.updateLists();

        fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fragments.add(new RecyclerViewFragment()); // Them'll be replaced in 2 ms
        }
        tabTitles = new ArrayList<>();
        tabTitles.add(getString(R.string.past));
        tabTitles.add(getString(R.string.today));
        tabTitles.add(getString(R.string.soon));
        tabTitles = Collections.unmodifiableList(tabTitles);

        mFragmentManager = getSupportFragmentManager();

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(mFragmentManager) {
            @Override
            public Fragment getItem(int position) {
                List<ListElement> elements;
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
                    default:
                        elements = Constants.todaysList;
                }
                RecyclerViewFragment fragment = new RecyclerViewFragment();
                fragment.setElements(elements);
                fragments.set(position, fragment);
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return tabTitles.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitles.get(position);
            }
        });
        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
