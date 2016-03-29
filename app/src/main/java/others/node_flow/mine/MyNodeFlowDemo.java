package others.node_flow.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import cn.six.open.R;

/**
 * Created by songzhw on 2016/3/17
 */
public class MyNodeFlowDemo extends Activity {
    private MySimpleNodeFlow flow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_node_flow);

        flow = (MySimpleNodeFlow) findViewById(R.id.msnf_my_node_flow);
    }

    public void clickOne(View v){
        flow.showFirstFloor();
    }

    public void clickTwo(View v){

    }

    public void clickThree(View v){
        flow.removeAllViewsInLayout();
    }
}
