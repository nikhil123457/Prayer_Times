package com.vitcode.iprayertimes.quran.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.quran.activity.QuranReadActivity;
import com.vitcode.iprayertimes.quran.helper.JuzDatabase;
import com.vitcode.iprayertimes.quran.helper.QuranHelper;
import com.vitcode.iprayertimes.quran.helper.arabicutils.ArabicUtilities;
import com.vitcode.iprayertimes.quran.model.JuzModel;
import com.vitcode.iprayertimes.quran.model.SurahModel;

import java.util.List;

public abstract class QuranReadAdapter extends RecyclerView.Adapter<QuranReadAdapter.ViewHolder> {
    private final Context context;
    private final QuranReadActivity fragment;
    JuzDatabase juzDatabase;
    List<JuzModel> juzModelList;
    private final List<SurahModel> surahList;
    private final int surahPosition;
    String[] urduParah;

    public abstract void ItemClick(int i);

    public abstract void ItemLongClick(int i);

    public QuranReadAdapter(QuranReadActivity quranReadActivity, Context context2, List<SurahModel> list, int i) {
        this.juzDatabase = new JuzDatabase(context2);
        this.fragment = quranReadActivity;
        this.context = context2;
        this.surahList = list;
        this.surahPosition = i;
        this.urduParah = context2.getResources().getStringArray(R.array.urdu_chapters);
        this.juzModelList = JuzDatabase.getJuzList(i);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_quran_read, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.continerJuzzTitle.setTag(Integer.valueOf(i));
        String arabicAyah = this.surahList.get(i).getArabicAyah();
        String translation = this.surahList.get(i).getTranslation();
        String transliteration = this.surahList.get(i).getTransliteration();
        if (this.surahList.get(i).getJuzzIndex() > -1) {
            viewHolder.continerJuzzTitle.setVisibility(View.VISIBLE);
            TextView textView = viewHolder.tvEngJuzzNo;
            textView.setText("Juz: " + this.juzModelList.get(i).getParaId());
            viewHolder.tvArabicJuzzName.setText(this.urduParah[this.juzModelList.get(i).getParaId() - 1]);
            TextView textView2 = viewHolder.tvArabicJuzzNo;
            textView2.setText(QuranHelper.arabicCounting[this.juzModelList.get(i).getParaId()] + " :" + ArabicUtilities.reshapeSentence("جزء"));
        } else {
            viewHolder.continerJuzzTitle.setVisibility(
                    View.GONE);
        }
        int i2 = this.surahPosition;
        if (i2 == 9) {
            TextView textView3 = viewHolder.tvArabic;
            textView3.setText(Html.fromHtml(ArabicUtilities.reshapeSentence(arabicAyah) + "<font color='#805D01'>﴿" + QuranHelper.arabicCounting[i + 1] + "﴾</font>"), TextView.BufferType.SPANNABLE);
        } else if (i2 == 1) {
            TextView textView4 = viewHolder.tvArabic;
            textView4.setText(Html.fromHtml(ArabicUtilities.reshapeSentence(arabicAyah) + "<font color='#805D01'>﴿" + QuranHelper.arabicCounting[i + 1] + "﴾</font>"), TextView.BufferType.SPANNABLE);
        } else if (i == 0) {
            viewHolder.tvArabic.setText(ArabicUtilities.reshapeSentence(arabicAyah));
        } else {
            TextView textView5 = viewHolder.tvArabic;
            textView5.setText(Html.fromHtml(ArabicUtilities.reshapeSentence(arabicAyah) + "<font color='#805D01'>﴿" + QuranHelper.arabicCounting[i] + "﴾</font>"), TextView.BufferType.SPANNABLE);
        }
        viewHolder.tvTranslation.setText(translation);
        viewHolder.tvTransliteration.setText(Html.fromHtml(transliteration));
        if (!QuranHelper.Translation.getTranslationPhoneTic(this.context)) {
            viewHolder.tvTransliteration.setVisibility(View.GONE);
        } else {
            viewHolder.tvTransliteration.setVisibility(View.VISIBLE);
        }
        TextView textView6 = this.fragment.tvJuzNumber;
        textView6.setText("Juz: " + this.juzModelList.get(i).getParaId());
    }

    public int getItemCount() {
        return this.surahList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ayahRow;
        LinearLayout continerJuzzTitle;
        TextView tvArabic;
        TextView tvArabicJuzzName;
        TextView tvArabicJuzzNo;
        TextView tvEngJuzzNo;
        TextView tvTranslation;
        TextView tvTransliteration;

        public ViewHolder(View view) {
            super(view);
            this.tvArabic = (TextView) view.findViewById(R.id.tv_ayah);
            this.tvTranslation = (TextView) view.findViewById(R.id.text_translation);
            this.tvTransliteration = (TextView) view.findViewById(R.id.text_transliteration);
            this.ayahRow = (LinearLayout) view.findViewById(R.id.ayah_row);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.containerJuzzView);
            this.continerJuzzTitle = linearLayout;
            linearLayout.setVisibility(View.GONE);
            this.tvArabicJuzzNo = (TextView) view.findViewById(R.id.txt_urdu_juzz_no);
            this.tvEngJuzzNo = (TextView) view.findViewById(R.id.txt_eng_juzz_no);
            this.tvArabicJuzzName = (TextView) view.findViewById(R.id.txt_urdu_para_name);
            this.itemView.setOnClickListener(view1 -> QuranReadAdapter.this.ItemClick(ViewHolder.this.getAdapterPosition()));
            this.itemView.setOnLongClickListener(view12 -> {
                QuranReadAdapter.this.ItemLongClick(ViewHolder.this.getAdapterPosition());
                return true;
            });
        }
    }
}
