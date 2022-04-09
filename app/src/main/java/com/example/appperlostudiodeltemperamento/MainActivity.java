package com.example.appperlostudiodeltemperamento;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppPerLoStudioDelTemperamento);
        setContentView(R.layout.activity_main);

        Button keyboard = findViewById(R.id.keyboard);
        keyboard.setOnTouchListener(this);
        Button explanation = findViewById(R.id.explanation);
        explanation.setOnTouchListener(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.keyboard) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startActivity(new Intent(MainActivity.this, scaleSelection.class));
            }
        }

        if (v.getId() == R.id.explanation) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startActivity(new Intent(MainActivity.this, TeoriaMicrotonale.class));
            }
        }

        return false;
    }
}