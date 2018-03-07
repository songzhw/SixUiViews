package cn.six.open.view.expandable;

import android.animation.Animator;
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

    private int contentHeight;

    private boolean isExpanded = false;

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
            System.out.println("szw click");
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
        if(isExpanded){
            collapse();
        } else {
            expand();
        }
        isExpanded = !isExpanded;
    }

    public void collapse(){
        ViewGroup.LayoutParams lp = contentView.getLayoutParams();
        System.out.println("szw 1 : "+contentHeight);
        ValueAnimator animator = ObjectAnimator.ofInt(contentHeight, 0);
        animator.addUpdateListener( anim -> {
            lp.height = (int) anim.getAnimatedValue();
            System.out.println("szw val1 = "+lp.height);
            contentView.setLayoutParams(lp);
        });
        animator.start();
    }

    public void expand(){
        contentView.setVisibility(View.VISIBLE);

        ViewGroup.LayoutParams lp = contentView.getLayoutParams();
        ValueAnimator animator = ObjectAnimator.ofInt(0, contentHeight);
        animator.addUpdateListener( anim -> {
            lp.height = (int) anim.getAnimatedValue();
            System.out.println("szw val2 = "+lp.height);
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