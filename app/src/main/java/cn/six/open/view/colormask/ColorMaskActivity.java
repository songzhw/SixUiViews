package cn.six.open.view.colormask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import cn.six.open.R;
import cn.six.open.others.colormask.ColorMaskUtil;
import cn.six.open.view.colormask.OneColorMaskUtil;

/**
 * Created by songzhw on 2015/12/21
 */
public class ColorMaskActivity extends Activity implements View.OnClickListener{
    private int index = 0;
    private  Bitmap[] bitmaps;
    private ImageView iv, iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_mask);

        iv = (ImageView) findViewById(R.id.ivColorMask);
        iv.setImageResource(R.drawable.ic_launcher);
        iv2 = (ImageView) findViewById(R.id.ivColorMask2);
        iv2.setImageResource(img2ResId);

        bitmaps = ColorMaskUtil.generateIconBitmaps(this, R.drawable.ic_phone);
        iv.setOnClickListener(this);
    }

    public void clickBlue(View v){
        index = 0;
        ColorMaskUtil.baseColor = Color.BLUE;
        bitmaps = ColorMaskUtil.generateIconBitmaps(this, R.drawable.ic_phone);
        iv.setImageResource(R.drawable.ic_launcher);

        Bitmap blue = OneColorMaskUtil.getColoredBitmap(this, img2ResId, Color.BLUE);
        iv2.setImageBitmap(blue);
    }

    public void clickYellow(View v){
        index = 0;
        ColorMaskUtil.baseColor = Color.YELLOW;
        bitmaps = ColorMaskUtil.generateIconBitmaps(this, R.drawable.ic_phone);
        iv.setImageResource(R.drawable.ic_launcher);

        Bitmap red = OneColorMaskUtil.getColoredBitmap(this, img2ResId, Color.RED);
        iv2.setImageBitmap(red);
        // Note: red.recycled() in Activity#onDestory()
    }

    @Override
    public void onClick(View v) {
        int length = bitmaps.length;
        index = index % length;
        Log.d("szw", "index = "+index);
        iv.setImageBitmap(bitmaps[index]);
        index++;
    }

    private int img2ResId = R.drawable.ic_phone2;
}
