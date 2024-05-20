package com.vitcode.iprayertimes.names.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.names.model.NamesModel;

import java.util.ArrayList;

public abstract class NamesListAdapter extends RecyclerView.Adapter<NamesListAdapter.ViewHolder> {
    private final Context context;
    private int highlightPosition = -1;
    private final ArrayList<NamesModel> namesModels;

    public abstract void OnItemClick(int i);

    public NamesListAdapter(Context context2, ArrayList<NamesModel> arrayList) {
        this.context = context2;
        this.namesModels = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_names99, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        TextView textView = viewHolder.tvSurahNo;
        textView.setText("" + (i + 1));
        viewHolder.tvMakkiMadni.setText(this.namesModels.get(i).getMeaning().trim());
        viewHolder.tvNameArabic.setText(this.namesModels.get(i).getArabic());
        viewHolder.tvNameEnglish.setText(this.namesModels.get(i).getEng());

    }

    public void movePosition(int i) {
        this.highlightPosition = i;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.namesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout ayahRow;
        public TextView tvMakkiMadni;
        public TextView tvNameArabic;
        public TextView tvNameEnglish;
        public TextView tvSurahNo;

        private ViewHolder(View view) {
            super(view);
            this.tvSurahNo = (TextView) view.findViewById(R.id.tv_surah_no);
            this.tvMakkiMadni = (TextView) view.findViewById(R.id.maki_madni);
            this.tvNameArabic = (TextView) view.findViewById(R.id.arabic_name);
            this.tvNameEnglish = (TextView) view.findViewById(R.id.tvEnglishName);
            this.ayahRow = (RelativeLayout) view.findViewById(R.id.constraint_layout_id);
            view.setOnClickListener(view1 -> NamesListAdapter.this.OnItemClick(ViewHolder.this.getAdapterPosition()));
        }
    }
}
