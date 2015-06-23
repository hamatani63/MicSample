package com.example.simanishi.micsample;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Mic mic;
    private Handler mHandler = new Handler();
    TextView soundVolumeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundVolumeTV = (TextView) findViewById(R.id.soundVolume);
    }

    @Override
    public void onResume() {
        super.onResume();

        mic = new Mic();
        mic.setOnVolumeReachedListener(
            new Mic.OnReachedVolumeListener() {
                public void onReachedVolume(final short volume) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Log.v("VOLUME", String.valueOf(volume));
                            soundVolumeTV.setText(String.valueOf(volume));

                        }
                    });
                }
            }
        );

        new Thread(mic).start(); //録音開始
    }

    @Override
    public void onPause() {
        super.onPause();
        mic.stop(); //録音停止
    }
}
