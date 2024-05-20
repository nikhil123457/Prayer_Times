package com.vitcode.iprayertimes.tasbeehcounter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.vitcode.iprayertimes.R;

import java.util.ArrayList;
import java.util.Collections;

public class TasbihAdapter extends RecyclerView.Adapter<TasbihAdapter.ViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {
    Context mContext;
    /* access modifiers changed from: private */
    public final StartDragListener mStartDragListener;
    Float radius;
    /* access modifiers changed from: private */
    public TasbihAdapterListener tasbihAdapterListener;
    public ArrayList<TasbihModel> tasbihModelArrayList;

    public interface TasbihAdapterListener {
        void onTasbihClick(Intent intent);
    }

    public TasbihAdapter(ArrayList<TasbihModel> arrayList, StartDragListener startDragListener) {
        mStartDragListener = startDragListener;
        this.tasbihModelArrayList = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recylcer_view_tasbih_layout, viewGroup, false);
        Context context = viewGroup.getContext();
        this.mContext = context;
        this.radius = Float.valueOf(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4.0f, context.getResources().getDisplayMetrics()));
        return new ViewHolder(inflate);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.tasbihName.setText(tasbihModelArrayList.get(i).getTasbihName());
        int tasbihCount = this.tasbihModelArrayList.get(i).getTasbihCount();
        int tasbihSet = this.tasbihModelArrayList.get(i).getTasbihSet();
        int tasbihMax = this.tasbihModelArrayList.get(i).getTasbihMax();
        AutoResizeTextView autoResizeTextView = viewHolder.total_count;
        autoResizeTextView.setText("Total: " + tasbihCount);
        if (tasbihMax == 0) {
            tasbihMax = 33;
        }
        viewHolder.count.setText((tasbihCount % tasbihMax) + "/" + tasbihMax + " (" + tasbihSet + ")");
        viewHolder.tasbih_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TasbihAdapter.this.mContext, MainActivity.class);
                intent.putExtra("tasbihName", tasbihModelArrayList.get(i).tasbihName);
                TasbihAdapter.this.mContext.getSharedPreferences("tasbih", 0).edit().putString("tasbihName", tasbihModelArrayList.get(i).tasbihName).apply();
                if (TasbihAdapter.this.tasbihAdapterListener != null) {
                    TasbihAdapter.this.mContext.startActivity(intent);

                    TasbihAdapter.this.tasbihAdapterListener.onTasbihClick(intent);
                } else {
                    TasbihAdapter.this.mContext.startActivity(intent);
                }
            }
        });
        TextView textView = viewHolder.tvPosition;
        textView.setText("" + (i + 1) );
    }

    public int getItemCount() {
        return this.tasbihModelArrayList.size();
    }

    public void onRowMoved(int i, int i2) {
        if (i < i2) {
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                Collections.swap(this.tasbihModelArrayList, i3, i4);
                i3 = i4;
            }
        } else {
            for (int i5 = i; i5 > i2; i5--) {
                Collections.swap(this.tasbihModelArrayList, i5, i5 - 1);
            }
        }
        notifyItemMoved(i, i2);
    }

    public void onRowSelected(ViewHolder viewHolder) {
        viewHolder.tasbih_layout.setCardBackgroundColor(-7829368);
        viewHolder.tasbih_layout.setCardElevation(this.radius.floatValue());
        viewHolder.tasbih_layout.setRadius(this.radius.floatValue());
    }

    public void onRowClear(ViewHolder viewHolder) {
        viewHolder.tasbih_layout.setCardBackgroundColor(-1);
        viewHolder.tasbih_layout.setCardElevation(this.radius.floatValue());
        viewHolder.tasbih_layout.setRadius(this.radius.floatValue());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AutoResizeTextView count;
        public TextView tvPosition;
        TextView tasbihName;
        CardView tasbih_layout;
        AutoResizeTextView total_count;

        public ViewHolder(View view) {
            super(view);
            this.tasbihName = (TextView) view.findViewById(R.id.tasbih_name);
            this.count = (AutoResizeTextView) view.findViewById(R.id.count);
            this.total_count = (AutoResizeTextView) view.findViewById(R.id.total_count);
            this.tasbih_layout = (CardView) view.findViewById(R.id.tasbih_layout);
            this.tvPosition = (TextView) view.findViewById(R.id.tv_position);

        }
    }

    public void setTasbihAdapterListener(TasbihAdapterListener tasbihAdapterListener2) {
        this.tasbihAdapterListener = tasbihAdapterListener2;
    }
}
