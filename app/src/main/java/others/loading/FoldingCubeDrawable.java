package others.loading;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

/**
 * Created by songzhw on 2016/4/5.
 */
public class FoldingCubeDrawable extends Drawable {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Cube[] children = new Cube[4];
    private Rect rect;

    public FoldingCubeDrawable() {
        paint.setColor(Color.DKGRAY);
    }

    // override from Drawable
    @Override
    protected void onBoundsChange(Rect bounds) {
        System.out.println("szw onBoundsChange : bounds = " + getBounds());
        super.onBoundsChange(bounds);

        rect = getBounds();
        for (int i = 0; i < 4; i++) {
            children[i] = new Cube();
            children[i].bounds = new Rect(rect.left + rect.width()/6, rect.top+rect.height()/6,
                    rect.left + rect.width()/2, rect.top + rect.height()/2);
            // TODO animator delay
        }

    }

    // override from Drawable
    @Override
    public void setAlpha(int alpha) {
        System.out.println("szw setAlpha()");
        if (alpha != paint.getAlpha()) {
            paint.setAlpha(alpha);
            this.invalidateSelf();
        }
    }

    // override from Drawable
    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        System.out.println("szw setColorFilter()");
        paint.setColorFilter(colorFilter);
        this.invalidateSelf();
    }

    // override from Drawable
    @Override
    public int getOpacity() {
        return PixelFormat.RGBA_8888;
    }


    // override from Drawable
    @Override
    public void draw(Canvas canvas) {
        System.out.println("szw draw()");
        for (int i = 0; i < 4; i++) {
            Cube cube = children[i];
            canvas.save();
            canvas.rotate(45 + i * 90, rect.centerX(), rect.centerY());
            cube.draw(canvas, paint);
            canvas.restore();
        }
    }


    /**
     * // override from Animatable
     *
     * @Override public void start() {
     * }
     * <p/>
     * // override from Animatable
     * @Override public void stop() {
     * <p/>
     * }
     * <p/>
     * // override from Animatable
     * @Override public boolean isRunning() {
     * return false;
     * }
     * <p/>
     * // AnimatorUpdateListener
     * @Override public void onAnimationUpdate(ValueAnimator animation) {
     * <p/>
     * }
     * <p/>
     * // override from Drawable.Callback
     * @Override public void invalidateDrawable(Drawable who) {
     * <p/>
     * }
     * <p/>
     * // override from Drawable.Callback
     * @Override public void scheduleDrawable(Drawable who, Runnable what, long when) {
     * <p/>
     * }
     * <p/>
     * // override from Drawable.Callback
     * @Override public void unscheduleDrawable(Drawable who, Runnable what) {
     * <p/>
     * }
     */


    class Cube {
        public Rect bounds;
        public ValueAnimator anim;

        public void draw(Canvas canvas, Paint paint) {
            canvas.drawRect(bounds, paint);
        }
    }
}
