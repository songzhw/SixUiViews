package ca.six.views.rv.builder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.six.views.rv.RvViewHolder


class BuilderAdapter(val data: List<BuilderItem>) : RecyclerView.Adapter<RvViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val datum = data[position]
        return datum.getViewType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder =
        RvViewHolder.createViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        if (data.size > position) {
            val datum = data[position]
            datum.render(holder)
        }
    }

    override fun getItemCount() = data.size

}
