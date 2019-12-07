package ca.six.views.demo

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import ca.six.views.lib.views.anim_check.CrossCheckBox
import ca.six.views.lib.views.anim_check.TickCheckBox
import kotlinx.android.synthetic.main.activity_anim_check_box.*

class AnimatableCheckDemo : Activity(), View.OnClickListener,
    CrossCheckBox.ICrossChangeListener, TickCheckBox.ITickChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_check_box)

        cbCross.setOnClickListener(this)
        cbCross.listener = this
        cbCross.setText("Play squash")

        cbTick.setOnClickListener(this)
        cbTick.listener = this
        cbTick.setText("Second line")

    }

    override fun onClick(v: View) {
        if (v === cbCross) {
            cbCross.toggle()
        } else if (v === cbTick) {
            cbTick.toggle()
        }
    }

    override fun onCrossChanged(isCrossed: Boolean) {
        println("szw onCrossChanged($isCrossed)")
    }

    override fun onTickChanged(isCrossed: Boolean) {
        println("szw onTickChanged($isCrossed)")
    }


    // Test the text and width when changing text
    val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            cbCross.setText("A delayed task!")
            cbTick.setText("Another delayed task")
        }
    }


}