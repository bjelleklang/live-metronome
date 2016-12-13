package no.chrish.lm.livemetronome;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilTest {
    @Test
    public void MetronomeTimerTestTap() throws Exception {
        MetronomeTimer mt = new MetronomeTimer();

        mt.Tap();
        Thread.sleep(1500);

        mt.Tap();
        Thread.sleep(500);

        mt.Tap();

        // Diff should be fairly set:
        Long[] data = mt.GetQueueArray();

        long result = mt.GetBpm();

        Assert.assertTrue(result < 65 && result > 55);
    }
}