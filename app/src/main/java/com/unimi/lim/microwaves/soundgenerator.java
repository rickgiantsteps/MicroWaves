package com.unimi.lim.microwaves;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;

import android.media.AudioTrack;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class soundgenerator {


    @SuppressLint("ShowToast")
    // Wave -> (0 = sine, 1 = square, 2 = tri, 3 = saw)
    static AudioTrack generateTone(double freq, double volume, int wave, Context context, AudioTrack[] tones, int tonenumber)
    {

        int samplingfreq = 44100;

        //toasts not used (@SuppressLint("ShowToast")) but aliasing warning can be reimplemented if needed
        Toast toast;

        if (freq > samplingfreq / 2.0) {
            toast = Toast.makeText(context, "Frequency played: " + new BigDecimal(freq).setScale(4, RoundingMode.FLOOR) + " Hz\n\nThe pitch generated is greater than half of the sampling frequency (44100Hz): aliasing might be present", Toast.LENGTH_LONG);
            //toast.show();
            toast.cancel();
        } else if (wave != 0 && freq > 10000) {
            toast = Toast.makeText(context, "Waves are synthesized by using a 'naive sampling' method, non sine waves can be inaccurate and may be affected by aliasing at high frequencies (use the sine wave for better results)", Toast.LENGTH_LONG);
            //toast.show();
            toast.cancel();
        }

        int durationmillsec = 5000;

        if (volume > 1 || volume < 0){
            volume = 1; //max volume = 1 regardless of input
        }

        int count = (int)(samplingfreq * 2.0 * (durationmillsec / 1000.0)) & ~1;
        short[] samples = new short[count];

        for(int i = 0; i < count; i += 2) {

            double sinewave = Math.sin(2 * Math.PI * i / (samplingfreq * 2.0 / freq));

            if (wave == 0){
                //sine wave
                short sample = (short) ((volume * sinewave) * 0x7FFF);
                //makes a stereo signal
                samples[i] = sample;
                samples[i + 1] = sample;

            } else if (wave == 1) {

                //this makes a stereo square wave
                short sample = (short) (sinewave * 0x7FFF);
                if (sample > 0.0) {
                    samples[i] = (short) (volume/2 * 0x7FFF);
                    samples[i + 1] = (short) (volume/2  * 0x7FFF);
                }

                if (sample < 0.0) {
                    samples[i] = (short) -(volume/2  * 0x7FFF);
                    samples[i + 1] = (short) -(volume/2  * 0x7FFF);
                }

            } else if (wave == 2) {

                //this makes a stereo triangle wave
                short sample = (short) (volume * ((Math.acos(sinewave)/1.5708)-1) * 0x7FFF);
                samples[i] = sample;
                samples[i + 1] = sample;

            } else if (wave == 3) {

                //this makes a stereo sawtooth wave
                short sample =(short) (volume * 2*(i%(samplingfreq * 2.0 / freq)/(samplingfreq * 2.0 / freq)-1) * 0x7FFF);
                samples[i] = sample;
                samples[i + 1] = sample;

            }

        }

        AudioTrack track = new AudioTrack.Builder()
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build())
                .setAudioFormat(new AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(samplingfreq)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
                        .build())
                .setBufferSizeInBytes(count * (Short.SIZE / 8))
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build();

        //loops the signal indefinitely
        track.setLoopPoints(0, track.getBufferSizeInFrames(), -1);

        //writes the tone to the audio track object
        track.write(samples, 0, count);

        tones[tonenumber] =  track;
        return tones[tonenumber];
    }

}
