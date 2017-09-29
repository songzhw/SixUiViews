package cn.six.open.view.sticky_column_table;

import android.support.v7.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.List;

public class CoordinateRvScrollListener extends RecyclerView.OnScrollListener {
    private RecyclerView rvOther;

    public CoordinateRvScrollListener(RecyclerView rvOther) {
        this.rvOther = rvOther;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        System.out.println("szw scoll listener: state = " + getState(newState) +" ; rv = "+recyclerView);

        // debug code
        try {
            Field field = RecyclerView.class.getDeclaredField("mScrollListeners");
            field.setAccessible(true);
            List<RecyclerView.OnScrollListener> scrollListeners = (List<RecyclerView.OnScrollListener>) field.get(recyclerView);
            List<RecyclerView.OnScrollListener> scrollListeners2 = (List<RecyclerView.OnScrollListener>) field.get(rvOther);
            System.err.println("szw onTouchEvent() : listeners size1 = "+ (scrollListeners == null? 0 : scrollListeners.size() ));
//                    +" ; size2 = "+ (scrollListeners2 == null ? 0 : scrollListeners2.size()) );
        } catch(Exception ex){
            System.err.println("szw error: "+ex);
        }


        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            recyclerView.removeOnScrollListener(this);
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        rvOther.scrollBy(dx, dy);
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