package others.node_flow.mine;

import android.content.Context;
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
        int i = 0;
        for(String atop : tops) {
            View view = getHeaderView(atop);
            view.setTranslationY(i * headHeight);
            addView(view);
            i++;
        }
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
