package com.example.bankingapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapp.MainActivity;
import com.example.bankingapp.R;
import com.example.bankingapp.ui.TransferFragment;
import com.example.bankingapp.util.Util;

import java.util.ArrayList;

public class ToAdapter extends RecyclerView.Adapter<ToAdapter.MyViewHolder> {

    Context context;
    private ArrayList<String> phoneNo, name, balance, email;
    static int oldPosition = -1;
    private Handler mHandler = new Handler();
    private TypedValue mTypedValue = new TypedValue();

    public ToAdapter(Context context, ArrayList<String> phoneNo, ArrayList<String> name, ArrayList<String> balance, ArrayList<String> email) {
        this.context = context;
        this.phoneNo = phoneNo;
        this.name = name;
        this.balance = balance;
        this.email = email;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.to_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToAdapter.MyViewHolder holder, int position) {
        context.getTheme().resolveAttribute(R.attr.colorSurface, mTypedValue, true);
        holder.mCardView.setCardBackgroundColor(mTypedValue.data);
        holder.constraintDown.setVisibility(View.GONE);
        holder.mCardView.setCardElevation(0f);
        holder.tvName.setText(String.valueOf(name.get(position)));
        holder.tvBalance.setText("Rs "+String.valueOf(balance.get(position)));
        holder.tvPhoneNo.setText(String.valueOf(phoneNo.get(position)));
        holder.tvEmail.setText(String.valueOf(email.get(position)));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.constraintDown.getVisibility()==View.GONE) {
                    if (holder.getAdapterPosition() != oldPosition){
                        if (oldPosition == -1){
                            oldPosition = holder.getAdapterPosition();
                        }else {
                            notifyItemChanged(oldPosition);
                        }
                    }
                    mHandler.postDelayed(new Runnable() {
                        public void run() {
                            context.getTheme().resolveAttribute(R.attr.colorBackgroundFloating, mTypedValue, true);
                            TransitionManager.beginDelayedTransition(holder.mCardView, new Fade().setDuration(500));
                            holder.constraintDown.setVisibility(View.VISIBLE);
                            holder.mCardView.setCardBackgroundColor(mTypedValue.data);
                            holder.mCardView.setCardElevation(15f);
                            holder.mCardView.setRadius(20f);
                        }
                    }, 90);
                    oldPosition = holder.getAdapterPosition();
                }
                else {
                    context.getTheme().resolveAttribute(R.attr.colorSurface, mTypedValue, true);
                    holder.mCardView.setCardBackgroundColor(mTypedValue.data);
                    TransitionManager.beginDelayedTransition(holder.mCardView, new Fade().setDuration(200));
                    holder.constraintDown.setVisibility(View.GONE);
                    holder.mCardView.setCardElevation(0f);
                }
            }
        });

        holder.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("To", String.valueOf(name.get(position)));
                Util.sFromTo.setTo(String.valueOf(name.get(position)));
                Util.sFromTo.setToBalance(String.valueOf(balance.get(position)));
                Util.sFromTo.setToEmail(String.valueOf(email.get(position)));
                Util.sFromTo.setToPhoneNo(String.valueOf(phoneNo.get(position)));
                Util.homeFragment = new TransferFragment();
                Util.homeFragment.setArguments(bundle);
                MainActivity.sFragmentManager.beginTransaction().replace(R.id.layout_container, Util.homeFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return phoneNo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvBalance,tvPhoneNo,tvEmail;
        Button btnDown;
        CardView mCardView;
        ConstraintLayout constraintDown;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            constraintDown = itemView.findViewById(R.id.constraintToDown);
            btnDown = itemView.findViewById(R.id.btnToDown);
            tvPhoneNo = itemView.findViewById(R.id.tvToPhoneNo);
            tvEmail = itemView.findViewById(R.id.tvToEmail);
            tvBalance = itemView.findViewById(R.id.tvToBalance);
            tvName = itemView.findViewById(R.id.tvToName);
            mCardView = itemView.findViewById(R.id.cardViewTo);

        }
    }
}
