package cn.six.open.view.sticky_column_table;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import java.lang.reflect.Field;
import java.util.List;

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
        boolean isActingWhenActionDown = action == MotionEvent.ACTION_DOWN && rvOther.getScrollState() != RecyclerView.SCROLL_STATE_DRAGGING;
//        System.out.println("szw onTouchEvent() : isActingWhenActionDown = "+isActingWhenActionDown +" ; other.state = "+getState(rvOther.getScrollState()));
//        System.out.println("szw onTouchEvent() : rvOther = "+rvOther);
        if (isActingWhenActionDown) {
            rv.addOnScrollListener(scrollListener);
        } else {
            // if this touch is not a scrolling action, remove the scroll listener
            boolean isTimeToRemoveListener = action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL;
            if (isTimeToRemoveListener) {
                rv.removeOnScrollListener(scrollListener);
            }
        }

        // debug code
        try {
            Field field = RecyclerView.class.getDeclaredField("mScrollListeners");
            field.setAccessible(true);
            List<RecyclerView.OnScrollListener> scrollListeners = (List<RecyclerView.OnScrollListener>) field.get(rv);
            List<RecyclerView.OnScrollListener> scrollListeners2 = (List<RecyclerView.OnScrollListener>) field.get(rvOther);
            System.err.println("szw onTouchEvent() : listeners size1 = "+ (scrollListeners == null? 0 : scrollListeners.size() )
                    +" ; size2 = "+ (scrollListeners2 == null ? 0 : scrollListeners2.size()) );
        } catch(Exception ex){
            System.err.println("szw error: "+ex);
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    private String getState(int state) {
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            return "idle";
        } else if(state == RecyclerView.SCROLL_STATE_DRAGGING) {
            return "dragging";
        } else if(state == RecyclerView.SCROLL_STATE_SETTLING) {
            return "settling";
        }
        return "null";
    }
}