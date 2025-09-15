package com.example.lab_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), InputFragment.OnCalculationRequestedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, InputFragment())
            transaction.commit()
        }
    }

    override fun onCalculationRequested(num1: Double, num2: Double, operation: String) {
        val result = when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "×" -> num1 * num2
            "÷" -> if (num2 != 0.0) num1 / num2 else Double.NaN
            else -> 0.0
        }

        val resultFragment = ResultFragment.newInstance(num1, num2, operation, result)

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, resultFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}