import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealz.Adapter.CartItemsAdapter
import com.example.mealz.Adapter.OrderItemsAdapter
import com.example.mealz.Entity.Commande
import com.example.mealz.Entity.Commande_menu
import com.example.mealz.Entity.UserCart
import com.example.mealz.LogIn
import com.example.mealz.R
import com.example.mealz.appDataBase
import com.example.mealz.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    lateinit var binding: FragmentOrderBinding
    private lateinit var myAdapter: OrderItemsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myRecyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(context)
        myRecyclerView.layoutManager = layoutManager
        val data = loadData(1)
        myAdapter = data?.let { OrderItemsAdapter(data,requireContext()) } ?: OrderItemsAdapter(
            mutableListOf(),
            requireContext()
        )
        myRecyclerView.adapter = myAdapter
        binding.mycart.setOnClickListener(){
            findNavController().navigate(R.id.action_orderFragment_to_cartFragment)
        }

    }

    // Function to load data from local db
    fun loadData(id: Int): MutableList<Commande>? {
        var data = mutableListOf<Commande>()
        var cart = mutableListOf<Commande_menu>()
        cart.add(Commande_menu(1,"tacos",1,2,1))
        cart.add(Commande_menu(1,"tacos",1,2,2))
        data.add(Commande(1,2400,"jjj",24000,200,1,"maison_tacos",cart))
        return data
    }

}