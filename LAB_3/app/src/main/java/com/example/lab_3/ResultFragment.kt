package com.example.lab_3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultFragment : Fragment() {

    private lateinit var textViewResult: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        textViewResult = view.findViewById(R.id.textViewResult)

        val result = arguments?.getDouble("result", 0.0) ?: 0.0
        val operation = arguments?.getString("operation", "") ?: ""
        val num1 = arguments?.getDouble("num1", 0.0) ?: 0.0
        val num2 = arguments?.getDouble("num2", 0.0) ?: 0.0

        if (result.isNaN()) {
            textViewResult.text = "Error: Division by zero"
        } else {
            textViewResult.text = "$num1 $operation $num2 = $result"
        }

        view.findViewById<Button>(R.id.buttonBack).setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

    companion object {
        fun newInstance(num1: Double, num2: Double, operation: String, result: Double): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putDouble("num1", num1)
            args.putDouble("num2", num2)
            args.putString("operation", operation)
            args.putDouble("result", result)
            fragment.arguments = args
            return fragment
        }
    }
}