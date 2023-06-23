package com.example.mealz.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealz.Entity.Menu
import com.example.mealz.Entity.Restaurant
import com.example.mealz.Retrofit.EndPointRest
import kotlinx.coroutines.*
import okhttp3.MediaType
import okhttp3.RequestBody


class MenuModel : ViewModel(){
    var menu = MutableLiveData<Menu>()
    val menus = MutableLiveData<List<Menu>>()
    val restau = MutableLiveData<Restaurant>()

    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch   {
            loading.value = false
            errorMessage.value = "Une erreur s'est produite  ${throwable.message}"
            Log.d("MY EXCEPTION", "Une erreur s'est produite  ${throwable.message}")
        }
    }


    fun loadMenus(restaurantId:Int) {
        if(menu.value==null) {
            loading.value = true
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = EndPointRest.createEndpoint().getMenus(restaurantId)
              //  Log.d("My response", response.toString())
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        menus.value = response.body()

                    } else {

                        errorMessage.value = "Une erreur s'est produite"
                    }
                }
            }
        }


    }
    fun loadMenuDetails(menuId:Int) {
        if(menu.value==null) {
            loading.value = true
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = EndPointRest.createEndpoint().getMenu(menuId)
                Log.d("My response", response.toString())
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        menu.value = response.body()

                    } else {

                        errorMessage.value = "Une erreur s'est produite"
                    }
                }
            }
        }
    }
    fun loadRest(id:Int){
        if(restau.value==null) {
            loading.value = true
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = EndPointRest.createEndpoint().getRestauById(id)
                withContext(Dispatchers.Main) {
                    loading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        restau.value = response.body()
                    } else {
                        errorMessage.value = "Une erreur s'est produite lors du chargement restau"
                    }
                }
            }
        }
    }

}