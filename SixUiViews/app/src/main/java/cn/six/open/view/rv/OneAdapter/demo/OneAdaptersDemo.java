package cn.six.open.view.rv.OneAdapter.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.open.R;
import cn.six.open.view.rv.OneAdapter.OneAdapter;
import cn.six.open.view.rv.OneAdapter.RvViewHolder;


public class OneAdaptersDemo extends Activity  {
    private RecyclerView rv;
    private OneAdapter adapter;
    private List<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_demo);

        rv = (RecyclerView) findViewById(R.id.rvRefresh);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new OneAdapter<String>(R.layout.item_rv_one) {
            @Override
            protected void apply(RvViewHolder vh, String value, int position) {
                vh.setText(R.id.tv_rv_item, value);
            }
        };
        data = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            String title = "Group " + (i / 3);
            data.add("Item : " + i);
        }
        adapter.data = data;
        rv.setAdapter(adapter);
    }


}