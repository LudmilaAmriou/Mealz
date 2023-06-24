package com.example.mealz
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.mealz.Entity.LoginRequest
import com.example.mealz.Entity.SignUpRequest
import com.example.mealz.ViewModel.LoginModel
import com.example.mealz.ViewModel.SignUpModel

import com.example.mealz.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    lateinit var signUpModel: SignUpModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        signUpModel = ViewModelProvider(this).get(SignUpModel::class.java)
        HomePage().setGradientTextColor(
            binding.textView23,
            Color.parseColor("#DC220F"),
            Color.parseColor("#F05600")
        )
      //  loginModel = ViewModelProvider(this).get(LoginModel::class.java)
        val screen = intent.getStringExtra("screen")

        binding.textView23.setOnClickListener{
            finish()
            // Start the sign up activity
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
         binding.button.setOnClickListener {
            val name = binding.NameField.text.toString()
            val email = binding.EmailField.text.toString()
            val password = binding.PasswordField.text.toString()
            val  address = binding.AddressField.text.toString()
            val signup = SignUpRequest(username = name, email =  email, password = password, address = address) // Initialize the signup property
            signUpModel.signUser(signup)
            signUpModel.user.observe(this, Observer { utilisateur ->
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
    }
}