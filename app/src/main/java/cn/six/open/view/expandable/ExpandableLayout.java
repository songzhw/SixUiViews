package cn.six.open.view.expandable;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


public class ExpandableLayout extends LinearLayout {

    private View headerView, contentView;

    private int contentHeight;

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

    // ★★★  onFinishInflate -> onAttachedToWindow -> onMeasure -> onSizeChanged -> onLayout  ★★★


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        headerView = getChildAt(0);
        contentView = getChildAt(1);

        headerView.setOnClickListener(v -> {
            toggle();
        });

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (getChildCount() != 2) {
            throw new RuntimeException("ExpandableLayout could only have two children: header and content!");
        }

        contentHeight = contentView.getMeasuredHeight(); //此时contentView.getHeight()仍是0
        System.out.println("szw onSizeChanged contentHeight = "+contentHeight);

        contentView.setVisibility(View.GONE); //onMeasure()完后, 即有了正确的contentHeight后, 才能让它为gone.不然contentHeight就是0了.
    }



    public void toggle(){

    }
}
