package com.example.mealz.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealz.Adapter.CellClickListener
import com.example.mealz.Adapter.RestaurantsListAdapter
import com.example.mealz.Entity.Restaurant
import com.example.mealz.R
import com.example.mealz.ViewModel.RestModel
import com.example.mealz.databinding.FragmentHomeBinding
class HomeFragment : Fragment(), CellClickListener {
    lateinit var binding: FragmentHomeBinding
    lateinit var restauModel: RestModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restauModel = ViewModelProvider(requireActivity()).get(RestModel::class.java)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = RestaurantsListAdapter(requireActivity(), this)
        binding.recyclerView.adapter = adapter
        restauModel.loadRestaus()

        // add Observers
        // loading observer
        restauModel.loading.observe(requireActivity()) { loading ->
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }


        restauModel.errorMessage.observe(requireActivity()) { errorMessaage ->
            Toast.makeText(requireContext(), errorMessaage, Toast.LENGTH_SHORT).show()
        }

        // List movies observer
        restauModel.restaus.observe(requireActivity()) { restaus ->
            adapter.setRest(restaus)
        }
    }

    override fun onCellClickListener(data: Restaurant) {
        val bundle = bundleOf("Res" to data.ID_Restaurant)
        this.findNavController()
            .navigate(R.id.action_homeFragment_to_restaurantMenuFragment, bundle)
    }
}