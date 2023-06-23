package com.example.mealz.Entity

data class Menu(
    val ID_Menu:Int,
    val Nom:String?,
    val Prix_unitare:Float?,
    var Ingredients:String?,
    var Description:String?,
    val Image:String?,
    val ID_Restaurant:Int,
    val ID_TMenu:Int,
    val Nom_TMenu: String?,
)
