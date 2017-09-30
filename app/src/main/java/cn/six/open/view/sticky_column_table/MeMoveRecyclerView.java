package cn.six.open.view.sticky_column_table;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MeMoveRecyclerView extends RecyclerView {
    private RecyclerView rvOther;

    public MeMoveRecyclerView(Context context) {
        super(context);
        init();
    }

    public MeMoveRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {

    }

    public void setCoordinateRecyclerView(RecyclerView rvOther) {
        this.rvOther = rvOther;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (isEnabled()) {
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                rvOther.setEnabled(true);
            } else {
                rvOther.setEnabled(false);
            }
        }

        if (action == MotionEvent.ACTION_DOWN) {
            rvOther.setEnabled(false);
            this.setEnabled(true);
        }
        return super.dispatchTouchEvent(ev);
    }
}
