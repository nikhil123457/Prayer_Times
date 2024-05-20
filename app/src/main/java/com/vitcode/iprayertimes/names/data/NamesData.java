package com.vitcode.iprayertimes.names.data;

import android.content.Context;
import android.widget.Toast;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.names.model.NamesModel;

import java.util.ArrayList;

public class NamesData {
    private final Context mContext;
    private final int[] nameTiming;
    private final String[] names;
    private final String[] namesArabic;
    private final String[] namesDetailMeaning;
    private final String[] namesMeaning;

    public NamesData(Context context) {
        this.mContext = context;
        this.nameTiming = convertToMillisMinutes(context.getResources().getStringArray(R.array.allah_name_time));
        this.names = context.getResources().getStringArray(R.array.allah_names_transliteration);
        this.namesArabic = context.getResources().getStringArray(R.array.allah_names);
        this.namesDetailMeaning = context.getResources().getStringArray(R.array.allah_names_detail_english);
        this.namesMeaning = context.getResources().getStringArray(R.array.allah_names_english);
    }

    public int[] getNameTiming() {
        return this.nameTiming;
    }

    public int getNameTiming(int i) {
        return this.nameTiming[i];
    }

    public String getNameEnglish(int i) {
        return this.names[i];
    }

    public String getNameArabic(int i) {
        return this.namesArabic[i];
    }

    public String getNameMeaning(int i) {
        return this.namesMeaning[i];
    }

    public String getNameDetails(int i) {
        return this.namesDetailMeaning[i];
    }

    public ArrayList<NamesModel> getNamesData() {
        ArrayList<NamesModel> arrayList = new ArrayList<>();
        for (int i = 0; i < this.names.length; i++) {
            NamesModel namesModel = new NamesModel();
            namesModel.setEng(this.names[i]);
            namesModel.setArabic(this.namesArabic[i]);
            namesModel.setDetails(this.namesDetailMeaning[i]);
            namesModel.setMeaning(this.namesMeaning[i]);
            arrayList.add(namesModel);
        }
        return arrayList;
    }

    private int[] convertToMillisMinutes(String[] strArr) {
        int[] iArr = new int[strArr.length];
        int i = 0;
        while (i < strArr.length) {
            try {
                String[] split = strArr[i].trim().split(":");
                int parseInt = Integer.parseInt(split[0].trim());
                String[] split2 = split[1].split("\\.");
                iArr[i] = (parseInt * 60 * 1000) + (Integer.parseInt(split2[0].trim()) * 1000) + Integer.parseInt(split2[1].trim());
                i++;
            } catch (Exception e) {
                Context context = this.mContext;
                Toast.makeText(context, "Exception occured at ayat = " + i, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        return iArr;
    }

    public int getNamesSize() {
        return this.namesArabic.length;
    }
}
