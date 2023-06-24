package com.example.mealz.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealz.Entity.Commande
import com.example.mealz.HomePage
import com.example.mealz.databinding.OrderItemsLayoutBinding


class OrderItemsAdapter(
    val data: MutableList<Commande>,
    private val ctx: Context,
) : RecyclerView.Adapter<OrderItemsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = OrderItemsLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val orderItem = data[position]
        holder.bind(orderItem)

    }

    inner class MyViewHolder(val binding: OrderItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(orderItem: Commande) {
            binding.apply {
              //  Name.text = orderItem.Restau_Name
                HomePage().setGradientTextColor(
                    price ,
                    Color.parseColor("#DC220F"),
                    Color.parseColor("#F05600")
                )
                price.text = orderItem.Prix_Tolal.toString()+" DA"
//                val orderedDishesAdapter = OrderedMenusAdapter(orderItem.Menus)
//                recyclerView.adapter = orderedDishesAdapter
//                val layoutManager = LinearLayoutManager(ctx)
//                recyclerView.layoutManager = layoutManager

            }
        }
    }
}
