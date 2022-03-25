package ca.six.views.rv.stickyheader;

import android.view.MotionEvent;

import androidx.recyclerview.widget.RecyclerView;

// 在rvOther是idle时， 才会真的能移动
public class CoordinateRvItemTouchListener implements RecyclerView.OnItemTouchListener {
    private RecyclerView rvOther;
    private CoordinateRvScrollListener scrollListener;
    private float downRawY;

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
        boolean isActingWhenActionDown = action == MotionEvent.ACTION_DOWN;
        if (isActingWhenActionDown) {
            downRawY = e.getRawY();
            stopScrollForRvs(rv);
            rv.addOnScrollListener(scrollListener);
        } else {
            // if this touch is not a scrolling action, remove the scroll listener
            boolean isTimeToRemoveListener = action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL;
            if (isTimeToRemoveListener && downRawY == e.getRawY()) {
                rv.removeOnScrollListener(scrollListener);
            }
        }

    }

    private void stopScrollForRvs(RecyclerView rv) {
        rvOther.stopScroll();
        rvOther.clearOnScrollListeners();
        rv.stopScroll();
        rv.clearOnScrollListeners();
    }


    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}