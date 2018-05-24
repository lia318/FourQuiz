package kr.hs.e_mirim.lia318.spatial_confusion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView play_button, explain_button;
    long pressedTime=0;
    int ansCount = 0;
    int number[] = new int[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        explain_button = (ImageView) findViewById(R.id.explain_button);
        explain_button.setOnClickListener(this); // MainActivity class 내에서 감시자 역할
    } // onCreate

    @Override
    public void onClick(View view) { // 시작 버튼 클릭 시 액비티비 전환
        switch (view.getId()) {
            case R.id.play_button :
                startActivity(new Intent(MainActivity.this, main_game_start.class));
                finish();
                break;
            case R.id.explain_button:
                popup();
                break;
        }
    };

    public void onBackPressed() { // 뒤로가기 표시
        if ( pressedTime == 0 ) {
            Toast.makeText(MainActivity.this, "한 번 더 누르면 종료됩니다." , Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if ( seconds > 2000 ) {
                Toast.makeText(MainActivity.this, "한 번 더 누르면 종료됩니다." , Toast.LENGTH_SHORT).show();
                pressedTime = 0 ;
            }
            else {
                super.onBackPressed();
            }
        }

    }

    public void popup() {
        try {
            LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.pop, null);

            ((TextView) layout.findViewById(R.id.title)).setText("게임방법");
            ((TextView) layout.findViewById(R.id.content)).setText(
                    "♥공간스런 혼란의 혼란스런 게임♥\n\n" +
                    "우리 다같이 네글자 퀴즈를 맞춰보아요!\n" +
                    "보기에 주어진 글자를 통해\n"+"나머지 두글자를 맞춰주세요!\n" +
                    "어려운 순간! key와 pass버튼을 통해\n"+"위기를 모면해보세요!\n");

            ((Button) layout.findViewById(R.id.ok)).setText("확인");

            float density= MainActivity.this.getResources().getDisplayMetrics().density;

            final PopupWindow pw= new PopupWindow(layout, (int)density*360, (int)density*420);

            ((Button)layout.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    pw.dismiss();
                }
            });

            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    } // onClick
}

/*
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/lia318/Spatial_Confusion.git
git push -u origin master
 */