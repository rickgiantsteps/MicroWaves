package com.example.appperlostudiodeltemperamento;

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
import android.widget.Toast;

public class Alphakeyboard extends AppCompatActivity implements View.OnTouchListener {

    int quinta = 4;
    private EditText a4frequency;
    private int wave = 0;
    private double volume = 1;
    private int noteduration = 500;
    private double[] alpha;
    double a4 = 440;
    private NumberPicker fifth;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppPerLoStudioDelTemperamento);
        setContentView(R.layout.activity_alphakeyboard);

        alpha = pitchcalculator.calculateAlpha(a4, quinta);

        fifth = findViewById(R.id.quinta);
        fifth.setMaxValue(10);
        fifth.setMinValue(0);
        fifth.setValue(4);
        fifth.setOnValueChangedListener((numberPicker, i, i1) -> {
            quinta = fifth.getValue();
            alpha = pitchcalculator.calculateAlpha(a4, quinta);
        });


        Button buttonPlayNote1 = findViewById(R.id.buttonPlayNote1);
        buttonPlayNote1.setOnTouchListener(this);
        Button buttonPlayNote2 = findViewById(R.id.buttonPlayNote2);
        buttonPlayNote2.setOnTouchListener(this);
        Button buttonPlayNote3 = findViewById(R.id.buttonPlayNote3);
        buttonPlayNote3.setOnTouchListener(this);
        Button buttonPlayNote4 = findViewById(R.id.buttonPlayNote4);
        buttonPlayNote4.setOnTouchListener(this);
        Button buttonPlayNote5 = findViewById(R.id.buttonPlayNote5);
        buttonPlayNote5.setOnTouchListener(this);
        Button buttonPlayNote6 = findViewById(R.id.buttonPlayNote6);
        buttonPlayNote6.setOnTouchListener(this);
        Button buttonPlayNote7 = findViewById(R.id.buttonPlayNote7);
        buttonPlayNote7.setOnTouchListener(this);
        Button buttonPlayNote8 = findViewById(R.id.buttonPlayNote8);
        buttonPlayNote8.setOnTouchListener(this);
        Button buttonPlayNote9 = findViewById(R.id.buttonPlayNote9);
        buttonPlayNote9.setOnTouchListener(this);

        SeekBar waveformslider = findViewById(R.id.waveformslider);
        waveformslider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wave = progress;

                Toast toast;

                if (progress == 0) {
                    toast = Toast.makeText(Alphakeyboard.this, "Wave chosen: sine", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (progress == 1) {
                    toast = Toast.makeText(Alphakeyboard.this, "Wave chosen: square", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (progress == 2) {
                    toast = Toast.makeText(Alphakeyboard.this, "Wave chosen: triangle", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    toast = Toast.makeText(Alphakeyboard.this, "Wave chosen: saw", Toast.LENGTH_SHORT);
                    toast.show();
                }

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        },
                        1500
                );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBar duration = findViewById(R.id.durataslider);
        duration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                noteduration = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBar volumeslider = findViewById(R.id.volumeslider);
        volumeslider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volume = (double) progress / 1000;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        a4frequency = findViewById(R.id.a4frequency);
        a4frequency.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if (!a4frequency.getText().toString().isEmpty()) {
                    a4 = Double.parseDouble(a4frequency.getText().toString());
                    alpha = pitchcalculator.calculateAlpha(a4, quinta);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.buttonPlayNote1) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(alpha[0], noteduration, volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration + 2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote2) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(alpha[1], noteduration, volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration + 2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote3) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(alpha[2], noteduration, volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration + 2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote4) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(alpha[3], noteduration, volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration + 2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote5) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(alpha[4], noteduration, volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration + 2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote6) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(alpha[5], noteduration, volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration + 2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote7) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(alpha[6], noteduration, volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration + 2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote8) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(alpha[7], noteduration, volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration + 2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote9) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(alpha[8], noteduration, volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration + 2
                );
            }
        }

        return false;

    }
}