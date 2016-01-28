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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // in this places, the adapter is already not null !
        if(adapter == null || adapter.getCount() == 0 ){
            System.out.println("szw onMeausre() : 0000");
            removeAllViewsInLayout();
            return;
        }

        System.out.println("szw onMeasure() 111");
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
            System.out.println("szw onLayout() : 0000");
            removeAllViewsInLayout();
            return;
        }

        int childCount = this.getChildCount();
        System.out.println("szw onLayout() : 111");
        for(int i = 0 ; i < childCount ; i++){
            View child = this.getChildAt(i);
            ViewCompat.setTranslationZ(child, 10 - i);

            child.layout(0, 0, child.getMeasuredWidth() + i * 20, child.getMeasuredHeight()  + i * 20 );
        }

    }

    public void setAdapter(BaseAdapter adapter){
        this.adapter = adapter;
    }

    private BaseAdapter adapter;
//    private int index = 0;
}
