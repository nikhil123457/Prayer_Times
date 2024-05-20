package com.vitcode.iprayertimes.calender.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.calender.helper.HGDate;
import com.vitcode.iprayertimes.calender.helper.NumbersLocal;
import com.vitcode.iprayertimes.calender.model.CalendarCellModel;

import java.util.ArrayList;

public abstract class CalenderDateAdapter extends RecyclerView.Adapter<CalenderDateAdapter.ViewHolder> {
    private final ArrayList<CalendarCellModel> calendarCellModels;
    private final Context context;
    private final String[] events = {"1-1", "10-1", "12-3", "27-7", "15-8", "1-9", "1-10", "9-12", "10-12"};

    public abstract void OnItemClick(int i);

    public CalenderDateAdapter(Context context2, ArrayList<CalendarCellModel> arrayList) {
        this.context = context2;
        this.calendarCellModels = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(
                R.layout.item_calender_cell, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        CalendarCellModel calendarCellModel = this.calendarCellModels.get(i);
        if (calendarCellModel.isSelect()) {
            viewHolder.tvDate.setTextColor(Color.parseColor("#ffffff"));
            viewHolder.tvDate.setBackgroundResource(R.drawable.back_oval_play);
        } else {
            viewHolder.tvDate.setTextColor(Color.parseColor("#222222"));
            viewHolder.tvDate.setBackgroundColor(0);
        }
        if (calendarCellModel.getHijriDay() == -1) {
            viewHolder.tvDate.setVisibility(View.GONE);
        } else {
            viewHolder.tvDate.setVisibility(View.VISIBLE);
        }
        String[] strArr = this.events;
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String[] split = strArr[i2].split("-");
            if (calendarCellModel.getHijriDay() == Integer.parseInt(split[0].trim()) && calendarCellModel.getHijriMonth() == Integer.parseInt(split[1].trim())) {
                viewHolder.vEvent.setVisibility(View.VISIBLE);
                break;
            } else {
                viewHolder.vEvent.setVisibility(View.GONE);
                i2++;
            }
        }
        HGDate hGDate = new HGDate();
        if (!(calendarCellModel.getGeorgianMonth() == hGDate.getMonth() && calendarCellModel.getGeorgianDay() == hGDate.getDay() && calendarCellModel.getGeorgianYear() == hGDate.getYear())) {
            int i3 = i % 7;
            if (i3 == 0) {
                viewHolder.tvDate.setTextColor(Color.parseColor("#ffcc0000"));
            }
            if (i3 == 6) {
                viewHolder.tvDate.setTextColor(Color.parseColor("#222222"));
            }
        }
        TextView textView = viewHolder.tvDate;
        Context context2 = this.context;
        textView.setText(NumbersLocal.convertNumberType(context2, calendarCellModel.getGeorgianDay() + ""));
    }

    public int getItemCount() {
        return this.calendarCellModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate;
        public TextView tvDateMuslim;
        public View vEvent;

        public ViewHolder(View view) {
            super(view);
            this.tvDate = (TextView) view.findViewById(R.id.tv_date);
            this.vEvent = view.findViewById(R.id.view_event);
            view.setOnClickListener(view1 -> CalenderDateAdapter.this.OnItemClick(ViewHolder.this.getAdapterPosition()));
        }
    }
}
