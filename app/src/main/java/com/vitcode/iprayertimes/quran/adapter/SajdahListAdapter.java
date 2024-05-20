package com.vitcode.iprayertimes.quran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.quran.model.SajdahModel;

import java.util.ArrayList;

public abstract class SajdahListAdapter extends RecyclerView.Adapter<SajdahListAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<SajdahModel> sajdahModels;

    public abstract void OnItemClick(int i);

    public SajdahListAdapter(Context context2, ArrayList<SajdahModel> arrayList) {
        this.context = context2;
        this.sajdahModels = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.sajdah_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String surahName = this.sajdahModels.get(i).getSurahName();
        int ayahNo = this.sajdahModels.get(i).getAyahNo();
        viewHolder.tvSajdaName.setText(surahName);
        if (surahName.equals("Al-Fatihah") || surahName.equals("At-Taubah")) {
            TextView textView = viewHolder.tvAyahNo;
            textView.setText("" + (ayahNo + 1));
            return;
        }
        TextView textView2 = viewHolder.tvAyahNo;
        textView2.setText("" + ayahNo);
        TextView textView = viewHolder.tvPosition;
        textView.setText("" + (i + 1) );
    }

    public int getItemCount() {
        return this.sajdahModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAyahNo;
        public TextView tvSajdaName;
        public TextView tvPosition;

        ViewHolder(View view) {
            super(view);
            this.tvSajdaName = (TextView) view.findViewById(R.id.tv_sajda_name);
            this.tvAyahNo = (TextView) view.findViewById(R.id.tv_aya_no);
            this.tvPosition = (TextView) view.findViewById(R.id.tv_position);

            view.setOnClickListener(view1 -> SajdahListAdapter.this.OnItemClick(ViewHolder.this.getAdapterPosition()));
        }
    }
}
