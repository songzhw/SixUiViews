/**
 *  *    GuagualeView.java
 *  *    
 *  *    Create by zhengwang.szw on 2013年11月8日
 *  
 */
package cn.six.open.view.scratchcard;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.app.Activity;
import android.graphics.*;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import cn.six.open.R;

public class ScratchCardActivity extends Activity {
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScratchCardView view = new ScratchCardView(this);
        view.setImageResource(R.drawable.forest1);
        view.setScaleType(ScaleType.FIT_XY);
        setContentView(view, new FrameLayout.LayoutParams(800,800));
    }

}