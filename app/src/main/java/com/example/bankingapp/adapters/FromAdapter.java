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
import com.example.bankingapp.ui.ToFragment;
import com.example.bankingapp.util.Util;

import java.util.ArrayList;

public class FromAdapter extends RecyclerView.Adapter<FromAdapter.MyViewHolder> {
    Context context;
    private ArrayList<String> phoneNo, name, balance, email;
    static int oldPosition = -1;
    private Handler mHandler = new Handler();
    private TypedValue mTypedValue = new TypedValue();

    public FromAdapter(Context context, ArrayList<String> phoneNo, ArrayList<String> name, ArrayList<String> balance, ArrayList<String> email) {
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
        View view = inflater.inflate(R.layout.from_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FromAdapter.MyViewHolder holder, int position) {
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
                bundle.putString("FROM", String.valueOf(name.get(position)));
                Util.sFromTo.setFrom(String.valueOf(name.get(position)));
                Util.sFromTo.setFromBalance(String.valueOf(balance.get(position)));
                Util.sFromTo.setFromEmail(String.valueOf(email.get(position)));
                Util.sFromTo.setFromPhoneNo(String.valueOf(phoneNo.get(position)));
                Util.homeFragment = new ToFragment();
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

            constraintDown = itemView.findViewById(R.id.constraintFromDown);
            btnDown = itemView.findViewById(R.id.btnFromDown);
            tvPhoneNo = itemView.findViewById(R.id.tvFromPhoneNo);
            tvEmail = itemView.findViewById(R.id.tvFromEmail);
            tvBalance = itemView.findViewById(R.id.tvFromBalance);
            tvName = itemView.findViewById(R.id.tvFromName);
            mCardView = itemView.findViewById(R.id.cardViewFrom);

        }
    }
}
