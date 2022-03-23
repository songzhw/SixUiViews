package ca.six.demo.sixuiviews.rv.jimu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.six.demo.sixuiviews.R
import ca.six.views.rv.builder.BuilderAdapterWrapper
import kotlinx.android.synthetic.main.activity_rv.*

class BuilderAdapterDemo : AppCompatActivity(R.layout.activity_rv) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv.layoutManager = LinearLayoutManager(this)

        val d1 = Description("scratch card", R.drawable.photo4)
        val d2 = Description("jimu rv", R.drawable.photo5)
        val d3 = Description("folder view", R.drawable.food)
        val d4 = Description("tiger", R.drawable.little_tiger)
        val d5 = Description("scope", R.drawable.photo1)
        val d6 = Description("hill", R.drawable.photo2)
        val d7 = Description("lake", R.drawable.photo3)

        val wrapper = BuilderAdapterWrapper()

        wrapper.add(TitleItem("views"))
        wrapper.add(DescriptionItem(d1))
        wrapper.add(DescriptionItem(d2))

        wrapper.add(TitleItem("recyclerviews"))
        wrapper.add(DescriptionItem(d3))
        wrapper.add(DescriptionItem(d4))

        wrapper.add(TitleItem("photos"))
        wrapper.add(DescriptionItem(d5))
        wrapper.add(DescriptionItem(d6))
        wrapper.add(DescriptionItem(d7))

        rv.adapter = wrapper.generateAdapter()

    }
}