package com.example.mealz.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealz.Entity.Commande_menu
import com.example.mealz.R

class OrderedMenusAdapter(private val orderedDishes: List<Commande_menu>) : RecyclerView.Adapter<OrderedMenusAdapter.OrderedDishViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderedDishViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ordered_menu_item, parent, false)
        return OrderedDishViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderedDishViewHolder, position: Int) {
        val orderedDish = orderedDishes[position]
        holder.bind(orderedDish)
    }

    override fun getItemCount(): Int {
        return orderedDishes.size
    }

    inner class OrderedDishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dishNameTextView: TextView = itemView.findViewById(R.id.name)
        private val quantityTextView: TextView = itemView.findViewById(R.id.quantity)

        fun bind(orderedDish: Commande_menu) {
     //       dishNameTextView.text = orderedDish.Menu_Name
          //  quantityTextView.text = "x" + orderedDish.quantity.toString()+" "
        }
    }

}
