package others.loading;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

/**
 * Created by songzhw on 2016/4/5.
 */
public class FoldingCubeDrawable extends Drawable
        implements ValueAnimator.AnimatorUpdateListener, Animatable, Drawable.Callback{


    private int alpha;

    // override from Drawable
    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
    }

    // override from Drawable
    @Override
    public void draw(Canvas canvas) {

    }

    // override from Drawable
    @Override
    public void setAlpha(int alpha) { this. alpha = alpha;}

    // override from Drawable
    @Override
    public void setColorFilter(ColorFilter colorFilter) {}

    // override from Drawable
    @Override
    public int getOpacity() { return PixelFormat.RGBA_8888; }





    // override from Animatable
    @Override
    public void start() {

    }

    // override from Animatable
    @Override
    public void stop() {

    }

    // override from Animatable
    @Override
    public boolean isRunning() {
        return false;
    }

    // AnimatorUpdateListener
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {

    }

    // override from Drawable.Callback
    @Override
    public void invalidateDrawable(Drawable who) {

    }

    // override from Drawable.Callback
    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {

    }

    // override from Drawable.Callback
    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {

    }




    class Cube {
        public Rect bounds;
        public ValueAnimator anim;
        public Cube(Rect bounds) {
            this.bounds = bounds;
        }

        public void draw(Canvas canvas, Paint paint){
            canvas.drawRect(bounds, paint);
        }
    }
}
