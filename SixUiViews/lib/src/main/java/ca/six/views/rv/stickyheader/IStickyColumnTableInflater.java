package ca.six.views.rv.stickyheader;


import ca.six.views.rv.RvViewHolder;

public interface IStickyColumnTableInflater<T> {
    void bindLeft(RvViewHolder vh, T s, int position);
    void bindRight(RvViewHolder vh, T s, int position);
}
