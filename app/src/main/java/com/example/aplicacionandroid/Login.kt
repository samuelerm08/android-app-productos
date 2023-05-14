package com.example.aplicacionandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    private lateinit var usernameText: TextView
    private lateinit var passwordText: TextView
    private lateinit var loginButton: Button
    private lateinit var toRegisterButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameText = findViewById(R.id.editTextEmail)
        passwordText = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.loginButton)
        toRegisterButton = findViewById(R.id.signUpButton)

        val sp = getSharedPreferences("my-pref", Activity.MODE_PRIVATE)
        val savedEmail = sp.getString("emInput", "")
        val savedPassword = sp.getString("pdInput", "")
        loginButton.setOnClickListener {
            val userNameInput = usernameText.text.toString().trim()
            val passwordInput = passwordText.text.toString().trim()
            if (userNameInput.isNotEmpty() && passwordInput.isNotEmpty()) {
                if (userNameInput == savedEmail && passwordInput == savedPassword) {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    val intentToMainActivity = Intent(this, MainActivity::class.java)
                    intentToMainActivity.putExtra("email", userNameInput)
                    startActivity(intentToMainActivity)
                }
                else Toast.makeText(this, "Email or Password Invalid", Toast.LENGTH_SHORT).show()
            }
            else Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
        toRegisterButton.setOnClickListener {
            val intentToRegister = Intent(this, SignUp::class.java)
            startActivity(intentToRegister)
        }
    }
}