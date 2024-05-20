package com.vitcode.iprayertimes.abcd;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vitcode.iprayertimes.R;
import com.vitcode.iprayertimes.abcd.Fragments.AzkarFragment;
import com.vitcode.iprayertimes.abcd.Fragments.OtherFragment;
import com.vitcode.iprayertimes.abcd.Fragments.PrayerTimesFragment;
import com.vitcode.iprayertimes.abcd.Fragments.Qqibla;
import com.vitcode.iprayertimes.abcd.Fragments.QuranFragment;
import com.vitcode.iprayertimes.splash.NavigationActivity;

public class Homee extends AppCompatActivity {
    private static final int MENU_HOME = R.id.menu_prayer_times;
    private static final int MENU_PRAYER_TIMES = R.id.menu_quran;
    private static final int MENU_QURAN = R.id.menu_Azkar;
    private static final int MENU_QIBLA = R.id.menu_qibla;
    private static final int MENU_SETTINGS = R.id.menu_settings;


    ImageView OpennavIMG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homee);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        OpennavIMG=findViewById(R.id.OpennavIMG);
        OpennavIMG.setOnClickListener(v -> {startActivity(new Intent(Homee.this, NavigationActivity.class));
        });

        // Default fragment
        loadFragment(new PrayerTimesFragment());

        // Handling navigation item clicks
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment fragment = null;
                // Use if-else statements to handle item selection
                int itemId = item.getItemId();
                if (itemId == MENU_HOME) {
                    fragment = new PrayerTimesFragment();
                } else if (itemId == MENU_PRAYER_TIMES) {
                    fragment = new QuranFragment();
                } else if (itemId == MENU_QURAN) {
                    fragment = new AzkarFragment();
                }else if (itemId == MENU_QIBLA) {
                    fragment = new Qqibla();
                }else if (itemId == MENU_SETTINGS) {
                    fragment = new OtherFragment();
                }
                return loadFragment(fragment);
            }
        });
    }

    // Method to load fragments
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }
}
