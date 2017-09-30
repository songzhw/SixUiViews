package cn.six.open.view.sticky_column_table;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import cn.six.open.R;
import cn.six.open.view.rv.OnRvItemClickListener;
import cn.six.open.view.rv.OneAdapter.OneAdapter;
import cn.six.open.view.rv.OneAdapter.RvViewHolder;

/**
 * Need to setAdapter(), then call refresh().
 */
public class StickyColumnTableView<T> extends LinearLayout {
    public static final int DEFAULT_COLUMN_NUMBER = 8;

    private StickyColumnTableAdapter adapter;
    private MeMoveRecyclerView rvLeft, rvRight;
    private CoordinateRvScrollListener rvLeftScrollListener, rvRightScrollListener;
    private IStickyColumnTableInflater<T> binder;


    public StickyColumnTableView(Context context) {
        super(context);
        init(context, null);
    }

    public StickyColumnTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context ctx, AttributeSet attrs) {
        this.setOrientation(HORIZONTAL);

        int width = DEFAULT_COLUMN_NUMBER;
        if (attrs != null) {
            TypedArray ta = ctx.obtainStyledAttributes(attrs, R.styleable.StickyColumnTableView);
            width = ta.getInteger(R.styleable.StickyColumnTableView_sctColumnNumber, DEFAULT_COLUMN_NUMBER);
            ta.recycle();
        }

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View leftView = inflater.inflate(R.layout.sub_multi_rv_left, this, false);
        View rightView = inflater.inflate(R.layout.sub_multi_rv_right, this, false);
        this.addView(leftView);
        this.addView(rightView);

        rvLeft = (MeMoveRecyclerView) findViewById(R.id.rvMultiRvLeft);
        rvRight = (MeMoveRecyclerView) findViewById(R.id.rvMultiRvRight);
        rvLeft.setCoordinateRecyclerView(rvRight);
        rvRight.setCoordinateRecyclerView(rvLeft);


        rvLeft.setLayoutManager(new LinearLayoutManager(ctx));
        rvRight.setLayoutManager(new GridLayoutManager(ctx, width));

        rvRight.addOnItemTouchListener(new OnRvItemClickListener(rvRight) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                System.out.println("szw rvRight click "+vh.getLayoutPosition());
            }
        });
    }

    public void setAdapter(StickyColumnTableAdapter adapter) {
        this.adapter = adapter;
    }

    public void setBinder(IStickyColumnTableInflater<T> binder) {
        this.binder = binder;
    }

    public void refresh() {
        if (adapter == null) {
            return;
        }

        rvLeft.setAdapter(new OneAdapter<T>(R.layout.item_left, adapter.getLeftData()) {
            @Override
            protected void apply(RvViewHolder vh, T value, int position) {
                binder.bindLeft(vh, value, position);
            }
        });

        rvRight.setAdapter(new OneAdapter<T>(R.layout.item_right, adapter.getRightData()) {
            @Override
            protected void apply(RvViewHolder vh, T value, int position) {
                binder.bindRight(vh, value, position);
            }
        });

        rvLeftScrollListener = new CoordinateRvScrollListener(rvRight);
        rvLeft.addOnItemTouchListener(new CoordinateRvItemTouchListener(rvRight, rvLeftScrollListener));

        rvRightScrollListener = new CoordinateRvScrollListener(rvLeft);
        rvRight.addOnItemTouchListener(new CoordinateRvItemTouchListener(rvLeft, rvRightScrollListener));

    }

}

