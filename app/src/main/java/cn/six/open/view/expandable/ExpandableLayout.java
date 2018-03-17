package cn.six.open.view.expandable;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class ExpandableLayout extends LinearLayout {

    private View headerView, contentView;

    private int contentHeight = -1;

    private boolean isExpanded = false;
    private ViewGroup.LayoutParams lp;

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

    // ★★★  onFinishInflate -> onAttachedToWindow -> onMeasure(可能2次,都在onSizeChanged前) -> onSizeChanged -> onLayout  ★★★
    // ★★★  visibility的改变也会让onSizeChanged()被调用  ★★★

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        headerView = getChildAt(0);
        contentView = getChildAt(1);

        headerView.setOnClickListener(v -> {
            System.out.println("szw click");
            toggle();
        });

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.out.println("szw onMeasure");

        // add this if condition, to make sure the contentHeight will not get back to 0 when visibility is changed
        if (contentHeight == -1) {
            contentHeight = contentView.getMeasuredHeight(); //此时contentView.getHeight()仍是0
        }
        System.out.println("szw onMeasure contentHeight = " + contentHeight);

//        contentView.setVisibility(View.GONE); //onMeasure()完后, 即有了正确的contentHeight后, 才能让它为gone.不然contentHeight就是0了.
        lp = contentView.getLayoutParams();
        lp.height = 0;
        contentView.setLayoutParams(lp);
    }


    public void toggle() {
        if (isExpanded) {
            collapse();
        } else {
            expand();
        }
        isExpanded = !isExpanded;
    }

    public void collapse() {
        ValueAnimator animator = ObjectAnimator.ofInt(contentHeight, 0);
        animator.addUpdateListener(anim -> {
            lp.height = (int) anim.getAnimatedValue();
            contentView.setLayoutParams(lp);
        });
        animator.start();
    }

    public void expand() {
        ValueAnimator animator = ObjectAnimator.ofInt(0, contentHeight);
        animator.addUpdateListener(anim -> {
            lp.height = (int) anim.getAnimatedValue();
            contentView.setLayoutParams(lp);
        });
        animator.start();
    }

}

/*
 No animation. Because :

szw click
szw val2 = 0
szw val2 = 0
szw onSizeChanged contentHeight = 0
szw val2 = 5
szw val2 = 19
szw val2 = 44
szw val2 = 78
szw val2 = 120


 */