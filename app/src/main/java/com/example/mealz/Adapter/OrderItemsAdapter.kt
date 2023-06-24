package com.example.mealz.Adapter

import OrderFragment
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealz.Entity.Order
import com.example.mealz.HomePage
import com.example.mealz.databinding.OrderItemsLayoutBinding


class OrderItemsAdapter(
    var ctx: Context, val cellClickListener: OrderFragment
):RecyclerView.Adapter<OrderItemsAdapter.MyViewHolder>() {

    var data = mutableListOf<Order>()
    fun setOrder(orders: List<Order>) {
        this.data = orders.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            OrderItemsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            Name.text = data[position].Nom
            price.text = data[position].Prix_Tolal.toString()
            menus.text = data[position].NomMs
            HomePage().setGradientTextColor(
                price,
                Color.parseColor("#DC220F"),
                Color.parseColor("#F05600")
            )
        }
    }


    class MyViewHolder(val binding: OrderItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
