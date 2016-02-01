package others.gesture_fun;

import android.app.Activity;
import android.os.Bundle;

import cn.six.open.R;

/**
 * Created by songzhw on 2016/2/1
 *
 * songzhw:
 * Tests on Nexus6P and hongmi are both failed.
 * I don't know if this library is functional or not.
 *
 */

@Deprecated
public class SwipeDeleteEtTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_swipe_delete_et);
    }
}
