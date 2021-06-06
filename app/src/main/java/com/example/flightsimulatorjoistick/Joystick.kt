package com.example.flightsimulatorjoistick

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View


class Joystick : View {
    private var drawPaint: Paint? = null

    var radius = 300
    var xPoint = 0
    var yPoint = 0
    var xAlias : Float= 0f
    var yAlias : Float= 0f
    constructor(context: Context?) : super(context!!) {
        radius = width/4
        xPoint = width/2
        yPoint = height/2
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    ) {radius = width/4
    xPoint = width/2
    yPoint = height/2}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        radius = width/4
        xPoint = width/2
        yPoint = height/2
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(h, w, oldh, oldw)
    }

    @Synchronized
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec)
        setMeasuredDimension(measuredHeight, measuredWidth)
    }

    override fun onDraw(c: Canvas) {
        setupPaint()
        drawPaint?.let { c.drawCircle((width/2 + xAlias).toFloat(), (height/2 + yAlias).toFloat(),width/4.toFloat(), it) };

        super.onDraw(c)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) {
            return false
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                var i = 0
                //i = max - (max * event.y / height).toInt()
                //progress = i
               // Log.i("Progress", progress.toString() + "")
                onSizeChanged(width, height, 0, 0)
                //xAlias = min(event.getX() - (width/2), width/4)
                var w = event.getX();
                var h = event.getY()
                println("width: " + w + " height: " + h  )

            }
            MotionEvent.ACTION_CANCEL -> {
            }
        }
        return true
    }
    private fun setupPaint() {
        drawPaint = Paint()
        drawPaint!!.setColor(Color.BLUE)
        drawPaint!!.setAntiAlias(true)
        drawPaint!!.setStrokeWidth(5F)
        drawPaint!!.setStyle(Paint.Style.FILL_AND_STROKE)
        drawPaint!!.setStrokeJoin(Paint.Join.ROUND)
        drawPaint!!.setStrokeCap(Paint.Cap.ROUND)
    }
}

