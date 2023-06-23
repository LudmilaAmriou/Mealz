package com.example.mealz.Entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "CartMenu")
data class UserCart(
    @PrimaryKey(autoGenerate = true)
    val ID: Int = 0,
    val IDUtilisateur: Int,
    val IDMenu: Int,
    val IDRestaurant:Int,
    var Quantity: Int?,
    val Nom:String?,
    val Prix_unitare:Float?,
    val Image:String?,
    val Nom_TMenu:String?,
)