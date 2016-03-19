package others.circle_reveal.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.six.open.R;
import others.circle_reveal.animation.SupportAnimator;
import others.circle_reveal.animation.ViewAnimationUtils;

/**
 * Created by songzhw on 3/19/16.
 */
public class MyCircleRevealDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circle_reveal);

        final View registeView = findViewById(R.id.view_register);
        final View loginView = findViewById(R.id.view_login);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_mycr);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // view, centerX, centerY, startRadius, endRadius)
                System.out.println("login width = "+loginView.getWidth()+" ; "+loginView.getHeight());
                SupportAnimator anim = ViewAnimationUtils.createCircularReveal(
                        loginView, loginView.getWidth(), 0,
                        0, 2 * loginView.getWidth()
                );
                anim.setDuration(700);
                anim.addListener(new SupportAnimator.SimpleAnimatorListener() {
                    @Override
                    public void onAnimationStart() {
                        registeView.setVisibility(View.GONE);
                    }

                });
                anim.start();
            }
        });
    }
}
