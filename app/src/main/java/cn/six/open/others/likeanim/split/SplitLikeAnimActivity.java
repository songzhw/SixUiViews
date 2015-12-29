package cn.six.open.others.likeanim.split;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;

import cn.six.open.util.UiUtil;

/**
 * Created by songzhw on 2015/12/29
 */
public class SplitLikeAnimActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SplitMyCirleView view = new SplitMyCirleView(this);
        int size = UiUtil.dp2px(this, 200);
        setContentView(view, new FrameLayout.LayoutParams(size, size));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.start();
            }
        });
    }

}


class SplitMyCirleView extends View {
    private static final int START_COLOR = 0xFFFF5722;
    private static final int END_COLOR = 0xFFFFC107;
    private Paint fillPaint,  clearPaint;
    private float x, y, radius, outProgress, innerProgress;
    private Bitmap tmpBitmap;
    private Canvas tmpCanvas;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public SplitMyCirleView(Context context) {
        super(context);
        init();
    }

    private void init(){
        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        clearPaint = new Paint();
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(w,h) / 3;
        x = w / 2;
        y = h / 2;

        tmpBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        tmpCanvas = new Canvas(tmpBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        tmpCanvas.drawColor(0xffffff, PorterDuff.Mode.CLEAR); // clear the canvas

        fillPaint.setColor( (Integer) argbEvaluator.evaluate(outProgress, START_COLOR, END_COLOR));
        tmpCanvas.drawCircle(x, y , radius * outProgress , fillPaint );

        tmpCanvas.drawCircle(x, y , radius * innerProgress, clearPaint);

        canvas.drawBitmap(tmpBitmap, 0, 0, null);
    }

    public void start(){
        outProgress = 0;
        innerProgress = 0;

        // For skilled eye there is one more thing - our outer circle changes its color based on current progress.
        // It’s done by ArgbEvaluator class which transforms two colors based on given fraction



        handler.sendEmptyMessageDelayed(11, 50);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            invalidate();
            if(outProgress <= 1) {
                outProgress += 0.1;
                if(outProgress >= 0.5) { innerProgress += 0.15; }
                handler.sendEmptyMessageDelayed(11, 50);
            }
        }
    };
}