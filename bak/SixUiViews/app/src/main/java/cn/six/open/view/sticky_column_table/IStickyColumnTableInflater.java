package cn.six.open.view.sticky_column_table;

import cn.six.open.view.rv.OneAdapter.RvViewHolder;

public interface IStickyColumnTableInflater<T> {
    void bindLeft(RvViewHolder vh, T s, int position);
    void bindRight(RvViewHolder vh, T s, int position);
}
