package com.example.microwaves;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.media.AudioTrack;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;


public class EDO11keyboard extends AppCompatActivity implements View.OnTouchListener {

    int edonumber = 11;
    int ottava = 4;
    private EditText a4frequency;
    private int wave = 0;
    private double volume = 1;
    private double[] edo11;
    double a4 = 440;
    private NumberPicker octave;
    private final AudioTrack[] tones = new AudioTrack[edonumber];
    private final Button[] buttons = new Button[edonumber];
    String toneid;
    String lastfreq;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppPerLoStudioDelTemperamento);
        setContentView(R.layout.activity_edo11keyboard);

        for (int i = 0; i < edonumber; i++) {
            String buttonName = "buttonPlayNote"+(i+1);
            int buttonid = getResources().getIdentifier(buttonName, "id", getPackageName());
            buttons[i] = findViewById(buttonid);
            buttons[i].setOnTouchListener(this);
            buttons[i].setText(String.valueOf(i+1));
            buttons[i].setTextSize(13);
        }

        edo11 = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);

        octave = findViewById(R.id.octave);
        octave.setMaxValue(10);
        octave.setMinValue(0);
        octave.setValue(4);
        octave.setOnValueChangedListener((numberPicker, i, i1) -> {
            ottava = octave.getValue();
            edo11 = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);
        });

        SeekBar waveformslider = findViewById(R.id.waveformslider);
        waveformslider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wave = progress;
                waveToast.waveinfo(progress, EDO11keyboard.this);
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
                    edo11 = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);
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
            tones[tone-1] = soundgenerator.generateTone(edo11[tone-1], volume, wave, this, tones, tone-1);
            tones[tone-1].play();
            lastfreq = BigDecimal.valueOf(edo11[tone - 1]).setScale(4, BigDecimal.ROUND_FLOOR) + " Hz";
            ((TextView)findViewById(R.id.lastfrequencytext)).setText(lastfreq);
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            tones[tone-1].release();
        }

        return false;

    }
}