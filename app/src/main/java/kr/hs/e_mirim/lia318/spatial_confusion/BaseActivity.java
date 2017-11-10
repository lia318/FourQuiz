package kr.hs.e_mirim.lia318.spatial_confusion;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sojun on 2017-11-09.
 */

public class BaseActivity extends Activity {

    private static Typeface typeface;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(this.getAssets(), "NanumSquareL.otf");
        }
        setGlobalFont(getWindow().getDecorView());
    }

    private void setGlobalFont(View view) {
        if (view != null) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int vgCnt = viewGroup.getChildCount();
                for (int i = 0; i < vgCnt; i++) {
                    View v = viewGroup.getChildAt(i);
                    if (v instanceof TextView) {
                        ((TextView) v).setTypeface(typeface);
                    }
                    setGlobalFont(v);
                }
            }
        }
    }
}
// 폰트 바꾸는 코드
