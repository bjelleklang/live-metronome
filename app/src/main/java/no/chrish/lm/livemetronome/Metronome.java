package no.chrish.lm.livemetronome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class Metronome extends AppCompatActivity {
    SeekBar tempoSlider = (SeekBar) findViewById(R.id.temposlider);
    int tempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metronomelayout);


    }

}
