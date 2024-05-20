package com.vitcode.iprayertimes.calender.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.calender.activity.EventDetailActivity;
import com.vitcode.iprayertimes.calender.helper.Dates;
import com.vitcode.iprayertimes.calender.helper.HGDate;
import com.vitcode.iprayertimes.calender.helper.NumbersLocal;
import com.vitcode.iprayertimes.calender.model.EventModel;

import java.util.ArrayList;

public class EventDetailAdapter extends RecyclerView.Adapter<EventDetailAdapter.ViewHolder> {
    public Activity activity;
    public Context context;
    public ArrayList<EventModel> eventModels;

    public EventDetailAdapter(Context context2, Activity activity2, ArrayList<EventModel> arrayList) {
        this.context = context2;
        this.activity = activity2;
        this.eventModels = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_event, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        EventModel eventModel = this.eventModels.get(i);
        String[] split = eventModel.getHejriDate().split("/");
        HGDate hGDate = new HGDate();
        hGDate.setHigri(Integer.valueOf(split[2]).intValue(), Integer.valueOf(split[1]).intValue(), Integer.valueOf(split[0]).intValue());
        viewHolder.tvTitle.setText(eventModel.getEventName());
        TextView textView = viewHolder.tvDateMuslim;
        textView.setText(NumbersLocal.convertNumberType(this.context, split[0]) + " " + Dates.islamicMonthName(this.context, Integer.valueOf(split[1]).intValue() - 1) + " " + NumbersLocal.convertNumberType(this.context, split[2]));
        hGDate.toGregorian();
        TextView textView2 = viewHolder.tvDate;
        StringBuilder sb = new StringBuilder();
        Context context2 = this.context;
        sb.append(NumbersLocal.convertNumberType(context2, hGDate.getDay() + ""));
        sb.append(" ");
        sb.append(Dates.gregorianMonthName(this.context, hGDate.getMonth() - 1));
        sb.append(" ");
        Context context3 = this.context;
        sb.append(NumbersLocal.convertNumberType(context3, hGDate.getYear() + ""));
        textView2.setText(sb.toString());
    }

    public int getItemCount() {
        return this.eventModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate;
        public TextView tvDateMuslim;
        public TextView tvTitle;

        public ViewHolder(View view) {
            super(view);
            this.tvDate = (TextView) view.findViewById(R.id.tv_date);
            this.tvDateMuslim = (TextView) view.findViewById(R.id.tv_date_muslim);
            this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            view.setOnClickListener(view1 -> {
                EventDetailActivity.setEvent(EventDetailAdapter.this.eventModels.get(ViewHolder.this.getAdapterPosition()));
                EventDetailAdapter.this.context.startActivity(new Intent(EventDetailAdapter.this.context, EventDetailActivity.class));
            });
        }
    }
}
