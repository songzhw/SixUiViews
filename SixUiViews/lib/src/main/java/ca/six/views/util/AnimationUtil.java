package ca.six.views.util;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class AnimationUtil {

    public static Animation createTranslationInAnimation(int duration) {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        TranslateAnimation an = new TranslateAnimation(type, 0, type, 0, type, 1, type, 0);
        an.setDuration(duration);
        return an;
    }

    public static Animation createAlphaInAnimation(int duration) {
        AlphaAnimation an = new AlphaAnimation(0, 1);
        an.setDuration(duration);
        return an;
    }

    public static Animation createTranslationOutAnimation(int duration) {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        TranslateAnimation an = new TranslateAnimation(type, 0, type, 0, type, 0, type, 1);
        an.setDuration(duration);
        an.setFillAfter(true);
        return an;
    }

    public static Animation createAlphaOutAnimation(int duration) {
        AlphaAnimation an = new AlphaAnimation(1, 0);
        an.setDuration(duration);
        an.setFillAfter(true);
        return an;
    }

}
