package com.example.mealz.Entity

data class Order(
    val ID_Commande : Int,
    val Adresse_livraison : String?,
    val Prix_tolal : Int?,
    val ID_Utilisateur : Int?,
    val Size : Int?,
    val Quantite:Int?,
    val Notes:String?,
    val Nom : Int?,
)
