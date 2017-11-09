package kr.hs.e_mirim.lia318.spatial_confusion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sojun on 2017-11-04.
 */

public class game_button extends AppCompatActivity implements View.OnClickListener {
    Button but1, but2;
    EditText edit_1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_start);

        findViewById(R.id.but1).setOnClickListener(this);
        findViewById(R.id.but2).setOnClickListener(this);
        findViewById(R.id.edit_1).setOnClickListener(this);

    } // onCreate

    @Override
    public void onClick(View view) {
        if(edit_1 != null){
            but1.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorAccent));
            but2.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorAccent));
        }

        /*if(edit_1 == 비교할 단어){
        }

        */
    } // onClick
} // game_button

