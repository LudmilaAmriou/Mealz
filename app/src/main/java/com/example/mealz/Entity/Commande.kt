package com.example.mealz.Entity

import androidx.room.PrimaryKey

data class Commande(
    val ID_Commande : Int,
    val Prix_Commande : Int?,
    val Adresse_livrasion : String?,
    val Prix_tolal : Int?,
    val Frais_Livraison : Int?,
    val ID_Utilisateur : Int,
    val Restau_Name : String,
    val Menus : List<Commande_menu>,
)
