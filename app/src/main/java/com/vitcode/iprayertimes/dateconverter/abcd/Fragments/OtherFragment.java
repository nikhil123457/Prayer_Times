package com.vitcode.iprayertimes.dateconverter.abcd.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vitcode.iprayertimes.Mosques.MosquesFragment;
import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.dateconverter.abcd.EditsActivity;
import com.vitcode.iprayertimes.dateconverter.abcd.FastingRulesActivity;
import com.vitcode.iprayertimes.dateconverter.abcd.MoreduvaActivity;
import com.vitcode.iprayertimes.dateconverter.abcd.PrayerGuideActivity;
import com.vitcode.iprayertimes.dateconverter.abcd.kalima6;
import com.vitcode.iprayertimes.calender.activity.CalendarActivity;
import com.vitcode.iprayertimes.fivepillars.FivePillarsActivity;
import com.vitcode.iprayertimes.hadith.ActivityHadith;
import com.vitcode.iprayertimes.names.activity.Name99Activity;
import com.vitcode.iprayertimes.tasbeehcounter.History;
import com.vitcode.iprayertimes.wether.Wetherrr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class OtherFragment extends Fragment {
    private LinearLayout LInShirk, btn_cal, LINFasting, btn_99names, btn_pillers, btn_hadith, LinKalima, LInPrayGaid;
    private LinearLayout btn_tasbeeh, btn_mos, btn_msg, LinMoredu, Linwether;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_fragment, container, false);


        btn_cal = view.findViewById(R.id.btn_cal);
        btn_99names = view.findViewById(R.id.btn_99names);
        btn_pillers = view.findViewById(R.id.btn_pillers);
        btn_hadith = view.findViewById(R.id.btn_hadith);
        btn_tasbeeh = view.findViewById(R.id.btn_tasbeeh);
        btn_mos = view.findViewById(R.id.btn_mos);
        LinKalima = view.findViewById(R.id.LinKalima);
        LInShirk = view.findViewById(R.id.LInShirk);
        LInPrayGaid = view.findViewById(R.id.LInPrayGaid);
        LinMoredu = view.findViewById(R.id.LinMoredu);
        Linwether = view.findViewById(R.id.Linwether);

        LINFasting = view.findViewById(R.id.LINFasting);
        btn_msg = view.findViewById(R.id.btn_msg);


        Linwether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), Wetherrr.class));
            }
        });
        btn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), EditsActivity.class));
            }
        });

        LinMoredu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), MoreduvaActivity.class));
            }
        });
        LInShirk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), ShirkActivity.class));
            }
        });
        LInPrayGaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), PrayerGuideActivity.class));
            }
        });


        btn_mos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), MosquesFragment.class));
            }
        });
        LinKalima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), kalima6.class));
            }
        });

        btn_tasbeeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences2 = requireActivity().getSharedPreferences("tasbih", 0);
                sharedPreferences = sharedPreferences2;
                SharedPreferences.Editor edit = sharedPreferences2.edit();
                manageAppOpenAd();
                if (!sharedPreferences.getBoolean("created", false)) {
                    try {
                        createDatabase();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    edit.putString("theme", "NA");
                    edit.apply();
                    edit.commit();
                }
            }
        });

        btn_pillers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), FivePillarsActivity.class));
            }
        });
        LINFasting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), FastingRulesActivity.class));
            }
        });


        btn_hadith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), ActivityHadith.class));
            }
        });

        btn_99names.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), Name99Activity.class));
            }
        });


        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireActivity(), CalendarActivity.class));
            }
        });

        return view;
    }

    public void manageAppOpenAd() {
        startActivity(new Intent(requireActivity(), History.class));
    }

    private void createDatabase() throws JSONException, IOException {
        JSONObject jsonObject;
        JSONObject jsonObject2;
        JSONObject jsonObject3;
        JSONObject jsonObject4;
        JSONObject jsonObject5;
        JSONObject jsonObject6;
        try {
            String str = requireActivity().getFilesDir() + "/";
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(str + "tasbih.json");
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject7 = new JSONObject();
        JSONObject jsonObject8 = new JSONObject();
        JSONObject jsonObject9 = new JSONObject();
        JSONObject jsonObject10 = new JSONObject();
        JSONObject jsonObject11 = new JSONObject();
        JSONObject jsonObject12 = new JSONObject();
        JSONObject jsonObject13 = new JSONObject();
        JSONObject jsonObject14 = new JSONObject();
        JSONObject jsonObject15 = new JSONObject();
        JSONObject jsonObject16 = new JSONObject();
        JSONObject jsonObject17 = new JSONObject();
        JSONObject jsonObject18 = new JSONObject();
        JSONObject jsonObject19 = new JSONObject();
        JSONObject jsonObject20 = new JSONObject();
        JSONObject jsonObject21 = new JSONObject();
        String str2 = "tasbih.json";
        JSONObject jsonObject22 = new JSONObject();
        JSONObject jsonObject23 = new JSONObject();
        JSONObject jsonObject24 = jsonObject21;
        try {
            jsonObject7.put("total_count", 0);
            jsonObject7.put("max", 33);
            jsonObject7.put("sets", 0);
            jsonObject8.put("SubhanAllah", jsonObject7);
            jsonObject9.put("Alhamdulillah", jsonObject7);
            jsonObject10.put("Allahuakbar", jsonObject7);
            jsonObject11.put("La illaha illallah", jsonObject7);
            jsonObject12.put("Astaghfir-Allaah", jsonObject7);
            jsonObject13.put("Bismillah", jsonObject7);
            jsonObject14.put("Allahuma Salli Ala Muhammadin Wa Aale Muhammad", jsonObject7);
            jsonObject15.put("Ya Waliyyul Hasanaat", jsonObject7);
            jsonObject16.put("Subhaan Allaah wa bi hamdihi Subhaan Allaah il-‘Azeem", jsonObject7);
            jsonObject17.put("Subhaan Allaah, wa’l-hamdu Lillah, wa laa ilaah ill-Allaah, wa Allaahu akbar", jsonObject7);
            jsonObject18.put("Laa hawla wa laa quwwata illa Billaah", jsonObject7);
            jsonObject19.put("Ash-hadu an lā ilāha illallāh, waḥdahu lā sharīka lahu", jsonObject7);
            jsonObject20.put("la Haola Wala Quwwata illa billahil AliYil Azeem.", jsonObject7);
            jsonObject4 = jsonObject24;
            try {
                jsonObject4.put("Allahumma, anta’s-salam wa minkas’salam, Tabarakta-ya-dhal’Jalali wa’l-Ikram.", jsonObject7);
                jsonObject = jsonObject8;
                jsonObject6 = jsonObject22;
            } catch (JSONException e2) {
                jsonObject = jsonObject8;
                jsonObject6 = jsonObject22;
                jsonObject3 = jsonObject9;
                jsonObject5 = jsonObject23;
                jsonObject2 = jsonObject10;
                Toast.makeText(requireContext(), e2.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                JSONObject jsonObject25 = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(jsonObject5);
                jSONArray.put(jsonObject6);
                jSONArray.put(jsonObject4);
                jSONArray.put(jsonObject20);
                jSONArray.put(jsonObject19);
                jSONArray.put(jsonObject18);
                jSONArray.put(jsonObject17);
                jSONArray.put(jsonObject16);
                jSONArray.put(jsonObject15);
                jSONArray.put(jsonObject14);
                jSONArray.put(jsonObject13);
                jSONArray.put(jsonObject12);
                jSONArray.put(jsonObject11);
                jSONArray.put(jsonObject2);
                jSONArray.put(jsonObject3);
                jSONArray.put(jsonObject);
                jsonObject25.put("Tasbeehat", jSONArray);
                String jSONObject26 = jsonObject25.toString();
                BufferedWriter bufferedWriter = null;
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(new File(requireActivity().getFilesDir(), str2)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bufferedWriter.write(jSONObject26);
                bufferedWriter.close();
                SharedPreferences.Editor edit = this.sharedPreferences.edit();
                edit.putBoolean("created", true);
                edit.commit();
            }
            try {
                jsonObject6.put("Subhana rabbika rabbil ‘izzati ‘amma yashifun. Wa salamun ‘ala l-mursalin. Wa l-hamdu lillahi rabbi l-‘alamin. Al-Fatihah \" and afterwards say the a‘udhu-basmalah with the fatiha", jsonObject7);
                jsonObject3 = jsonObject9;
                jsonObject5 = jsonObject23;
                try {
                    jsonObject5.put("Ayate Karima", jsonObject7);
                    jsonObject2 = jsonObject10;
                } catch (JSONException e4) {
                    jsonObject2 = jsonObject10;
                    Toast.makeText(requireContext(), e4.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject252 = new JSONObject();
                    JSONArray jSONArray2 = new JSONArray();
                    jSONArray2.put(jsonObject5);
                    jSONArray2.put(jsonObject6);
                    jSONArray2.put(jsonObject4);
                    jSONArray2.put(jsonObject20);
                    jSONArray2.put(jsonObject19);
                    jSONArray2.put(jsonObject18);
                    jSONArray2.put(jsonObject17);
                    jSONArray2.put(jsonObject16);
                    jSONArray2.put(jsonObject15);
                    jSONArray2.put(jsonObject14);
                    jSONArray2.put(jsonObject13);
                    jSONArray2.put(jsonObject12);
                    jSONArray2.put(jsonObject11);
                    jSONArray2.put(jsonObject2);
                    jSONArray2.put(jsonObject3);
                    jSONArray2.put(jsonObject);
                    jsonObject252.put("Tasbeehat", jSONArray2);
                    String jSONObject262 = jsonObject252.toString();
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(new File(requireActivity().getFilesDir(), str2)));
                    bufferedWriter2.write(jSONObject262);
                    bufferedWriter2.close();
                    SharedPreferences.Editor edit2 = this.sharedPreferences.edit();
                    edit2.putBoolean("created", true);
                    edit2.commit();
                }
            } catch (JSONException e5) {
                jsonObject3 = jsonObject9;
                jsonObject5 = jsonObject23;
                jsonObject2 = jsonObject10;
                Toast.makeText(requireContext(), e5.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                JSONObject jsonObject2522 = new JSONObject();
                JSONArray jSONArray22 = new JSONArray();
                jSONArray22.put(jsonObject5);
                jSONArray22.put(jsonObject6);
                jSONArray22.put(jsonObject4);
                jSONArray22.put(jsonObject20);
                jSONArray22.put(jsonObject19);
                jSONArray22.put(jsonObject18);
                jSONArray22.put(jsonObject17);
                jSONArray22.put(jsonObject16);
                jSONArray22.put(jsonObject15);
                jSONArray22.put(jsonObject14);
                jSONArray22.put(jsonObject13);
                jSONArray22.put(jsonObject12);
                jSONArray22.put(jsonObject11);
                jSONArray22.put(jsonObject2);
                jSONArray22.put(jsonObject3);
                jSONArray22.put(jsonObject);
                jsonObject2522.put("Tasbeehat", jSONArray22);
                String jSONObject2622 = jsonObject2522.toString();
                BufferedWriter bufferedWriter22 = new BufferedWriter(new FileWriter(new File(requireActivity().getFilesDir(), str2)));
                bufferedWriter22.write(jSONObject2622);
                bufferedWriter22.close();
                SharedPreferences.Editor edit22 = this.sharedPreferences.edit();
                edit22.putBoolean("created", true);
                edit22.commit();
            }
        } catch (JSONException | IOException e6) {
            jsonObject4 = jsonObject24;
            jsonObject = jsonObject8;
            jsonObject6 = jsonObject22;
            jsonObject3 = jsonObject9;
            jsonObject5 = jsonObject23;
            jsonObject2 = jsonObject10;
            Toast.makeText(requireContext(), e6.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            JSONObject jsonObject25222 = new JSONObject();
            JSONArray jSONArray222 = new JSONArray();
            jSONArray222.put(jsonObject5);
            jSONArray222.put(jsonObject6);
            jSONArray222.put(jsonObject4);
            jSONArray222.put(jsonObject20);
            jSONArray222.put(jsonObject19);
            jSONArray222.put(jsonObject18);
            jSONArray222.put(jsonObject17);
            jSONArray222.put(jsonObject16);
            jSONArray222.put(jsonObject15);
            jSONArray222.put(jsonObject14);
            jSONArray222.put(jsonObject13);
            jSONArray222.put(jsonObject12);
            jSONArray222.put(jsonObject11);
            jSONArray222.put(jsonObject2);
            jSONArray222.put(jsonObject3);
            jSONArray222.put(jsonObject);
            jsonObject25222.put("Tasbeehat", jSONArray222);
            String jSONObject26222 = jsonObject25222.toString();
            BufferedWriter bufferedWriter222 = new BufferedWriter(new FileWriter(new File(requireActivity().getFilesDir(), str2)));
            try {
                bufferedWriter222.write(jSONObject26222);
            } catch (IOException e) {
                e.printStackTrace();
            }
            bufferedWriter222.close();
            SharedPreferences.Editor edit222 = this.sharedPreferences.edit();
            edit222.putBoolean("created", true);
            edit222.commit();
        }
        try {
            JSONObject jsonObject252222 = new JSONObject();
            JSONArray jSONArray2222 = new JSONArray();
            jSONArray2222.put(jsonObject5);
            jSONArray2222.put(jsonObject6);
            jSONArray2222.put(jsonObject4);
            jSONArray2222.put(jsonObject20);
            jSONArray2222.put(jsonObject19);
            jSONArray2222.put(jsonObject18);
            jSONArray2222.put(jsonObject17);
            jSONArray2222.put(jsonObject16);
            jSONArray2222.put(jsonObject15);
            jSONArray2222.put(jsonObject14);
            jSONArray2222.put(jsonObject13);
            jSONArray2222.put(jsonObject12);
            jSONArray2222.put(jsonObject11);
            jSONArray2222.put(jsonObject2);
            jSONArray2222.put(jsonObject3);
            jSONArray2222.put(jsonObject);
            jsonObject252222.put("Tasbeehat", jSONArray2222);
            String jSONObject262222 = jsonObject252222.toString();
            BufferedWriter bufferedWriter2222 = new BufferedWriter(new FileWriter(new File(requireActivity().getFilesDir(), str2)));
            bufferedWriter2222.write(jSONObject262222);
            bufferedWriter2222.close();
            SharedPreferences.Editor edit2222 = this.sharedPreferences.edit();
            edit2222.putBoolean("created", true);
            edit2222.commit();
        } catch (IOException | JSONException e7) {
            e7.printStackTrace();
        }
    }
}
