package cn.six.open.view.porterduff_clear;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

public class MyClear01View extends View {
    private int width, height;
    private Bitmap tempBitmap;
    private Canvas tempCanvas;
    private Paint eraser, paint;

    public MyClear01View(Context context) {
        super(context);
    }

    public MyClear01View(Context context, AttributeSet attrs) {
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
        tempCanvas.drawColor(0x70000000);
        tempCanvas.drawCircle(200, 200, 150, eraser);
        canvas.drawBitmap(tempBitmap, 0, 0, null);
    }
}


/*
Error 01 : black circle

@Override
protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawColor(0x70000000);
    canvas.drawCircle(200, 200, 150, eraser);
}
 */


/*
Error 02 : Nothing. No circle.

@Override
protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawColor(0x70000000);
    tempCanvas.drawCircle(200, 200, 150, eraser);
    canvas.drawBitmap(tempBitmap, 0, 0, null);
}

 */