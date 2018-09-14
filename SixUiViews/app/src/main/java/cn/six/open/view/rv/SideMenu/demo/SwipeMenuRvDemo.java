package cn.six.open.view.rv.SideMenu.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.six.open.R;
import cn.six.open.view.rv.OnRvItemClickListener;
import cn.six.open.view.rv.OneAdapter.OneAdapter;
import cn.six.open.view.rv.OneAdapter.RvViewHolder;
import cn.six.open.view.rv.SideMenu.SwipeMenuLayout;


public class SwipeMenuRvDemo extends AppCompatActivity {
    private RecyclerView rv;
    private OneAdapter<String> adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_menu_demo);

        rv = findViewById(R.id.rv_data);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));


        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("Item " + i);
        }
        adapter = new OneAdapter<String>(R.layout.item_swipe_menu_demo, data) {
            @Override
            protected void apply(RvViewHolder vh, String value, int position) {
                // IMPORTANT: othewise, the recycled item will be a problem when this item is open already
                SwipeMenuLayout swipeMenuLayout = vh. getView(R.id.swipeMenuLayout);
                if(swipeMenuLayout.isOpen){
                    swipeMenuLayout.close();
                }
                vh.setSrc(R.id.ivRvItemSwipe, R.drawable.ic_launcher);
                vh.setText(R.id.tvRvItemSwipe, value);
            }
        };
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(new OnRvItemClickListener(rv) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("click item " + vh.getLayoutPosition());
            }
        });
    }
}