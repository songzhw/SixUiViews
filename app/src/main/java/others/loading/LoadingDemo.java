package others.loading;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by songzhw on 2016/4/4.
 */
public class LoadingDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoadingView view = new LoadingView(this);
        setContentView(view);
    }
}
