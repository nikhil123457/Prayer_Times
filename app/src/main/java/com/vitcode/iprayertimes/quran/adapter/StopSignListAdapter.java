package com.vitcode.iprayertimes.quran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vitcode.iprayertimes.R;


public class StopSignListAdapter extends RecyclerView.Adapter<StopSignListAdapter.ViewHolder> {
    private final Context context;
    private final String[] engStopSign;
    private final String[] urduStopSign;

    public StopSignListAdapter(Context context2, String[] strArr, String[] strArr2) {
        this.context = context2;
        this.engStopSign = strArr;
        this.urduStopSign = strArr2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.stop_sign_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvStopSign.setText(this.engStopSign[i]);
        viewHolder.tvUrduSign.setText(this.urduStopSign[i]);
        TextView textView = viewHolder.tvPosition;
        textView.setText("" + (i + 1));
    }

    public int getItemCount() {
        return this.engStopSign.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPosition;
        public TextView tvStopSign;
        public TextView tvUrduSign;

        ViewHolder(View view) {
            super(view);
            this.tvStopSign = (TextView) view.findViewById(R.id.tv_stop_sign);
            this.tvUrduSign = (TextView) view.findViewById(R.id.tv_urdu_sign);
            this.tvPosition = (TextView) view.findViewById(R.id.tv_position);
        }
    }
}
