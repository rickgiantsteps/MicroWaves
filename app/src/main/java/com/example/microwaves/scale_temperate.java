package com.example.microwaves;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class scale_temperate extends AppCompatActivity  implements View.OnTouchListener {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_temperate);

        Button temperateindettaglio = findViewById(R.id.temperateindettaglio);
        temperateindettaglio.setOnTouchListener(this);

    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.temperateindettaglio) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startActivity(new Intent(this, temperate_dettaglio.class));
            }
        }


        return false;
    }
}