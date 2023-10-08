package com.example.compoundinterest
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.SeekBar
import android.widget.AdapterView
import android.widget.Spinner

import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var yearsSeekBar: SeekBar
    private lateinit var selectedYearsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Initialize SeekBar and TextView
        yearsSeekBar = findViewById(R.id.howManyYears)
        selectedYearsTextView = findViewById(R.id.selectedYears)

        val nTimesCompounded = findViewById<Spinner>(R.id.nTimesCompounded)
        val calculateButton = findViewById<TextView>(R.id.calculateButton)

        calculateButton.setOnClickListener {
            showResult()
        }
        nTimesCompounded.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                // Update the TextView with the selected value
                selectedYearsTextView.text = parentView.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Handle nothing selected if needed
            }
        }
        yearsSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update the TextView with the selected value
                selectedYearsTextView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Nothing to do here
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Nothing to do here
            }
        })
    }

    private fun calculateResult(): Double {
        val originalAmountText = findViewById<EditText>(R.id.originalAmount)
        val interestRateText = findViewById<EditText>(R.id.interestRate)
//        val nTimesCompoundedText = findViewById<EditText>(R.id.nTimesCompounded)
        val nTimesCompoundedSpinner = findViewById<Spinner>(R.id.nTimesCompounded)
        val howManyYears = selectedYearsTextView.text.toString().toDouble()
//        val howManyYearsText = findViewById<EditText>(R.id.howManyYears)


        val originalAmount = originalAmountText.text.toString().toDouble()

        val interestRate = interestRateText.text.toString().toDouble()
//        val nTimesCompounded = nTimesCompoundedText.text.toString().toDouble()
        val nTimesCompoundedPosition = nTimesCompoundedSpinner.selectedItemPosition
        val nTimesCompoundedValues = resources.getIntArray(R.array.times_compounded_values)
        val nTimesCompounded = nTimesCompoundedValues[nTimesCompoundedPosition].toDouble()
//        val howManyYears = howManyYearsText.text.toString().toDouble()


        // Calculate the compound interest

        return originalAmount * (1 + (interestRate / nTimesCompounded)).pow(
            nTimesCompounded * howManyYears
        )
    }


    private fun showResult() {
        val finalAmountResult = calculateResult()
        val finalAmountText = findViewById<TextView>(R.id.finalAmount)
        val resultText = "Your final amount is: %.2f".format(finalAmountResult)

        finalAmountText.text = resultText
    }



}