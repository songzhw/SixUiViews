package ca.six.views.rv.builder

import androidx.recyclerview.widget.RecyclerView
import ca.six.views.rv.RvViewHolder

class BuilderAdapterWrapper {
    val list = arrayListOf<BuilderItem>()

    fun add(item: BuilderItem) {
        list.add(item)
    }

    fun clear() {
        list.clear()
    }

    fun generateAdapter(): RecyclerView.Adapter<RvViewHolder> {
        return BuilderAdapter(list)
    }

}