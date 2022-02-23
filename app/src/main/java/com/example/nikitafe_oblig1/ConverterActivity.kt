package com.example.nikitafe_oblig1

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.nikitafe_oblig1.databinding.ActivityConverterBinding
import java.math.BigDecimal
import java.math.RoundingMode

class ConverterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConverterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConverterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // made spinner
        val spinner: Spinner = binding.spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.sp_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.button.setOnClickListener {
            try {
                val userNumber = binding.editText.text.toString().toLong()

                when (spinner.selectedItem) {
                    "Fluid ounce" -> binding.textView.text = toDecimal(userNumber * 0.02957)
                    "Cup" -> binding.textView.text = toDecimal(userNumber * 0.23659)
                    "Gallon" -> binding.textView.text = toDecimal(userNumber * 3.78541)
                    "Hogshead" -> binding.textView.text = toDecimal(userNumber * 238.481)
                    // viser en toast ved feil input i array fra strings.xml
                    else -> Toast.makeText(applicationContext, "Ugyldig input i xml", Toast.LENGTH_SHORT).show()
                }
            } catch (exception: NumberFormatException) {
                Toast.makeText(applicationContext, "Ugyldig input", Toast.LENGTH_SHORT).show()
            }

            hideKeyboard(currentFocus ?: View(this))
        }

        // button2 tar deg til neste aktivitet
        binding.button2.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
    private fun toDecimal(userNumber: Double): String {
        return BigDecimal(userNumber).setScale(2, RoundingMode.HALF_EVEN).toString()
    }
    // can use the mainActivity's hideKeyboard function if it was a static one but idk how to do that
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}