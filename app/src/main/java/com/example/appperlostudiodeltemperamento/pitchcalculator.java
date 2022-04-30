package com.example.appperlostudiodeltemperamento;

public class pitchcalculator {

    static double[] calculateTemperateScale(double startingnote, int ottava, int subdivision) {

        double root = Math.pow(2, 1.0/subdivision);
        double[] edo = new double[subdivision];

        for (int i = 0; i < subdivision; i += 1) {
            if (i==0) {
                edo[i]=startingnote;
            } else {
                edo[i]=startingnote*Math.pow(root, i);
            }
        }

        if (ottava<4) {

            for(int i=0; i<subdivision; i++) {
                edo[i] = edo[i] / Math.pow(2,(4-ottava));
            }

        } else if (ottava>4) {

            for(int i=0; i<subdivision; i++) {
                edo[i] = edo[i] * Math.pow(2,(ottava-4));
            }

        }

        return edo;
    }

    static double[] calculateZarlinoNatural(double startingnote, int ottava) {

        double[] rapporti = {1, 9.0/8, 5.0/4, 4.0/3, 3.0/2, 5.0/3, 15.0/8};
        double[] natural = new double[7];


        for (int i = 0; i < 7; i += 1) {

            natural[i] = startingnote * rapporti[i];

        }


        if (ottava<4) {

            for(int i=0; i<7; i++) {
                natural[i] = natural[i] / Math.pow(2,(4-ottava));
            }

        } else if (ottava>4) {

            for(int i=0; i<7; i++) {
                natural[i] = natural[i] * Math.pow(2,(ottava-4));
            }

        }

        return natural;

        }

    static double[] calculatePitagora(double startingnote, int ottava) {

        double[] rapporti = {1, 9.0/8, 81.0/64, 4.0/3, 3.0/2, 27.0/16, 243.0/128};
        double[] natural = new double[7];


        for (int i = 0; i < 7; i += 1) {

            natural[i] = startingnote * rapporti[i];

        }


        if (ottava<4) {

            for(int i=0; i<7; i++) {
                natural[i] = natural[i] / Math.pow(2,(4-ottava));
            }

        } else if (ottava>4) {

            for(int i=0; i<7; i++) {
                natural[i] = natural[i] * Math.pow(2,(ottava-4));
            }

        }

        return natural;

    }

    static double[] calculateAlpha(double startingnote, int quinta) {

        double perfect5th = startingnote*(3.0/2);
        double step = (perfect5th-startingnote)/9;
        double[] alpha = new double[9];



        for (int i = 0; i < 9; i += 1) {

            if (i==0) {
                alpha[i] = startingnote;
            } else {
                alpha[i] = startingnote + step*i;
            }

        }

        if (quinta<4) {

            for (int i = 0; i < 6-quinta; i += 1) {
                startingnote = perfect5th;
                perfect5th = startingnote*(2.0/3);

            }


            for (int i = 0; i < 9; i += 1) {

                perfect5th = startingnote*(3.0/2);
                step = (perfect5th-startingnote)/9;

                if (i==0) {
                    alpha[i] = startingnote;
                } else {
                    alpha[i] = startingnote + step*i;
                }

            }

        } else if (quinta>4) {

            for (int i = 0; i < quinta-4; i += 1) {
                startingnote = perfect5th;
                perfect5th = startingnote*(3.0/2);
                step = (perfect5th-startingnote)/9;
            }


            for (int i = 0; i < 9; i += 1) {

                if (i==0) {
                    alpha[i] = startingnote;
                } else {
                    alpha[i] = startingnote + step*i;
                }

            }

        }

        return alpha;

    }

    static double[] calculateBohlenPierce(double startingnote, int tritave) {

        double root = Math.pow(3, 1.0/13);
        double[] bp = new double[13];

        for (int i = 0; i < 13; i += 1) {
            if (i==0) {
                bp[i]=startingnote;
            } else {
                bp[i]=startingnote*Math.pow(root, i);
            }
        }

        if (tritave<4) {

            for(int i=0; i<13; i++) {
                bp[i] = bp[i] / Math.pow(3,(4-tritave));
            }

        } else if (tritave>4) {

            for(int i=0; i<13; i++) {
                bp[i] = bp[i] * Math.pow(3,(tritave-4));
            }

        }

        return bp;

    }

    static double[] calculateChinese(double startingnote, int octave) {

        double[] rapporti = {1, 2187.0/2048, 9.0/8, 1968.0/1630, 81.0/64, 1771.0/1311, 729.0/512, 3.0/2, 6561.0/4096, 27.0/16, 5905.0/3277, 243.0/128,};
        double[] shierlu = new double[12];


        for (int i = 0; i < 12; i += 1) {

            shierlu[i] = startingnote * rapporti[i];

        }


        if (octave<4) {

            for(int i=0; i<12; i++) {
                shierlu[i] = shierlu[i] / Math.pow(3,(4-octave));
            }

        } else if (octave>4) {

            for(int i=0; i<12; i++) {
                shierlu[i] = shierlu[i] * Math.pow(3,(octave-4));
            }

        }

        return shierlu;

    }

}
