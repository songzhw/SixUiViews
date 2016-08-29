package cn.six.open.view.animcheck;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import cn.six.open.R;


/**
 * Created by songzhw on 2016-08-17
 */
public class AnimCheckBoxDemo extends Activity implements View.OnClickListener,
        CrossCheckBox.ICrossChangeListener, TickCheckBox.ITickChangeListener{

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
        cbTick.listener = this;
        cbTick.setText("Second line");

//        handler.sendEmptyMessageDelayed(11, 2000);
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

    @Override
    public void onTickChanged(boolean isCrossed) {
        System.out.println("szw onTickChanged("+isCrossed+")");
    }



    // Test the text and width when changing text
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            cbCross.setText("A delayed task!");
            cbTick.setText("Another delayed task");
        }
    };


}