package ca.six.views.views.anim_check;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

import java.util.ArrayList;

import ca.six.views.util.UiUtil;


/**
 * Created by songzhw on 2016-08-28
 */

public class TickCheckBox extends View implements Checkable {
    public int DEFAULT_HEIGHT, DEFAULT_SIZE, DEFAULT_HALF;

    public int startX, startY;
    public String text = "";
    public int stringWidth;

    private Paint linePaint, textPaint;
    private Path tickPath, squarePath;
    private PathMeasure squarePathMeasure, tickPathMeasure;
    private float squarePathLength, tickPathLength;

    private boolean isChecked = false;
    private ValueAnimator animRotate, animDisappear;
    private AnimatorSet animatorSet;
    private int rotate;
    private float percentage = 0;

    public ITickChangeListener listener;


    public TickCheckBox(Context context) {
        this(context, null);
    }

    public TickCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.BLUE);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(UiUtil.dp2px(getContext(), 2.5f));

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(UiUtil.dp2px(getContext(), 24)); // irrelevant to device screen

        tickPath = new Path();
        squarePath = new Path();
        squarePathMeasure = new PathMeasure();
        tickPathMeasure = new PathMeasure();

        DEFAULT_HEIGHT = UiUtil.dp2px(getContext(), 50);
        DEFAULT_SIZE = UiUtil.dp2px(getContext(), 18);
        DEFAULT_HALF = DEFAULT_SIZE / 2;
        startY = (DEFAULT_HEIGHT - DEFAULT_SIZE) / 2;
        startX = startY;

        // 1. rotate the square
        animRotate = ValueAnimator.ofInt(0, 45);
        animRotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                rotate = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        // 2. make the circle frame disapppear  &  draw the tick
        animDisappear = ValueAnimator.ofFloat(0, 1);
        animDisappear.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                percentage = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playSequentially(animRotate, animDisappear);
    }

    // measure : AT_MOST
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Path originalPath = new Path();
        originalPath.addRect(0, 0, DEFAULT_SIZE, DEFAULT_SIZE, Path.Direction.CCW);
        squarePathMeasure.setPath(originalPath, false);
        squarePathLength = squarePathMeasure.getLength();

        Path origTickPath = new Path();
        origTickPath.moveTo(DEFAULT_SIZE * 1f, DEFAULT_SIZE * 0f);
        origTickPath.lineTo(DEFAULT_SIZE * 0.4f, DEFAULT_SIZE * 1f);
        origTickPath.lineTo(DEFAULT_SIZE * 0.05f, DEFAULT_SIZE * 0.6f);
        tickPathMeasure.setPath(origTickPath, false);
        tickPathLength = tickPathMeasure.getLength();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        squarePath.reset(); // 不加这句， 就总是画出整个方框，不会消失的
        tickPath.reset();

        canvas.save();

        canvas.translate(startX, startY);

        canvas.save();
        canvas.rotate(rotate, DEFAULT_HALF, DEFAULT_HALF);
        squarePathMeasure.getSegment(squarePathLength * percentage, squarePathLength, squarePath, true);
        canvas.drawPath(squarePath, linePaint);
        canvas.restore();

        tickPathMeasure.getSegment(tickPathLength * (1 - percentage), tickPathLength, tickPath, true);
        canvas.drawPath(tickPath, linePaint);

        canvas.restore();

        canvas.drawText(text, startX + DEFAULT_SIZE + startX,
                (DEFAULT_HEIGHT - textPaint.descent() - textPaint.ascent()) / 2, textPaint);

    }

    // ============== public method ==============
    public void setText(String txt) {
        this.text = txt;
        stringWidth = (int) textPaint.measureText(text);
        requestLayout(); // invalidate() is okay, too
    }

    // ============== Checkable interface ==============
    @Override
    public void setChecked(boolean checked) {
        if (isChecked) { // false -> true
            animatorSet.start();
        } else {
            // Note : animatorSet.reverse() is a @hide method !!!
            ArrayList<Animator> anims = animatorSet.getChildAnimations();
            for (Animator anim : anims) {
                ValueAnimator valueAnimator = (ValueAnimator) anim;
                valueAnimator.reverse();
            }
        }

        if (listener != null) {
            listener.onTickChanged(isChecked);
        }
    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {
        isChecked = !isChecked;
        setChecked(isChecked);
    }

    // ============== Listener ==============
    public interface ITickChangeListener {
        void onTickChanged(boolean isCrossed);
    }
}