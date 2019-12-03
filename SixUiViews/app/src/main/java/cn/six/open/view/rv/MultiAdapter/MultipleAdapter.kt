package cn.six.open.view.rv.MultiAdapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import cn.six.open.view.rv.OneAdapter.RvViewHolder

/* TODO
    (to refactor) we may have 100 data, and only 2 IRvTypes, so it's impossible to make `data: List<IRvType>`
    also, I don't want to do a for iteration in the getItemViewType() and other methods
 */
class MultipleAdapter<T>(val data: List<T>, val types: List<IRvType<T>>) : RecyclerView.Adapter<RvViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        for (rvType in types) {
            val viewType = rvType.getType(position)
            if (viewType != IRvType.TYPE_INVALID) {
                return viewType
            }
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
//        for (rvType in types) {
//            val viewType = rvType.getType(position)
//            if (viewType != IRvType.TYPE_INVALID) {
//                return rvType.get
//            }
//        }
        TODO("not implemented")
    }


    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}


interface IRvType<T> {
    fun getType(position: Int): Int
    fun getLayoutResId(viewType: Int): Int
    fun apply(vh: RvViewHolder, datum: T, position: Int)

    companion object {
        val TYPE_INVALID = -99
    }
}