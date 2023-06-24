package com.example.mealz.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealz.Entity.Commande
import com.example.mealz.Entity.Commande_menu
import com.example.mealz.Entity.Order
import com.example.mealz.Entity.Rating
import com.example.mealz.Retrofit.EndPointRest
import kotlinx.coroutines.*

class OrderModel : ViewModel(){

    val commande = MutableLiveData<Commande>()
    val menucommande = MutableLiveData<List<Commande_menu>>()
    val orders = MutableLiveData<List<Order>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    var stateSend1 = MutableLiveData<Int?>()
    val stateSend2 = MutableLiveData<Boolean?>()



    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite  ${throwable.message}"
            Log.d("MY EXCEPTION", "Une erreur s'est produite  ${throwable.message}")
        }
    }
    fun loadOrders(userId:Int) {
        if(orders.value==null ) {
            loading.value = true
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = EndPointRest.createEndpoint().getOrders(userId)
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        orders.value = response.body()

                    } else {
                        errorMessage.value = "Une erreur s'est produite"
                    }
                }
            }
        }
    }

    fun sendCommand(command: Commande) {
        loading.value = true
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = EndPointRest.createEndpoint().commande(command)
            withContext(Dispatchers.Main) {
                loading.value = false
                if (response.isSuccessful && response.body() != null) {
                    stateSend1.value = response.body()

                } else {
                    errorMessage.value = "Une erreur s'est produite"
                }
            }
        }
    }
    fun sendCommandMenu(commandM: Commande_menu) {
        loading.value = true
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = EndPointRest.createEndpoint().commandeMenu(commandM)
            withContext(Dispatchers.Main) {
                loading.value = false
                if (response.isSuccessful && response.body() != null) {
                    stateSend2.value = response.body()
                } else {
                    errorMessage.value = "Une erreur s'est produite"
                }
            }
        }
    }
}