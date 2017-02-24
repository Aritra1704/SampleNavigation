package com.arpaul.samplenavigation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arpaul.samplenavigation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aritra on 24-02-2017.
 */

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder> {

    private Context context;
    private List<String> arrLead = new ArrayList<>();

    public SecondAdapter(Context context, List<String> arrDashboard) {
        this.context = context;
        this.arrLead = arrDashboard;
    }

    public void refresh(List<String> arrFarms) {
        this.arrLead = arrFarms;
        notifyDataSetChanged();
    }

    @Override
    public SecondAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_lead, parent, false);
        return new SecondAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SecondAdapter.ViewHolder holder, final int position) {
        final String objLeadDO = arrLead.get(position);

        holder.tvLead.setText(objLeadDO);

        holder.tvLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mView.performClick();
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        if(arrLead != null)
            return arrLead.size();

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView tvLead;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            tvLead             = (TextView) view.findViewById(R.id.tvLead);
        }

        @Override
        public String toString() {
            return "";
        }
    }
}
