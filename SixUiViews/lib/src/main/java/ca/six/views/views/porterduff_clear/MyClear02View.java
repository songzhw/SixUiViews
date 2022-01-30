package ca.six.views.views.porterduff_clear;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by songzhw on 2/6/16.
 */
public class MyClear02View extends View {
    private int width, height;
    private Bitmap tempBitmap;
    private Canvas tempCanvas;
    private Paint eraser, paint;

    int x = 250, y = 250, radius = 200;

    public MyClear02View(Context context) {
        super(context);
    }

    public MyClear02View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        tempBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.tempCanvas = new Canvas(tempBitmap);

        eraser = new Paint();
        eraser.setColor(0xFFFFFFFF);
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        eraser.setFlags(Paint.ANTI_ALIAS_FLAG);

        paint = new Paint(Paint.DITHER_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        tempBitmap.eraseColor(Color.TRANSPARENT);//without this line, moving the circle will have a black background
        tempCanvas.drawColor(0x70000000);
        tempCanvas.drawCircle(x, y, radius, eraser);
        canvas.drawBitmap(tempBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_MOVE:
                x = (int) event.getX();
                y = (int) event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
