package com.vitcode.iprayertimes.fivepillars;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.fivepillars.adapter.ExpandableListAdapter;
import com.vitcode.iprayertimes.hadith.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PillarsListActivity extends Utils implements View.OnClickListener {
    ExpandableListView expListView;
    Bundle extra;
    HashMap<String, List<String>> listDataChild;
    List<String> listDataHeader;
    ExpandableListAdapter listadapter;
    String name = "";
    String type = "";
    TextView title;
    ImageView back;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandlist);

        this.expListView = findViewById(R.id.lvExp);
        this.title = findViewById(R.id.tvtitle);
        this.back = findViewById(R.id.bt_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.extra = getIntent().getExtras();
        this.name = this.extra.getString("name");
        if (this.name.equals("shahadah")) {
            title.setText(getString(R.string.lbl_shahdah));
            this.type = "Shahadah";
            ShahadahList();
        } else if (this.name.equals("salah")) {
            title.setText(getString(R.string.lbl_salat));
            this.type = "Salah";
            SalahList();
        } else if (this.name.equals("zakat")) {
            title.setText(getString(R.string.lbl_zakat));
            this.type = "Zakat";
            ZakatList();
        } else if (this.name.equals("fasting")) {
            title.setText(getString(R.string.lbl_sawn));
            this.type = "Fasting";
            FastingList();
        } else if (this.name.equals("haji")) {
            title.setText(getString(R.string.lbl_hajj));
            this.type = "Haji";
            HajiList();
        }

        this.listadapter = new ExpandableListAdapter(this, this.listDataHeader, this.listDataChild);
        this.expListView.setAdapter(this.listadapter);
        this.expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            public void onGroupExpand(int groupPosition) {
                Intent in = new Intent(PillarsListActivity.this, PagerActivity.class);
                in.putExtra("ga", groupPosition);
                in.putExtra("type", PillarsListActivity.this.type);
                Log.d("Sending", groupPosition + "");
                PillarsListActivity.this.startActivity(in);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    private void FastingList() {
        this.listDataHeader = new ArrayList();
        this.listDataChild = new HashMap<>();
        this.listDataHeader.add(getString(R.string.fasting_lheader1));
        this.listDataHeader.add(getString(R.string.fasting_lheader2));
        this.listDataHeader.add(getString(R.string.fasting_lheader3));
        this.listDataHeader.add(getString(R.string.fasting_lheader4));
        this.listDataHeader.add(getString(R.string.fasting_lheader5));
        this.listDataHeader.add(getString(R.string.fasting_lheader6));
        this.listDataHeader.add(getString(R.string.fasting_lheader7));
        this.listDataHeader.add(getString(R.string.fasting_lheader8));
        List<String> Introduction = new ArrayList<>();
        List<String> Things_Fast = new ArrayList<>();
        List<String> Haram_Fasts = new ArrayList<>();
        List<String> Person_Exempted = new ArrayList<>();
        List<String> Invalidating_Fast = new ArrayList<>();
        List<String> Traveler = new ArrayList<>();
        List<String> Sick_person = new ArrayList<>();
        List<String> Breaking_Fast = new ArrayList<>();
        this.listDataChild.put(this.listDataHeader.get(0), Introduction);
        this.listDataChild.put(this.listDataHeader.get(1), Things_Fast);
        this.listDataChild.put(this.listDataHeader.get(2), Haram_Fasts);
        this.listDataChild.put(this.listDataHeader.get(3), Person_Exempted);
        this.listDataChild.put(this.listDataHeader.get(4), Invalidating_Fast);
        this.listDataChild.put(this.listDataHeader.get(5), Traveler);
        this.listDataChild.put(this.listDataHeader.get(6), Sick_person);
        this.listDataChild.put(this.listDataHeader.get(7), Breaking_Fast);
    }

    private void ShahadahList() {
        this.listDataHeader = new ArrayList();
        this.listDataChild = new HashMap<>();
        this.listDataHeader.add(getString(R.string.shahadah_lheader1));
        this.listDataHeader.add(getString(R.string.shahadah_lheader2));
        this.listDataHeader.add(getString(R.string.shahadah_lheader3));
        this.listDataHeader.add(getString(R.string.shahadah_lheader4));
        this.listDataHeader.add(getString(R.string.shahadah_lheader5));
        this.listDataHeader.add(getString(R.string.shahadah_lheader6));
        this.listDataHeader.add(getString(R.string.shahadah_lheader7));
        List<String> Introduction = new ArrayList<>();
        List<String> Believing_in_God = new ArrayList<>();
        List<String> Believing_in_Prophets = new ArrayList<>();
        List<String> Believing_in_Holy_Books = new ArrayList<>();
        List<String> Believing_in_Angels = new ArrayList<>();
        List<String> Believing_in_Judgement_Day = new ArrayList<>();
        List<String> Believing_in_Fate = new ArrayList<>();
        this.listDataChild.put(this.listDataHeader.get(0), Introduction);
        this.listDataChild.put(this.listDataHeader.get(1), Believing_in_God);
        this.listDataChild.put(this.listDataHeader.get(2), Believing_in_Prophets);
        this.listDataChild.put(this.listDataHeader.get(3), Believing_in_Holy_Books);
        this.listDataChild.put(this.listDataHeader.get(4), Believing_in_Angels);
        this.listDataChild.put(this.listDataHeader.get(5), Believing_in_Judgement_Day);
        this.listDataChild.put(this.listDataHeader.get(6), Believing_in_Fate);
    }

    private void ZakatList() {
        this.listDataHeader = new ArrayList();
        this.listDataChild = new HashMap<>();
        this.listDataHeader.add(getString(R.string.zakat_lheader1));
        this.listDataHeader.add(getString(R.string.zakat_lheader2));
        this.listDataHeader.add(getString(R.string.zakat_lheader3));
        this.listDataHeader.add(getString(R.string.zakat_lheader4));
        this.listDataHeader.add(getString(R.string.zakat_lheader5));
        this.listDataHeader.add(getString(R.string.zakat_lheader6));
        this.listDataHeader.add(getString(R.string.zakat_lheader7));
        this.listDataHeader.add(getString(R.string.zakat_lheader8));
        List<String> Introduction = new ArrayList<>();
        List<String> Zakat_is_Obligatory = new ArrayList<>();
        List<String> Zakat_Imposed = new ArrayList<>();
        List<String> Zakat_Distribution = new ArrayList<>();
        List<String> Applicable_for_Zakat = new ArrayList<>();
        List<String> Zakat_Recipients = new ArrayList<>();
        List<String> Person = new ArrayList<>();
        List<String> Punishment = new ArrayList<>();
        this.listDataChild.put(this.listDataHeader.get(0), Introduction);
        this.listDataChild.put(this.listDataHeader.get(1), Zakat_is_Obligatory);
        this.listDataChild.put(this.listDataHeader.get(2), Zakat_Imposed);
        this.listDataChild.put(this.listDataHeader.get(3), Zakat_Distribution);
        this.listDataChild.put(this.listDataHeader.get(4), Applicable_for_Zakat);
        this.listDataChild.put(this.listDataHeader.get(5), Zakat_Recipients);
        this.listDataChild.put(this.listDataHeader.get(6), Person);
        this.listDataChild.put(this.listDataHeader.get(7), Punishment);
    }

    private void SalahList() {
        this.listDataHeader = new ArrayList();
        this.listDataChild = new HashMap<>();
        this.listDataHeader.add(getString(R.string.salah_lheader1));
        this.listDataHeader.add(getString(R.string.salah_lheader2));
        this.listDataHeader.add(getString(R.string.salah_lheader3));
        this.listDataHeader.add(getString(R.string.salah_lheader4));
        this.listDataHeader.add(getString(R.string.salah_lheader5));
        this.listDataHeader.add(getString(R.string.salah_lheader6));
        this.listDataHeader.add(getString(R.string.salah_lheader7));
        this.listDataHeader.add(getString(R.string.salah_lheader8));
        List<String> Introduction = new ArrayList<>();
        List<String> Types_of_Salah = new ArrayList<>();
        List<String> Daily_Prayer_Rakaats = new ArrayList<>();
        List<String> Rules_of_Qibla = new ArrayList<>();
        List<String> Condition_For_Dress_Worn_for_Prayers = new ArrayList<>();
        List<String> Makrooh_Thing = new ArrayList<>();
        List<String> Things_which_make_Prayer_Void = new ArrayList<>();
        List<String> Doubts_in_Namaz = new ArrayList<>();
        this.listDataChild.put(this.listDataHeader.get(0), Introduction);
        this.listDataChild.put(this.listDataHeader.get(1), Types_of_Salah);
        this.listDataChild.put(this.listDataHeader.get(2), Daily_Prayer_Rakaats);
        this.listDataChild.put(this.listDataHeader.get(3), Rules_of_Qibla);
        this.listDataChild.put(this.listDataHeader.get(4), Condition_For_Dress_Worn_for_Prayers);
        this.listDataChild.put(this.listDataHeader.get(5), Makrooh_Thing);
        this.listDataChild.put(this.listDataHeader.get(6), Things_which_make_Prayer_Void);
        this.listDataChild.put(this.listDataHeader.get(7), Doubts_in_Namaz);
    }

    private void HajiList() {
        this.listDataHeader = new ArrayList();
        this.listDataChild = new HashMap<>();
        this.listDataHeader.add(getString(R.string.haji_lheader1));
        this.listDataHeader.add(getString(R.string.haji_lheader2));
        this.listDataHeader.add(getString(R.string.haji_lheader3));
        this.listDataHeader.add(getString(R.string.haji_lheader4));
        this.listDataHeader.add(getString(R.string.haji_lheader5));
        this.listDataHeader.add(getString(R.string.haji_lheader6));
        this.listDataHeader.add(getString(R.string.haji_lheader7));
        this.listDataHeader.add(getString(R.string.haji_lheader8));
        this.listDataHeader.add(getString(R.string.haji_lheader9));
        this.listDataHeader.add(getString(R.string.haji_lheader10));
        List<String> Introduction = new ArrayList<>();
        List<String> State_of_Ihram = new ArrayList<>();
        List<String> Performing_Umrah = new ArrayList<>();
        List<String> Departure_to_Mina = new ArrayList<>();
        List<String> Departure_to_Arafat = new ArrayList<>();
        List<String> Departure_to_Muzdalifa = new ArrayList<>();
        List<String> Returning_to_Mina = new ArrayList<>();
        List<String> Tawaf_Al_Ifada = new ArrayList<>();
        List<String> Returning_Mina = new ArrayList<>();
        List<String> FArewell_Tawaf = new ArrayList<>();
        this.listDataChild.put(this.listDataHeader.get(0), Introduction);
        this.listDataChild.put(this.listDataHeader.get(1), State_of_Ihram);
        this.listDataChild.put(this.listDataHeader.get(2), Performing_Umrah);
        this.listDataChild.put(this.listDataHeader.get(3), Departure_to_Mina);
        this.listDataChild.put(this.listDataHeader.get(4), Departure_to_Arafat);
        this.listDataChild.put(this.listDataHeader.get(5), Departure_to_Muzdalifa);
        this.listDataChild.put(this.listDataHeader.get(6), Returning_to_Mina);
        this.listDataChild.put(this.listDataHeader.get(7), Tawaf_Al_Ifada);
        this.listDataChild.put(this.listDataHeader.get(8), Returning_Mina);
        this.listDataChild.put(this.listDataHeader.get(9), FArewell_Tawaf);
    }

    public void onClick(View v) {
    }
}
