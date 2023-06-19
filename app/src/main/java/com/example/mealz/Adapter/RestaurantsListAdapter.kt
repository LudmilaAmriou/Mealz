package com.example.mealz.Adapter

import com.example.mealz.HomePage
import com.example.mealz.openPage
import com.example.mealz.sendEmail

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.mealz.Entity.Restaurant
import com.example.mealz.databinding.RestaurantsLayoutBinding

class RestaurantsListAdapter(
    var ctx: Context, val cellClickListener: CellClickListener
):RecyclerView.Adapter<RestaurantsListAdapter.MyViewHolder>() {

    var data = mutableListOf<Restaurant>()
    fun setRest(Restaurants: List<Restaurant>) {
        this.data = Restaurants.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RestaurantsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            Name.text = data[position].Nom
            Adress.text = data[position].Localisation
            phoneNumber.text = data[position].Phone_number
            HomePage().setGradientTextColor(rating, Color.parseColor("#DC220F"), Color.parseColor("#F05600"))
            rating.text = data[position].Rating.toString()
            Type.text = data[position].Nom_Type.toString()
            Glide.with(ctx).load(data[position].Image).into(restaurantLogo)
        }

        holder.binding.facebook.setOnClickListener {
            data[position].Facebook_Web?.let { it1 ->
                data[position].Facebook_Web?.let { it2 ->
                    openPage(ctx,
                        it1, it2
                    )
                }
            }
        }
        holder.binding.instagram.setOnClickListener {
            data[position].Instagram_App?.let { it1 ->
                data[position].Instagram_Web?.let { it2 ->
                    openPage(ctx,
                        it1, it2
                    )
                }
            }
        }

        holder.binding.restaurantNumber.setOnClickListener{
            val data = Uri.parse("tel:021212121")
            val intent = Intent(Intent.ACTION_DIAL, data)
            ctx.startActivity(intent)
        }
        holder.binding.restaurantAdress.setOnClickListener {
            val latitude =  data[position].Latitude
            val longitude = data[position].Longitude


            val data = Uri.parse("geo:$latitude,$longitude")
            val intent = Intent(Intent.ACTION_VIEW,data)
            ctx.startActivity(intent)
        }
        holder.binding.email.setOnClickListener{
            sendEmail(ctx,"jm_haddou@esi.dz")
        }


        holder.binding.root.setOnClickListener{
            cellClickListener.onCellClickListener(data[position])
        }

    }



    class MyViewHolder(val binding: RestaurantsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}
