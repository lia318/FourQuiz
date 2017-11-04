package kr.hs.e_mirim.lia318.spatial_confusion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by sojun on 2017-11-03.
 */

public class main_game_explain extends AppCompatActivity implements View.OnClickListener { // 현재 클래스에서 OnClickListener 사용 가능
    // Handler Class : 규격화 되어있다. 조건이 있는 인터페이스 추가
    String[] itemarr = {"JB", "Mark", "Jackson", "박진영", "최영재", "BamBam", "김유겸"};
    ImageView reversed_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_explain);
        ImageView reversed_button = (ImageView) findViewById(R.id.reversed_button);
        reversed_button.setOnClickListener(this); // MainActivity class 내에서 감시자 역할
        reversed_button = (ImageView)findViewById(R.id.reversed_button);
        reversed_button.setOnClickListener(this);
    } // end of MainActivity

    @Override
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

/*
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/lia318/Spatial_Confusion.git
git push -u origin master
 */