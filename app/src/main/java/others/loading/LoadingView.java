package others.loading;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by songzhw on 2016/4/4.
 */
public class LoadingView extends ProgressBar {
    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(Context ctx){

    }

    //  Define the drawable used to draw the progress bar in indeterminate mode.
    @Override
    public void setIndeterminateDrawable(Drawable d) {
        super.setIndeterminateDrawable(d);
    }

}
