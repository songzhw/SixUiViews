package ca.six.demo.sixuiviews.rv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.six.demo.sixuiviews.R
import kotlinx.android.synthetic.main.activity_rv.*

class JiMuAdapterDemo : AppCompatActivity(R.layout.activity_rv) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv.layoutManager = LinearLayoutManager(this)
    }
}