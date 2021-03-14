package com.example.bankingapp.ui;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bankingapp.R;
import com.example.bankingapp.adapters.HistoryAdapter;
import com.example.bankingapp.util.Util;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    RecyclerView mRecyclerView;
    HistoryAdapter mHistoryAdapter;
    private ArrayList<String> from, to, date, amount;
    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        from = new ArrayList<>();
        to = new ArrayList<>();
        date = new ArrayList<>();
        amount = new ArrayList<>();
        displayData();

        mRecyclerView = view.findViewById(R.id.recyclerHistory);
        mHistoryAdapter = new HistoryAdapter(getContext(), from, to, date, amount);
        mRecyclerView.setAdapter(mHistoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;

    }

    private void displayData() {
        Cursor cursor = Util.db.allHistoryData();
        while (cursor.moveToNext()){
            date.add(cursor.getString(1));
            from.add(cursor.getString(2));
            to.add(cursor.getString(3));
            amount.add(String.valueOf(cursor.getInt(4)));
        }
    }
}