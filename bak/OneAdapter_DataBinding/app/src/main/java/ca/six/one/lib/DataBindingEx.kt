package ca.six.one.lib

import android.databinding.BindingAdapter
import android.support.annotation.MainThread
import android.support.v7.widget.RecyclerView
import ca.six.one.lib.multitype.BindingTypesRow
import ca.six.one.lib.multitype.OneBindingTypesAdapter
import ca.six.one.lib.simple.BindingRow
import ca.six.one.lib.simple.OneBindingAdapter

object DataBindingEx {
    @BindingAdapter("data", "row")
    @JvmStatic
    @MainThread
    fun <T> rvSetData(rv: RecyclerView, data: List<T>?, row: BindingRow?) {
        if (row != null) {
            val adapter = OneBindingAdapter<T>(row.layoutResId, row.bindingId, data)
            rv.adapter = adapter
        }
    }

    @BindingAdapter("rows")
    @JvmStatic
    @MainThread
    fun rvSetRows(rv: RecyclerView, rows: List<BindingTypesRow>?) {
        if (rows != null) {
            val adapter = OneBindingTypesAdapter(rows)
            rv.adapter = adapter
        }
    }
}