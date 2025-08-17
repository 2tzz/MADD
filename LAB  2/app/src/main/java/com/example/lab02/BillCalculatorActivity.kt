package com.example.lab02

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class BillCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bill_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val unitsInput = findViewById<EditText>(R.id.units_input)
        val billOutput = findViewById<TextView>(R.id.textView2)
        val calculateButton = findViewById<Button>(R.id.button2)

        calculateButton.setOnClickListener {
            try {
                val units = unitsInput.text.toString().toDouble()
                val bill = calculateElectricityBill(units)
                billOutput.text = "Electricity Bill: %.2f LKR".format(bill)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please enter valid units", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateElectricityBill(units: Double): Double {
        val fixedCharge = 150.0
        val unitCost = 29.0
        val serviceCharge = 50.0 // Assuming service charge if not specified

        val energyCharge = units * unitCost
        val subtotal = fixedCharge + energyCharge + serviceCharge
        val vat = subtotal * 0.15

        return subtotal + vat
    }
}