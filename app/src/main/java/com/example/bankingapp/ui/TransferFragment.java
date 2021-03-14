package com.example.bankingapp.ui;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankingapp.MainActivity;
import com.example.bankingapp.R;
import com.example.bankingapp.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransferFragment extends Fragment {

    private TextInputEditText etTransfer;
    private Button btnTransfer;
    private TextView textViewFrom, textViewTo, textViewFromBalance, textViewFromPhoneNo, textViewFromEmail, textViewToBalance, textViewToPhoneNo, textViewToEmail;
    public TransferFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transfer, container, false);

        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                Util.homeFragment = new HomeFragment();
                MainActivity.sFragmentManager.beginTransaction().replace(R.id.layout_container, Util.homeFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        textViewFrom = view.findViewById(R.id.textViewFrom);
        textViewTo = view.findViewById(R.id.textViewTo);
        textViewFromBalance = view.findViewById(R.id.textViewFromBalance);
        textViewFromEmail = view.findViewById(R.id.textViewFromEmail);
        textViewFromPhoneNo = view.findViewById(R.id.textViewFromPhoneNo);
        textViewToBalance = view.findViewById(R.id.textViewToBalance);
        textViewToEmail = view.findViewById(R.id.textViewToEmail);
        textViewToPhoneNo = view.findViewById(R.id.textViewToPhoneNo);
        etTransfer = view.findViewById(R.id.etTransfer);
        btnTransfer = view.findViewById(R.id.btnTransfer);

        String from = Util.sFromTo.getFrom();
        String to = Util.sFromTo.getTo();
        String fromBalance = Util.sFromTo.getFromBalance();
        String fromPhoneNo = Util.sFromTo.getFromPhoneNo();
        String fromEmail = Util.sFromTo.getFromEmail();
        String toBalance = Util.sFromTo.getToBalance();
        String toPhoneNo = Util.sFromTo.getToPhoneNo();
        String toEmail = Util.sFromTo.getToEmail();

        textViewFrom.setText(from);
        textViewTo.setText(to);
        textViewFromBalance.setText("Rs. "+fromBalance);
        textViewFromPhoneNo.setText("+91"+fromPhoneNo);
        textViewFromEmail.setText(fromEmail);
        textViewToBalance.setText("Rs. "+toBalance);
        textViewToPhoneNo.setText("+91"+toPhoneNo);
        textViewToEmail.setText(toEmail);

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etTransfer.getText().toString().trim())){
                    etTransfer.setError("Cannot put empty value");

                }else if(Integer.parseInt(String.valueOf(etTransfer.getText())) == 0){
                    etTransfer.setError("Cannot put zero as the value");
                }else if(Integer.parseInt(String.valueOf(etTransfer.getText())) > Integer.parseInt(fromBalance) ){
                    etTransfer.setError("Don't have enough balance");
                }else {
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    String date = df.format(c.getTime());
                    int amount = Integer.parseInt(String.valueOf(etTransfer.getText()));
                    int newFromBalance = Integer.parseInt(fromBalance) - amount;
                    int newToBalance = Integer.parseInt(toBalance) + amount;
                    SQLiteDatabase database = Util.db.getWritableDatabase();
                    Util.db.addTransferHistory(date, from, to, amount, database);
                    Util.db.updateFromBalance(from, newFromBalance, database);
                    Util.db.updateToBalance(to, newToBalance, database);
                    Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
                    Util.homeFragment = new HomeFragment();
                    MainActivity.sFragmentManager.beginTransaction().replace(R.id.layout_container, Util.homeFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

                }

            }
        });
        return view;
    }
}