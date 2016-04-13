package others.loading.spinkit.shape;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

public class RectSprite extends ShapeSprite {
    @Override
    public ValueAnimator getAnimation() {
        return null;
    }

    @Override
    public void drawShape(Canvas canvas, Paint paint) {
        if (getDrawBounds() != null) {
            canvas.drawRect(getDrawBounds(), paint);
        }
    }
}