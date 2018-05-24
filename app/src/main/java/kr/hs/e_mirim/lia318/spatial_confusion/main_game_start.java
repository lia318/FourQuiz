package kr.hs.e_mirim.lia318.spatial_confusion;

/*
 * Created by Admin on 2017-11-08.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Objects;
import java.util.Random;

import static kr.hs.e_mirim.lia318.spatial_confusion.R.id.edit_1;
import static kr.hs.e_mirim.lia318.spatial_confusion.R.id.textView;


public class main_game_start extends MainActivity implements View.OnClickListener {
    String str=null;
    int answer;
    ImageView pass;
    ImageView environment_setting;
    ImageView key;
    ImageView imageOo;
    ImageView imageXx;
    int[] compare; //중복체크 하는 애
    int n;//중복체크에 필요한 애
    String word = "액체괴물,드라이기,다이어리,필기도구,물레방아,손목시계,사자성어,감언이설,이심전심" +
            ",박학다식,전전긍긍,시시비비,십시일반,인과응보,금의환향,사필귀정,국회의원,디자이너," +
            "발레리나,바리스타,바보온달,피노키오,백설공주,신데렐라,갑오개혁,교통사고,자연재해," +
            "신혼여행,추석연휴,충무김밥,파인애플,카페라떼,스파게티,마요네즈,닭가슴살,코카콜라," +
            "샌드위치," + "닭볶음탕,낙지볶음,나무늘보,바다표범,이구아나,청둥오리,황제펭귄," + "대한민국," +
            "콜롬비아,과테말라,이스라엘,이탈리아,자메이카,나미비아,네덜란드,노르웨이," +
            "파키스탄,대형마트,오피스텔,만리장성,가로수길,해수욕장,양떼목장,홍대입구,에버랜드," +
            "한옥마을,롯데월드,도라에몽,세일러문,곰돌이푸,스폰지밥,이누야사,헬로키티,아따맘마," +
            "보노보노,리락쿠마,도날드덕,에이핑크,동방신기,소녀시대,젝스키스,원더걸스,에프엑스," +
            "뉴이스트,인피니트,씨엔블루,미쓰에이,한끼줍쇼,신서유기,무한도전,신혼일기,복면가왕," +
            "삼시세끼,인기가요,동물농장,보니하니,생활영어,국가대표,모의고사,모나리자,바리스타,블랙베리,블루베리,카푸치노," +
            "스킨푸드,아디다스,맥도날드,아리따움," + "뚜레쥬르,루이비통,스타벅스,아마스빈," + "육쌈냉면,피자스쿨,쉑쉑버거,자연별곡," + "계절밥상,던킨도넛,먹쉬돈나,전화위복" +
            "볼리비아,세르비아,싱가포르,아일랜드," + "에콰도르,온두라스,짐바브웨,캄보디아," + "파라과이,가온누리,그린나래,누리보듬," + "국민연금,고슴도치,기말고사,중간고사," +
            "기억상실,레스토랑,막상막하,모래시계," + "물레방아,미니어처,바이올렛,별주부전," + "컴파일러,엑스레이,연지곤지,급속충전," + "유비무환,과유불급,견물생심,와신상담," +
            "일거양득,대기만성,치즈스틱,겨울왕국" + "해바라기,핸드크림,호두과자,호두파이," + "삼각김밥,왕의남자,설국열차,알포인트,맘마미아,화양연화,아기와나,데스노트," +
            "부당거래,해리포터,파파로티,워낭소리,라라랜드,스타워즈,올드보이," +
            "영웅본색,아마겟돈,타이타닉,내부자들,국제시장,맨인블랙,다이하드," +
            "덕혜옹주,주토피아,에일리언,남한산성,청년경찰,원더우면,범죄도시" +
            "아나콘다,순정만화,그래비티,어벤져스";

    long pressedTime = 0;
    String[] c = new String[110];
    String[] data = word.split(",");
    Random random = new Random();
    int rIndex = random.nextInt(c.length);

    TextView textView1, textView2;

    // 서연 : 타이머 부분
    ImageView imgTime;
    int leftTime = 6;

    Handler mHandler = new Handler(Looper.getMainLooper());
    Runnable countTime = new Runnable() {
        @Override
        public void run() {

            mHandler.postDelayed(this, 1000);
            leftTime--;

            if (leftTime == 5) {
                imgTime.setImageResource(R.drawable.number5);
            }

            if (leftTime == 4) {
                imgTime.setImageResource(R.drawable.number4);
            }

            if (leftTime == 3) {
                imgTime.setImageResource(R.drawable.number3);
            }

            if (leftTime == 2) {
                imgTime.setImageResource(R.drawable.number2);
            }

            if (leftTime == 1) {
                imgTime.setImageResource(R.drawable.number1);
            }
            if (leftTime == 0) {
                imgTime.setImageResource(R.drawable.number0);

                LayoutInflater inflater = (LayoutInflater) main_game_start.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.pop, null);

                ((TextView) layout.findViewById(R.id.title)).setText("Game Over");
                ((TextView) layout.findViewById(R.id.content)).setText(
                        "");

                ((Button) layout.findViewById(R.id.ok)).setText("확인");

                float density = main_game_start.this.getResources().getDisplayMetrics().density;

                final PopupWindow pw = new PopupWindow(layout, (int)density*300, (int)density*200);

                ((Button) layout.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        pw.dismiss();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);


                    }
                });
                pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
            }
        }
    };
    ImageView imgStar;
    EditText e;
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_start);

        imgTime = (ImageView)findViewById(R.id.imgTime);

        textView1 = (TextView) findViewById(textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        e = (EditText) findViewById(edit_1);
        findViewById(R.id.answer).setOnClickListener(ClickListener);
        findViewById(R.id.pass).setOnClickListener(ClickListener);
        findViewById(R.id.key).setOnClickListener(ClickListener);
        findViewById(textView);
        findViewById(R.id.textView2);

        imageOo = (ImageView) findViewById(R.id.imageOo);
        imageXx = (ImageView) findViewById(R.id.imageXx);

        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "NanumBarunpenB.ttf");
        e.setTypeface(typeface);
        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);

        int count = word.length();

        mHandler.postDelayed(countTime, 9000);

        String[] c = new String[110];
        String[] d = new String[110];
        String[] data;

        data = word.split(",");

        for (int i = 0; i < 109; i++) {
            c[i] = data[i].substring(0, 2);
        }

        n=0;
        //중복체크
        Random random = new Random();
        int rIndex = random.nextInt(c.length);
        textView1.setText(c[rIndex]);
        textView1.setTextColor(Color.WHITE); //상수이용
        textView2.setText(data[rIndex].substring(1, 2));
        textView2.setTextColor(Color.WHITE); //상수이용

        str = data[rIndex].substring(2, 4);

        random = new Random();
        rIndex = random.nextInt(c.length);

        if(random.nextInt(c.length)==rIndex) {
            textView1.setText(c[rIndex]);
            textView1.setTextColor(Color.WHITE); //상수이용
            textView2.setText(data[rIndex].substring(1, 2));
            textView2.setTextColor(Color.WHITE); //상수이용

            str = data[rIndex].substring(2, 4);
        }
        else{
            textView1.setText(c[rIndex]);
            textView1.setTextColor(Color.WHITE); //상수이용
            textView2.setText(data[rIndex].substring(1, 2));
            textView2.setTextColor(Color.WHITE); //상수이용

            str = data[rIndex].substring(2, 4);


        }
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    } // onCreate

    MainActivity  m = new MainActivity();

    public Button.OnClickListener ClickListener = new View.OnClickListener() {
        String word = "액체괴물,드라이기,다이어리,필기도구,물레방아,손목시계,사자성어,감언이설,이심전심" +
                ",박학다식,전전긍긍,시시비비,십시일반,인과응보,금의환향,사필귀정,국회의원,디자이너," +
                "발레리나,바리스타,바보온달,피노키오,백설공주,신데렐라,갑오개혁,교통사고,자연재해," +
                "신혼여행,추석연휴,충무김밥,파인애플,카페라떼,스파게티,마요네즈,닭가슴살,코카콜라," +
                "샌드위치," + "닭볶음탕,낙지볶음,나무늘보,바다표범,이구아나,청둥오리,황제펭귄," + "대한민국," +
                "콜롬비아,과테말라,이스라엘,이탈리아,자메이카,나미비아,네덜란드,노르웨이," +
                "파키스탄,대형마트,오피스텔,만리장성,가로수길,해수욕장,양떼목장,홍대입구,에버랜드," +
                "한옥마을,롯데월드,도라에몽,세일러문,곰돌이푸,스폰지밥,이누야사,헬로키티,아따맘마," +
                "보노보노,리락쿠마,도날드덕,에이핑크,동방신기,소녀시대,젝스키스,원더걸스,에프엑스," +
                "뉴이스트,인피니트,씨엔블루,미쓰에이,한끼줍쇼,신서유기,무한도전,신혼일기,복면가왕," +
                "삼시세끼,인기가요,동물농장,보니하니,생활용품,국가대표,모의고사,모나리자,바리스타,블랙베리,블루베리,카푸치노," +
                "스킨푸드,아디다스,맥도날드,아리따움," + "뚜레쥬르,루이비통,스타벅스,아마스빈," + "육쌈냉면,피자스쿨,쉑쉑버거,자연별곡," + "계절밥상,던킨도넛,먹쉬돈나,전화위복" +
                "볼리비아,세르비아,싱가포르,아일랜드," + "에콰도르,온두라스,짐바브웨,캄보디아," + "파라과이,가온누리,그린나래,누리보듬," + "국민연금,고슴도치,기말고사,중간고사," +
                "기억상실,레스토랑,막상막하,모래시계," + "물레방아,미니어처,바이올렛,별주부전," + "컴파일러,엑스레이,연지곤지,급속충전," + "유비무환,과유불급,견물생심,와신상담," +
                "일거양득,대기만성,치즈스틱,겨울왕국" + "해바라기,핸드크림,호두과자,호두파이," + "삼각김밥,왕의남자,설국열차,알포인트,맘마미아,화양연화,아기와나,데스노트," +
                "부당거래,해리포터,파파로티,워낭소리,라라랜드,스타워즈,올드보이," +
                "영웅본색,아마겟돈,타이타닉,내부자들,국제시장,맨인블랙,다이하드," +
                "덕혜옹주,주토피아,에일리언,남한산성,청년경찰,원더우면,범죄도시" +
                "아나콘다,순정만화,그래비티,어벤져스";
        String[] result = new String[110];
        String d;


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.answer:
                    //내가 보여준거+사용자가 입력하는 공간의 id.getText/
                    //data[rIndex].substring(1,2)
                    String an = (String) textView1.getText() + e.getText().toString();
                    Log.d("@@@@@@@@:", (String) textView1.getText());
                    //Log.d("@@@@@@@#:", (String) textView2.getText());//
                    Log.d("@@@@@@@$:",e.getText().toString());
                    //Log.d("결과:",an); 자일리톨
                    result = word.split(",");

                    d = (String)textView1.getText()+str;
                    //Log.d("dddddddddddddddddd",d);

                    if (d.equals(an)) {// 정답일 땐 oo를 VISIBLE,

                        //O버튼 그림 띄우기
                        imageOo.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                //Log.d("정답",an);
                                ansCount++;
                                number[0] = ansCount;
                                switch (ansCount) {
                                    /*case 1:
                                        imgStar.setImageResource(R.drawable.star1);
                                        break;
                                    case 2:
                                        imgStar.setImageResource(R.drawable.star2);
                                        break;
                                    case 3:
                                        imgStar.setImageResource(R.drawable.star3);
                                        break;
                                    case 4:
                                        imgStar.setImageResource(R.drawable.star4);
                                        break;*/
                                    case 5:
                                        //imgStar.setImageResource(R.drawable.star5);

                                        LayoutInflater inflater = (LayoutInflater) main_game_start.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        View layout = inflater.inflate(R.layout.pop, null);

                                        ((TextView) layout.findViewById(R.id.title)).setText("Game Clear");
                                        ((TextView) layout.findViewById(R.id.content)).setText(
                                                "");

                                        ((Button) layout.findViewById(R.id.ok)).setText("확인");

                                        float density = main_game_start.this.getResources().getDisplayMetrics().density;

                                        final PopupWindow pw = new PopupWindow(layout, (int) density * 320, (int) density * 200);

                                        ((Button) layout.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                pw.dismiss();
                                                finish();
                                            }
                                        });
                                        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
                                        break;
                                    default:

                                        break;
                                } startActivity(new Intent(main_game_start.this, main_game_start.class));
                            }
                        }, 2000);
                    }
                    else { // 오답일 땐 xx를 VISIBLE
                        imageXx.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                finish();
                                startActivity(new Intent(main_game_start.this, main_game_start.class));
                                //finish();
                            }
                        }, 2000);
                    }
                    break;
                case R.id.pass:
                    startActivity(new Intent(main_game_start.this, main_game_start.class));
                    imageOo.setVisibility(View.INVISIBLE);
                    finish();
                    break;

                case R.id.key:
                    AlertDialog.Builder key = new AlertDialog.Builder(main_game_start.this);
                    key.setIcon(R.drawable.key);

                    key.setIcon(R.drawable.key);
                    key.setTitle("힌트");

                    String str=(String) textView1.getText();
                    //왜넣었나 싶겠지만 원래 textView2까지 두글자였는데 너가 준거로 해보니 두글자가 textView1에 모두 저장 되어있어서 원래 str에 저장해놓고 if문 속이 전부 str이라서

                    //두글자 값 가져와서  조건문에 따라 문자열 띄워주면 됨.
                    Log.d("^^^^^^^^^^^^^^",str);
                    if (Objects.equals(str, "액체") || Objects.equals(str, "드라") || Objects.equals(str, "다이") || Objects.equals(str, "필기")
                            || Objects.equals(str, "물레") || Objects.equals(str, "손목") || Objects.equals(str, "핸드") ||
                            Objects.equals(str, "모래") ||
                            Objects.equals(str, "엑스")|| Objects.equals(str, "생활")){
                        key.setMessage("물건");

                    } else if (Objects.equals(str, "사자") || Objects.equals(str, "감언") || Objects.equals(str, "이심") || Objects.equals(str, "박학") ||
                            Objects.equals(str, "전전") || Objects.equals(str, "시시") || Objects.equals(str, "십시") || Objects.equals(str, "인과") ||
                            Objects.equals(str, "금의") || Objects.equals(str, "사필") || Objects.equals(str, "막상") ||
                            Objects.equals(str, "유비") ||
                            Objects.equals(str, "과유") ||
                            Objects.equals(str, "견물") ||
                            Objects.equals(str, "와신") ||
                            Objects.equals(str, "일거") ||
                            Objects.equals(str, "대기") ||
                            Objects.equals(str, "전화")) {
                        key.setMessage("사자성어");

                    } else if (Objects.equals(str, "국회") || Objects.equals(str, "디자") || Objects.equals(str, "발레") || Objects.equals(str, "바리") || Objects.equals(str, "바보") || Objects.equals(str, "피노") ||
                            Objects.equals(str, "백설") || Objects.equals(str, "신데") || Objects.equals(str, "국가") || Objects.equals(str, "바리") || Objects.equals(str, "모나")) {
                        key.setMessage("직업/사람");

                    } else if (Objects.equals(str, "갑오") || Objects.equals(str, "교통") || Objects.equals(str, "자연")
                            || Objects.equals(str, "신혼") || Objects.equals(str, "추석") || Objects.equals(str, "모의") ||
                            Objects.equals(str, "기말") ||
                            Objects.equals(str, "중간") ||
                            Objects.equals(str, "기억")) {
                        key.setMessage("사건");

                    } else if (Objects.equals(str, "충무") || Objects.equals(str, "파인") || Objects.equals(str, "카페") ||
                            Objects.equals(str, "스파") || Objects.equals(str, "마요") || Objects.equals(str, "닭가") ||
                            Objects.equals(str, "코카") || Objects.equals(str, "샌드") || Objects.equals(str, "닭볶") ||
                            Objects.equals(str, "낙지") || Objects.equals(str, "블랙") || Objects.equals(str, "블루") ||
                            Objects.equals(str, "카푸") || Objects.equals(str, "호두") || Objects.equals(str, "치즈") ||
                            Objects.equals(str, "삼각")) {
                        key.setMessage("음식");

                    } else if (Objects.equals(str, "대한") || Objects.equals(str, "콜롬") || Objects.equals(str, "과테") ||
                            Objects.equals(str, "이스") || Objects.equals(str, "이탈") || Objects.equals(str, "자메") ||
                            Objects.equals(str, "나미") || Objects.equals(str, "네덜") || Objects.equals(str, "노르") || Objects.equals(str, "파키") ||
                            Objects.equals(str, "볼리") || Objects.equals(str, "세르") || Objects.equals(str, "싱가") ||
                            Objects.equals(str, "아일") || Objects.equals(str, "에콰") || Objects.equals(str, "온두") ||
                            Objects.equals(str, "짐바") || Objects.equals(str, "캄보") ||
                            Objects.equals(str, "파라")) {
                        key.setMessage("나라");

                    } else if (Objects.equals(str, "나무") || Objects.equals(str, "바다") || Objects.equals(str, "이구") ||
                            Objects.equals(str, "청둥") || Objects.equals(str, "황제") || Objects.equals(str, "고슴")) {
                        key.setMessage("동물");

                    } else if (Objects.equals(str, "대형") || Objects.equals(str, "오피") || Objects.equals(str, "만리") ||
                            Objects.equals(str, "홍대") || Objects.equals(str, "에버") || Objects.equals(str, "레스") || Objects.equals(str, "한옥")) {
                        key.setMessage("장소");

                    } else if (Objects.equals(str, "도라") || Objects.equals(str, "세일") || Objects.equals(str, "곰돌") ||
                            Objects.equals(str, "스폰") || Objects.equals(str, "이누") || Objects.equals(str, "헬로") ||
                            Objects.equals(str, "아따") || Objects.equals(str, "보노") || Objects.equals(str, "리락") || Objects.equals(str, "도날")) {
                        key.setMessage("캐릭터");

                    } else if (Objects.equals(str, "에이") || Objects.equals(str, "동방") || Objects.equals(str, "소녀") || Objects.equals(str, "젝스") ||
                            Objects.equals(str, "원더") || Objects.equals(str, "에프") || Objects.equals(str, "뉴이") || Objects.equals(str, "인피") ||
                            Objects.equals(str, "씨엔") || Objects.equals(str, "미쓰")) {
                        key.setMessage("아이돌 그룹");

                    } else if (Objects.equals(str, "한끼") || Objects.equals(str, "신서") || Objects.equals(str, "신혼") ||
                            Objects.equals(str, "보니") || Objects.equals(str, "무한") || Objects.equals(str, "삼시") || Objects.equals(str, "복면") ||
                            Objects.equals(str, "인기") || Objects.equals(str, "동물") ) {
                        key.setMessage("TV 프로그램");
                    } else if (Objects.equals(str, "스킨") ||
                            Objects.equals(str, "아디") ||
                            Objects.equals(str, "맥도") ||
                            Objects.equals(str, "아리") ||
                            Objects.equals(str, "뚜레") ||
                            Objects.equals(str, "루이") ||
                            Objects.equals(str, "스타") ||
                            Objects.equals(str, "아마") ||
                            Objects.equals(str, "육쌈") ||
                            Objects.equals(str, "피자") ||
                            Objects.equals(str, "쉑쉑") ||
                            Objects.equals(str, "자연") ||
                            Objects.equals(str, "계절") ||
                            Objects.equals(str, "던킨") ||
                            Objects.equals(str, "먹쉬")) {

                        key.setMessage("브랜드");
                    } else if (Objects.equals(str, "가온") ||
                            Objects.equals(str, "그린") ||
                            Objects.equals(str, "누리")) {
                        key.setMessage("순우리말");
                    } else if (Objects.equals(str, "해바")) {
                        key.setMessage("꽃");

                    } else if (Objects.equals(str, "겨울") || Objects.equals(str, "왕의") ||
                            Objects.equals(str, "설국") ||
                            Objects.equals(str, "알포") ||
                            Objects.equals(str, "맘마") ||
                            Objects.equals(str, "화양") ||
                            Objects.equals(str, "아기") ||
                            Objects.equals(str, "데스") ||
                            Objects.equals(str, "부당") ||
                            Objects.equals(str, "해리") ||
                            Objects.equals(str, "파파") ||
                            Objects.equals(str, "워낭") ||
                            Objects.equals(str, "라라") ||
                            Objects.equals(str, "스타") ||
                            Objects.equals(str, "올드") ||
                            Objects.equals(str, "영웅") ||
                            Objects.equals(str, "아마") ||
                            Objects.equals(str, "타이") ||
                            Objects.equals(str, "내부") ||
                            Objects.equals(str, "국제") ||
                            Objects.equals(str, "맨인") ||
                            Objects.equals(str, "다이") ||
                            Objects.equals(str, "덕혜") ||
                            Objects.equals(str, "주토") ||
                            Objects.equals(str, "에일") ||
                            Objects.equals(str, "남한") ||
                            Objects.equals(str, "청년") ||
                            Objects.equals(str, "원더") ||
                            Objects.equals(str, "범죄") ||
                            Objects.equals(str, "아나") ||
                            Objects.equals(str, "순정") ||
                            Objects.equals(str, "그래") ||
                            Objects.equals(str, "어벤")) {
                        key.setMessage("영화");
                    } else if (Objects.equals(str, "바이")) {
                        key.setMessage("악기");
                    } else if (Objects.equals(str, "별주")) {
                        key.setMessage("이솝우화");
                    } else if (Objects.equals(str, "컴파")) {
                        key.setMessage("개발자라면 무조건 아는 단어");
                    } else {
                        key.setMessage("흥! 힌트 없지롱~");
                    }
                    key.setPositiveButton("알았어~", null); // null => 이벤트 발생x
                    key.show(); // 꼭 설정하기
                    break;
            }//Switch
        }
    };


    public void starImg(){
        switch (ansCount / 5) {
            case 1:
                imgStar.setImageResource(R.drawable.star1);
                break;
            case 2:
                imgStar.setImageResource(R.drawable.star2);
                break;
            case 3:
                imgStar.setImageResource(R.drawable.star3);
                break;
            case 4:
                imgStar.setImageResource(R.drawable.star4);
                break;
            case 5:
                imgStar.setImageResource(R.drawable.star5);

                LayoutInflater inflater = (LayoutInflater) main_game_start.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.pop, null);

                ((TextView) layout.findViewById(R.id.title)).setText("Game Clear");
                ((TextView) layout.findViewById(R.id.content)).setText(
                        "");

                ((Button) layout.findViewById(R.id.ok)).setText("확인");

                float density = main_game_start.this.getResources().getDisplayMetrics().density;

                final PopupWindow pw = new PopupWindow(layout, (int) density * 320, (int) density * 200);

                ((Button) layout.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        pw.dismiss();
                        finish();
                    }
                });

                pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

                break;
            default:

                break;
        } // switch
    } // starImg

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("main_game_start Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)

                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
} // main_game_strat