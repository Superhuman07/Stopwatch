package com.example.admin.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
private int seconds= 0;
    private boolean running;
    private boolean wasRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");

        }
        runTimer();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }



    // onPause helps the app to remember variables when the app isnt in the foreground
    @Override
    protected void onPause()
    {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    // onResume helps the app to remember variables when the app comes back in the foreground
    @Override
    protected void onResume()
    {
        super.onResume();
        if(wasRunning)
        {
            running = true;
        }
    }

    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view)
    {
        running = false;
    }

    public void onClickReset(View view)
    {
        running = false;
        seconds = 0;
    }

    private void runTimer()
    {   final Handler handler = new Handler();
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/ 3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);

                timeView.setText(time);

                if(running)
                {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });



    }

}
