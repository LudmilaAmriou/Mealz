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
    lateinit var loginModel:LoginModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        HomePage().setGradientTextColor(
            binding.textView4,
            Color.parseColor("#DC220F"),
            Color.parseColor("#F05600")
        )
        loginModel = ViewModelProvider(this).get(LoginModel::class.java)

        val screen = intent.getStringExtra("screen")

        binding.buttonLogin.setOnClickListener {
            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()
            val log = LoginRequest(email, password) // Initialize the log property
            loginModel.logUser(log)

            // Move the observer outside the button click listener
            loginModel.user.observe(this, Observer { utilisateur ->
                if (utilisateur != null) {
                    if (utilisateur.success) {
                        val sharedPreferences = getSharedPreferences("my_app", Context.MODE_PRIVATE)
                        sharedPreferences.edit {
                            putBoolean("isLoggedIn", true)
                            utilisateur.ID_Utilisateur?.let { it1 -> putInt("userId", it1) }
                        }
                        // Continue with the desired logic for a successful login
                        // Redirect to cart page
                        /*
                                val intent = Intent(this, HomePage::class.java)
                                intent.putExtra("screen", screen)
                                startActivity(intent)
                                */
                        finish()
                    }
                }
                if (utilisateur != null) {
                    Toast.makeText(this, utilisateur.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
        binding.textView4.setOnClickListener{
            finish()

            // Start the sign up activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}