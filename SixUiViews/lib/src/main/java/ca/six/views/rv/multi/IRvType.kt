package ca.six.views.rv.multi

import ca.six.views.rv.RvViewHolder

interface IRvType {
    fun getLayoutResId(): Int
    fun render(vh: RvViewHolder, datum: Any, position: Int)
}