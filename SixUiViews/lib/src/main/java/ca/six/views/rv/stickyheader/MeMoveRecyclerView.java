package ca.six.views.rv.stickyheader;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**

 3个touch事件的被调用顺序:
 szw MeMoveRecyclerView             dispatchTouchEvent
 szw CoordinateRvItemTouchListener  onInterceptTouchEvent
 szw CoordinateRvItemTouchListener  onTouchEvent

 */
public class MeMoveRecyclerView extends RecyclerView {
    private RecyclerView rvOther;

    public MeMoveRecyclerView(Context context) {
        super(context);
    }

    public MeMoveRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
            return super.dispatchTouchEvent(ev);
        } else {
            return false;
        }

    }
}
