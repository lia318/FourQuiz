package kr.hs.e_mirim.lia318.spatial_confusion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sojun on 2017-11-09.
 */

public class main_SplashActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this,MainActivity.class));
        finish();
    } // onCreate
} // main_SplashActivity
