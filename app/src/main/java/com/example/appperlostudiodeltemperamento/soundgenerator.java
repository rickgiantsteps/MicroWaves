package com.example.appperlostudiodeltemperamento;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;

import android.media.AudioTrack;
import android.widget.Toast;

import java.math.BigDecimal;


public class soundgenerator {

    // Wave -> (0 = sine, 1 = square, 2 = tri, 3 = saw)
    static AudioTrack generateTone(double freq, int durationmillsec, double volume, int wave, Context context)
    {
        Toast toast;

        if (freq > 44100.0 / 2) {
            toast = Toast.makeText(context, "Frequency played: " + new BigDecimal(freq).setScale(4, BigDecimal.ROUND_FLOOR) + " Hz\n\nThe pitch generated is greater than half of the sampling frequency (44100Hz): aliasing might be present", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast = Toast.makeText(context, "Frequency played: " + new BigDecimal(freq).setScale(4, BigDecimal.ROUND_FLOOR) + " Hz", Toast.LENGTH_SHORT);
            //toast = Toast.makeText(context, "Frequency played: " + freq + " Hz", Toast.LENGTH_SHORT);
            toast.show();
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    },
                    750
            );
        }


        if (volume > 1 || volume < 0){
            volume = 1; //max volume = 1 regardless of input
        }

        int count = (int)(44100.0 * 2.0 * (durationmillsec / 1000.0)) & ~1;
        short[] samples = new short[count];

        for(int i = 0; i < count; i += 2) {

            double sinewave = Math.sin(2 * Math.PI * i / (44100.0 * 2.0 / freq));

            if (wave == 0){
                short sample = (short)((volume * sinewave) * 0x7FFF);
                //makes a stereo signal
                samples[i] = sample;
                samples[i + 1] = sample;

            } else if (wave == 1) {

                //this makes a stereo square wave
                short sample = (short)(sinewave * 0x7FFF);
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
                short sample = (short)(volume * ((Math.acos(sinewave)/1.5708)-1) * 0x7FFF);
                samples[i] = sample;
                samples[i + 1] = sample;

            } else if (wave == 3) {

                //this makes a stereo sawtooth wave
                short sample =(short) (volume * 2*(i%(44100.0 * 2.0 / freq)/(44100.0 * 2.0 / freq)-1) * 0x7FFF);
                samples[i] = sample;
                samples[i + 1] = sample;

            }

        }

        //this audio track builder is deprecated, trying the new one

        //AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
        //        AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
        //        count * (Short.SIZE / 8), AudioTrack.MODE_STATIC);


        //new audio track builder
       AudioTrack track = new AudioTrack.Builder()
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build())
                .setAudioFormat(new AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(44100)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
                        .build())
                .setBufferSizeInBytes(count * (Short.SIZE / 8))
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build();

        track.write(samples, 0, count);
        return track;
    }

    static AudioTrack generateTone2(double freq, double volume, int wave, Context context)
    {

        Toast toast;

        if (freq > 44100.0 / 2) {
            toast = Toast.makeText(context, "Frequency played: " + new BigDecimal(freq).setScale(4, BigDecimal.ROUND_FLOOR) + " Hz\n\nThe pitch generated is greater than half of the sampling frequency (44100Hz): aliasing might be present", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast = Toast.makeText(context, "Frequency played: " + new BigDecimal(freq).setScale(4, BigDecimal.ROUND_FLOOR) + " Hz", Toast.LENGTH_SHORT);
            //toast = Toast.makeText(context, "Frequency played: " + freq + " Hz", Toast.LENGTH_SHORT);
            toast.show();
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    },
                    750
            );
        }

        int durationmillsec = 10000;

        if (volume > 1 || volume < 0){
            volume = 1; //max volume = 1 regardless of input
        }

        int count = (int)(44100.0 * 2.0 * (durationmillsec / 1000.0)) & ~1;
        short[] samples = new short[count];

        for(int i = 0; i < count; i += 2) {

            double sinewave = Math.sin(2 * Math.PI * i / (44100.0 * 2.0 / freq));

            if (wave == 0){
                short sample = (short)((volume * sinewave) * 0x7FFF);
                //makes a stereo signal
                samples[i] = sample;
                samples[i + 1] = sample;

            } else if (wave == 1) {

                //this makes a stereo square wave
                short sample = (short)(sinewave * 0x7FFF);
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
                short sample = (short)(volume * ((Math.acos(sinewave)/1.5708)-1) * 0x7FFF);
                samples[i] = sample;
                samples[i + 1] = sample;

            } else if (wave == 3) {

                //this makes a stereo sawtooth wave
                short sample =(short) (volume * 2*(i%(44100.0 * 2.0 / freq)/(44100.0 * 2.0 / freq)-1) * 0x7FFF);
                samples[i] = sample;
                samples[i + 1] = sample;

            }

        }

        //this audio track builder is deprecated, trying the new one

        //AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
        //        AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
        //        count * (Short.SIZE / 8), AudioTrack.MODE_STATIC);


        //new audio track builder
        AudioTrack track = new AudioTrack.Builder()
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build())
                .setAudioFormat(new AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(44100)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
                        .build())
                .setBufferSizeInBytes(count * (Short.SIZE / 8))
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build();

        //this should make a continuous tone when a button is pressed
        track.setLoopPoints(0, track.getBufferSizeInFrames(), -1);

        track.write(samples, 0, count);
        return track;
    }

}
