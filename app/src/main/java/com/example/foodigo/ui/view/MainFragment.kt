package com.example.foodigo.ui.view



import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodigo.databinding.FragmentMainBinding
import com.example.foodigo.ui.viewmodel.MainViewModel
import com.example.foodigo.ui.view.adapter.YemekAdapter
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: YemekAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = YemekAdapter(emptyList()) { secilenYemek ->
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(secilenYemek)
            findNavController().navigate(action)

        }
        binding.buttonSepet.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCartFragment()
            findNavController().navigate(action)
        }

        binding.recyclerViewYemekler.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewYemekler.adapter = adapter

        viewModel.yemekListesi.observe(viewLifecycleOwner) { yemekler ->
            adapter.updateList(yemekler)
        }

        viewModel.hataMesaji.observe(viewLifecycleOwner) { hata ->
            hata?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.hataMesajiniTemizle()
            }
        }

        viewModel.yemekleriYukle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
