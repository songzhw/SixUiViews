package cn.six.open.others.zoomtv;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

public class ZoomTvActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ZoomTextView tv = new ZoomTextView(this);
        tv.setGravity(Gravity.CENTER);
        tv.setText("Hello, Zoom Tv");
        setContentView(tv);
    }
}
