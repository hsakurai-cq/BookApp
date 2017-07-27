package com.hiromisakurai.bookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void switchFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Button addButton = (Button)findViewById(R.id.toolbar_button_add);
            TextView title = (TextView)findViewById(R.id.toolbar_main_title);

            switch (item.getItemId()) {
                case R.id.menu_book:
                    BookListFragment bookListFragment = new BookListFragment();
                    switchFragment(bookListFragment);
                    title.setText(R.string.toolbar_title_list);
                    addButton.setVisibility(View.VISIBLE);
                    addButton.setText(R.string.toolbar_button_add);
                    return true;
                case R.id.menu_setting:
                    SettingFragment settingFragment = new SettingFragment();
                    switchFragment(settingFragment);
                    title.setText(R.string.toolbar_title_setting);
                    addButton.setVisibility(View.GONE);
                    return true;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookListFragment bookListFragment = new BookListFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragment_container, bookListFragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button button = (Button) findViewById(R.id.toolbar_button_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), AddBookActivity.class);
                startActivity(intent);
            }
        });
    }
}
