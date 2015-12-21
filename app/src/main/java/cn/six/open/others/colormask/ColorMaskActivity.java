package cn.six.open.others.colormask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import cn.six.open.R;

/**
 * Created by songzhw on 2015/12/21
 */
public class ColorMaskActivity extends Activity {
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.ic_launcher);
        final Bitmap[] bitmaps = ColorMaskUtil.generateIconBitmaps(this, R.drawable.ic_phone);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = bitmaps.length;
                index = index % length;
                iv.setImageBitmap(bitmaps[index]);
                index++;

            }
        });
        setContentView(iv, new FrameLayout.LayoutParams(200,200));
    }
}
