package cn.six.open.view.scratchcard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by songzhw on 1/3/16.
 */
public class ScratchCardView extends ImageView {
    private static final float MINP = 0.25f;
    private static final float MAXP = 0.75f;
    private Bitmap mBitmap;
    private Canvas mBitmapCanvas;
    private Path mPath;
    private Paint mBitmapPaint, mPaint;

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    public ScratchCardView(Context c) {
        super(c);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(150);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mBitmapCanvas = new Canvas(mBitmap);
        mBitmapCanvas.drawColor(0xFFAAAAAA);//draw the background
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // commit the path to our offscreen. When finger are up, the path will be saved.
        mBitmapCanvas.drawPath(mPath, mPaint);

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
//            canvas.drawPath(mPath, mPaint);//so the paint will have the eraser effect when users select "eraser"
    }

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            //actually, i found the two lines below will both get the job done. maybe the first one is more sensitive?
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);//二次方曲线
//    mPath.quadTo(mX, mY, x, y);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
        mPath.lineTo(mX, mY);//when omit this line, this will still work!

        // kill this so we don't double draw
        mPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }
}
