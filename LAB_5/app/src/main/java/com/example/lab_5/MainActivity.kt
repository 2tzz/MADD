package com.example.lab_5

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    private lateinit var rootLayout: ConstraintLayout
    private lateinit var imageView: ImageView

    private val gravity = FloatArray(3) { 0f }
    private val alpha = 0.85f
    private var parentWidth = 0
    private var parentHeight = 0
    private var imageWidth = 0
    private var imageHeight = 0
    private var sensitivity = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rootLayout = findViewById(R.id.main)
        imageView = findViewById(R.id.imageView)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val vto: ViewTreeObserver = rootLayout.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                rootLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                parentWidth = rootLayout.width
                parentHeight = rootLayout.height
                imageWidth = imageView.width
                imageHeight = imageView.height
            }
        })
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.also { accel ->
            sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return
        if (event.sensor.type != Sensor.TYPE_ACCELEROMETER) return

        val ax = event.values[0]
        val ay = event.values[1]
        val az = event.values[2]

        gravity[0] = alpha * gravity[0] + (1 - alpha) * ax
        gravity[1] = alpha * gravity[1] + (1 - alpha) * ay
        gravity[2] = alpha * gravity[2] + (1 - alpha) * az

        applyTiltToImage(gravity[0], gravity[1])
    }

    private fun applyTiltToImage(gx: Float, gy: Float) {
        if (parentWidth == 0 || parentHeight == 0) return

        val G = 9.81f
        val nx = (gx / G).coerceIn(-1f, 1f)
        val ny = (gy / G).coerceIn(-1f, 1f)

        val maxTranslateX = ((parentWidth - imageWidth) / 2).toFloat()
        val maxTranslateY = ((parentHeight - imageHeight) / 2).toFloat()

        val tx = (-nx * maxTranslateX * sensitivity).coerceIn(-maxTranslateX, maxTranslateX)
        val ty = (ny * maxTranslateY * sensitivity).coerceIn(-maxTranslateY, maxTranslateY)

        imageView.translationX = tx
        imageView.translationY = ty
    }
}
