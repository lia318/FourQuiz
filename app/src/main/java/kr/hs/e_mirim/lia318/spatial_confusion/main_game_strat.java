package kr.hs.e_mirim.lia318.spatial_confusion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by sojun on 2017-11-03.
 */

public class main_game_strat extends AppCompatActivity implements View.OnClickListener {
    ImageView answer, pass, environment_setting, key, imageOo, imageXx;
    long pressedTime=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_strat);

        findViewById(R.id.answer).setOnClickListener(ClickListener);
        findViewById(R.id.pass).setOnClickListener(ClickListener);
        findViewById(R.id.environment_setting).setOnClickListener(this);
        findViewById(R.id.key).setOnClickListener(this);

        imageOo = (ImageView)findViewById(R.id.imageOo);
        imageXx = (ImageView)findViewById(R.id.imageXx);
    } // onCreate

    Button.OnClickListener ClickListener=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.answer :
                    // 정답일 땐 oo를 VISIBLE, 오답일 땐 xx를 VISIBLE
                    imageOo.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            imageOo.setVisibility(View.INVISIBLE);
                            // 다음 문제로 넘어가게...
                        }
                    }, 1000);
                    //startActivity(new Intent(main_game_strat.this, main_game_strat.class));
                    //finish();
                    break;
                case R.id.pass :
                    startActivity(new Intent(main_game_strat.this, main_game_strat.class));
                    finish();
                    break;
            } // switch
        }
    };

    public void onBackPressed() { // 뒤로가기 표시
        if ( pressedTime == 0 ) {
            Toast.makeText(main_game_strat.this, "한 번 더 누르면 종료됩니다." , Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if ( seconds > 2000 ) {
                Toast.makeText(main_game_strat.this, "한 번 더 누르면 종료됩니다." , Toast.LENGTH_SHORT).show();
                pressedTime = 0 ;
            }
            else {
                super.onBackPressed();
            }
        }

    }

    @Override
    public void onClick(View view) { // 환경설정 + key를 다이얼로그 보여주는 코드
        switch (view.getId()) {
            case R.id.environment_setting :
                AlertDialog.Builder environment_setting = new AlertDialog.Builder(this);
                environment_setting.setIcon(R.drawable.environment_setting);
                environment_setting.setTitle("환경설정");
                environment_setting.setMessage("");
                environment_setting.setPositiveButton("Ok", null); // null => 이벤트 발생x
                environment_setting.show(); // 꼭 설정하기
                break;
            case R.id.key :
                AlertDialog.Builder key = new AlertDialog.Builder(this);
                key.setIcon(R.drawable.key);
                key.setTitle("key");
                key.setMessage("");
                key.setPositiveButton("알았어~", null); // null => 이벤트 발생x
                key.show(); // 꼭 설정하기
                break;
        } // switch
    }
} // main_game_strat