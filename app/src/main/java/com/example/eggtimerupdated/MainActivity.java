package com.example.eggtimerupdated;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    Button count_down_button;
    boolean count_down_active=false;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        count_down_button = findViewById(R.id.button);

        seekBar.setProgress(30);
        seekBar.setMax(600);
        updateTextView("0:30");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int min= i/60;
                int sec = i - (min*60);
                String _count_down="";
                if(sec<=9){
                    _count_down =min+":"+"0"+sec;
                }
                else{
                    _count_down =min+":"+sec;
                }
                updateTextView(_count_down);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    public void updateTextView(String count_down){

        textView.setText(count_down);
    }

    public void resetCountDown(){
        countDownTimer.cancel();
        count_down_active=false;
        count_down_button.setText("GO");
        seekBar.setEnabled(true);
        seekBar.setProgress(30);
        updateTextView("0:30");
    }

    public void button_pressed(View view){

        if(count_down_active){
            resetCountDown();
        }
        else{
            count_down_active= true;
            seekBar.setEnabled(false);
            count_down_button.setText("StOP");
            long count_down_value = seekBar.getProgress();
            countDownTimer = new CountDownTimer(count_down_value * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    int min= ((int) l/1000)/60;
                    int sec = ((int) l/1000) - (min*60);
                    String _count_down="";
                    if(sec<=9){
                        _count_down =min+":"+"0"+sec;
                    }
                    else{
                        _count_down =min+":"+sec;
                    }
                    updateTextView(_count_down);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetCountDown();
                }
            }.start();
        }

    }
}
