package ca.six.views.rv.builder

import ca.six.views.rv.RvViewHolder

// 要有CF参数, 所以不能是interface (即不能利用IRvType)
abstract class BuilderItem(var data: Any) {
    abstract fun getViewType(): Int
    abstract fun render(holder: RvViewHolder)
}
