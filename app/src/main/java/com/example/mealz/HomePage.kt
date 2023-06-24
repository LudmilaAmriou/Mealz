package com.example.mealz

import android.content.ContentValues
import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.mealz.databinding.ActivityHomePageBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class HomePage : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var binding: ActivityHomePageBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = navHostFragment.navController
        binding.navBottom.itemIconTintList = null
        NavigationUI.setupWithNavController(binding.navBottom, navController)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }
                // Get new FCM registration token
                val token = task.result.toString()
                // Log and toast
                Log.d("TAG", token)


            })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
           when (item.itemId) {
        }
        return super.onOptionsItemSelected(item)

    }
    // For the gradient design  (color of text)
    fun setGradientTextColor(textView: TextView, startColor: Int, endColor: Int) {
        textView.setTextColor(endColor)
        val textShader: Shader = LinearGradient(
            0f,
            0f,
            textView.paint.measureText(textView.text.toString()),
            textView.textSize,
            intArrayOf(startColor, endColor),
            floatArrayOf(0f, 1f),
            Shader.TileMode.CLAMP
        )
        textView.paint.shader = textShader
    }


}