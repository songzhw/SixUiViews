package cn.six.open.view.sticky_column_table;

import android.support.v7.widget.RecyclerView;

public class CoordinateRvScrollListener extends RecyclerView.OnScrollListener {
    private RecyclerView rvOther;

    public CoordinateRvScrollListener(RecyclerView rvOther) {
        this.rvOther = rvOther;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        System.out.println("szw scoll listener: state = " + getState(newState) +" ; rv = "+recyclerView);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            recyclerView.removeOnScrollListener(this);
        }
    }
    private RecyclerView rv;

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