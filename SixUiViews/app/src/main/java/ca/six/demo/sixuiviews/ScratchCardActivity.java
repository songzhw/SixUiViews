
package ca.six.demo.sixuiviews;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;

import ca.six.views.views.porterduff_clear.ScratchCardView;

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