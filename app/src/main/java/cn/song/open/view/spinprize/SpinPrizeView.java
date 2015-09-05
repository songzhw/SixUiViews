package cn.song.open.view.spinprize;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import cn.song.open.R;


/**
 * TODO: document your custom view class.
 */
public class SpinPrizeView extends ViewGroup {
    private String[] names = new String[]{"gold","cash","coin","gem","ring","treasure"};
    private int[] icons = new int[]{R.drawable.gold, R.drawable.cash, R.drawable.coin, R.drawable.gem,R.drawable.ring,R.drawable.treasure};

    private double startAngle = 30d;

    public SpinPrizeView(Context context) {
        super(context);
        init(context, null, 0);
    }
    public SpinPrizeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }
    public SpinPrizeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        int length = icons.length;
        for (int i = 0 ; i < length ; i++ ){
            final int j = i;
            ImageView iv = new ImageView(context);
            iv.setImageResource(icons[i]);
            iv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("szw","clicked "+names[j]);
                    Toast.makeText(getContext(), "click "+names[j], Toast.LENGTH_SHORT).show();
                }
            });
            addView(iv);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Log.d("szw","onMeasure() : suggestMinWidth = "+getSuggestedMinimumWidth()+" ; suggestMinHeight = "+getSuggestedMinimumHeight());
        setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());

        int mySize = getHeight(); // 要求width, height要是一样的值
        int childCount = getChildCount();
        for(int i = 0 ; i < childCount ; i++){
            View child = getChildAt(i);
            int childMeasureMode = MeasureSpec.EXACTLY;
            int childMeasureSize = mySize / 2;
            int childMeasureSpec = MeasureSpec.makeMeasureSpec(childMeasureSize, childMeasureMode);
            child.measure(childMeasureSpec,childMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // 最后4个参数是本ViewGroup位于手机屏幕上的绝对坐标： 122， 377， 591， 852. 以右right值为例， 591 + 122 就是719， 差不多是720像素的手机宽度了。
        int parentRadius = getMeasuredHeight() / 2; // 要求width, height要是一样的值
        int childSize = parentRadius / 2;
        int childRadius = parentRadius / 4;
        int childCount = getChildCount();
//        Log.d("szw","onLayout() : left = "+left+" ; top = "+top+" ; right = "+right+" ; bottom = "+bottom);
//        Log.d("szw","onLayout() : parentRadius = "+parentRadius+" ; childSize = "+childSize+" ; childCount = "+childCount);
        for(int i = 0 ; i < childCount ; i++){
            double radians = Math.toRadians(startAngle);
            int childCenterX = parentRadius + (int) ((parentRadius-65) * Math.cos(radians));//再减65， 是让图标更靠里面一些。 半径小了，自然位置就更靠父View中心一些了
            int childCenterY = parentRadius - (int) ((parentRadius-65) * Math.sin(radians));
            int childLeft = childCenterX - childRadius;
            int childTop = childCenterY - childRadius;

            View child = getChildAt(i);
            child.layout(childLeft,childTop,childLeft+childSize,childTop+childSize);
//            child.layout(0,0, childSize,childSize);

            int eachItemAngle = 360 / icons.length;
            startAngle += eachItemAngle;
            startAngle %= 360;
        }
    }

    // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

    // touch事件不放到onTouchEvent()里的原因是， 本ViewGroup的子类是有点击事件的，这就可能导致本ViewGroup收不到touch事件
    private int downX, downY;
    private double lastAngle;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        Log.d("szw","dispatchTouchEvent() : (0-2-1)   "+action);
        if(action == MotionEvent.ACTION_DOWN){
            boolean isHitingChild = false;
            int childCount = this.getChildCount();
            for(int i = 0 ; i < childCount; i++){
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                View child = getChildAt(i);
                Rect rect = new Rect();
                child.getHitRect(rect);
                isHitingChild = isHitingChild || rect.contains(x,y);
            }

            if(!isHitingChild){
                return true;
            }

            downX = (int) ev.getX();
            downY = (int) ev.getY();
            lastAngle = getAngle(downX, downY);
        }
        else if(action == MotionEvent.ACTION_MOVE){
            int moveX = (int) ev.getX();
            int moveY = (int) ev.getY();
            double moveAngle = getAngle(moveX, moveY);
            double angleDiff = moveAngle - lastAngle;
            lastAngle = moveAngle;
            startAngle -= angleDiff * 3; // 不是+=， 不然就是反方向了  // * 3是因为转动得太慢了
            Log.d("szw"," move : angleDiff = "+angleDiff+" ; startAngle = "+startAngle);
            //这里可能会有点卡，没必要每次move都reqestLayout()，可以到达一个量之后才requestLayout()
            requestLayout();
        }
        else if(action == MotionEvent.ACTION_UP) {

        }

        return super.dispatchTouchEvent(ev);
    }


    private double getAngle(int x, int y){
        double sinValue = y / Math.hypot(x,y);
        double radians = Math.asin(sinValue);
        double angle = radians * 180 / Math.PI;
        return angle;
    }
}
