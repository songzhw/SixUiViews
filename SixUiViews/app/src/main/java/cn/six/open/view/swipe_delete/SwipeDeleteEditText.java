package cn.six.open.view.swipe_delete;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created by songzhw on 2016/2/1
 */
public class SwipeDeleteEditText extends EditText {
    private GestureDetectorCompat detector;
    private SwipeDeleteEditText self;

    public SwipeDeleteEditText(Context context) {
        super(context);
        init(context);
    }

    public SwipeDeleteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwipeDeleteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        self = this;
        detector = new GestureDetectorCompat(context, new MySwipeDeleter());
    }

    class MySwipeDeleter extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // the original onFiling() can be called very easily, which is not what I want.
            float distance = e1.getX() - e2.getX();
            if(Math.abs(distance) > 300){
                self.setText("");
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
