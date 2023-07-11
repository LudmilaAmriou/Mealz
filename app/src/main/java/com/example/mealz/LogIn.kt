package com.example.mealz

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mealz.Entity.LoginRequest
import com.example.mealz.ViewModel.LoginModel
import com.example.mealz.ViewModel.MenuModel
import com.example.mealz.databinding.ActivityHomePageBinding
import com.example.mealz.databinding.ActivityLogInBinding
class LogIn : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var loginModel: LoginModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        loginModel = ViewModelProvider(this).get(LoginModel::class.java)

        // Set up click listener for login button
        binding.buttonLogin.setOnClickListener {
            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()
            val log = LoginRequest(email, password)

            loginModel.logUser(log)

            // Observe the login response
            loginModel.user.observe(this, Observer { utilisateur ->
                if (utilisateur != null) {
                    if (utilisateur.success) {
                        val sharedPreferences =
                            getSharedPreferences("my_app", Context.MODE_PRIVATE)

                        // Store login status and user ID in SharedPreferences
                        sharedPreferences.edit {
                            putBoolean("isLoggedIn", true)
                            utilisateur.ID_Utilisateur?.let { userId ->
                                putInt("userId", userId)
                            }
                        }

                        // Redirect to the cart page
                        Toast.makeText(
                            this,
                            "Login successful. Command has been successfully added!",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this, HomePage::class.java)
                        intent.putExtra("screen", "cart")
                        startActivity(intent)

                        // Finish the login activity
                        finish()
                    } else {
                        // Display toast message for unsuccessful login
                        Toast.makeText(this, utilisateur.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        // Set up click listener for sign up text
        binding.textView4.setOnClickListener {
            finish()

            // Start the sign-up activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
