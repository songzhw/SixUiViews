package ca.six.demo.sixuiviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.six.views.views.guider.GuideView
import kotlinx.android.synthetic.main.activity_guide.*

class GuideActivity : AppCompatActivity(R.layout.activity_guide) {
    lateinit var guideView: GuideView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnGuide3.setOnClickListener { println("szw click button") }

        guideView = GuideView(this)
        val guideView = GuideView(this)
        guideView.addMe(this, "new release: west lake", ivGuide1, btnGuide3)

    }

    override fun onDestroy() {
        super.onDestroy()
        guideView.removeMe(this)
    }
}

