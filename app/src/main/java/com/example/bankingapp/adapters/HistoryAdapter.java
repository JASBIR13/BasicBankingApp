package com.example.bankingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingapp.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    Context context;
    private ArrayList<String> from, to, date, amount;

    public HistoryAdapter(Context context, ArrayList<String> from, ArrayList<String> to, ArrayList<String> date, ArrayList<String> amount) {
        this.context = context;
        this.from = from;
        this.to = to;
        this.date = date;
        this.amount = amount;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvHistoryFrom.setText(String.valueOf(from.get(position)));
        holder.tvHistoryTo.setText(String.valueOf(to.get(position)));
        holder.tvHistoryDate.setText(String.valueOf(date.get(position)));
        holder.tvHistoryAmount.setText("Rs. "+String.valueOf(amount.get(position)));
    }

    @Override
    public int getItemCount() {
        return from.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvHistoryFrom, tvHistoryTo, tvHistoryDate, tvHistoryAmount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHistoryFrom = itemView.findViewById(R.id.tvHistoryFrom);
            tvHistoryTo = itemView.findViewById(R.id.tvHistoryTo);
            tvHistoryDate = itemView.findViewById(R.id.tvHistoryDate);
            tvHistoryAmount = itemView.findViewById(R.id.tvHistoryAmount);
        }
    }
}
