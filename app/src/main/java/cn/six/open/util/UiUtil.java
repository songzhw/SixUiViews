/**
 * @author hzsongzhengwang
 * @date 2014年12月12日
 * UiUtil.java
 * Copyright 2014 NetEase. All rights reserved. 
 */
package cn.six.open.util;

import android.content.Context;
import android.util.TypedValue;

public class UiUtil {
	public static int dp2px(Context ctx, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, ctx.getResources() .getDisplayMetrics());
	}
}
