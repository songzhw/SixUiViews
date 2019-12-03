package cn.six.open.view.rv.MultiAdapter.demo

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import cn.six.open.R
import cn.six.open.view.rv.MultiAdapter.IRvType
import cn.six.open.view.rv.MultiAdapter.MultipleAdapter
import cn.six.open.view.rv.OneAdapter.RvViewHolder
import kotlinx.android.synthetic.main.activity_rv_demo.*

class MultipleRvTypesDemo : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_demo)

        val data = arrayListOf(
                Yang("001"), Yin("1A"), Yin("1B"), Yin("1C"),
                Yang("002"), Yin("2A"), Yin("2B"), Yin("2C"), Yin("2D")
        )
        val typeTitle = object : IRvType {
            override fun getLayoutResId(): Int = R.layout.item_rv_title
            override fun render(vh: RvViewHolder, datum: Any, position: Int) {
                vh.setText(R.id.tvRvTitle, (datum as Yang).title)
            }
        }
        val typeContent = object : IRvType {
            override fun getLayoutResId(): Int = R.layout.item_rv_content
            override fun render(vh: RvViewHolder, datum: Any, position: Int) {
                vh.setText(R.id.tvRvTitle, (datum as Yin).name)
            }
        }
        val types = mapOf(Yang::class.java to typeTitle, Yin::class.java to typeContent)

        rvRefresh.setLayoutManager(LinearLayoutManager(this))
        val adapter = MultipleAdapter(data, types)
        rvRefresh.adapter = adapter

    }
}

class Yin(val name: String)
class Yang(val title: String)