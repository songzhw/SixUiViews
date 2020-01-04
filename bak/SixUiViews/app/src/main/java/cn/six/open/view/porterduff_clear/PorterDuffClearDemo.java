package cn.six.open.view.porterduff_clear;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import cn.six.open.R;

/**
 * Created by songzhw on 2016/2/4
 */
public class PorterDuffClearDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1.
//        setContentView(R.layout.activity_porterduff_clear);

        // 2.
        setContentView(R.layout.activity_porterduff_clear_two);
    }


}
