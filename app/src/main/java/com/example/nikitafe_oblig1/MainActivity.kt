package com.example.nikitafe_oblig1

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.nikitafe_oblig1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button1.setOnClickListener {
            if (isPalindromeString(binding.editText.text.toString())) {
                binding.textView.text = "${binding.editText.text} er palindrom!"
            } else {
                binding.textView.text = "${binding.editText.text} er ikke palindrom!"
            }
            binding.editText.text.clear()
            hideKeyboard(currentFocus ?: View(this))
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, ConverterActivity::class.java)
            startActivity(intent)
        }
    }
    // funksjon for å se om en streng er palindrom
    private fun isPalindromeString(inputStr: String): Boolean {
        val reverseStr = inputStr.reversed()

        return inputStr.equals(reverseStr, ignoreCase = true)
    }
    // funksjon for å hide keyboard
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}