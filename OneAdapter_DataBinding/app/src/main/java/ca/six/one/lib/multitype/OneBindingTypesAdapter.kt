package ca.six.one.lib.multitype

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ca.six.one.lib.RvBindingViewHolder

class OneBindingTypesAdapter(private val rows: List<BindingTypesRow<*>>)
    : RecyclerView.Adapter<RvBindingViewHolder>() {

    override fun getItemCount(): Int {
        return rows.size
    }

    override fun getItemViewType(position: Int): Int {
        return rows[position].layoutResId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvBindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return RvBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvBindingViewHolder, position: Int) {
        val row = rows.get(position)
        holder.binding.setVariable(row.bindingId, row.data)
        holder.binding.executePendingBindings()
    }

}