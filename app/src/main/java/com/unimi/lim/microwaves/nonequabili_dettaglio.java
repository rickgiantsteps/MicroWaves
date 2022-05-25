package com.unimi.lim.microwaves;

import android.os.Bundle;

import com.unimi.lim.microwaves.ui.main.SectionsPagerAdapter2;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.unimi.lim.microwaves.databinding.ActivityNontemperateDettaglioBinding;

public class nonequabili_dettaglio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.unimi.lim.microwaves.databinding.ActivityNontemperateDettaglioBinding binding = ActivityNontemperateDettaglioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter2 sectionsPagerAdapter = new SectionsPagerAdapter2(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }
}