package others.progress_dilating.mine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songzhw on 2016/2/13.
 */
public class ExpandingCircleView extends View {
    private int numbers = 3, radius = 50, spacing = 50;
    private List<ExpandingCircleDrawable> drawables;

    public ExpandingCircleView(Context context) {
        super(context);
        init(context, null);
    }

    public ExpandingCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if(attrs != null) {
            // get custom attribute from TypedArray.
            // Then typedArray.recycle()
        }

        // TODO -- delete
        this.setBackgroundColor(Color.GRAY);

        drawables = new ArrayList<>(numbers);
        for(int i = 1 ; i <= numbers ; i++){
            ExpandingCircleDrawable drawable = new ExpandingCircleDrawable(radius);
            drawables.add(drawable);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = (numbers * 2) * radius + (numbers - 1) * spacing;
        width += (spacing + spacing); // two spacing in the head and tail.
        setMeasuredDimension( width,
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int singleLeft = left+spacing, singleRight = singleLeft + radius + radius;
        for(Drawable d : drawables){
            d.setBounds(singleLeft, top, singleRight, bottom);
            singleLeft += (2 * radius + spacing);
            singleRight += (2 * radius + spacing); // 平移同样的距离
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(Drawable d : drawables){
            d.draw(canvas);
        }
    }
}
