package cn.six.open.view.scratchcard;

import android.annotation.SuppressLint;
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
 * Created by songzhw on 2016/01/03.
 */
@SuppressLint("AppCompatCustomView")
public class ScratchCardView extends ImageView {
    private Bitmap tempBitamp;
    private Canvas tempCanvas;
    private Path fingerPath;
    private Paint paint, fingerPaint;

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    public ScratchCardView(Context c) {
        super(c);
        fingerPath = new Path();
        paint = new Paint(Paint.DITHER_FLAG);

        fingerPaint = new Paint();
        fingerPaint.setAntiAlias(true);
        fingerPaint.setDither(true);
        fingerPaint.setStyle(Paint.Style.STROKE);
        fingerPaint.setStrokeJoin(Paint.Join.ROUND);
        fingerPaint.setStrokeCap(Paint.Cap.ROUND);
        fingerPaint.setStrokeWidth(150);
        fingerPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        tempBitamp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        tempCanvas = new Canvas(tempBitamp);
        tempCanvas.drawColor(0xFFAAAAAA);//draw the gray mask above the picture
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // commit the path to our offscreen. When finger are up, the path will be saved.
        tempCanvas.drawPath(fingerPath, fingerPaint);

        canvas.drawBitmap(tempBitamp, 0, 0, paint);
//            canvas.drawPath(fingerPath, fingerPaint);//so the paint will have the eraser effect when users select "eraser"
    }

    private void touch_start(float x, float y) {
        fingerPath.reset();
        fingerPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            //actually, i found the two lines below will both get the job done. maybe the first one is more sensitive?
            fingerPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);//二次方曲线
//    fingerPath.quadTo(mX, mY, x, y);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
        fingerPath.lineTo(mX, mY);//when omit this line, this will still work!

        // kill this so we don't double draw
        fingerPath.reset();
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
