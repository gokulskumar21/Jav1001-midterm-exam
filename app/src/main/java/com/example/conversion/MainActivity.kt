package com.example.conversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import com.example.conversion.R
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    // Declaring UI components
    private lateinit var inputValueLayout: TextInputLayout // Input text field layout
    private lateinit var output: TextView // Text view to display the result
    private lateinit var convertFrom: Spinner // Spinner for selecting the conversion unit
    private lateinit var outputUnit: TextView  // Text view to display the result unit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing the UI components
        inputValueLayout = findViewById(R.id.enteredValue) // TextInputLayout for the input value
        convertFrom = findViewById(R.id.convertFromUnit) // Spinner for selecting the conversion unit
        output = findViewById(R.id.answer) // To display the result
        outputUnit = findViewById(R.id.answerUnit) // To display the result in a particular unit


        // Set the onItemSelectedListener for the Spinner to convert the units on item selection
        // onItemSelectedListener will be triggered when the user selects an item from the Spinner
        convertFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Call the calculateResult function with the conversion unit that is selected and with the input parameter
                calculateResult(convertFrom.selectedItem.toString(), inputValueLayout.editText?.text.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing when no item is selected
            }
        }


        // Set the TextWatcher for the EditText so that it will be triggered when the text in edit text changes
        inputValueLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing before text changes
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Call the calculateResult function with the selected conversion unit and the input value
                calculateResult(convertFrom.selectedItem.toString(), s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing after the text changes
            }
        })
    }

    // Function to convert the value to the specified unit
    // This function is used to convert from one unit to a specified unit
    private fun calculateResult(fromUnit: String, inputValue: String) {

        val input = inputValue.toFloatOrNull()

        // Declaring variables to store the converted value and the unit
        var result: Double? = null
        var resultUnit: String? = null

        // Before conversion, checking whether the input value is null or not
        // If the input value is null the going to the else statement otherwise executing the conversion
        if (input != null && fromUnit != null) {
            when (fromUnit) {
                "Kilometer" -> {
                    result = input / 1.609
                    resultUnit = "Miles"
                }

                "Miles" -> {
                    result =  input * 1.609
                    resultUnit = "Kilometer"
                }
                "Centimeter" -> {
                    result = input / 2.54
                    resultUnit = "Inches"
                }
                "Inches" -> {
                    result = input * 2.54
                    resultUnit = "Centimeter"
                }
                "Celsius" -> {
                    result = (input * 1.8) + 32
                    resultUnit = "Fahrenheit"
                }
                "Fahrenheit" -> {
                    result = (input - 32) * 0.55
                    resultUnit = "Celsius"
                }
                "Kelvin" -> {
                    result = input - 273.15
                    resultUnit = "Celsius"
                }
                // It executes when the condition is false
                else -> print("Try again with a different input")
            }
        }
        //Display the result and its corresponding unit
        output.text = result?.toString() ?: ""
        outputUnit.text = resultUnit?.toString() ?: ""
    }
}