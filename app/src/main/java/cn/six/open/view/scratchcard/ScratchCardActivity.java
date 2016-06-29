
package cn.six.open.view.scratchcard;

import android.graphics.Paint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import cn.six.open.R;

public class ScratchCardActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScratchCardView view = new ScratchCardView(this);
        view.setImageResource(R.drawable.forest1);
        view.setScaleType(ScaleType.FIT_XY);
        setContentView(view, new FrameLayout.LayoutParams(800,800));
    }
}