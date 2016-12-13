package no.chrish.lm.livemetronome;

import android.support.v7.app.AppCompatActivity;
import java.lang.*;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

public class Metronome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metronomelayout);

        // Update seekbar/bpmfield
        final SeekBar tempoSlider = (SeekBar) findViewById(R.id.temposlider);
        final EditText bpmm = (EditText)findViewById(R.id.txt_bpm);

        bpmm.setOnFocusChangeListener(new EditText.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    try {
                        int bpmValue = Integer.parseInt(bpmm.getText().toString());

                        if (bpmValue < 301 && bpmValue > 20) {
                            tempoSlider.setProgress(bpmValue);
                        }else if (bpmValue < 20) {
                            tempoSlider.setProgress(20);
                            bpmm.setText(String.valueOf(20));
                        } else {
                            bpmm.setText(String.valueOf(tempoSlider.getProgress()));
                        }
                    }catch (NumberFormatException e){

                    }
                }
            }
        });

        tempoSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                if (progress < 20){
                    bpmm.setText(String.valueOf(20));
                    seekBar.setProgress(20);
                } else {
                    bpmm.setText(String.valueOf(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

    }

}
