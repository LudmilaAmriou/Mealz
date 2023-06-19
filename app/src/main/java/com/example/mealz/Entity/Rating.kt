package com.example.mealz.Entity

import java.math.BigDecimal

data class Rating(
    val ID_Utilisateur: Int,
    val ID_Restaurant:Int,
    val Rating: BigDecimal?,
    val Commentaire: String,
    val Nom:String
)