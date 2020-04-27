package cn.six.open.view.guide

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

class GuideView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    val coordinates = ArrayList<Triple<Float, Float, Float>>()
    lateinit var tempBitmap: Bitmap
    lateinit var tempCanvas: Canvas
    val paint: Paint
    val metrics: DisplayMetrics

    lateinit var textContainer: StaticLayout
    val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    init {
        metrics = DisplayMetrics()
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.parseColor("#ffffffff")
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

        textPaint.color = Color.parseColor("#e1f5fe")
        textPaint.textSize = 40f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val windowMgr = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowMgr.defaultDisplay.getMetrics(metrics)
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        tempBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        tempCanvas = Canvas(tempBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        tempCanvas.drawColor(Color.parseColor("#99000000"))
        for (triple in coordinates) {
            tempCanvas.drawCircle(triple.first, triple.second, triple.third, paint)
        }
        canvas.drawBitmap(tempBitmap, 0f, 0f, null)

        // StaticLayout虽然可以让drawText()换行. 但它默认从(0,0)开始画. 所以这里要位移下canvas, 来保证文字在中间
        canvas.save()
        canvas.translate(measuredWidth / 4f, measuredHeight / 3f)
        textContainer.draw(canvas)
        canvas.restore()
    }


    // ================== public =================
    fun addMe(activity: Activity, description: String, vararg viewList: View) {
        val rootView = activity.findViewById<View>(android.R.id.content) as ViewGroup
        rootView.post {
            for (view in viewList) {
                val centerX = view.x + view.width / 2
                val centerY = view.y + view.height / 2
                val size = Math.min(120f, Math.max(view.width / 2.5f, view.height / 2.5f))
                coordinates.add(Triple(centerX, centerY, size))
            }

            textContainer = StaticLayout(
                description, textPaint, rootView.measuredWidth / 2,
                Layout.Alignment.ALIGN_CENTER, 1f, 0f, false
            )

            this.setOnClickListener {
                removeMe(activity)
            }

            rootView.addView(this)
            invalidate()
        }
    }

    fun removeMe(activity: Activity) {
        val rootView = activity.findViewById<View>(android.R.id.content) as ViewGroup
        rootView.removeView(this) //若已没了也没事, 这里的removeView()不会crash的, 只不过不做事了而已
    }

}