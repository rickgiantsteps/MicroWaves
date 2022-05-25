package com.unimi.lim.microwaves;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.unimi.lim.microwaves.ui.main.SectionsPagerAdapter;
import com.unimi.lim.microwaves.databinding.ActivityTemperateDettaglioBinding;

public class equabili_dettaglio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.unimi.lim.microwaves.databinding.ActivityTemperateDettaglioBinding binding = ActivityTemperateDettaglioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

    }
}