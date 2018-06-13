package cn.six.open.view.rv.SideMenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by songzhw on 2018-06-13
 */
public class SwipeMenuLayout extends FrameLayout {
    private SwipeMenuLayout self;
    private ViewDragHelper dragger;
    private View contentView, menuView;
    private int menuWidth;
    private int draggedDistance;
    public boolean isOpen = false;

    public SwipeMenuLayout(Context context) {
        super(context);
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        self = this;
        contentView = getChildAt(0);
        menuView = getChildAt(1);
        dragger = ViewDragHelper.create(this, callback);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        menuWidth = menuView.getMeasuredWidth();
        contentView.layout(left, top, right, bottom);
        menuView.layout(right, top, right + menuWidth, bottom);
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == contentView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (left < -menuWidth) {
                return -menuWidth;
            } else if (left > 0) {
                return 0;
            }
            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            draggedDistance = left; // menuWidth是246. 左滑拉出菜单时, left从0一直变到-246.  反之则是一直从负数变成0
            menuView.offsetLeftAndRight(dx);
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) { //后两参是velocity
            if (releasedChild != contentView) {
                return;  //对menuView不要滑, 只要点击即可
            }

            int distance = Math.abs(draggedDistance); //大于0, 说明菜单拉出来了一部分.  若为0, 才表示菜单没出来.
            int threshold = menuWidth / 2;
            if (distance > threshold) { //拉出了一半多, 这时松手, 要回到拉出的状态
                open();
            } else {
                close();
            }

        }
    };

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragger.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (dragger.continueSettling(true)) {
            invalidate();
        }
    }

    public void open(){
        isOpen = true;
        if (dragger.smoothSlideViewTo(contentView, -menuWidth, 0)) {
            ViewCompat.postInvalidateOnAnimation(self);
        }
    }

    public void close(){
        isOpen = false;
        if (dragger.smoothSlideViewTo(contentView, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(self);
        }
    }

//    public void closeWithAnimation(){
//        isOpen = false;
//        dragger.settleCapturedViewAt(0, 0); //效果和smoothSlideViewTo()一样
//    }

}

/*
两个问题

1. 滑动过半, 应该自动就位

2. (bug) 第一项滑出菜单, 再垂直滑到第二屏, 因为复用的关系 , 第16项也变成滑出了菜单(但我是没有滑的, 只是复用view的关系引起了混乱)
 */