package others.swipedeck.split;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

/**
 * Created by songzhw on 2016/1/11
 */
public class MySwipeDeck extends FrameLayout {

    public MySwipeDeck(Context context) {
        this(context, null);
    }

    public MySwipeDeck(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // onMeasure() --> onMeasure() --> onSizeChanged() --> onLayout()
    //  --> onMeasure() --> onLayout()
    //  : It's the "onSizeChanged()" only be called once!
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        System.out.println("szw onSizeChanged()");

        // in this places, the adapter is already not null !
        if(adapter == null || adapter.getCount() == 0 ){
            System.out.println("szw onMeasure() 000");
            removeAllViewsInLayout();
            return;
        }

        int childCount = adapter.getCount();
        for(int i = 0 ; i < childCount ; i++){
            View child = adapter.getView(i, null/*last removed view*/, this);

            ViewGroup.LayoutParams params = child.getLayoutParams();
            if (params == null) {
                params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            }
            addViewInLayout(child, -1, params, true); // the default value of the forth argument is "false"
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if(adapter == null || adapter.getCount() == 0 ){
            System.out.println("szw onLayout() 000");
            removeAllViewsInLayout();
            return;
        }

        System.out.println("szw onLayout() 111");
        int childCount = this.getChildCount();
        View aChild = this.getChildAt(0);
        int childWidth = aChild.getMeasuredWidth();
        int childheight = aChild.getMeasuredHeight();
        int left2 = (getWidth() - childWidth)/2 - 50;
        int top2 = (getHeight() - childheight)/2 - 50;
        for(int i = 0 ; i < childCount ; i++){
            View child = this.getChildAt(i);
            ViewCompat.setTranslationZ(child, 10 - i);

            int step = i * 25;
            child.layout(left2 + step, top2 + step, left2 + childWidth + step, top2 + childheight + step );
        }

    }

    public void setAdapter(BaseAdapter adapter){
        this.adapter = adapter;
    }

    private BaseAdapter adapter;

}
