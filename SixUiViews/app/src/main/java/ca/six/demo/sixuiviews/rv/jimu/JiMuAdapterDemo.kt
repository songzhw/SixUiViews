package ca.six.demo.sixuiviews.rv.jimu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.six.demo.sixuiviews.R
import ca.six.views.rv.jimu.JiMuAdapter
import ca.six.views.rv.jimu.JiMuItem
import kotlinx.android.synthetic.main.activity_rv.*

class JiMuAdapterDemo : AppCompatActivity(R.layout.activity_rv) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv.layoutManager = LinearLayoutManager(this)

        val d1 = Description("scratch card", R.drawable.forest1)
        val d2 = Description("folder view", R.drawable.food)
        val d3 = Description("jimu rv", R.drawable.coin)

        val adapter = JiMuAdapter()
        adapter.add(TitleItem("views"))
        adapter.add(DescriptionItem(d1))
        adapter.add(DescriptionItem(d2))
        adapter.add(TitleItem("recyclerviews"))
        adapter.add(DescriptionItem(d3))

        rv.adapter = adapter.generateAdapter()


    }
}