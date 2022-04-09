package com.example.appperlostudiodeltemperamento;

import android.os.Bundle;

import com.example.appperlostudiodeltemperamento.ui.main.SectionsPagerAdapter2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.appperlostudiodeltemperamento.databinding.ActivityNontemperateDettaglioBinding;

public class nontemperate_dettaglio extends AppCompatActivity {

    private ActivityNontemperateDettaglioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNontemperateDettaglioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter2 sectionsPagerAdapter = new SectionsPagerAdapter2(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }
}