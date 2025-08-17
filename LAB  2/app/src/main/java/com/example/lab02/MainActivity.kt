package com.example.lab02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Find the button from layout
        val beginButton = findViewById<Button>(R.id.button)

        // 2. Set click listener with proper error handling
        beginButton.setOnClickListener {
            try {
                // 3. Create intent to launch BillCalculator (not BillCalculatorActivity)
                val intent = Intent(this, BillCalculatorActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                // 4. Show error message if something goes wrong
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
}