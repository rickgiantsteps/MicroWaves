package com.example.appperlostudiodeltemperamento;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class scale_non_temperate extends AppCompatActivity  implements View.OnTouchListener {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_non_temperate);
        Button nontemperateindettaglio = findViewById(R.id.nontemperateindettaglio);
        nontemperateindettaglio.setOnTouchListener(this);

    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.nontemperateindettaglio) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startActivity(new Intent(this, nontemperate_dettaglio.class));
            }
        }


        return false;
    }
}