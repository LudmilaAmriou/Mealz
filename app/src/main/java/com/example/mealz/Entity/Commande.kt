package com.example.mealz.Entity

import androidx.room.PrimaryKey

data class Commande(
    val Adresse_livraison : String?,
    val Prix_Tolal : Double?,
    val ID_Utilisateur : Int,
)
