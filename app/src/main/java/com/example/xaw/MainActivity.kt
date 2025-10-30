package com.example.xaw

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var EmailET: EditText
    private lateinit var passwordET: EditText
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        EmailET = findViewById(R.id.EmailET)
        passwordET = findViewById(R.id.passwordET)
        loginBtn = findViewById(R.id.loginBtn)

        //Temporary user details
        val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val savedEmail = sharedPref.getString("email", "guest1@gmail.com")
        val savedPassword = sharedPref.getString("password", "12345678")

        loginBtn.setOnClickListener {
            val enteredEmail = EmailET.text.toString().trim()
            val enteredPassowrd = passwordET.text.toString()

            if (enteredEmail == savedEmail && enteredPassowrd == savedPassword) {
                //Success = go to home screen
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish() //Close the login screen so user cannot go back
            } else {
                //Invalid details entered
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        //Sign Up button
        val signBtn = findViewById<Button>(R.id.signBtn)

        signBtn.setOnClickListener {
            val intent = Intent(this, signUpScreen::class.java)
            startActivity(intent)
        }
    }
}