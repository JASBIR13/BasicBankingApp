package com.example.bankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bankingapp.data.DatabaseHandler;
import com.example.bankingapp.ui.HistoryFragment;
import com.example.bankingapp.ui.HomeFragment;
import com.example.bankingapp.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager sFragmentManager;
    private BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.db = new DatabaseHandler(getApplicationContext());

        sFragmentManager = getSupportFragmentManager();


        bnv = findViewById(R.id.bottomNavigation);
        if (savedInstanceState == null) {
            Util.homeFragment = new HomeFragment();
            sFragmentManager.beginTransaction().replace(R.id.layout_container, new HomeFragment()).commit();
        }

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;
                switch (item.getItemId()){
                    case R.id.home_icon:
                        temp = Util.homeFragment;
                        break;
                    case R.id.call_icon:
                        temp = new HistoryFragment();
                        break;
                }
                sFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_fragment, R.anim.exit_fragment).replace(R.id.layout_container, temp).commit();
                return true;
            }
        });
    }
}