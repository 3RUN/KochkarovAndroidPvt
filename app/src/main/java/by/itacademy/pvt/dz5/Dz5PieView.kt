package by.itacademy.pvt.dz5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import by.itacademy.pvt.R

class Dz5PieView : View {

    private var screenCenterWidth = 0f
    private var screenCenterHeight = 0f

    lateinit var pieData: Array<Double>
    private var pieRadius = 0f
    private var pieRect = RectF()
    private val piePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val pieColors =
        arrayOf(
            R.color.dz5Pie1,
            R.color.dz5Pie2,
            R.color.dz5Pie3,
            R.color.dz5Pie4,
            R.color.dz5Pie5,
            R.color.dz5Pie6,
            R.color.dz5Pie7,
            R.color.dz5Pie8,
            R.color.dz5Pie9,
            R.color.dz5Pie10
        )

    private val textRect = Rect()
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val pointPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        textPaint.textSize = resources.getDimension(R.dimen.dz5PieTextSize)
        textPaint.isFakeBoldText = true
        textPaint.color = ContextCompat.getColor(context, R.color.dz3Black)

        linePaint.color = ContextCompat.getColor(context, R.color.dz3Gray)
        linePaint.style = Paint.Style.STROKE
        linePaint.strokeWidth = resources.getDimension(R.dimen.dz5PieLineWidth)

        pointPaint.color = ContextCompat.getColor(context, R.color.dz3Gray)
        pointPaint.style = Paint.Style.FILL
    }

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        pieRadius = Math.min(w, h) * 0.3f

        screenCenterWidth = w / 2f
        screenCenterHeight = h / 2f

        pieRect.set(
            screenCenterWidth - pieRadius,
            screenCenterHeight - pieRadius,
            screenCenterWidth + pieRadius,
            screenCenterHeight + pieRadius
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas ?: return

        drawDiagram(canvas)
    }

    private fun drawConnection(
        canvas: Canvas,
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        textWidth: Float
    ) {
        canvas.drawLine(startX, startY, endX, endY, linePaint)
        val radiusCircle = textWidth * 1.25f
        canvas.drawCircle(endX, endY, radiusCircle, pointPaint)
    }

    private fun drawDiagram(canvas: Canvas) {
        val dataSum = pieData.sum()
        var angleStart = 0f
        var angleSweep = 0f
        var colorIterator = 0
        var radialAngle = 0.0
        val textOffset = 1.4f

        for (i in pieData) {

            // sweep and radial angles
            angleSweep = ((i * 360) / dataSum).toFloat()
            radialAngle = (angleStart + angleSweep / 2) * Math.PI / 180

            // get circle edge position, relative to current radial angle
            val circleX = (screenCenterWidth + (pieRadius * Math.cos(radialAngle))).toFloat()
            val circleY = (screenCenterHeight + (pieRadius * Math.sin(radialAngle))).toFloat()

            // get text position
            val textX = (screenCenterWidth + ((pieRadius * textOffset) * Math.cos(radialAngle))).toFloat()
            val textY = (screenCenterHeight + ((pieRadius * textOffset) * Math.sin(radialAngle))).toFloat()

            val text = i.toString()
            textPaint.getTextBounds(text, 0, text.length, textRect)
            val textWidth = textPaint.measureText(text, 0, text.length) / 2.0f

            // draw connection and text
            drawConnection(canvas, circleX, circleY, textX, textY, textWidth)
            canvas.drawText(text, textX - textWidth, textY + textRect.height() / 2, textPaint)

            // set color for the diagram pie
            piePaint.color = ContextCompat.getColor(context, pieColors[colorIterator++])
            colorIterator %= pieColors.size

            // draw diagram pie
            canvas.drawArc(pieRect, angleStart, angleSweep, true, piePaint)
            angleStart += angleSweep
        }
    }
}