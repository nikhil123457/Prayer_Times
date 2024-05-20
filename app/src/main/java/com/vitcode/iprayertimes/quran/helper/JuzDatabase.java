package com.vitcode.iprayertimes.quran.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vitcode.iprayertimes.quran.model.JuzModel;
import com.vitcode.iprayertimes.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class JuzDatabase extends SQLiteAssetHelper {
    private static final String DB_NAME = "juzData.db";
    private static String DB_PATH = null;
    private static final int DB_VERSION = 1;
    private static SQLiteDatabase database;
    private static JuzDatabase duaHelper;

    public JuzDatabase(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 2);
        database = getReadableDatabase();
    }

    @SuppressLint("Range")
    public static List<JuzModel> getJuzList(int i) {
        String str = "SELECT * from tbl_QuranComplete where surat_ID=" + i;
        if (database == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = database.rawQuery(str, (String[]) null);
        if (rawQuery.moveToFirst()) {
            do {
                JuzModel juzModel = new JuzModel();
                juzModel.setAyatId(rawQuery.getInt(rawQuery.getColumnIndex("Ayat_ID")));
                juzModel.setSuratId(rawQuery.getInt(rawQuery.getColumnIndex("surat_ID")));
                juzModel.setParaId(rawQuery.getInt(rawQuery.getColumnIndex("para_ID")));
                juzModel.setAyatNo(rawQuery.getInt(rawQuery.getColumnIndex("only_aayat_no")));
                arrayList.add(juzModel);
            } while (rawQuery.moveToNext());
            rawQuery.close();
        }
        return arrayList;
    }

    @SuppressLint("Range")
    public JuzModel getJuzNumber(int i, int i2) {
        String str = "SELECT * from tbl_QuranComplete where surat_ID=" + i + " and only_aayat_no=" + i2;
        SQLiteDatabase sQLiteDatabase = database;
        JuzModel juzModel = null;
        if (sQLiteDatabase == null) {
            return null;
        }
        Cursor rawQuery = sQLiteDatabase.rawQuery(str, (String[]) null);
        if (rawQuery.moveToFirst()) {
            do {
                juzModel = new JuzModel();
                juzModel.setAyatId(rawQuery.getInt(rawQuery.getColumnIndex("Ayat_ID")));
                juzModel.setSuratId(rawQuery.getInt(rawQuery.getColumnIndex("surat_ID")));
                juzModel.setParaId(rawQuery.getInt(rawQuery.getColumnIndex("para_ID")));
                juzModel.setAyatNo(rawQuery.getInt(rawQuery.getColumnIndex("only_aayat_no")));
            } while (rawQuery.moveToNext());
            rawQuery.close();
        }
        return juzModel;
    }
}
