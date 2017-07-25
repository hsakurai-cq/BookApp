package com.hiromisakurai.bookapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new BookListFragment(), "Book List");
        viewPagerAdapter.addFragments(new SettingFragment(), "Setting");
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Log.i("page selected", String.valueOf(position));
                TextView title = (TextView) findViewById(R.id.toolbar_main_title);
                Button addButton = (Button) findViewById(R.id.button_add);
                if (position == 1) {
                    title.setText("Setting");
                    addButton.setVisibility(View.GONE);
                } else {
                    title.setText("Book List");
                    addButton.setVisibility(View.VISIBLE);
                }

            }
        });
        tabLayout.setupWithViewPager(viewPager);

    }
}
