package ca.six.views.rv.multi

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.six.views.rv.RvViewHolder

class MultipleAdapter(val data: List<Any>, val types: Map<Class<out Any>, IRvType>) :
    RecyclerView.Adapter<RvViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val datum = data[position]
        val clazz = datum::class.java
        return types[clazz]?.getLayoutResId()!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        // viewType即layoutResId
        return RvViewHolder.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        if (data.size > position) {
            val datum = data[position]
            val clazz = datum::class.java
            val rvType: IRvType? = types.get(clazz)
            rvType?.render(holder, datum, position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}