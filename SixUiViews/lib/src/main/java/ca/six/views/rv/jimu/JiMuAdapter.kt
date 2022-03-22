package ca.six.views.rv.jimu

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.six.views.rv.RvViewHolder
import ca.six.views.rv.multi.IRvType
import ca.six.views.rv.multi.MultipleAdapter

class JiMuAdapter {
    val list = arrayListOf<JiMuItem>()

    fun add(item: JiMuItem) {
        list.add(item)
    }

    fun clear() {
        list.clear()
    }

    fun generateAdapter(): RecyclerView.Adapter<RvViewHolder> {

        // reduce()不带initValue的, 只有fold()才带
        val data = list.fold(arrayListOf<Any>()) { accu, item ->
            accu.add(item.data)
            accu
        }

        //TODO 数量上对上了. 但是render这里不对了
        val types = list.fold(hashMapOf<Class<out Any>, IRvType>()) { accu, item ->
            accu[item.data::class.java] = item
            accu
        }

        println("szw data = $data")
        println("szw type = $types")
        return MultipleAdapter(data, types)
    }
}



/*
szw data = [
    views,
    Description(desp=scratch card, imageResId=2131165287), Description(desp=folder view, imageResId=2131165286),
    recyclerviews,
    Description(desp=jimu rv, imageResId=2131165280)
]

szw types = [
    ca.six.demo.sixuiviews.rv.jimu.TitleItem@8888f01,
    ca.six.demo.sixuiviews.rv.jimu.DescriptionItem@97d61a6,
    ca.six.demo.sixuiviews.rv.jimu.DescriptionItem@a8757e7,
    ca.six.demo.sixuiviews.rv.jimu.TitleItem@c12ff94,
    ca.six.demo.sixuiviews.rv.jimu.DescriptionItem@e6e1a3d
]

szw type = {
    class java.lang.String                              =   ca.six.demo.sixuiviews.rv.jimu.TitleItem@a8757e7,
    class ca.six.demo.sixuiviews.rv.jimu.Description    =   ca.six.demo.sixuiviews.rv.jimu.DescriptionItem@c12ff94
}
 */



/*
// 要有CF参数, 所以不能是interface
// TODO 要有个toIRvType()的方法才行
abstract class JiMuItem(var data: Any) : IRvType {
    abstract fun getViewType(): Int
    abstract fun render(holder: RvViewHolder)

    override fun getLayoutResId() = getViewType()

    override fun render(vh: RvViewHolder, datum: Any, position: Int) = render(vh)

}
*/