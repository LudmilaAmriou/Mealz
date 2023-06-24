package com.example.mealz.Fragment
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mealz.Entity.Menu
import com.example.mealz.Entity.UserCart

import com.example.mealz.HomePage
import com.example.mealz.R
import com.example.mealz.ViewModel.MenuModel
import com.example.mealz.ViewModel.RestModel
import com.example.mealz.appDataBase
//import com.example.mealz.appDataBase
import com.example.mealz.databinding.FragmentMenuItemDetailsBinding

class MenuItemDetailsFragment : Fragment() {
    lateinit var binding: FragmentMenuItemDetailsBinding
    lateinit var menuModel: MenuModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuItemDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuId = arguments?.getInt("mId")
        Log.d("HERE THE ID OF MENU", menuId.toString())
        menuModel = ViewModelProvider(requireActivity()).get(MenuModel::class.java)
        menuId?.let {
            menuModel.menu.value = null
            menuModel.loadMenuDetails(it) }

        menuModel.menu.observe(viewLifecycleOwner, Observer { menu ->

            if (menu != null) {
                binding.Category.text = menu.Nom_TMenu
                binding.Name.text = menu.Nom
                HomePage().setGradientTextColor(
                    binding.Price,
                    Color.parseColor("#DC220F"),
                    Color.parseColor("#F05600")
                )
                binding.Price.text = menu.Prix_unitare.toString() + " DA"
                binding.ServingInfo.text = "1 Person"
                binding.Description.text = menu.Description
                binding.IngredientsList.text = menu.Ingredients


                // Add a button to redirect user only if they are logged in
                binding.cart.setOnClickListener {
                    // Redirect to authenticated page
                    findNavController().navigate(R.id.action_menuItemDetailsFragment_to_cartFragment)

                    // Insert element to database
                    val resto = appDataBase.buildDatabase(requireContext())?.getUserCartDAO()
                        ?.getUserCart(1)
                    if (resto.isNullOrEmpty() || resto[0].IDRestaurant == menu.ID_Restaurant) {
                        val userCart = UserCart(
                            IDUtilisateur = 1, // must change
                            IDMenu = menu.ID_Menu,
                            Quantity = 1,
                            IDRestaurant = menu.ID_Restaurant,
                            Image = menu.Image,
                            Prix_unitare = menu.Prix_unitare,
                            Note = binding.Notes.text.toString(),
                            Nom_TMenu = menu.Nom_TMenu.toString(),
                            Nom = menu.Nom,
                        )
                        appDataBase.buildDatabase(requireContext())?.getUserCartDAO()
                            ?.insertUserCart(userCart)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "You can only make commands from the same restaurant",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }
}
