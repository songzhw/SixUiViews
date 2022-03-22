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

        val d1 = JiMuItem("title")
        val d2 = JiMuItem(23)
        val d3 = JiMuItem(true)
        val d4 = JiMuItem("hello")
        val d5 = JiMuItem(314159L)
        val adapter = JiMuAdapter()
        adapter.add(d1)
        adapter.add(d2)
        adapter.add(d3)
        adapter.add(d4)
        adapter.add(d5)
        adapter.generateAdapter()


    }
}