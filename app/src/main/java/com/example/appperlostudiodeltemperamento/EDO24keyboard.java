package com.example.appperlostudiodeltemperamento;

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
import android.widget.Toast;


public class EDO24keyboard extends AppCompatActivity implements View.OnTouchListener {

    int edonumber = 24;
    int ottava = 4;
    private EditText a4frequency;
    private int wave = 0;
    private double volume = 1;
    private int noteduration = 500;
    private double[] edo24;
    double a4 = 440;
    private NumberPicker octave;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppPerLoStudioDelTemperamento);
        setContentView(R.layout.activity_edo24keyboard);

        edo24 = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);

        octave = findViewById(R.id.octave);
        octave.setMaxValue(10);
        octave.setMinValue(0);
        octave.setValue(4);
        octave.setOnValueChangedListener((numberPicker, i, i1) -> {
            ottava = octave.getValue();
            edo24 = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);
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
        Button buttonPlayNote10 = findViewById(R.id.buttonPlayNote10);
        buttonPlayNote10.setOnTouchListener(this);
        Button buttonPlayNote11 = findViewById(R.id.buttonPlayNote11);
        buttonPlayNote11.setOnTouchListener(this);
        Button buttonPlayNote12 = findViewById(R.id.buttonPlayNote12);
        buttonPlayNote12.setOnTouchListener(this);
        Button buttonPlayNote13 = findViewById(R.id.buttonPlayNote13);
        buttonPlayNote13.setOnTouchListener(this);
        Button buttonPlayNote14 = findViewById(R.id.buttonPlayNote14);
        buttonPlayNote14.setOnTouchListener(this);
        Button buttonPlayNote15 = findViewById(R.id.buttonPlayNote15);
        buttonPlayNote15.setOnTouchListener(this);
        Button buttonPlayNote16 = findViewById(R.id.buttonPlayNote16);
        buttonPlayNote16.setOnTouchListener(this);
        Button buttonPlayNote17 = findViewById(R.id.buttonPlayNote17);
        buttonPlayNote17.setOnTouchListener(this);
        Button buttonPlayNote18 = findViewById(R.id.buttonPlayNote18);
        buttonPlayNote18.setOnTouchListener(this);
        Button buttonPlayNote19 = findViewById(R.id.buttonPlayNote19);
        buttonPlayNote19.setOnTouchListener(this);
        Button buttonPlayNote20 = findViewById(R.id.buttonPlayNote20);
        buttonPlayNote20.setOnTouchListener(this);
        Button buttonPlayNote21 = findViewById(R.id.buttonPlayNote21);
        buttonPlayNote21.setOnTouchListener(this);
        Button buttonPlayNote22 = findViewById(R.id.buttonPlayNote22);
        buttonPlayNote22.setOnTouchListener(this);
        Button buttonPlayNote23 = findViewById(R.id.buttonPlayNote23);
        buttonPlayNote23.setOnTouchListener(this);
        Button buttonPlayNote24 = findViewById(R.id.buttonPlayNote24);
        buttonPlayNote24.setOnTouchListener(this);
        SeekBar waveformslider = findViewById(R.id.waveformslider);
        waveformslider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wave = progress;

                Toast toast;

                if (progress==0) {
                    toast = Toast.makeText(EDO24keyboard.this, "Wave chosen: sine", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (progress==1) {
                    toast = Toast.makeText(EDO24keyboard.this, "Wave chosen: square", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (progress==2) {
                    toast = Toast.makeText(EDO24keyboard.this, "Wave chosen: triangle", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    toast = Toast.makeText(EDO24keyboard.this, "Wave chosen: saw", Toast.LENGTH_SHORT);
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
                noteduration = progress + 1;
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
                    edo24 = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);
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
                AudioTrack tone = soundgenerator.generateTone(edo24[0],noteduration,volume, wave, this);
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
                AudioTrack tone = soundgenerator.generateTone(edo24[1],noteduration,volume, wave, this);
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
                AudioTrack tone = soundgenerator.generateTone(edo24[2],noteduration,volume, wave, this);
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
                AudioTrack tone = soundgenerator.generateTone(edo24[3],noteduration,volume, wave, this);
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
                AudioTrack tone = soundgenerator.generateTone(edo24[4],noteduration,volume, wave, this);
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
                AudioTrack tone = soundgenerator.generateTone(edo24[5],noteduration,volume, wave, this);
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
                AudioTrack tone = soundgenerator.generateTone(edo24[6],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote8) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[7],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote9) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[8],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote10) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[9],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote11) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[10],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote12) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[11],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote13) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[12],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote14) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[13],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote15) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[14],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote16) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[15],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote17) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[16],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote18) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[17],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote19) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[18],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote20) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[19],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote21) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[20],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote22) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[21],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote23) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AudioTrack tone = soundgenerator.generateTone(edo24[22],noteduration,volume, wave, this);
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

        if (v.getId() == R.id.buttonPlayNote24) {
            AudioTrack tone = soundgenerator.generateTone(edo24[23],noteduration,volume, wave, this);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
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