package com.example.mealz.Adapter

import com.example.mealz.HomePage

import android.content.Context
import android.graphics.Color

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealz.Entity.Rating

import com.example.mealz.databinding.ReviewsLayoutBinding

class ReviewListAdapter(
    var ctx: Context, val cellClickListener: CellClickListener
):RecyclerView.Adapter<ReviewListAdapter.MyViewHolder>() {

    var data = mutableListOf<Rating>()
    fun setReview(rating: List<Rating>?) {
        if (rating != null) {
            this.data =rating.toMutableList()
        }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ReviewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            Name.text = data[position].Nom
            rating.text = data[position].Rating.toString()
            HomePage().setGradientTextColor(rating, Color.parseColor("#DC220F"), Color.parseColor("#F05600"))
            review.text = data[position].Commentaire
        }
    }

    class MyViewHolder(val binding: ReviewsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}
