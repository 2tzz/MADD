package com.example.lab_3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class InputFragment : Fragment() {

    private lateinit var listener: OnCalculationRequestedListener

    interface OnCalculationRequestedListener {
        fun onCalculationRequested(num1: Double, num2: Double, operation: String)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the listener to the parent activity
        if (activity is OnCalculationRequestedListener) {
            listener = activity as OnCalculationRequestedListener
        } else {
            throw RuntimeException("Activity must implement OnCalculationRequestedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)

        val editTextNumber1 = view.findViewById<EditText>(R.id.editTextNumber1)
        val editTextNumber2 = view.findViewById<EditText>(R.id.editTextNumber2)

        view.findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            performCalculation(editTextNumber1, editTextNumber2, "+")
        }

        view.findViewById<Button>(R.id.buttonSubtract).setOnClickListener {
            performCalculation(editTextNumber1, editTextNumber2, "-")
        }

        view.findViewById<Button>(R.id.buttonMultiply).setOnClickListener {
            performCalculation(editTextNumber1, editTextNumber2, "×")
        }

        view.findViewById<Button>(R.id.buttonDivide).setOnClickListener {
            performCalculation(editTextNumber1, editTextNumber2, "÷")
        }

        return view
    }

    private fun performCalculation(editText1: EditText, editText2: EditText, operation: String) {
        val num1 = editText1.text.toString().toDoubleOrNull() ?: 0.0
        val num2 = editText2.text.toString().toDoubleOrNull() ?: 0.0

        listener.onCalculationRequested(num1, num2, operation)
    }

    companion object {
        @JvmStatic
        fun newInstance() = InputFragment()
    }
}