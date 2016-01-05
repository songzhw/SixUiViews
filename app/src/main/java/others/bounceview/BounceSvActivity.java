package others.bounceview;

import android.os.Bundle;
import android.app.Activity;  
import android.view.Window;

import cn.six.open.R;

public class BounceSvActivity extends Activity {
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.activity_bounce_sv);
    }  

  
} 