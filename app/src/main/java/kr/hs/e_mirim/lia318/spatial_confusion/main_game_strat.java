package kr.hs.e_mirim.lia318.spatial_confusion;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by sojun on 2017-11-03.
 *
 *
 */

public class main_game_strat extends AppCompatActivity implements View.OnClickListener {
    int answer;
    ImageView pass;
    ImageView environment_setting;
    ImageView key;
    ImageView imageOo;
    ImageView imageXx;
    String word="감언이설,갑오징어,갑오개혁,액체괴물,감언이설," +
            "사자성어,대한민국,엑스레이,국회의원,알쏭달쏭," +
            "교통사고,사필귀정,상하좌우,이심전심,충무김밥," +
            "디자이너,드라이기,발레리나,스파게티,나무늘보," +
            "카페라떼,자연재해,대형마트,샌드위치,비눗방울," +
            "겨드랑이,모나리자,와이파이,연지곤지,바리스타," +
            "파인애플,마요네즈,자일리톨,우측통행,박학다식," +
            "금의환햔,시시비비,십시일반,인과응보,프로그램," +
            "현모양처,시나리오,치아교정,오피스텔,국민연금," +
            "귀차니즘,기억상실,깐따삐아,다이어리,닭가슴살," +
            "데스노트,도라에몽,만리장성,무한도전,물레방아," +
            "미니어처,별주부전,스타트업,신혼여행,아스피린," +
            "아카시아,에이핑크,이솝우화,즐겨찾기,전전긍긍," +
            "추석연휴,캐리커쳐,코카콜라,크리스탈,바보온달," +
            "피노키오,필기도구";
    long pressedTime=0;
    String [] c=new String[word.length()-1];
    String[] data=word.split(",");
    Random random=new Random();
    int rIndex=random.nextInt(c.length);

    //이거 파일처리 하다가 8일아침까지 하긴 힘들거같아서 일단 임시방편으로 조금만 저장 해뒀어.파일처리 할게ㅜㅜ
    //String b[];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_strat);
        findViewById(R.id.answer).setOnClickListener(ClickListener);
        findViewById(R.id.pass).setOnClickListener(ClickListener);
        findViewById(R.id.environment_setting).setOnClickListener(this);
        findViewById(R.id.key).setOnClickListener(this);
        findViewById(R.id.textView);
        findViewById(R.id.textView2);

        TextView textView1=(TextView)findViewById(R.id.textView);
        TextView textView2=(TextView)findViewById(R.id.textView2);

        TextView textView3=(TextView)findViewById(R.id.textView3);
        TextView textView4=(TextView)findViewById(R.id.textView4);
        textView3.setVisibility(View.GONE);
        textView4.setVisibility(View.GONE);
        imageOo = (ImageView)findViewById(R.id.imageOo);
        imageXx = (ImageView)findViewById(R.id.imageXx);

        int count=word.length();
        //이 부분을 배열 갯수에 따라서 a.length()랑 -1도 해봤는데, ArrayIndexOutOfBounds가 뜨더라....

        String [] c=new String[71];//상수 값을 count-1로 해봤는데 자꾸 -1을 붙여도 ArrayIndexOutOf떠서 일단 상수로 해뒀어
        String[] data;

        data=word.split(",");

        for(int i=0;i<71;i++){//상수 값을 count-1로 해봤는데 자꾸 -1을 붙여도 ArrayIndexOutOf떠서 일단 상수로 해뒀어
            c[i]=data[i].substring(0,2);
        }
        Random random=new Random();
        int rIndex=random.nextInt(c.length);

        textView1.setText(c[rIndex]);
        textView2.setText(data[rIndex].substring(1,2));


    } // onCreate

    Button.OnClickListener ClickListener=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.answer:
                        //사용자가 입력한 값+화면글자네글자와 맞는지 비교
                        /*if(data.equals(c[0]+data[rIndex].substring(1,2)+data[rIndex].substring(2,3)+data[rIndex].substring(3,4))) {
                            Drawable drawable = getResources().getDrawable(
                                    R.drawable.o_but);

                            // XML 에 있는 ImageView 위젯에 이미지 셋팅
                            ImageView imageView = (ImageView) findViewById(R.id.relativeLayout);
                            imageView.setImageDrawable(drawable);
                        }*/
                        //else { // 정답일 땐 oo를 VISIBLE, 오답일 땐 xx를 VISIBLE
                                new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    imageOo.setVisibility(View.INVISIBLE);
                                    // 다음 문제로 넘어가게...
                                }
                            }, 3000);
                            startActivity(new Intent(main_game_strat.this, main_game_strat.class));
                            finish();
                            break;
                       // }


                case R.id.pass :
                    startActivity(new Intent(main_game_strat.this, main_game_strat.class));
                    // drawable 리소스 객체 가져오기
                    Drawable drawable = getResources().getDrawable(
                            R.drawable.x_but);

                    // XML 에 있는 ImageView 위젯에 이미지 셋팅
                    ImageView imageView = (ImageView) findViewById(R.id.relativeLayout);
                    imageView.setImageDrawable(drawable);

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