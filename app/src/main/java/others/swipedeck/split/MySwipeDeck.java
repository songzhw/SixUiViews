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

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(adapter == null || adapter.getCount() == 0 ){
            removeAllViewsInLayout();
            return;
        }

        int childCount = adapter.getCount(); //TODO   max is 3 - 5
        System.out.println("szw childCount = "+childCount);
        for(int i = 0 ; i < childCount ; i++){
            System.out.println("szw for  i = "+i);
            View child = adapter.getView(i, null/*last removed view*/, this);
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            addView(child, lp);

            int childWidth = getMeasuredWidth();   System.out.println("szw childWidth = "+childWidth);
            int childHeight = getMeasuredHeight(); System.out.println("szw childHeight = "+childHeight);
            child.measure(MeasureSpec.EXACTLY | childWidth, MeasureSpec.EXACTLY | childHeight);

            ViewCompat.setTranslationZ(child, 10 - i);

            child.layout(0, 0, child.getMeasuredWidth() + i * 20, child.getMeasuredHeight()  + i * 20 );
//            child.animate()
//                    .setDuration(200)
//                    .y(i * 20);

//            index++;

        }

    }

    public void setAdapter(BaseAdapter adapter){
        this.adapter = adapter;
    }

    private BaseAdapter adapter;
//    private int index = 0;
}
