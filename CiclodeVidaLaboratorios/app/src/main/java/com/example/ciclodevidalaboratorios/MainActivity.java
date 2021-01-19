package com.example.ciclodevidalaboratorios;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends Activity {

    private Bundle savedInstanceState;
    private int seconds = 0, cont=1;
    private boolean running,restat,parcial;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState !=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    //@Override
    public void onSaveSavedInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
        restat=true;
    }

    public  void vueltas(View view){
        parcial=true;
    }

    private void runTimer(){
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final EditText vuelasTxt=(EditText)findViewById(R.id.vueltas);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours,minutes,secs);
                timeView.setText(time);

                if(running){
                    seconds ++;
                }

                if(parcial){
                    vuelasTxt.setText(cont +"  "+time+"\n"+vuelasTxt.getText().toString());cont++;
                    parcial=false;
                }
                if(restat){
                    cont=1;
                    vuelasTxt.setText("");
                    restat=false;
                }

                handler.postDelayed(this, 1000);
            }
        });


    }
}