package cn.six.open.view.sticky_column_table;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

// 在rvOther是idle时， 才会真的能移动
public class CoordinateRvItemTouchListener implements RecyclerView.OnItemTouchListener {
    private RecyclerView rvOther;
    private CoordinateRvScrollListener scrollListener;

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
            System.out.println("szw action_down : add a scroll listener");
            rvOther.stopScroll();
            rvOther.clearOnScrollListeners();
            rv.stopScroll();
            rv.clearOnScrollListeners();

            rv.addOnScrollListener(scrollListener);
        } else {
            System.out.println("szw " + getActionString(e.getAction()));
            // if this touch is not a scrolling action, remove the scroll listener
            boolean isTimeToRemoveListener = action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL;
            if (isTimeToRemoveListener) {
                System.err.println("szw remove listener 111 action up");
                rv.removeOnScrollListener(scrollListener);
            }
        }

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    private String getActionString(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return "action_down";
            case MotionEvent.ACTION_MOVE:
                return "action_move";
            case MotionEvent.ACTION_UP:
                return "action_up";
            case MotionEvent.ACTION_CANCEL:
                return "action_cancel";
            default:
                return " - " + action;
        }
    }
}