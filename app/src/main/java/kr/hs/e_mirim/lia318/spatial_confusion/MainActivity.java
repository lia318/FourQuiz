package kr.hs.e_mirim.lia318.spatial_confusion;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView play_button, explain_button;
    long pressedTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.play_button).setOnClickListener(ClickListener);
        findViewById(R.id.explain_button).setOnClickListener(ClickListener);

        explain_button = (ImageView) findViewById(R.id.explain_button);
        explain_button.setOnClickListener(this); // MainActivity class 내에서 감시자 역할
    } // onCreate

    Button.OnClickListener ClickListener=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.play_button :
                    startActivity(new Intent(MainActivity.this, main_game_strat.class));
                    finish();
                    break;
            }
        }
    };

    public void onBackPressed() {
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

    public void onClick(View v) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setIcon(R.drawable.appicon);
        dialog.setTitle("게임방법");
        dialog.setMessage("♥공간스런 혼란의 혼란스러운 네글자 게임♥\n\n" +
                "우리 다같이 네글자 퀴즈를 맞춰보아요!\n"+
                "보기에 주어진 글자를 통해 나머지 두글자를 맞춰주세요!"+
                "어려운 순간이 있다면 key와 pass버튼을 통해 위기를 모면해보세요!\n"+
                "그럼 오지게 잘해보세용~ \n\n\n"+
                "[key] 단어에 대한 키워드가 궁금해!\n"+
                "pass 다음 문제로 고고! 단, 3번만 사용 가능해요!");

        dialog.setPositiveButton("알았어~", null); // null => 이벤트 발생x
        dialog.show(); // 꼭 설정하기
    }
}
