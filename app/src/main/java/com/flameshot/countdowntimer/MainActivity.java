package com.flameshot.countdowntimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button btnStop;
    Button btnStart;
    Button btnResume;
    EditText txtEnterTime;
    String getString = null;
    long timeEscaped;
    long timeNew;
    CountDownTimer mainTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textEdit);
        btnStart = findViewById(R.id.start);
        btnResume = findViewById(R.id.resume);
        btnStop = findViewById(R.id.stop);
        txtEnterTime = findViewById(R.id.txtTime);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO code START button function
                getString = txtEnterTime.getText().toString();
                if (getString.length() != 0){
                    timeNew = Long.parseLong(getString);
                    textView.setText(getString);
                    mainTimer = startNewCountDown((timeNew*1000)+1000);
                    btnStart.setText("Pause");
                }

            }
        });

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainTimer.cancel();
                mainTimer = startNewCountDown(timeEscaped);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO code STOP button function
                mainTimer.cancel();
                btnStop.setText("Pause");
            }
        });
    }

    public void setTime(long timeInput)
    {
        timeEscaped = timeInput;
    }

    public CountDownTimer startNewCountDown(long timeNew)
    {
        CountDownTimer countDownTimer = new CountDownTimer(timeNew, 1000) {

            public void onTick(long millisUntilFinished) {
                setTime(millisUntilFinished);
                textView.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textView.setText("done!");
            }
        }.start();
        return countDownTimer;
    }
}
