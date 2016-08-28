package cn.six.open.view.animcheck;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

import cn.six.open.util.UiUtil;


/**
 * Created by songzhw on 2016-08-17
 */
public class CrossCheckBox extends View implements Checkable{
    public int DEFAULT_HEIGHT, DEFAULT_SIZE, DEFAULT_HALF;

    public int startX, startY;
    private String text = "";
    private int stringWidth;

    private Paint linePaint, textPaint;
    private Path crossPath;

    public ICrossChangeListener listener;

    public CrossCheckBox(Context context) {
        this(context, null);
    }

    public CrossCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.BLUE);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(UiUtil.dp2px(getContext(), 2.5f));

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(UiUtil.dp2px(getContext(), 24)); // irrelevant to device screen

        crossPath = new Path();

        DEFAULT_HEIGHT = UiUtil.dp2px(getContext(), 50);
        DEFAULT_SIZE   = UiUtil.dp2px(getContext(), 18);
        DEFAULT_HALF = DEFAULT_SIZE / 2;
        startY = (DEFAULT_HEIGHT - DEFAULT_SIZE) / 2;
        startX = startY;

        anim = ValueAnimator.ofInt(0, DEFAULT_HALF);
        anim.setDuration(400);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        int measuredWidth = widthMeasureSpec;
        int measureHeight = heightMeasureSpec;
        if (widthSpecMode == MeasureSpec.AT_MOST) {
            measuredWidth = startX + DEFAULT_SIZE + startX + stringWidth + startX;
        }

        if (heightSpecMode == MeasureSpec.AT_MOST) {
            measureHeight = DEFAULT_HEIGHT;
        }
        setMeasuredDimension(measuredWidth, measureHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(startX, startY);
        crossPath.reset();
        crossPath.moveTo(0,0);
        crossPath.lineTo(DEFAULT_HALF, offset);
        crossPath.lineTo(DEFAULT_SIZE, 0);
        crossPath.lineTo(DEFAULT_SIZE - offset, DEFAULT_HALF);
        crossPath.lineTo(DEFAULT_SIZE, DEFAULT_SIZE);
        crossPath.lineTo(DEFAULT_HALF, DEFAULT_SIZE - offset);
        crossPath.lineTo(0, DEFAULT_SIZE);
        crossPath.lineTo(offset, DEFAULT_HALF);
        crossPath.lineTo(0, 0); // closure
        canvas.drawPath(crossPath, linePaint);
        canvas.restore();

        canvas.drawText(text, startX + DEFAULT_SIZE + startX,
                (DEFAULT_HEIGHT - textPaint.descent() - textPaint.ascent()) / 2, textPaint);

    }

    // ============== public method ==============
    public void setText(String txt){
        this.text = txt;
        stringWidth = (int) textPaint.measureText(text);
        requestLayout(); // invalidate() is okay, too
    }

    // ============== Checkable interface ==============
    private boolean isChecked = false;
    private ValueAnimator anim;
    private int offset;
    @Override
    public void setChecked(boolean checked) {
        if(isChecked){ // false -> true
            anim.start();
        } else {
            anim.reverse();
        }

        if(listener != null){
            listener.onCrossChanged(isChecked);
        }
    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle(){
        isChecked = !isChecked;
        setChecked(isChecked);
    }

    // ============== Listener ==============
    public interface ICrossChangeListener{
        void onCrossChanged(boolean isCrossed);
    }
}
