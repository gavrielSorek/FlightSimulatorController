package com.example.flightsimulatorjoistick

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.reflect.KFunction


class Joystick : View {
    private var drawPaint: Paint? = null

    var radius = width/4
    var xPoint = width/2
    var yPoint = height/2
    var xAlias : Float= 0f
    var yAlias : Float= 0f
    //var onChange : KFunction<Any>? = null
    //var paramsHolder : Array<out Any?> ?= null
    //hold observer that execute when joystick moves
    private  var onChange : (param1 :Float, param2:Float) -> Unit = { fl: Float, fl1: Float -> }

    fun joystickChangedAddFunction(func :(param1 :Float, param2:Float) -> Unit) {
        onChange = func;
    }

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

    fun validXPos(xPosition: Float): Float {
        if(xPosition < width/4) {
            return width/4.toFloat();
        }
        else if(xPosition > width - width/4) {
            return (width - width/4).toFloat();
        } else {
            return xPosition;
        }
    }
    fun validYPos(yPosition: Float): Float {
        if(yPosition < width/4) {
            return width/4.toFloat();
        }
        else if(yPosition > height - width/4) {
            return (width - width/4).toFloat();
        } else {
            return yPosition;
        }
    }
    override fun onDraw(c: Canvas) {
        setupPaint()
        drawPaint?.let { c.drawCircle((validXPos(width/2 + xAlias)).toFloat(), (validYPos(height/2 + yAlias)).toFloat(),width/4.toFloat(), it) };

        super.onDraw(c)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) {
            return false
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                var i = 0
                onSizeChanged(width, height, 0, 0)
                xAlias = event.getX() - width/2
                yAlias = event.getY() - height/2
                onChange(convertToRatioX(xAlias), convertToRatioX(yAlias))
                invalidate()
            }
            MotionEvent.ACTION_CANCEL -> {
            }
            MotionEvent.ACTION_UP -> {
                xAlias = 0F
                yAlias = 0F
                onChange(convertToRatioX(xAlias), convertToRatioX(yAlias))
                invalidate()
            }
        }
        return true
    }
    private fun convertToRatioX(xPos: Float): Float {
       var currentXPos = validXPos(xPos + width/2)
       return (currentXPos - width/2)/(width/4)
    }
    private fun convertToRatioY(yPos: Float): Float  {
        var currentYPos = validYPos(yPos + height/2)
        return (currentYPos - height/2)/(height/2 - width/4)
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

