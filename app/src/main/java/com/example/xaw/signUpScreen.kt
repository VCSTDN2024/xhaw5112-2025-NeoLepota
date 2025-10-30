package com.example.xaw

import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class signUpScreen : AppCompatActivity() {

    private lateinit var EmailET: EditText
    private lateinit var passwordET: EditText
    private lateinit var PasswordConfirmET: EditText
    private lateinit var signUpBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up_screen)

        EmailET = findViewById(R.id.EmailET)
        passwordET = findViewById(R.id.passwordET)
        PasswordConfirmET = findViewById(R.id.PasswordConfirmET)
        signUpBTN = findViewById(R.id.signUpBTN)

        val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        signUpBTN.setOnClickListener {
            val email = EmailET.text.toString().trim()
            val password = passwordET.text.toString()
            val confirmPassword = PasswordConfirmET.text.toString()

            // Basic Validation
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //save the new user in sharedpreferences
            with(sharedPref.edit()) {
                putString("email", email)
                putString("password", password)
                apply()
            }

            //Simulate email confirmation
            Toast.makeText(this, "A confirmation was sent to $email", Toast.LENGTH_LONG).show()

            //Redirect to Home Screen
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }

        //Allow the user to enter in their details that will be stored so that they can just login on next entry
        //An email will be sent to the email address that was entered on confirmation of making the account

        //Go to login screen if they have an account
        val loginBtn = findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}