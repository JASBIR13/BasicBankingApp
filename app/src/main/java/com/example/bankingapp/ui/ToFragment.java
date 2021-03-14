package com.example.bankingapp.ui;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bankingapp.MainActivity;
import com.example.bankingapp.R;
import com.example.bankingapp.adapters.ToAdapter;
import com.example.bankingapp.util.Util;

import java.util.ArrayList;

public class ToFragment extends Fragment {


    RecyclerView mRecyclerView;
    ToAdapter mUserAdapter;

    private ArrayList<String> phoneNo, name, balance, email;
    public ToFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_to, container, false);
        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                Util.homeFragment = new FromFragment();
                MainActivity.sFragmentManager.beginTransaction().replace(R.id.layout_container, Util.homeFragment).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);


        phoneNo = new ArrayList<>();
        name = new ArrayList<>();
        balance = new ArrayList<>();
        email = new ArrayList<>();
        displayData();

        mRecyclerView = view.findViewById(R.id.recyclerTo);
        mUserAdapter = new ToAdapter(getContext(), phoneNo, name, balance, email);
        mRecyclerView.setAdapter(mUserAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;

    }
    private void displayData() {
        Cursor cursor = Util.db.allData();
        Bundle bundle = this.getArguments();
        String data = bundle.getString("FROM");
        Log.d("TAG", "displayData: "+data);
        while (cursor.moveToNext()){
            if (cursor.getString(2).equals(data)){
                cursor.move(0);
            }else {
                phoneNo.add(cursor.getString(1));
                name.add(cursor.getString(2));
                balance.add(String.valueOf(cursor.getInt(3)));
                email.add(cursor.getString(4));
            }
        }
    }

}