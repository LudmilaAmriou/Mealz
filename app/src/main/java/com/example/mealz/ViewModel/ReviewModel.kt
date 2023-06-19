package com.example.mealz.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealz.Entity.LoginRequest
import com.example.mealz.Entity.Rating
import com.example.mealz.Entity.Restaurant
import com.example.mealz.Retrofit.EndPointRest
import kotlinx.coroutines.*

class ReviewModel: ViewModel(){
    val reviews = MutableLiveData<List<Rating>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val stateSend = MutableLiveData<Boolean?>()



    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite  ${throwable.message}"
            Log.d("MY EXCEPTION", "Une erreur s'est produite  ${throwable.message}")
        }
    }


    fun loadReviews(restaurantId:Int) {
        if(reviews.value==null) {
            loading.value = true
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = EndPointRest.createEndpoint().getReviews(restaurantId)
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        reviews.value = response.body()

                    } else {

                        errorMessage.value = "Une erreur s'est produite"
                    }
                }
            }
        }


    }

    fun sendReview(review:Rating) {
            loading.value = true
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = EndPointRest.createEndpoint().review(review)
                Log.d("My response", response.toString())
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        stateSend.value = response.body()

                    } else {
                        errorMessage.value = "Une erreur s'est produite"
                    }
                }
            }
    }



}