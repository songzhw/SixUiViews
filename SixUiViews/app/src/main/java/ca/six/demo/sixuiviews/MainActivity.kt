package ca.six.demo.sixuiviews

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener { nav<ColorMaskActivity>() }
        btn2.setOnClickListener { nav<CustomeAcitonSheetActivity>() }
        btn3.setOnClickListener { nav<LoginNameDemo>() }
        btn4.setOnClickListener { nav<MyTabsViewActivity>() }

        btn11.setOnClickListener { nav<AnimatableCheckDemo>() }
        btn12.setOnClickListener { nav<ExpandableViewDemo>() }
        btn13.setOnClickListener { nav<ScratchCardActivity>() }
    }

}


inline fun <reified T>  Activity.nav() {
    val intent2 = Intent(this, T::class.java)
    startActivity(intent2)
}