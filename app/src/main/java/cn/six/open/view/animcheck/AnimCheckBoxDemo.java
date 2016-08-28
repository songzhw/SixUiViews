package cn.six.open.view.animcheck;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import cn.six.open.R;


/**
 * Created by songzhw on 2016-08-17
 */
public class AnimCheckBoxDemo extends Activity implements View.OnClickListener,
        CrossCheckBox.ICrossChangeListener{

    private CrossCheckBox cbCross;
    private TickCheckBox cbTick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_check_box);

        this.cbCross = (CrossCheckBox) findViewById(R.id.cbAnim);
        this.cbCross.setOnClickListener(this);
        cbCross.listener = this;
        cbCross.setText("Play squash");

        this.cbTick = (TickCheckBox) findViewById(R.id.cbAnim2);
        this.cbTick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == cbCross) {
            cbCross.toggle();
        } else if (v == cbTick) {
            cbTick.toggle();
        }
    }

    @Override
    public void onCrossChanged(boolean isCrossed) {
        System.out.println("szw onCrossChanged("+isCrossed+")");
    }

}