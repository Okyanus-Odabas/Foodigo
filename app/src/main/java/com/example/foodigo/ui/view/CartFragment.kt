package com.example.foodigo.ui.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodigo.databinding.FragmentCartBinding
import com.example.foodigo.ui.viewmodel.CartViewModel
import com.example.foodigo.ui.view.adapter.SepetAdapter

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CartViewModel by viewModels()
    private lateinit var adapter: SepetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SepetAdapter(emptyList(), viewModel)
        binding.recyclerViewSepet.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSepet.adapter = adapter

        viewModel.sepetListesi.observe(viewLifecycleOwner) { liste ->
            adapter.updateList(liste)

            val toplamFiyat = liste.sumOf {
                it.yemek_fiyat.toInt() * it.yemek_siparis_adet.toInt()
            }

            binding.textViewToplamFiyat.text = "Toplam: ${toplamFiyat}₺"
        }


        viewModel.hata.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.hatayiTemizle()
            }
        }

        viewModel.sepetiYukle("kasim_adalan") // sabit kullanıcı adı
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
