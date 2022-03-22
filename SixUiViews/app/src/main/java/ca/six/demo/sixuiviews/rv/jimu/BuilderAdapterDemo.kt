package ca.six.demo.sixuiviews.rv.jimu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.six.demo.sixuiviews.R
import ca.six.views.rv.jimu.BuilderAdapterWrapper
import kotlinx.android.synthetic.main.activity_rv.*

class BuilderAdapterDemo : AppCompatActivity(R.layout.activity_rv) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv.layoutManager = LinearLayoutManager(this)

        val d1 = Description("scratch card", R.drawable.forest1)
        val d2 = Description("folder view", R.drawable.food)
        val d3 = Description("jimu rv", R.drawable.coin)

        val wrapper = BuilderAdapterWrapper()
        wrapper.add(TitleItem("views"))
        wrapper.add(DescriptionItem(d1))
        wrapper.add(DescriptionItem(d2))
        wrapper.add(TitleItem("recyclerviews"))
        wrapper.add(DescriptionItem(d3))

        rv.adapter = wrapper.generateAdapter()


    }
}