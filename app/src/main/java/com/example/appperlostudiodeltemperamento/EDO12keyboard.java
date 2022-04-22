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

public class EDO12keyboard extends AppCompatActivity implements View.OnTouchListener {

    int edonumber = 12;
    int ottava = 4;
    private EditText a4frequency;
    private int wave = 0;
    private double volume = 1;
    private double[] edo12;
    double a4 = 440;
    private NumberPicker octave;
    private final AudioTrack[] tones = new AudioTrack[12];
    //private Button[] buttons = new Button[12];

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppPerLoStudioDelTemperamento);
        setContentView(R.layout.activity_edo12keyboard);

        edo12 = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);

        octave = findViewById(R.id.octave);
        octave.setMaxValue(10);
        octave.setMinValue(0);
        octave.setValue(4);
        octave.setOnValueChangedListener((numberPicker, i, i1) -> {
            ottava = octave.getValue();
            edo12 = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);
        });

        /*for (int i = 0; i < 12; i++) {
            String buttonName = "buttonPlayNote"+(i+1);
        }*/


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
        Button buttonPlayNote10 = findViewById(R.id.buttonPlayNote10);
        buttonPlayNote10.setOnTouchListener(this);
        Button buttonPlayNote11 = findViewById(R.id.buttonPlayNote11);
        buttonPlayNote11.setOnTouchListener(this);
        Button buttonPlayNote12 = findViewById(R.id.buttonPlayNote12);
        buttonPlayNote12.setOnTouchListener(this);
        SeekBar waveformslider = findViewById(R.id.waveformslider);
        waveformslider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wave = progress;

                Toast toast;

                if (progress==0) {
                    toast = Toast.makeText(EDO12keyboard.this, "Wave chosen: sine", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (progress==1) {
                    toast = Toast.makeText(EDO12keyboard.this, "Wave chosen: square", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (progress==2) {
                    toast = Toast.makeText(EDO12keyboard.this, "Wave chosen: triangular", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    toast = Toast.makeText(EDO12keyboard.this, "Wave chosen: saw", Toast.LENGTH_SHORT);
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
                    edo12 = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });
    }

    public void generateFreq(int tonenumber) {

        tones[tonenumber] = soundgenerator.generateTone2(edo12[tonenumber],volume, wave, this);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.buttonPlayNote1) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateFreq(0);
                tones[0].play();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                tones[0].release();
            }
        }

        if (v.getId() == R.id.buttonPlayNote2) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateFreq(1);
                tones[1].play();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                tones[1].release();
            }
        }

        if (v.getId() == R.id.buttonPlayNote3) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateFreq(2);
                tones[2].play();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                tones[2].release();
            }
        }

        if (v.getId() == R.id.buttonPlayNote4) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateFreq(3);
                tones[3].play();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                tones[3].release();
            }
        }

        if (v.getId() == R.id.buttonPlayNote5) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateFreq(4);
                tones[4].play();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                tones[4].release();
            }
        }

        if (v.getId() == R.id.buttonPlayNote6) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateFreq(5);
                tones[5].play();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                tones[5].release();
            }
        }

        if (v.getId() == R.id.buttonPlayNote7) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateFreq(6);
                tones[6].play();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                tones[6].release();
            }
        }

        if (v.getId() == R.id.buttonPlayNote8) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateFreq(7);
                tones[7].play();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                tones[7].release();
            }
        }

        if (v.getId() == R.id.buttonPlayNote9) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateFreq(8);
                tones[8].play();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                tones[8].release();
            }
        }

        if (v.getId() == R.id.buttonPlayNote10) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateFreq(9);
                tones[9].play();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                tones[9].release();
            }
        }

        if (v.getId() == R.id.buttonPlayNote11) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateFreq(10);
                tones[10].play();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                tones[10].release();
            }
        }

        if (v.getId() == R.id.buttonPlayNote12) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                generateFreq(11);
                tones[11].play();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                tones[11].release();
            }
        }

        return false;
    }

}