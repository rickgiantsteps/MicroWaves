package com.unimi.lim.microwaves;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class TeoriaMicrotonale extends AppCompatActivity implements View.OnTouchListener {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teoria_microtonale);

        Button temperate = findViewById(R.id.temperate);
        temperate.setOnTouchListener(this);
        Button nontemperate = findViewById(R.id.nontemperate);
        nontemperate.setOnTouchListener(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.temperate) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startActivity(new Intent(TeoriaMicrotonale.this, scale_temperate.class));
            }
        }

        if (v.getId() == R.id.nontemperate) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startActivity(new Intent(TeoriaMicrotonale.this, scale_non_temperate.class));
            }
        }

        return false;
    }
}