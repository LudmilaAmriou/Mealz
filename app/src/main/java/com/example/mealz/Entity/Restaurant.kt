package com.example.mealz.Entity

import java.math.BigDecimal
import java.text.DecimalFormat

data class Restaurant (
   val ID_Restaurant:Int,
   val Nom:String?,
   val review_pic:String?,
   val Localisation:String?,
   val Facebook_Web: String?,
   val Facebook_App: String?,
   val Instagram_Web: String?,
   val Instagram_App: String?,
   val Mail:String?,
   val Image:String?,
   val Rating: BigDecimal?,
   val Phone_number:String?,
   val Latitude:Float?,
   val Longitude:Float?,
   val Nom_Type:String?,
)
