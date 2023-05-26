package com.example.mealz.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealz.Entity.UserCart
import com.example.mealz.Fragment.CartFragment
import com.example.mealz.HomePage
import com.example.mealz.appDataBase
import com.example.mealz.databinding.CartItemsLayoutBinding
import java.util.Locale.Category

class CartItemsAdapter(
    val data: MutableList<UserCart>,
    private val ctx: Context,
) : RecyclerView.Adapter<CartItemsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CartItemsLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val menuItem = data[position]
        holder.bind(menuItem)
        holder.binding.imageButton.setOnClickListener {
            holder.removeItem(menuItem)
        }
    }

    inner class MyViewHolder(val binding: CartItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(menuItem: UserCart) {
            binding.apply {
                Name.text = menuItem.Nom
                Glide.with(ctx).load(menuItem.Image).into(platePic)
                HomePage().setGradientTextColor(
                    Price,
                    Color.parseColor("#DC220F"),
                    Color.parseColor("#F05600")
                )
                Price.text = menuItem.Prix_unitare.toString() + " DA"
                category.text = menuItem.Nom_TMenu.toString()
            }
        }
        // Method to handle item removal
        fun removeItem(menuItem: UserCart) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                appDataBase.buildDatabase(ctx)?.getUserCartDAO()?.deleteUserCart(menuItem.IDMenu)
                data.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, data.size)
            }
        }
    }
}
