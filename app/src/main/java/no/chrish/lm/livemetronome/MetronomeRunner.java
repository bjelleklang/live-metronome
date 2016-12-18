package no.chrish.lm.livemetronome;

import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by christoffer.hafsahl on 15.12.2016.
 */

public class MetronomeRunner implements Runnable {
    protected boolean shouldStop;
    protected long currentBpm;
    protected String pattern;

    public MetronomeRunner(long bpm, String pat){
        shouldStop = false;
        currentBpm = bpm;
        pattern = pat;
    }

    public void run(){
        SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        int lowSound = sp.load(Metronome.getAppContext(), R.raw.dampenedclick, 1);
        int medSound = sp.load(Metronome.getAppContext(), R.raw.loudclick, 1);
        int highSound = sp.load(Metronome.getAppContext(), R.raw.loucerclick, 1);

        char[] charPattern = pattern.toCharArray();
        int loc = 0;

        while (!shouldStop){
            // play pattern here
            if (charPattern[loc] == MetronomePattern.LOW_NOTE){
                sp.play(lowSound, 1, 1, 0, 0, 1);
            } else if(charPattern[loc] == MetronomePattern.MED_NOTE){
                sp.play(medSound, 1, 1, 0, 0, 1);
            } else if(charPattern[loc] == MetronomePattern.HIGH_NOTE) {
                sp.play(highSound, 1, 1, 0, 0, 1);
            }


                // sleep
            try {
                Thread.sleep(sleepPeriod());
            } catch (InterruptedException e){

            }
        }
    }

    public void stopMetronome(){
        shouldStop = true;
    }

    protected long sleepPeriod(){
        return 60000/currentBpm;
    }
}
