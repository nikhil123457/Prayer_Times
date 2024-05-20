package com.vitcode.iprayertimes.quran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vitcode.iprayertimes.R;


public abstract class JuzListAdapter extends RecyclerView.Adapter<JuzListAdapter.ViewHolder> {
    private final Context context;
    private final String[] engParah;
    private final String[] urduParah;
    private final String[] versesParah;

    public abstract void OnItemClick(int i);

    public JuzListAdapter(Context context2, String[] strArr, String[] strArr2, String[] strArr3) {
        this.context = context2;
        this.engParah = strArr;
        this.urduParah = strArr2;
        this.versesParah = strArr3;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.juz_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvJuz.setText(this.engParah[i]);
        viewHolder.tvUrduJuz.setText(this.urduParah[i]);
        viewHolder.tvVerses.setText(this.versesParah[i]);
        TextView textView = viewHolder.tvPosition;
        textView.setText("" + (i + 1));
    }

    public int getItemCount() {
        return this.engParah.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvJuz;
        public TextView tvPosition;
        public TextView tvUrduJuz;
        public TextView tvVerses;

        ViewHolder(View view) {
            super(view);
            this.tvJuz = (TextView) view.findViewById(R.id.tv_juz);
            this.tvUrduJuz = (TextView) view.findViewById(R.id.tv_urdu_juz);
            this.tvVerses = (TextView) view.findViewById(R.id.tv_verses_juz);
            this.tvPosition = (TextView) view.findViewById(R.id.tv_position);
            view.setOnClickListener(view1 -> JuzListAdapter.this.OnItemClick(ViewHolder.this.getAdapterPosition()));
        }
    }
}
