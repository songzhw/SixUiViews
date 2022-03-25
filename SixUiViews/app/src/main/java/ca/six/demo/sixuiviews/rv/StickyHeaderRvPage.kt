package ca.six.demo.sixuiviews.rv

import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import ca.six.demo.sixuiviews.R
import ca.six.views.rv.RvViewHolder
import ca.six.views.rv.stickyheader.IStickyColumnTableInflater
import ca.six.views.rv.stickyheader.StickyColumnTableAdapter
import ca.six.views.rv.stickyheader.StickyColumnTableView


class StickyHeaderRvPage: AppCompatActivity(R.layout.activity_multi_rv), IStickyColumnTableInflater<String> {
    val HEIGHT = 15
    val WIDTH = 7 // need to match with "app:sctColumnNumber=7" in xml


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataLeft: MutableList<String> = ArrayList()
        for (i in 1..HEIGHT) {
            dataLeft.add("" + i)
        }
        val dataRight: MutableList<String> = ArrayList()
        val sum = HEIGHT * WIDTH
        for (i in 1..sum) {
            dataRight.add("" + i)
        }
        val adapter = StickyColumnTableAdapter(dataLeft, dataRight)
        val tableView = findViewById<View>(R.id.sctv_demo2) as StickyColumnTableView<String>
        tableView.setAdapter(adapter)
        tableView.setBinder(this)
        // tableView.refresh(false); // It seems like this also works well here
        tableView.refresh()
    }

    override fun bindLeft(vh: RvViewHolder, s: String, position: Int) {
        vh.setText(R.id.tvItemSymbol, s)
    }

    override fun bindRight(vh: RvViewHolder, s: String, position: Int) {
        vh.setText(R.id.tvItemDetails, s)
    }

}