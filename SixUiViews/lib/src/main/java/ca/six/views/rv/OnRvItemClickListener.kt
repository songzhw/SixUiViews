package ca.six.views.rv


import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by songzhw on 2016-07-13
 *
 * NOTE: If you have a head wrapper, or a footer wrapper, or a load more wrapper,
 * please note that the position may different than usual!
 */
abstract class OnRvItemClickListener(val rv: RecyclerView) : RecyclerView.OnItemTouchListener {
    private val gestureDetector: GestureDetectorCompat

    init {
        gestureDetector = GestureDetectorCompat(rv.context, RvGestureListener())
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(e)
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        gestureDetector.onTouchEvent(e)
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    fun onLongClick(vh: RecyclerView.ViewHolder?) {}

    private inner class RvGestureListener : SimpleOnGestureListener() {

        override fun onLongPress(e: MotionEvent) {
            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null) {
                val vh: RecyclerView.ViewHolder = rv.getChildViewHolder(child)
                onLongClick(vh)
            }
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null) {
                val vh: RecyclerView.ViewHolder = rv.getChildViewHolder(child)
                onItemClick(vh)
            }
            return true
        }
    }

    // ========================= abstract methods =================================
    abstract fun onItemClick(vh: RecyclerView.ViewHolder)


}