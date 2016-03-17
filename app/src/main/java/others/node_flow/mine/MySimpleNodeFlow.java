package others.node_flow.mine;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.six.open.R;

/**
 * Created by songzhw on 2016/3/17
 */
public class MySimpleNodeFlow extends FrameLayout {
    private List<String> tops, sub1, sub2;
    private int headHeight = 0;

    public MySimpleNodeFlow(Context context) {
        super(context);
        init();
    }

    public MySimpleNodeFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        this.tops = Arrays.asList("Asia", "Europe");
        this.sub1 = Arrays.asList("China", "Japan", "Korean");
        this.sub2 = Arrays.asList("France", "England");
    }

    public void showFirstFloor(){
        int j = 0;
        for(String atop : tops) {
            View view = getHeaderView(atop);
            view.setTranslationY(j * headHeight);
            addView(view);
            j++;
        }

        // start to do the animation
        ValueAnimator anim = ValueAnimator.ofInt(1, 100);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer curr = (Integer) animation.getAnimatedValue();
                int height = getHeight();
                for(int i = 0; i < getChildCount(); i++ ){
                    System.out.println("szw i = "+i);
                    View child = getChildAt(i);
                    float step = (height - i*headHeight) / 100; // (end - start) / 10
                    child.setTranslationY(height - curr * step);
                }
            }
        });
        anim.setDuration(1000);
        anim.setInterpolator(new FastOutSlowInInterpolator());
        anim.start();
    }

    private View getHeaderView(String text){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.node_header, this, false);
        ((TextView) view.findViewById(R.id.tv_nf_header_name)).setText(text);

        if(headHeight <= 0){
            headHeight = view.getLayoutParams().height; //TODO margin (top, bottom)
        }

        return view;
    }



}
