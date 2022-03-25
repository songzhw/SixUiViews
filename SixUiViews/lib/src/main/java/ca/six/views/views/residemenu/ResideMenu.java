package ca.six.views.views.residemenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.customview.widget.ViewDragHelper;

import ca.six.views.util.UiUtil;


/**
 * Created by songzhw on 2016-05-24.
 */
public class ResideMenu extends FrameLayout implements View.OnClickListener {
    private FrameLayout decorView;
    private View menuView;
    private TouchWatchParent middleView; // to intercept the Button clicking in the contentView
    private Activity actv;

    private ViewDragHelper dragHelper;

    public ResideMenu(Context ctx) {
        super(ctx);
    }

    public void attach2Activity(Activity actv, View menus){
        this.actv = actv;
        this.menuView = menus;
        this.decorView = (FrameLayout) actv.getWindow().getDecorView();
        View subDecorView = decorView.getChildAt(0); // decorView -- llay -- ViewStub + FrameLayout(content)

        menuView.setAlpha(0);
        this.addView(menuView);  // this is "fillParent, fillParent", so the "side_rmenu_left" should have a outter layout to wrap the ScrollView

        decorView.removeView(subDecorView);
        middleView = new TouchWatchParent(actv);
        middleView.addView(subDecorView);
        this.addView(middleView);
        decorView.addView(this);

        dragHelper = ViewDragHelper.create(this, callback);
        dragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);

    }


    public void openMenu() {
        // if we have soft keyboard, dismiss it before we do the animation below
        UiUtil.dismissInputMethod(this.actv);

        // set the pivot x and y , or the scale anim is wrong
        int screenWidth = UiUtil.getScreenWidth(actv);
        int screeenHeight = UiUtil.getScreenHeight(actv);
        middleView.setPivotX(screenWidth * 1.5f);
        middleView.setPivotY(screeenHeight * 0.5f);

        // add the clicking listener
        middleView.setOnClickListener(this);
        middleView.isIntercepted = true;

        // start the anim
        AnimatorSet anims = new AnimatorSet();
        anims.playTogether(
                ObjectAnimator.ofFloat(middleView, "scaleX", 0.5f),
                ObjectAnimator.ofFloat(middleView, "scaleY", 0.5f),
                ObjectAnimator.ofFloat(menuView, "alpha", 1)
        );
        anims.setDuration(400);
        anims.start();
    }

    @Override
    public void onClick(View v) {
        this.closeMenu();
    }

    public void closeMenu() {
        // remove the overall clicking listener
        middleView.setOnClickListener(null);
        middleView.isIntercepted = false;


        // AnimatorSet has no such method called "reverse()"

        // reverse the anim
        AnimatorSet anims = new AnimatorSet();
        anims.playTogether(
                ObjectAnimator.ofFloat(middleView, "scaleX", 1),
                ObjectAnimator.ofFloat(middleView, "scaleY", 1),
                ObjectAnimator.ofFloat(menuView, "alpha", 0)
        );
        anims.setDuration(400);
        anims.start();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        dragHelper.processTouchEvent(ev);
        return true;
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            menuView.setAlpha(1);
            dragHelper.captureChildView(middleView, pointerId);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            openMenu();
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            // middleView is the "changedView" arg
            System.out.println("szw onViewPositionChanged : changedView = "+changedView+" ; left = "+left);
        }
    };

}
