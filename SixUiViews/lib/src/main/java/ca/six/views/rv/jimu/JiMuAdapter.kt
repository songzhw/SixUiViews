package ca.six.views.rv.jimu

import android.view.ViewGroup
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

abstract class BuilderItem(var data: Any) {
    abstract fun getViewType(): Int
    abstract fun render(holder: RvViewHolder)
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