package com.example.aplicacionandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SignUp : AppCompatActivity() {
    private lateinit var username: TextView
    private lateinit var password: TextView
    private lateinit var buttonToLogin: Button
    private lateinit var returnButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        username = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        buttonToLogin = findViewById(R.id.buttonToLogin)
        returnButton = findViewById(R.id.returnButton)

        buttonToLogin.setOnClickListener {
            val userInput = username.text.toString().trim()
            val passwordInput = password.text.toString().trim()
            if (userInput.isNotEmpty() && passwordInput.isNotEmpty()) {
                saveData(userInput, passwordInput)
                Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show()
                val intentToLogin = Intent(this, Login::class.java)
                startActivity(intentToLogin)
            } else Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
        returnButton.setOnClickListener {
            val intentToLogin = Intent(this, Login::class.java)
            startActivity(intentToLogin)
        }
    }

    private fun saveData(userInput: String, passwordInput: String) {
        val sp = getSharedPreferences("my-pref", Activity.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putString("emInput", userInput)
        edit.putString("pdInput", passwordInput)
        edit.apply()
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}