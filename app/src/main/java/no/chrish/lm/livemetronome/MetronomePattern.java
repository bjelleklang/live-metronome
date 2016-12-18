package no.chrish.lm.livemetronome;

import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by christoffer.hafsahl on 16.12.2016.
 */

public class MetronomePattern {
    public static int HIGH_NOTE = 3;
    public static int MED_NOTE = 2;
    public static int LOW_NOTE = 1;

    // Number of beats per bar
    protected int beat;

    // Type of beats. Can be 4, 8 or 16. Not used at the moment.
    protected int bar;

    public MetronomePattern(int bt, int br){
        beat = bt;
        bar = br;
    }

    /**
     * Default beat patterns for some beats.
     * Each pattern contains a series of digits
     * corresponding to high, medium or low beats (1->3).
     */
    public String BuildDefaultPattern(){
        String pat="1333";

        switch (beat){
            case 2:
                pat = "13";
                break;
            case 3:
                pat = "133";
                break;
            case 4:
            default:
                pat = "1333";
                break;
            case 5:
                pat = "13323";
                break;
            case 6:
                pat = "133133";
                break;
            case 7:
                pat = "1333233";
                break;
        }

        return pat;
    }

    public RadioGroup[] BuildRadioGroup(){
        RadioGroup[] rg = new RadioGroup[beat];

        char[] defaultPattern = BuildDefaultPattern().toCharArray();


        for (int i =0; i<rg.length; i++){
            rg[i] = new RadioGroup(Metronome.getAppContext());

            RadioButton rb = new RadioButton(Metronome.getAppContext());
            rb.setId(HIGH_NOTE);
            rb.setText("High");
            rg[i].addView(rb);

            rb = new RadioButton(Metronome.getAppContext());
            rb.setId(MED_NOTE);
            rb.setText("Medium");
            rg[i].addView(rb);

            rb = new RadioButton(Metronome.getAppContext());
            rb.setId(LOW_NOTE);
            rb.setText("Low");
            rb.setSelected(true);
            rg[i].addView(rb);

            if (defaultPattern[i] == '3'){
                rg[i].check(LOW_NOTE);
            } else if (defaultPattern[i] == '2'){
                rg[i].check(MED_NOTE);
            } else if (defaultPattern[i] == '1'){
                rg[i].check(HIGH_NOTE);
            }
        }

        return rg;
    }
}
