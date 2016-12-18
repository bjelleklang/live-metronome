package no.chrish.lm.livemetronome;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import java.lang.*;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class Metronome extends AppCompatActivity {

    private static Context appContext;

    public static Context getAppContext(){
        return appContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appContext = getBaseContext();

        setContentView(R.layout.metronomelayout);
        final MetronomeTimer mt = new MetronomeTimer();

        // Update seekbar/bpmfield
        final SeekBar tempoSlider = (SeekBar) findViewById(R.id.temposlider);
        final EditText bpmm = (EditText)findViewById(R.id.txt_bpm);
        final Button tapButton = (Button)findViewById(R.id.btn_tap);
        final Button toggleButton = (Button)findViewById(R.id.btn_met_toggle);
        final Spinner bpmSpinner = (Spinner)findViewById(R.id.txt_beat);
        final Spinner barSpinner = (Spinner)findViewById(R.id.txt_bar);

        toggleButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                MetronomeRunner mr = new MetronomeRunner(Integer.parseInt(bpmm.getText().toString()), "4333");
                mr.run();
            }
        });

        tapButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mt.Tap();
                if (mt.GetBpm() > 0){
                    int bpm = Integer.parseInt(mt.GetBpm()+"");
                    tempoSlider.setProgress(bpm);
                    bpmm.setText(String.valueOf(bpm));
                }
            }
        });

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
                        // Is this remotely possible?
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

        bpmSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int barValue = Integer.parseInt(bpmSpinner.getSelectedItem().toString());
                int beatValue = Integer.parseInt(barSpinner.getSelectedItem().toString());

                MetronomePattern mp = new MetronomePattern(barValue, beatValue);
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.patternLout);
                linearLayout.removeAllViews();

                RadioGroup[] rg = mp.BuildRadioGroup();
                for (int i=0; i<rg.length; i++) {
                    linearLayout.addView(rg[i]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}
