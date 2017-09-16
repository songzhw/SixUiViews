package cn.six.open.view.sticky_column_table;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

// 在rvOther是idle时， 才会真的能移动
public class CoordinateRvItemTouchListener implements RecyclerView.OnItemTouchListener {
    private RecyclerView rvOther;
    private CoordinateRvScrollListener scrollListener;
    private int lastY;

    public CoordinateRvItemTouchListener(RecyclerView rvOther, CoordinateRvScrollListener scrollListener) {
        this.rvOther = rvOther;
        this.scrollListener = scrollListener;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        // fire the touch event, otherwise ,the onTouchEvent() will never get called
        if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
            onTouchEvent(rv, e);
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        int action = e.getAction();
        if (action == MotionEvent.ACTION_DOWN && rvOther.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
            lastY = rv.getScrollY();
            rv.addOnScrollListener(scrollListener);
        } else {
            // if this touch is not a scrolling action, remove the scroll listener
            if (action == MotionEvent.ACTION_UP && rv.getScrollY() == lastY) {
                rv.removeOnScrollListener(scrollListener);
            }
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}