package cn.six.open.view.rv.MultiAdapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import cn.six.open.view.rv.OneAdapter.RvViewHolder

// @parameter types:  mapOf(Yang::class.java to rvType1, Yin::class.java to rvType1)
class MultipleAdapter<C> (val data: List<Any>, val types: Map<Class<C>, IRvType<C>>) : RecyclerView.Adapter<RvViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val datum = data.get(position)
        val clazz = datum::class.java
        return types.get(clazz)?.getLayoutResId()!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        // viewType即layoutResId
        return RvViewHolder.createViewHolder(parent, viewType)
    }


    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        if (data.size > position) {
            val datum = data.get(position)
            val clazz = datum::class.java
            val rvType = types.get(clazz)
            rvType?.render(holder, datum, position)
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

}


interface IRvType<T> {
    fun getLayoutResId(): Int
    fun render(vh: RvViewHolder, datum: T, position: Int)
}