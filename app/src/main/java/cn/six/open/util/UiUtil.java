
package cn.six.open.util;

import android.content.Context;
import android.util.TypedValue;

public class UiUtil {
	public static int dp2px(Context ctx, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, ctx.getResources() .getDisplayMetrics());
	}

	public static double mapValueFromRangeToRange(double value, double fromLow, double fromHigh, double toLow, double toHigh) {
		return toLow + ((value - fromLow) / (fromHigh - fromLow) * (toHigh - toLow));
	}

	public static double clamp(double value, double low, double high) {
		return Math.min(Math.max(value, low), high);
	}
}
