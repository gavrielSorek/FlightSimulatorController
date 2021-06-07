package com.example.flightsimulatorjoistick

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.reflect.KFunction


class Joystick : View {
    private var drawPaint: Paint? = null

    var radius :Float = width/4F
    var xCenter :Float= width/2F
    var yCenter :Float= height/2F
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
        radius = width/6F
        xCenter = width/2F
        yCenter = height/2F
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    ) {
    radius = width/6F
    xCenter = width/2F
    yCenter = height/2F}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        radius = width/6F
        xCenter = width/2F
        yCenter = height/2F
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = width/6F
        xCenter = width/2F
        yCenter = height/2F
        super.onSizeChanged(h, w, oldh, oldw)
    }

    @Synchronized
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec)
        setMeasuredDimension(measuredHeight, measuredWidth)
    }

    fun validXPos(xPosition: Float): Float {
        if(xPosition < radius) {
            return radius;
        }
        else if(xPosition > width - radius) {
            return (width - radius);
        } else {
            return xPosition;
        }
    }
    fun validYPos(yPosition: Float): Float {
        if(yPosition < radius) {
            return radius;
        }
        else if(yPosition > height - radius) {
            return (height - radius);
        } else {
            return yPosition;
        }
    }
    override fun onDraw(c: Canvas) {
        setupPaint()
        drawPaint?.let { c.drawCircle((validXPos(xCenter + xAlias)).toFloat(), (validYPos(yCenter + yAlias)).toFloat(),radius.toFloat(), it) };

        super.onDraw(c)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //validate radius and circle center
        radius = width/6F
        xCenter = width/2F
        yCenter = height/2F
        if (!isEnabled) {
            return false
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                var i = 0
                //onSizeChanged(width, height, 0, 0)
                xAlias = event.getX() - xCenter
                yAlias = event.getY() - yCenter
                onChange(convertToRatioX(xAlias), convertToRatioY(yAlias))
                invalidate()
            }
            MotionEvent.ACTION_CANCEL -> {
            }
            MotionEvent.ACTION_UP -> {
                xAlias = 0F
                yAlias = 0F
                onChange(0F, 0F) //the circle returned to center
                invalidate()
            }
        }
        return true
    }
    //convert to number in range [-1,1] accordingly to xPosition
    private fun convertToRatioX(xPos: Float): Float {

       var currentXPos = validXPos(xPos + xCenter)
        //println("currentXPos: ${currentXPos}, xCenter: ${xCenter}, radius: ${radius}")
       return (currentXPos - xCenter)/(xCenter - radius)
    }
    private fun convertToRatioY(yPos: Float): Float  {
        var currentYPos = validYPos(yPos + yCenter)
        //println("currentYPos: ${currentYPos}, yCenter: ${yCenter}, radius: ${radius}")
        return (currentYPos - yCenter)/(yCenter - radius)
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

