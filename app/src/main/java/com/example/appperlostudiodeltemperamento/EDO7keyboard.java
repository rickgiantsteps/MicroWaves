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

public class EDO7keyboard extends AppCompatActivity implements View.OnTouchListener {

    int edonumber = 7;
    int ottava = 4;
    private EditText a4frequency;
    private int wave = 0;
    private double volume = 1;
    private int noteduration = 500;
    private double[] edo7;
    double a4 = 440;
    private NumberPicker octave;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppPerLoStudioDelTemperamento);
        setContentView(R.layout.activity_edo7keyboard);

        edo7 = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);

        octave = findViewById(R.id.octave);
        octave.setMaxValue(10);
        octave.setMinValue(0);
        octave.setValue(4);
        octave.setOnValueChangedListener((numberPicker, i, i1) -> {
            ottava = octave.getValue();
            edo7 = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);
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

        SeekBar waveformslider = findViewById(R.id.waveformslider);
        waveformslider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wave = progress;

                Toast toast;

                if (progress==0) {
                    toast = Toast.makeText(EDO7keyboard.this, "Wave chosen: sine", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (progress==1) {
                    toast = Toast.makeText(EDO7keyboard.this, "Wave chosen: square", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (progress==2) {
                    toast = Toast.makeText(EDO7keyboard.this, "Wave chosen: triangle", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    toast = Toast.makeText(EDO7keyboard.this, "Wave chosen: saw", Toast.LENGTH_SHORT);
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
                volume = (double) progress /1000;
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
                    edo7 = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.buttonPlayNote1) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo7[0],noteduration,volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration+2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote2) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo7[1],noteduration,volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration+2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote3) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo7[2],noteduration,volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration+2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote4) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo7[3],noteduration,volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration+2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote5) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo7[4],noteduration,volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration+2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote6) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo7[5],noteduration,volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration+2
                );
            }
        }

        if (v.getId() == R.id.buttonPlayNote7) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo7[6],noteduration,volume, wave, this);
                tone.play();
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                tone.release();
                            }
                        },
                        noteduration+2
                );
            }
        }

        return false;
    }
}