package com.example.appperlostudiodeltemperamento;

import androidx.appcompat.app.AppCompatActivity;

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

public class nEDOkeyboard extends AppCompatActivity implements View.OnTouchListener {

    int edonumber = 2;
    int ottava = 4;
    private EditText a4frequency;
    private NumberPicker nedo;
    private NumberPicker octave;
    private int wave = 0;
    private double volume = 1;
    private int noteduration = 500;
    private double[] edo;
    double a4 = 440;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nedokeyboard);

        edo = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);

        octave = findViewById(R.id.octave);
        octave.setMaxValue(10);
        octave.setMinValue(0);
        octave.setValue(4);
        octave.setOnValueChangedListener((numberPicker, i, i1) -> {
            ottava = octave.getValue();
            edo = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);
        });

        SeekBar waveformslider = findViewById(R.id.waveformslider);
        waveformslider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                wave = progress;

                Toast toast;

                if (progress==0) {
                    toast = Toast.makeText(nEDOkeyboard.this, "Wave chosen: sine", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (progress==1) {
                    toast = Toast.makeText(nEDOkeyboard.this, "Wave chosen: square", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (progress==2) {
                    toast = Toast.makeText(nEDOkeyboard.this, "Wave chosen: triangle", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    toast = Toast.makeText(nEDOkeyboard.this, "Wave chosen: saw", Toast.LENGTH_SHORT);
                    toast.show();
                }

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        },
                        1000
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


        nedo = findViewById(R.id.nedo);
        nedo.setMaxValue(8);
        nedo.setMinValue(2);
        nedo.setValue(2);
        nedo.setOnValueChangedListener((numberPicker, i, i1) -> {

            for (int k = 1; k <= nedo.getMaxValue(); k++) {
                String buttonidstring = "buttonPlayNote" + k;
                int buttonid = getResources().getIdentifier(buttonidstring, "id", getPackageName());
                findViewById(buttonid).setBackgroundColor(getResources().getColor(R.color.buttonblue));
            }

            for (int k = nedo.getMaxValue(); k > nedo.getValue(); k--) {
                String buttonidstring = "buttonPlayNote" + k;
                int buttonid = getResources().getIdentifier(buttonidstring, "id", getPackageName());
                findViewById(buttonid).setBackgroundColor(getResources().getColor(R.color.dark_gray_bg));
            }

            edonumber = nedo.getValue();
            edo = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);
        });

       for (int i = nedo.getMaxValue(); i > nedo.getValue() ; i--) {
            String buttonidstring = "buttonPlayNote" + i;
            int buttonid = getResources().getIdentifier(buttonidstring, "id", getPackageName());
            findViewById(buttonid).setBackgroundColor(getResources().getColor(R.color.dark_gray_bg));
        }

        a4frequency = findViewById(R.id.a4frequency);
        a4frequency.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if (!a4frequency.getText().toString().isEmpty()) {
                    a4 = Double.parseDouble(a4frequency.getText().toString());
                    edo = pitchcalculator.calculateTemperateScale(a4, ottava, edonumber);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.buttonPlayNote1) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                try {
                    AudioTrack tone = soundgenerator.generateTone(edo[0], noteduration, volume, wave, this);
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
                } catch (Exception e) {}
            }
        }

        if (v.getId() == R.id.buttonPlayNote2) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                try {
                    AudioTrack tone = soundgenerator.generateTone(edo[1], noteduration, volume, wave, this);
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
                } catch (Exception e) {}
            }
        }

        if (v.getId() == R.id.buttonPlayNote3) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                try {
                    AudioTrack tone = soundgenerator.generateTone(edo[2], noteduration, volume, wave, this);
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
                } catch (Exception e) {}
            }
        }

        if (v.getId() == R.id.buttonPlayNote4) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                try {
                    AudioTrack tone = soundgenerator.generateTone(edo[3], noteduration, volume, wave, this);
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
                } catch (Exception e) {}
            }
        }

        if (v.getId() == R.id.buttonPlayNote5) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                try {
                    AudioTrack tone = soundgenerator.generateTone(edo[4], noteduration, volume, wave, this);
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
                } catch (Exception e) {}
            }
        }

        if (v.getId() == R.id.buttonPlayNote6) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                try {
                    AudioTrack tone = soundgenerator.generateTone(edo[5], noteduration, volume, wave, this);
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
                } catch (Exception e) {}
            }
        }

        if (v.getId() == R.id.buttonPlayNote7) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                try {
                    AudioTrack tone = soundgenerator.generateTone(edo[6], noteduration, volume, wave, this);
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
                } catch (Exception e) {}
            }
        }

        if (v.getId() == R.id.buttonPlayNote8) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                try {
                    AudioTrack tone = soundgenerator.generateTone(edo[7], noteduration, volume, wave, this);
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
                } catch (Exception e) {}
            }
        }

        return false;
    }
}