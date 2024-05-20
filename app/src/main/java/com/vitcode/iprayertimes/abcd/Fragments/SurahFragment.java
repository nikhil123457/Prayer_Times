package com.vitcode.iprayertimes.abcd.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.quran.activity.QuranReadActivity;
import com.vitcode.iprayertimes.quran.adapter.SuraListAdapter;
import com.vitcode.iprayertimes.quran.helper.QuranHelper;
import com.vitcode.iprayertimes.quran.model.SuraModel;

import java.util.ArrayList;

public class SurahFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<SuraModel> suraModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_surah, container, false);

        recyclerView = rootView.findViewById(R.id.rcv_sura);


        // Set up RecyclerView and adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        suraModels = new ArrayList<>();
        getData();
        recyclerView.setAdapter(new SuraListAdapter(getActivity(), suraModels) {
            @Override
            public void OnItemClick(SuraModel suraModel) {
                Intent intent = new Intent(getActivity(), QuranReadActivity.class);
                QuranReadActivity.setSurahNumber(suraModel.getItemPosition() + 1);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void getData() {
        String[] stringArray = getResources().getStringArray(R.array.surah_names);
        String[] stringArray2 = getResources().getStringArray(R.array.surahNamesArabic);
        int[] intArray = getResources().getIntArray(R.array.noOfVerses);
        String[] stringArray3 = getResources().getStringArray(R.array.revealedPlaces);
        for (int i = 0; i < stringArray.length; i++) {
            int surahNumber = i + 1;
            SuraModel suraModel = new SuraModel(surahNumber, stringArray[i], stringArray2[i], stringArray3[i], intArray[i], i);
            suraModel.setParaIndex(QuranHelper.paraWithSurrahIndex[i]);
            suraModels.add(suraModel);
        }
    }
}
