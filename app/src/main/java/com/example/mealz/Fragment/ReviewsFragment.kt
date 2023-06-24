package com.example.mealz.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealz.Adapter.CellClickListener
import com.example.mealz.Adapter.ReviewListAdapter
import com.example.mealz.Entity.Rating
import com.example.mealz.Entity.Restaurant
import com.example.mealz.ViewModel.ReviewModel
import com.example.mealz.databinding.FragmentReviewsBinding
import java.math.BigDecimal

class ReviewsFragment : Fragment(), CellClickListener {
    lateinit var binding: FragmentReviewsBinding
    lateinit var reviewModel: ReviewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restaurantId = arguments?.getInt("Res")
        reviewModel = ViewModelProvider(this).get(ReviewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = ReviewListAdapter(requireActivity(), this)
        binding.recyclerView.adapter = adapter
        restaurantId?.let {
            reviewModel.reviews.value = null
            reviewModel.loadReviews(it)
        }
        // add Observers
        // loading observer
        reviewModel.loading.observe(requireActivity()) { loading ->
            if (loading) {
                binding.progressBar2.visibility = View.VISIBLE
            } else {
                binding.progressBar2.visibility = View.GONE
            }
        }


        reviewModel.errorMessage.observe(requireActivity()) { errorMessaage ->
            Toast.makeText(requireContext(), errorMessaage, Toast.LENGTH_SHORT).show()
        }

        // List reviews observer
        reviewModel.reviews.observe(requireActivity()) { reviews ->
            adapter.setReview(reviews)
        }

        // Add a review from a specific user

        val sharedPreferences =
            requireContext().getSharedPreferences("my_app", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        binding.send.setOnClickListener() {
            if (isLoggedIn) {

                val review = restaurantId?.let { it1 ->
                    Rating(
                        ID_Utilisateur = sharedPreferences.getInt("userId", 1),
                        ID_Restaurant = it1,
                        Rating = BigDecimal(binding.ratingBar.rating.toDouble()),
                        Commentaire = binding.notes.text.toString(),
                        Nom = "user"
                    )
                }
                review?.let { it1 -> reviewModel.sendReview(it1) }
                // State observer
                reviewModel.stateSend.observe(requireActivity()) { state ->
                    if (state == true) {
                        Toast.makeText(
                            requireContext(),
                            "Thank you, your rating will be of great use!",
                            Toast.LENGTH_SHORT
                        ).show()
                        adapter.notifyDataSetChanged()

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Oups, a problem occured, try to resend again...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } else {
                Toast.makeText(
                    requireContext(),
                    "You can't rate us unless you're logged in!",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    override fun onCellClickListener(data: Restaurant) {
        val bundle = bundleOf("Res" to data.ID_Restaurant)
//        this.findNavController()
//            .navigate(R.id.action_homeFragment_to_restaurantMenuFragment, bundle)
    }
}