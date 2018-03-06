package cn.six.open.view.expandable;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


public class ExpandableLayout extends LinearLayout {
    private View headerView, contentView;

    public ExpandableLayout(Context context) {
        super(context);
        init(context);
    }

    public ExpandableLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
    }

    // ★★★  onFinishInflate -> onMeasure -> onSizeChanged -> onLayout  ★★★

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(getChildCount() != 2){
            throw new RuntimeException("ExpandableLayout could only have two children: header and content!");
        }
        headerView = getChildAt(0);
        contentView = getChildAt(1);
    }
}
