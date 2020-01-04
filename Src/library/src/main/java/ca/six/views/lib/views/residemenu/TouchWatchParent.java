package ca.six.views.lib.views.residemenu;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by songzhw on 2016-05-27.
 */
public class TouchWatchParent extends FrameLayout {
    public boolean isIntercepted;

    public TouchWatchParent(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isIntercepted;
    }
}
