package others.progress_dilating.mine;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import cn.six.open.R;

/**
 * Created by songzhw on 2016/2/13.
 */
public class ExpandingCircleTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanding_circle);

        ExpandingCircleView view = (ExpandingCircleView) findViewById(R.id.expanding_circlr_view);
        view.show();

    }
}
