package com.example.tute_4

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private val walkingFragment = WalkingFragment()
    private val cyclingFragment = CyclingFragment()
    private val drivingFragment = DrivingFragment()

    private lateinit var btnWalking: Button
    private lateinit var btnCycling: Button
    private lateinit var btnDriving: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnWalking = findViewById(R.id.btnWalking)
        btnCycling = findViewById(R.id.btnCycling)
        btnDriving = findViewById(R.id.btnDriving)

        btnWalking.setOnClickListener {
            replaceFragment(walkingFragment)
        }

        btnCycling.setOnClickListener {
            replaceFragment(cyclingFragment)
        }

        btnDriving.setOnClickListener {
            replaceFragment(drivingFragment)
        }

        // Load default fragment when app starts
        replaceFragment(walkingFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
