package com.example.todo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.todo.R;

import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_activity);

        myTimerTextView = findViewById(R.id.timer_textView);
        myTimerButton = findViewById(R.id.timer_button);

        String[] secondsValue = new String[60];
        for (int i = 0; i < 60; i++){
            secondsValue[i] = String.valueOf(i);
        }

        mySecondsTimerNumberPicker = findViewById(R.id.timerSeconds_numberPicker);
        mySecondsTimerNumberPicker.setMinValue(0);
        mySecondsTimerNumberPicker.setMaxValue(59);
        mySecondsTimerNumberPicker.setDisplayedValues(secondsValue);
        mySecondsTimerNumberPicker.setOnValueChangedListener((numberPicker, i, i1) -> mySecondsTimerNumber = numberPicker.getValue());

        String[] minutesValues = new String[61];
        for (int i = 0; i < 61; i++){
            minutesValues[i] = String.valueOf(i);
        }

        myMinutesTimerNumberPicker = findViewById(R.id.timerMinutes_numberPicker);
        myMinutesTimerNumberPicker.setMinValue(0);
        myMinutesTimerNumberPicker.setMaxValue(60);
        myMinutesTimerNumberPicker.setValue(25);
        myMinutesTimerNumberPicker.setDisplayedValues(minutesValues);
        myMinutesTimerNumberPicker.setOnValueChangedListener((numberPicker, i, i1) -> myMinutesTimerNumber = numberPicker.getValue());
    }

    public void startTimer(View view) {
        myTimerButton.setEnabled(false);
        mySecondsTimerNumberPicker.setVisibility(View.INVISIBLE);
        myMinutesTimerNumberPicker.setVisibility(View.INVISIBLE);
        new CountDownTimer((myMinutesTimerNumber * 1000 * 60) + (mySecondsTimerNumber * 1000), 1000) {

            public void onTick(long millisUntilFinished) {
                long seconds = 0;
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                if(minutes == 0){
                    seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                }else{
                    seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - (minutes * 60);
                }

                myTimerTextView.setText(minutes + ":" + seconds);
            }

            public void onFinish() {
                myTimerTextView.setText("Done!");
                myTimerButton.setEnabled(true);
                mySecondsTimerNumberPicker.setVisibility(View.VISIBLE);
                myMinutesTimerNumberPicker.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private TextView myTimerTextView;
    private Button myTimerButton;
    private NumberPicker mySecondsTimerNumberPicker;
    private NumberPicker myMinutesTimerNumberPicker;
    private int mySecondsTimerNumber;
    private int myMinutesTimerNumber = 25;
}
