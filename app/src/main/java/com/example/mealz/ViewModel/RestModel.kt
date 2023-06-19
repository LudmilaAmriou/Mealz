package com.example.mealz.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealz.Entity.Restaurant
import com.example.mealz.Retrofit.EndPointRest
import kotlinx.coroutines.*

class RestModel : ViewModel(){
    val restaus = MutableLiveData<List<Restaurant>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()



    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite  ${throwable.message}"
            Log.d("MY EXCEPTION", "Une erreur s'est produite  ${throwable.message}")
        }
    }


    fun loadRestaus() {
        if(restaus.value==null) {
            loading.value = true
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = EndPointRest.createEndpoint().getAllRestaus()
//                Log.d("My response", response.toString())
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        restaus.value = response.body()

                    } else {

                        errorMessage.value = "Une erreur s'est produite"
                    }
                }
            }
        }


    }



}