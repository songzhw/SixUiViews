package ca.six.demo.sixuiviews

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_color_mask.*
import ca.six.demo.sixuiviews.R
import ca.six.views.util.ColorMaskUtil

/**
 * Created by songzhw on 2015/12/21
 */
class ColorMaskActivity : Activity(){
    private var index = 0
    private lateinit var bitmaps: Array<Bitmap>

    private val phone2Id: Int = R.drawable.ic_phone2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_mask)
    }

    fun clickBlue(v: View) {
        val blue: Bitmap = ColorMaskUtil.getColoredBitmap(this, phone2Id, Color.BLUE)
        ivColorMask.setImageBitmap(blue)
    }

    fun clickRed(v: View) {
        val red: Bitmap = ColorMaskUtil.getColoredBitmap(this, phone2Id, Color.RED)
        ivColorMask.setImageBitmap(red)
    }

}
