package ca.six.demo.sixuiviews.rv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.six.demo.sixuiviews.R
import ca.six.views.rv.OnRvItemClickListener
import ca.six.views.rv.RvViewHolder
import ca.six.views.rv.single.OneAdapter
import kotlinx.android.synthetic.main.activity_rv.*


class OneAdapterDemo : AppCompatActivity(R.layout.activity_rv) {
    private lateinit var adapter: OneAdapter<String>
    private lateinit var data: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv.layoutManager = LinearLayoutManager(this)
        adapter = object : OneAdapter<String>(R.layout.item_rv_one) {
            override fun apply(vh: RvViewHolder, value: String, position: Int) {
                vh.setText(R.id.tv_rv_item, value)
            }
        }
        data = ArrayList()
        for (i in 0..25) {
            data.add("Item : $i")
        }
        adapter.data = data
        rv.adapter = adapter

        rv.addOnItemTouchListener(object : OnRvItemClickListener(rv) {
            override fun onItemClick(vh: RecyclerView.ViewHolder) {
                val pos = vh.layoutPosition
                val datum = data[pos]
                println("click $pos : data = $datum")
            }
        })
    }
}