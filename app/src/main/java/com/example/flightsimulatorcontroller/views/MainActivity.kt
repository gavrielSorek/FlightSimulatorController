package com.example.flightsimulatorcontroller.views


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.Window
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flightsimulatorcontroller.model.Model
import com.example.flightsimulatorcontroller.R
import com.example.flightsimulatorcontroller.view_model.ViewModel
import com.example.flightsimulatorcontroller.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var model = Model()
    private var viewModel: ViewModel = ViewModel(model)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply {
            this.setLifecycleOwner(this@MainActivity)
            this.viewmodel = viewModel
        }

        model.errorHandler = {
            startActivity(Intent(this@MainActivity, ErrorWindow::class.java))
        }
        model.successHandler = {
            startActivity(Intent(this@MainActivity, SuccessWindow::class.java))
        }


        //rudder
        var rudderSeekBar = findViewById<SeekBar>(R.id.RudderSeekBar)
        rudderSeekBar.setPadding(75,0,75,0);
        rudderSeekBar.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                viewModel.rudder = ((rudderSeekBar.progress.toFloat() / 50)-1)
                return@OnTouchListener false
            }
            true
        })

        //set listeners
        var throttleSeekBar = findViewById<com.lukelorusso.verticalseekbar.VerticalSeekBar>(R.id.throttleSeekBar)
        throttleSeekBar.setOnProgressChangeListener{progress -> viewModel.throttle = (progress.toFloat() / 100) }

        var joystick = findViewById<Joystick>(R.id.joystick)
        joystick.onChange = ::joystickOnChange

    }
    private fun joystickOnChange(aileron: Float, elevator: Float) {

        viewModel.aileron = aileron
        viewModel.elevator = -1*elevator

    }

}