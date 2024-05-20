package com.vitcode.iprayertimes.dateconverter.abcd.Fragments;

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
import com.vitcode.iprayertimes.quran.adapter.JuzListAdapter;
import com.vitcode.iprayertimes.quran.helper.QuranHelper;

public class JuzFragment extends Fragment {

    private String[] engParah;
    private final int[][] juzzIndex = QuranHelper.juzzIndex;
    private String[] urduParah;
    private String[] versesParah;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_juz, container, false);



        getData();

        RecyclerView recyclerView = rootView.findViewById(R.id.rcv_juz);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new JuzListAdapter(requireContext(), engParah, urduParah, versesParah) {
            @Override
            public void OnItemClick(int i) {
                int[] iArr = juzzIndex[i];
                Intent intent = new Intent(requireContext(), QuranReadActivity.class);
                QuranReadActivity.setSurahNumber(iArr[0]);
                QuranReadActivity.setAyahPos(iArr[1]);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void getData() {
        engParah = getResources().getStringArray(R.array.eng_chapters);
        urduParah = getResources().getStringArray(R.array.urdu_chapters);
        versesParah = getResources().getStringArray(R.array.verses_chapters);
    }
}
