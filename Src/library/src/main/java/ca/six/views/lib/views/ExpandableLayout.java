package ca.six.views.lib.views;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


public class ExpandableLayout extends LinearLayout {

    private View headerView, contentView;

    private int contentHeight = -1;

    private boolean isExpanded = false;
    private ViewGroup.LayoutParams lp;

    public ExpandableLayout(Context context) {
        super(context);
        init();
    }

    public ExpandableLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() != 2) {
            throw new RuntimeException("ExpandableLayout could only have two children: header and content!");
        }
        headerView = getChildAt(0);
        contentView = getChildAt(1);

        headerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // add this if condition, to make sure the contentHeight will not get back to 0 when visibility is changed
        if (contentHeight == -1) {
            contentHeight = contentView.getMeasuredHeight(); //此时contentView.getHeight()仍是0
        }

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
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator anim) {
                lp.height = (int) anim.getAnimatedValue();
                contentView.setLayoutParams(lp);
            }
        });

        animator.start();
    }

    public void expand() {
        ValueAnimator animator = ObjectAnimator.ofInt(0, contentHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator anim) {
                lp.height = (int) anim.getAnimatedValue();
                contentView.setLayoutParams(lp);
            }
        });

        animator.start();
    }

}