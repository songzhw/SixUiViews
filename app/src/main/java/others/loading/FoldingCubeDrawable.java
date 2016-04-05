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

    public FoldingCubeDrawable() {
        paint.setColor(Color.DKGRAY);
    }

    // override from Drawable
    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        System.out.println("szw onBoundsChange()");

        System.out.println("szw CF : bounds = "+ getBounds());
        for (int i = 0; i < 4; i++) {
            children[i] = new Cube();
            children[i].bounds = getBounds();
            // TODO animator delay
        }

    }

    // override from Drawable
    @Override
    public void draw(Canvas canvas) {
        System.out.println("szw draw()");
        for (int i = 0; i < 4; i++) {
            Cube cube = children[i];
            cube.draw(canvas, paint);
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
