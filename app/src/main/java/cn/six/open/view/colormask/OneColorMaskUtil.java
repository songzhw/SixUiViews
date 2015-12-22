package cn.six.open.view.colormask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;

/**
 * Created by songzhw on 2015/12/22
 */
public class OneColorMaskUtil {

    public static Bitmap getColoredBitmap(Context ctx, int imgResId, int baseColor){
        Bitmap tmp = BitmapFactory.decodeResource(ctx.getResources(), imgResId);
        Bitmap rlt = tmp.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(rlt);
        canvas.drawColor(baseColor, PorterDuff.Mode.SRC_IN);
        tmp.recycle();
        return rlt;
    }

}
