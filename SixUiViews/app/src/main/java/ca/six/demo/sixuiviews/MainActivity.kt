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
    }

}


inline fun <reified T>  Activity.nav() {
    val intent2 = Intent(this, T::class.java)
    startActivity(intent2)
}