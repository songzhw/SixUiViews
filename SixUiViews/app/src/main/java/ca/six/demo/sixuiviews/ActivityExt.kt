package ca.six.demo.sixuiviews

import android.app.Activity
import android.content.Intent

inline fun <reified T> Activity.nav() {
    val intent2 = Intent(this, T::class.java)
    startActivity(intent2)
}