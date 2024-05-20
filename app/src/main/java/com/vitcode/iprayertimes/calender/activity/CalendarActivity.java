package com.vitcode.iprayertimes.calender.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.calender.adapter.CalenderDateAdapter;
import com.vitcode.iprayertimes.calender.adapter.EventDetailAdapter;
import com.vitcode.iprayertimes.calender.helper.Dates;
import com.vitcode.iprayertimes.calender.helper.HGDate;
import com.vitcode.iprayertimes.calender.helper.NumbersLocal;
import com.vitcode.iprayertimes.calender.model.CalendarCellModel;
import com.vitcode.iprayertimes.calender.model.EventModel;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {
    private CalenderDateAdapter adapter;
    private View btnLeft;
    private View btnRight;
    public int mainMonth;
    public int mainYear;
    public ArrayList<CalendarCellModel> monthList;
    private int space;
    private TextView tvMonth;
    private TextView tvMonthMuslim;
    RecyclerView recyclerView;

    ImageView bt_back;

    static int access$008(CalendarActivity calendarActivity) {
        int i = calendarActivity.mainMonth;
        calendarActivity.mainMonth = i + 1;
        return i;
    }

    static int access$010(CalendarActivity calendarActivity) {
        int i = calendarActivity.mainMonth;
        calendarActivity.mainMonth = i - 1;
        return i;
    }

    static int access$108(CalendarActivity calendarActivity) {
        int i = calendarActivity.mainYear;
        calendarActivity.mainYear = i + 1;
        return i;
    }

    static int access$110(CalendarActivity calendarActivity) {
        int i = calendarActivity.mainYear;
        calendarActivity.mainYear = i - 1;
        return i;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
        recyclerView = findViewById(R.id.rcv_calender);
        initEvent();
        setup();
        bt_back=findViewById(R.id.bt_back);
        bt_back.setOnClickListener(v->{onBackPressed();});
    }

    private void setup() {
        this.monthList = new ArrayList<>();
        this.adapter = new CalenderDateAdapter(this, this.monthList) {
            public void OnItemClick(int i) {
                if (CalendarActivity.this.monthList.get(i).getGeorgianDay() != -1) {
                    for (int i2 = 0; i2 < CalendarActivity.this.monthList.size(); i2++) {
                        if (CalendarActivity.this.monthList.get(i2).isSelect()) {
                            CalendarActivity.this.monthList.get(i2).setSelect(false);
                            notifyItemChanged(i2);
                        }
                    }
                    CalendarActivity.this.monthList.get(i).setSelect(true);
                    notifyItemChanged(i);
                    CalendarActivity calendarActivity = CalendarActivity.this;
                    calendarActivity.getTitleDay(calendarActivity.monthList.get(i).getGeorgianDay());
                }
            }
        };
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 7));
        recyclerView.setAdapter(this.adapter);
        moveCurrentDay();

        this.btnLeft.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CalendarActivity calendarActivity = CalendarActivity.this;
                CalendarActivity.access$010(calendarActivity);
                int access$000 = calendarActivity.mainMonth;
                if (access$000 <= 1) {
                    int unused = calendarActivity.mainMonth = 12;
                    CalendarActivity.access$110(calendarActivity);
                    calendarActivity.loadMonthsDayGregorian(calendarActivity.mainMonth, calendarActivity.mainYear);
                    return;
                }
                calendarActivity.loadMonthsDayGregorian(access$000, calendarActivity.mainYear);
            }
        });
        this.btnRight.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CalendarActivity calendarActivity = CalendarActivity.this;
                CalendarActivity.access$008(calendarActivity);
                int access$000 = calendarActivity.mainMonth;
                if (access$000 >= 13) {
                    int unused = calendarActivity.mainMonth = 1;
                    CalendarActivity.access$108(calendarActivity);
                    calendarActivity.loadMonthsDayGregorian(calendarActivity.mainMonth, calendarActivity.mainYear);
                    return;
                }
                calendarActivity.loadMonthsDayGregorian(access$000, calendarActivity.mainYear);
            }
        });
    }
    private void moveCurrentDay() {
        loadGregorianCalendar(new HGDate());
    }

    private void loadGregorianCalendar(HGDate hGDate) {
        this.mainMonth = hGDate.getMonth();
        this.mainYear = hGDate.getYear();
        loadMonthsDayGregorian(hGDate.getMonth(), hGDate.getYear());
    }

    public void loadMonthsDayGregorian(int i, int i2) {
        int i3 = i;
        this.monthList.clear();
        HGDate hGDate = new HGDate();
        hGDate.setGregorian(i2, i3, 1);
        boolean z = false;
        boolean z2 = true;
        while (i3 == hGDate.getMonth()) {
            HGDate hGDate2 = new HGDate(hGDate);
            hGDate2.toHigri();
            Log.d("MyTag", "loadMonthsDayGregorian: hGDate.getDay() = " + hGDate2.getDay() + "hGDate.getDay() = " + hGDate.getDay());
            CalendarCellModel calendarCellModel = new CalendarCellModel(hGDate2.getDay(), hGDate.getDay(), hGDate2.getMonth(), i, hGDate2.weekDay() + 1, hGDate.getYear());
            if (z2) {
                this.space = hGDate.weekDay();
                Log.d("MyTag", "loadMonthsDayGregorian: weekDay=" + this.space);
                z2 = false;
            }
            HGDate hGDate3 = new HGDate();
            if (calendarCellModel.getGeorgianMonth() == hGDate3.getMonth() && calendarCellModel.getGeorgianDay() == hGDate3.getDay() && calendarCellModel.getGeorgianYear() == hGDate3.getYear()) {
                getTitleDay(hGDate3.getDay());
                calendarCellModel.setSelect(true);
                z = true;
            }
            this.monthList.add(calendarCellModel);
            hGDate.nextDay();
        }


        if (!z) {
            getTitleDay(1);
            this.monthList.get(0).setSelect(true);
        }
        for (int i4 = 0; i4 < this.space; i4++) {
            this.monthList.add(0, new CalendarCellModel(-1, -1, -1, -1, -1, -1));
        }
        this.adapter.notifyDataSetChanged();
    }

    public void getTitleDay(int i) {
        HGDate hGDate = new HGDate();
        hGDate.setGregorian(this.mainYear, this.mainMonth, i);
        TextView textView = this.tvMonth;
        StringBuilder sb = new StringBuilder();
        sb.append(NumbersLocal.convertNumberType(this, hGDate.getDay() + ""));
        sb.append(" ");
        sb.append(Dates.gregorianMonthName(this, hGDate.getMonth() + -1));
        sb.append(" ");
        sb.append(NumbersLocal.convertNumberType(this, hGDate.getYear() + ""));
        textView.setText(sb.toString());
        hGDate.toHigri();
        String convertNumberType = NumbersLocal.convertNumberType(this, String.valueOf(hGDate.getDay()).trim());
        String trim = Dates.islamicMonthName(this, hGDate.getMonth() - 1).trim();
        TextView textView2 = this.tvMonthMuslim;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(convertNumberType);
        sb2.append(" ");
        sb2.append(trim);
        sb2.append(" ");
        sb2.append(NumbersLocal.convertNumberType(this, hGDate.getYear() + ""));
        textView2.setText(sb2.toString());
    }

    private void initView() {
        this.btnLeft = findViewById(R.id.bt_left);
        this.btnRight = findViewById(R.id.bt_right);
        this.tvMonth = findViewById(R.id.tv_month);
        this.tvMonthMuslim = findViewById(R.id.tv_month_muslim);
    }

    private void initEvent() {
        RecyclerView recyclerView = findViewById(R.id.rcv_event);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EventDetailAdapter(this, this, getEvents()));
    }

    private ArrayList<EventModel> getEvents() {
        HGDate hGDate = new HGDate();
        hGDate.toHigri();
        ArrayList<EventModel> arrayList = new ArrayList<>();
        hGDate.setHigri(hGDate.getYear(), 1, 1);
        arrayList.add(new EventModel(getResources().getString(R.string.new_year), hGDate.toString(), "https://en.wikipedia.org/wiki/Islamic_New_Year"));
        hGDate.setHigri(hGDate.getYear(), 1, 10);
        arrayList.add(new EventModel(getResources().getString(R.string.ashura), hGDate.toString(), "https://en.wikipedia.org/wiki/Ashura"));
        hGDate.setHigri(hGDate.getYear(), 3, 12);
        arrayList.add(new EventModel(getResources().getString(R.string.mavlid), hGDate.toString(), "https://en.wikipedia.org/wiki/Mawlid"));
        hGDate.setHigri(hGDate.getYear(), 7, 27);
        arrayList.add(new EventModel(getResources().getString(R.string.laylat), hGDate.toString(), "https://en.wikipedia.org/wiki/Isra_and_Mi%27raj"));
        hGDate.setHigri(hGDate.getYear(), 8, 15);
        arrayList.add(new EventModel(getResources().getString(R.string.laylatB), hGDate.toString(), "https://en.wikipedia.org/wiki/Mid-Sha%27ban"));
        hGDate.setHigri(hGDate.getYear(), 9, 1);
        arrayList.add(new EventModel(getResources().getString(R.string.firstR), hGDate.toString(), "https://en.wikipedia.org/wiki/Ramadan"));
        hGDate.setHigri(hGDate.getYear(), 10, 1);
        arrayList.add(new EventModel(getResources().getString(R.string.eidALfitr), hGDate.toString(), "https://en.wikipedia.org/wiki/Eid_al-Fitr"));
        hGDate.setHigri(hGDate.getYear(), 12, 9);
        arrayList.add(new EventModel(getResources().getString(R.string.dayOfArafa), hGDate.toString(), "https://en.wikipedia.org/wiki/Day_of_Arafah"));
        hGDate.setHigri(hGDate.getYear(), 12, 10);
        arrayList.add(new EventModel(getResources().getString(R.string.dayaladha), hGDate.toString(), "https://en.wikipedia.org/wiki/Eid_al-Adha"));
        return arrayList;
    }
}