package com.example.foodigo.ui.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodigo.data.model.Yemek
import com.example.foodigo.databinding.FragmentDetailBinding
import com.example.foodigo.ui.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs() // ✅ Buraya taşındı

    private var adet = 1
    private lateinit var yemek: Yemek

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yemek = args.yemek // ✅ Artık güvenli şekilde alınabilir

        binding.textViewYemekAdi.text = yemek.yemek_adi
        binding.textViewFiyat.text = "${yemek.yemek_fiyat}₺"

        val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(requireContext())
            .load(resimUrl)
            .into(binding.imageViewYemek)

        binding.buttonArttir.setOnClickListener {
            adet++
            binding.textViewAdet.text = adet.toString()
        }

        binding.buttonAzalt.setOnClickListener {
            if (adet > 1) {
                adet--
                binding.textViewAdet.text = adet.toString()
            }
        }

        binding.buttonSepeteEkle.setOnClickListener {
            viewModel.sepeteEkle(
                yemek,
                adet,
                kullaniciAdi = "kasim_adalan"
            )
            Toast.makeText(requireContext(), "Sepete eklendi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
