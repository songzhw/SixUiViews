package ca.six.views.rv.jimu

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.six.views.rv.RvViewHolder
import ca.six.views.rv.multi.MultipleAdapter

class JiMuAdapter {
    val list = arrayListOf<JiMuItem>()

    fun add(item: JiMuItem) {
        list.add(item)
    }

    fun clear() {
        list.clear()
    }

    fun generateAdapter()/*: RecyclerView.Adapter<RvViewHolder>*/ {

        // reduce()不带initValue的, 只有fold()才带
        val data = list.fold(arrayListOf<Any>()) { accu, item ->
            accu.add(item.data)
            accu
        }
        println("szw data = $data")

    }
}

class JiMuItem(val data: Any) {
    fun getViewType(position: Int): Int {
        return 0
    }

    fun render(holder: RvViewHolder, position: Int) {

    }
}