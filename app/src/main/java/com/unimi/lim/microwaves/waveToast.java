package com.unimi.lim.microwaves;

import android.content.Context;
import android.widget.Toast;

public class waveToast {

    static void waveinfo(int type, Context context) {

        Toast toast;

        if (type==0) {
            toast = Toast.makeText(context, "Wave chosen: sine", Toast.LENGTH_SHORT);
            toast.show();
        } else if (type==1) {
            toast = Toast.makeText(context, "Wave chosen: square", Toast.LENGTH_SHORT);
            toast.show();
        } else if (type==2) {
            toast = Toast.makeText(context, "Wave chosen: triangular", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast = Toast.makeText(context, "Wave chosen: saw", Toast.LENGTH_SHORT);
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
}
