package com.unimi.lim.microwaves;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.AudioTrack;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BohlenPiercekeyboard extends AppCompatActivity  implements View.OnTouchListener  {

    int notenumber = 13;
    int trittava = 4;
    private EditText a4frequency;
    private int wave = 0;
    private double volume = 1;
    private double[] bp;
    double a4 = 440;
    private NumberPicker tritave;
    private final AudioTrack[] tones = new AudioTrack[notenumber];
    private final Button[] buttons = new Button[notenumber];
    String toneid;
    String lastfreq;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MicroWaves);
        setContentView(R.layout.activity_bohlen_piercekeyboard);

        for (int i = 0; i < notenumber; i++) {
            String buttonName = "buttonPlayNote"+(i+1);
            int buttonid = getResources().getIdentifier(buttonName, "id", getPackageName());
            buttons[i] = findViewById(buttonid);
            buttons[i].setOnTouchListener(this);
            buttons[i].setText(String.valueOf(i+1));
            buttons[i].setTextSize(13);
        }

        bp = pitchcalculator.calculateBohlenPierce(a4, trittava);

        tritave = findViewById(R.id.tritave);
        tritave.setMaxValue(6);
        tritave.setMinValue(1);
        tritave.setValue(4);
        tritave.setOnValueChangedListener((numberPicker, i, i1) -> {
            trittava = tritave.getValue();
            bp = pitchcalculator.calculateBohlenPierce(a4, trittava);
        });

        SeekBar waveformslider = findViewById(R.id.waveformslider);
        waveformslider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wave = progress;
                waveToast.waveinfo(progress, BohlenPiercekeyboard.this);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        SeekBar volumeslider = findViewById(R.id.volumeslider);
        volumeslider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volume = (double) progress /1000;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });

        a4frequency = findViewById(R.id.a4frequency);
        a4frequency.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (!a4frequency.getText().toString().isEmpty()) {
                    a4 = Double.parseDouble(a4frequency.getText().toString());
                    bp = pitchcalculator.calculateBohlenPierce(a4, trittava);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        toneid = v.getContext().getResources().getResourceName(v.getId());
        int tone;

        if (!Character.isDigit(toneid.charAt(toneid.length() - 2))) {
            tone = Integer.parseInt(toneid.substring(toneid.length() - 1));
        } else {
            tone = Integer.parseInt(toneid.substring(toneid.length() - 2));
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            tones[tone-1] = soundgenerator.generateTone(bp[tone-1], volume, wave, this, tones, tone-1);
            tones[tone-1].play();
            lastfreq = BigDecimal.valueOf(bp[tone - 1]).setScale(4, RoundingMode.FLOOR) + " Hz";
            ((TextView)findViewById(R.id.lastfrequencytext)).setText(lastfreq);
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            tones[tone-1].release();
        }

        return false;

    }

}