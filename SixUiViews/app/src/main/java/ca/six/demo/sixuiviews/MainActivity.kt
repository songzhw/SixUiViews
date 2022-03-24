package ca.six.demo.sixuiviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.six.demo.sixuiviews.rv.jimu.BuilderAdapterDemo
import ca.six.demo.sixuiviews.rv.MultiAdapterDemo
import ca.six.demo.sixuiviews.rv.OneAdapterDemo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener { nav<ColorMaskActivity>() }
        btn2.setOnClickListener { nav<CustomeAcitonSheetActivity>() }
        btn3.setOnClickListener { nav<LoginNameDemo>() }
        btn4.setOnClickListener { nav<MyTabsViewActivity>() }
        btn5.setOnClickListener { nav<GuideActivity>() }

        btn11.setOnClickListener { nav<AnimatableCheckDemo>() }
        btn12.setOnClickListener { nav<ExpandableViewDemo>() }
        btn13.setOnClickListener { nav<ScratchCardActivity>() }
        btn14.setOnClickListener { nav<ResideMenuActivity>() }

        // [rv]
        btn21.setOnClickListener { nav<OneAdapterDemo>() }
        btn22.setOnClickListener { nav<MultiAdapterDemo>() }
        btn23.setOnClickListener { nav<BuilderAdapterDemo>() }
    }

}