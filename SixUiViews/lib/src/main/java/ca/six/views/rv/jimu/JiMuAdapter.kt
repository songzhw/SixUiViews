package ca.six.views.rv.jimu

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.six.views.rv.RvViewHolder

class JiMuAdapter : RecyclerView.Adapter<RvViewHolder>() {
    val list = arrayListOf<JiMuItem>()

    fun add(item: JiMuItem){
        list.add(item)
    }

    fun clear(){
        list.clear()
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].getViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        if (list.size > position) {
            val item = list[position]
            item.render(holder, position)
        }
    }

    override fun getItemCount(): Int = list.count()
}

class JiMuItem {
    fun getViewType(position: Int) : Int  {
        return 0
    }

    fun render(holder: RvViewHolder, position: Int){

    }
}