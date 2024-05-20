package com.vitcode.iprayertimes.splash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vitcode.iprayertimes.R;

import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {
    List<LanguageModel> arraylist;
    Context context;

    public LanguageAdapter(Context context2, List<LanguageModel> list) {
        this.context = context2;
        this.arraylist = list;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lang, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        LanguageModel rowItem = arraylist.get(i);
        viewHolder.tvLanguages.setText(rowItem.getTvName());
        Glide.with(context).load(rowItem.getIvImage()).placeholder(R.drawable.ic_launcher).into(viewHolder.imgFlag);
        viewHolder.llMain.setOnClickListener(v -> {
            MyApp.selectedLanguagePosition = i;
            notifyDataSetChanged();
        });

        if (MyApp.selectedLanguagePosition == i) {
            viewHolder.imgCheck.setVisibility(View.VISIBLE);
        } else {
            viewHolder.imgCheck.setVisibility(View.GONE);
        }
    }

    public int getItemCount() {
        return arraylist.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCheck;
        ImageView imgFlag;
        LinearLayout llMain;
        TextView tvLanguages;

        public ViewHolder(View view) {
            super(view);
            imgCheck = view.findViewById(R.id.imgCheck);
            imgFlag = view.findViewById(R.id.imgFlag);
            tvLanguages = view.findViewById(R.id.tvLanguages);
            llMain = view.findViewById(R.id.llMain);
        }
    }
}
