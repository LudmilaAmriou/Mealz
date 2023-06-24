import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealz.Adapter.OrderItemsAdapter
import com.example.mealz.R
import com.example.mealz.ViewModel.OrderModel
import com.example.mealz.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    lateinit var binding: FragmentOrderBinding
    private lateinit var orderModel: OrderModel


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

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = OrderItemsAdapter(requireActivity(),this)
        binding.recyclerView.adapter = adapter

        orderModel = ViewModelProvider(this).get(OrderModel::class.java)

        val sharedPreferences = requireContext().getSharedPreferences("my_app", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("userId", 1)
        binding.mycart.setOnClickListener() {
            findNavController().navigate(R.id.action_orderFragment_to_cartFragment)
        }
        userId?.let {
            orderModel.loadOrders(it)
             }
        // Add Observers
        orderModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.progressBar4.visibility = View.VISIBLE
            } else {
                binding.progressBar4.visibility = View.GONE
            }
        }

        orderModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }

        orderModel.orders.observe(viewLifecycleOwner) { orders ->
            adapter.setOrder(orders)
        }
    }


}