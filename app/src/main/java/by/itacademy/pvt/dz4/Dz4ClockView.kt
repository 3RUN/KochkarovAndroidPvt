package by.itacademy.pvt.dz4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import by.itacademy.pvt.R
import java.util.Calendar

class Dz4ClockView : View {

    private val rect = Rect()
    private var screenCenterWidth = 0f
    private var screenCenterHeight = 0f

    private val clockCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var clockCircleX = 0f
    private var clockCircleY = 0f
    private var clockCircleRadius = 0f
    private var clockCirclePadding = 0f

    private val clockCenterDotPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var clockCenterDotRadius = 0f

    private val clockLinesPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var clockLinesMainWidth = 0f
    private var clockLinesMainLength = 0f
    private var clockLinesWidth = 0f
    private var clockLinesLength = 0f

    private val clockNumbers = intArrayOf(3, 6, 9, 12)
    private val clockNumberPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var clockNumberPadding = 0f

    private val handHourPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val handMinutePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val handSecondPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var handTruncation = 0
    private var hourHandTruncation = 0

    init {
        clockCirclePaint.color = ContextCompat.getColor(context, R.color.dz3Gray)
        clockCirclePaint.style = Paint.Style.STROKE
        clockCirclePaint.strokeWidth = resources.getDimension(R.dimen.dz4ClockCircleWidth)
        clockCirclePadding = resources.getDimension(R.dimen.dz4ClockCirclePadding)

        clockCenterDotPaint.color = ContextCompat.getColor(context, R.color.dz3White)
        clockCenterDotPaint.style = Paint.Style.FILL
        clockCenterDotRadius = resources.getDimension(R.dimen.dz4ClockCenterDotRadius)

        clockLinesPaint.style = Paint.Style.STROKE
        clockLinesPaint.color = ContextCompat.getColor(context, R.color.dz3Gray)

        clockLinesMainLength = resources.getDimension(R.dimen.dz4ClockLineMainLength)
        clockLinesMainWidth = resources.getDimension(R.dimen.dz4ClockLineMainWidth)
        clockLinesLength = resources.getDimension(R.dimen.dz4ClockLinesLength)
        clockLinesWidth = resources.getDimension(R.dimen.dz4ClockLinesWidth)

        clockNumberPaint.color = ContextCompat.getColor(context, R.color.dz3White)
        clockNumberPaint.style = Paint.Style.FILL
        clockNumberPaint.textSize = resources.getDimension(R.dimen.dz4ClockNumberTextSize)
        clockNumberPadding = resources.getDimension(R.dimen.dz4ClockNumberTextPaddings)

        handHourPaint.color = ContextCompat.getColor(context, R.color.dz3Gray)
        handHourPaint.style = Paint.Style.STROKE
        handHourPaint.strokeWidth = resources.getDimension(R.dimen.dz4ClockHandHourWidth)

        handMinutePaint.color = ContextCompat.getColor(context, R.color.dz3Gray)
        handMinutePaint.style = Paint.Style.STROKE
        handMinutePaint.strokeWidth = resources.getDimension(R.dimen.dz4ClockHandMinuteWidth)

        handSecondPaint.color = ContextCompat.getColor(context, R.color.dz3Gray)
        handSecondPaint.style = Paint.Style.STROKE
        handSecondPaint.strokeWidth = resources.getDimension(R.dimen.dz4ClockHandSecondWidth)
    }

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    private fun drawLines(canvas: Canvas) {
        var sizeOffset: Float
        canvas.save()
        for (i in 0..11) {
            if (i == 0 || i == 3 || i == 6 || i == 9) {
                sizeOffset = clockLinesMainLength
                clockLinesPaint.strokeWidth = clockLinesMainWidth
            } else {
                sizeOffset = clockLinesLength
                clockLinesPaint.strokeWidth = clockLinesWidth
            }
            val startPosY = (screenCenterHeight - clockCircleRadius)
            val endPosY = (screenCenterHeight - clockCircleRadius) + sizeOffset
            canvas.drawLine(screenCenterWidth, startPosY, screenCenterWidth, endPosY, clockLinesPaint)
            canvas.rotate(30f, screenCenterWidth, screenCenterHeight)
        }
        canvas.restore()
    }

    private fun drawNumbers(canvas: Canvas) {
        val radius = clockCircleRadius - clockCirclePadding - clockNumberPadding
        for (number in clockNumbers) {
            val tmp = number.toString()
            clockNumberPaint.getTextBounds(tmp, 0, tmp.length, rect)
            val angle = Math.PI / 2 * (number + 1)
            val x = (screenCenterWidth + Math.cos(angle) * radius - rect.width() / 2)
            val y = (screenCenterHeight - Math.sin(angle) * radius + rect.height() / 2)
            canvas.drawText(tmp, x.toFloat(), y.toFloat(), clockNumberPaint)
        }
    }

    private fun drawHand(canvas: Canvas, paint: Paint, loc: Double, isHour: Boolean) {
        val radius = clockCircleRadius
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handRadius = if (isHour) radius - handTruncation - hourHandTruncation else radius - handTruncation
        canvas.drawLine(
            screenCenterWidth,
            screenCenterHeight,
            (screenCenterWidth + Math.cos(angle) * handRadius).toFloat(),
            (screenCenterHeight + Math.sin(angle) * handRadius).toFloat(),
            paint
        )
    }

    private fun drawHands(canvas: Canvas) {
        val c = Calendar.getInstance()
        var hour = c.get(Calendar.HOUR_OF_DAY)
        hour = if (hour > 12) hour - 12 else hour
        drawHand(canvas, handHourPaint, ((hour + c.get(Calendar.MINUTE) / 60) * 5).toDouble(), true)
        drawHand(canvas, handMinutePaint, c.get(Calendar.MINUTE).toDouble(), false)
        drawHand(canvas, handSecondPaint, c.get(Calendar.SECOND).toDouble(), false)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        handTruncation = Math.min(w, h) / 20
        hourHandTruncation = Math.min(w, h) / 10

        screenCenterWidth = w / 2f
        screenCenterHeight = h / 2f

        clockCircleX = screenCenterWidth
        clockCircleY = screenCenterHeight
        clockCircleRadius = Math.min(screenCenterWidth, screenCenterHeight) - clockCirclePadding
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        canvas.drawCircle(clockCircleX, clockCircleY, clockCircleRadius, clockCirclePaint)

        drawLines(canvas)
        drawNumbers(canvas)
        drawHands(canvas)

        canvas.drawCircle(clockCircleX, clockCircleY, clockCenterDotRadius, clockCenterDotPaint)

        postInvalidateDelayed(500)
        invalidate()
    }
}