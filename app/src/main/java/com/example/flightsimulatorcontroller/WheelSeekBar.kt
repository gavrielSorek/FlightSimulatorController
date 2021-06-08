package com.example.flightsimulatorcontroller

import android.R
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView


class WheelSeekBar : androidx.appcompat.widget.AppCompatSeekBar {
    lateinit var wheelPaint :Paint
    constructor(context: Context?) : super(context!!) {
        updatePaint()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    ) { updatePaint()}

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {updatePaint()}
    /*

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(h, w, oldh, oldw)
    }

    @Synchronized
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec)
    }
*/
    override fun onDraw(c: Canvas) {


        super.onDraw(c)
        //c.drawCircle(this.thumb.bounds.exactCenterX(), this.thumb.bounds.exactCenterY(),30f,wheelPaint)
    }
    private fun updatePaint(){
        wheelPaint = Paint()
        wheelPaint!!.setColor(Color.BLUE)
        wheelPaint!!.setAntiAlias(true)
        wheelPaint!!.setStrokeWidth(5F)
        wheelPaint!!.setStyle(Paint.Style.FILL_AND_STROKE)
        wheelPaint!!.setStrokeJoin(Paint.Join.ROUND)
        wheelPaint!!.setStrokeCap(Paint.Cap.ROUND)
    }

}