package cn.six.open.view.sticky_column_table.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cn.six.open.R;
import cn.six.open.view.rv.OneAdapter.RvViewHolder;
import cn.six.open.view.sticky_column_table.IStickyColumnTableInflater;
import cn.six.open.view.sticky_column_table.StickyColumnTableAdapter;
import cn.six.open.view.sticky_column_table.StickyColumnTableView;


public class MultiRvDemo2 extends Activity implements IStickyColumnTableInflater<String> {
    public static final int HEIGHT = 15;
    public static final int WIDTH = 7; // need to match with "app:sctColumnNumber=7" in xml

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_multi_rv);

        List<String> dataLeft = new ArrayList<>();
        for (int i = 1; i <= HEIGHT; i++) {
            dataLeft.add("" + i);
        }

        List<String> dataRight = new ArrayList<>();
        int sum = HEIGHT * WIDTH;
        for (int i = 1; i <= sum; i++) {
            dataRight.add("" + i);
        }

        StickyColumnTableAdapter<String> adapter = new StickyColumnTableAdapter<>(dataLeft, dataRight);
        StickyColumnTableView<String> tableView = (StickyColumnTableView<String>) findViewById(R.id.sctv_demo2);
        tableView.setAdapter(adapter);
        tableView.setBinder(this);
//        tableView.refresh(false); // It seems like this also works well here
        tableView.refresh();
    }

    @Override
    public void bindLeft(RvViewHolder vh, String s, int position) {
        vh.setText(R.id.tvItemSymbol, s);
    }

    @Override
    public void bindRight(RvViewHolder vh, String s, int position) {
        vh.setText(R.id.tvItemDetails, s);
    }
}

