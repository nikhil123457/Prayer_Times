package com.vitcode.iprayertimes.quran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.quran.model.SuraModel;

import java.util.ArrayList;

public abstract class SuraListAdapter extends RecyclerView.Adapter<SuraListAdapter.ViewHolder> {
    private final Context context;
    public ArrayList<SuraModel> suraModels;

    public abstract void OnItemClick(SuraModel suraModel);

    public SuraListAdapter(Context context2, ArrayList<SuraModel> arrayList) {
        this.context = context2;
        this.suraModels = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.sura_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        TextView textView = viewHolder.tvSurahNo;
        textView.setText("" + (this.suraModels.get(i).getItemPosition() + 1));
        TextView textView2 = viewHolder.tvMakkiMadni;
        textView2.setText(this.suraModels.get(i).getPlaceOfRevelation().trim() + ", Juz: " + this.suraModels.get(i).getParaIndex());
        viewHolder.tvNameArabic.setText(this.suraModels.get(i).getArabicSurahName());
        viewHolder.tvNameEnglish.setText(this.suraModels.get(i).getEngSurahName());
        TextView textView3 = viewHolder.tvVerses;
        textView3.setText("Verses : " + this.suraModels.get(i).getTotalVerses() + ", ");
    }

    public int getItemCount() {
        return this.suraModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvMakkiMadni;
        public TextView tvNameArabic;
        public TextView tvNameEnglish;
        public TextView tvSurahNo;
        public TextView tvVerses;

        ViewHolder(View view) {
            super(view);
            this.tvSurahNo = (TextView) view.findViewById(R.id.tv_surah_no);
            this.tvMakkiMadni = (TextView) view.findViewById(R.id.maki_madni);
            this.tvNameArabic = (TextView) view.findViewById(R.id.arabic_name);
            this.tvNameEnglish = (TextView) view.findViewById(R.id.tvEnglishName);
            this.tvVerses = (TextView) view.findViewById(R.id.verses);
            view.setOnClickListener(view1 -> SuraListAdapter.this.OnItemClick(SuraListAdapter.this.suraModels.get(ViewHolder.this.getAdapterPosition())));
        }
    }
}
