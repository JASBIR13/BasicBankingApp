package com.example.bankingapp.ui;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bankingapp.MainActivity;
import com.example.bankingapp.R;
import com.example.bankingapp.adapters.FromAdapter;
import com.example.bankingapp.util.Util;

import java.util.ArrayList;

public class FromFragment extends Fragment {


    RecyclerView mRecyclerView;
    FromAdapter mUserAdapter;
    private ArrayList<String> phoneNo, name, balance, email;
    public FromFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_from, container, false);
        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                Util.homeFragment = new HomeFragment();
                MainActivity.sFragmentManager.beginTransaction().replace(R.id.layout_container, Util.homeFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        phoneNo = new ArrayList<>();
        name = new ArrayList<>();
        balance = new ArrayList<>();
        email = new ArrayList<>();
        displayData();

        mRecyclerView = view.findViewById(R.id.recyclerFrom);
        mUserAdapter = new FromAdapter(getContext(), phoneNo, name, balance, email);
        mRecyclerView.setAdapter(mUserAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;

    }
    private void displayData() {
        Cursor cursor = Util.db.allData();
        while (cursor.moveToNext()){
            phoneNo.add(cursor.getString(1));
            name.add(cursor.getString(2));
            balance.add(String.valueOf(cursor.getInt(3)));
            email.add(cursor.getString(4));
        }
    }

}