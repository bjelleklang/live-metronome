package no.chrish.lm.livemetronome;

import java.util.concurrent.ArrayBlockingQueue;
import java.lang.*;

/**
 * A simple container to measure bpm set by tapping
 * Created by christoffer.hafsahl on 13.12.2016.
 */

public class MetronomeTimer {
    int DEFAULT_SIZE = 3;

    // First idx in queue is newest
    long[] queue;

    /** Initing the queue, setting all elements to 0 */
    public MetronomeTimer(){
        queue = new long[DEFAULT_SIZE];

        for (int i=0; i<queue.length; i++){
            queue[i] = 0;
        }
    }

    public void Tap(){
        // Move existing values, discarding the oldest one
        for(int i=queue.length-2; i>=0; i--){
            queue[i+1] = queue[i];
        }

        queue[0] = System.currentTimeMillis();
    }

    public long[] GetQueueArray(){
        return queue;
    }

    /**
     * Get the bpm of the registered samples.
     * Works by averaging the differences on the intervals between taps.
     *
     * Not a flexible implementation, but checking 2 diffs on tap should be sufficient.
     * Not really likely to change in the future.
     * */
    public long GetBpm(){
        long sumOfSampleDiffs = 0;
        long prevSample = 0;

        // Number of samples are total minus one. First sample does not produce a diff, it's just a starting point used to calculate diffs.
        int numSamples = 0;

        // Get avg between taps. Ignore if taps happen with >3 sec intervals
        for (int i=queue.length-2; i>-1; i--){ // -2 to get the second-to-last index
            long qDiff = queue[i]-queue[i+1];
            if (qDiff < 3001) {
                sumOfSampleDiffs += qDiff;
                numSamples++;
            }
        }

        long bpm = 0;
        if (sumOfSampleDiffs > 0){
            bpm = 60000/(sumOfSampleDiffs /numSamples);
        }

        return bpm;
    }
}
