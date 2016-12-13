package no.chrish.lm.livemetronome;

import java.util.concurrent.ArrayBlockingQueue;
import java.lang.*;

/**
 * A simple container to measure bpm set by tapping
 * Created by christoffer.hafsahl on 13.12.2016.
 */

public class MetronomeTimer {
    int DEFAULT_SIZE = 3;

    ArrayBlockingQueue<Long> queue;

    public MetronomeTimer(){
        queue = new ArrayBlockingQueue(DEFAULT_SIZE);
        long nada = 0;

        for (int i=0; i<queue.size(); i++){
            queue.add(nada);
        }
    }

    public void Tap(){
        queue.add(System.currentTimeMillis());
    }

    public Long[] GetQueueArray(){
        Long[] l = new Long[queue.size()];
        return queue.toArray(l);
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
        int numSamples = 2;

        Long[] l = GetQueueArray();
        sumOfSampleDiffs += l[1]-l[0];
        sumOfSampleDiffs += l[2]-l[1];

        long bpm = 60000/(sumOfSampleDiffs /numSamples);
        return bpm;
    }
}
