package kr.hs.e_mirim.lia318.spatial_confusion;

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
        explain_button = (ImageView)findViewById(R.id.explain_button);
        explain_button.setOnClickListener(this);
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
        dialog.setTitle("첫번째 Dialog");
        //dialog.setIcon(R.drawable.got7_jinyoung_icon);
        // dialog.setMessage("여기는 메시지를 쓰는 곳입니다.");
        /*dialog.setItems(itemarr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                butDialog.setText(itemarr[i]);
            }
        });*/

        // 라디오 버튼 목록
        /*dialog.setSingleChoiceItems(itemarr, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                butDialog.setText(itemarr[i]);
            }
        }); // (배열이름, 배열 인덱스 번호, 이벤트) */

        // 체크박스 목록 : 선택 여부 배열(boolean 형태)
        boolean[] checkedItems = {true, false, true, false, true, false, true };
        // 사용할 배열 크기에 맞춰서 작성
        // 사용자에 따라 체크(true)값 설정


        dialog.setPositiveButton("Ok", null); // null => 이벤트 발생x
        dialog.show(); // 꼭 설정하기
    }
}
