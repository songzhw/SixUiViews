package ca.six.views.rv.single

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ca.six.views.rv.RvViewHolder

// 注意不用重写getItem与getItemCount了
// 另外, 不能像OneAdapter一样把data传进来. 不需要一个data, 因为ListAdapter的list data是变化的, 最好是不同对象(或深拷贝)
abstract class OneListAdapter<T>(@LayoutRes resId: Int, diff: DiffUtil.ItemCallback<T>)
    : ListAdapter<T, RvViewHolder>(diff) {
    private var layoutResId: Int = resId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = RvViewHolder.createViewHolder(parent, layoutResId)

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val value = getItem(position)
        apply(holder, value, position)
    }

    protected abstract fun apply(holder: RvViewHolder, value: T, position: Int)

}