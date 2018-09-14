package cn.six.open.compat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.six.open.R;

/**
 android.support.v4.view.ViewCompat

 ViewCompat # setX (View view, int x);
  :  * In Android 2.x , ViewCompat#setX() actually do nothing. The view will not move
     * In Android >= 3.x , ViewCompat # setX() will call view.setX(x).
 (I found out it by test this activity in the 2.3 and 4.4 phone)

*/
public class ViewCompatActivity extends Activity {

    private Button btnTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_button);
        btnTarget = (Button)findViewById(R.id.btnTarget);
    }

    public void click01(View v){
        ViewCompat.setX(btnTarget, 250 );
    }

    public void click02(View v){
        Log.d("szw","click the target !!!");
    }

}
