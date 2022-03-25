/**
 * @author songzhw
 * @date 2015-01-26
 * MaxHeightRelativeLayout.java
 */
package ca.six.views.views.maxheig;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import ca.six.views.R;

public class MaxHeightRelativeLayout extends RelativeLayout {
	private int maxHeight;

	public MaxHeightRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	public MaxHeightRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public MaxHeightRelativeLayout(Context context) {
		super(context);
		maxHeight = 0;
	}
	
	private void init(Context ctx, AttributeSet attrs){
		TypedArray ta = ctx.obtainStyledAttributes(attrs, R.styleable.MaxHeightRelativeLayout);
		maxHeight = ta.getDimensionPixelSize(R.styleable.MaxHeightRelativeLayout_max_height, 0);
		ta.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int origHeight = MeasureSpec.getSize(heightMeasureSpec);
		if(maxHeight > 0 && maxHeight < origHeight) {
			int measureMode = MeasureSpec.getMode(heightMeasureSpec);
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, measureMode);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
}
