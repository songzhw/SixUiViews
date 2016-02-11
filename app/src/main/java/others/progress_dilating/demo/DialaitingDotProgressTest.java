package others.progress_dilating.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.widget.SeekBar;
import android.widget.TextView;

import cn.six.open.R;
import others.progress_dilating.DilatingDotsProgressBar;


public class DialaitingDotProgressTest extends Activity {
    private DilatingDotsProgressBar mDilatingDotsProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_dilating_dots);

        mDilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress_dilating_dots);
        mDilatingDotsProgressBar.setDotRadius(50);
        mDilatingDotsProgressBar.setDotSpacing(40);
        mDilatingDotsProgressBar.setNumberOfDots(6);
        mDilatingDotsProgressBar.show(500);

    }

}
