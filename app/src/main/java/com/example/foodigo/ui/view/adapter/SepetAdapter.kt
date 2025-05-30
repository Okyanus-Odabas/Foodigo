package com.example.foodigo.ui.view.adapter

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodigo.data.model.SepetYemek
import com.example.foodigo.databinding.ItemSepetBinding
import com.example.foodigo.ui.viewmodel.CartViewModel

class SepetAdapter(
    private var yemekler: List<SepetYemek>,
    private val viewModel: CartViewModel
) : RecyclerView.Adapter<SepetAdapter.SepetViewHolder>() {

    inner class SepetViewHolder(val binding: ItemSepetBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSepetBinding.inflate(inflater, parent, false)
        return SepetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SepetViewHolder, position: Int) {
        val yemek = yemekler[position]
        with(holder.binding) {
            textViewYemekAdi.text = yemek.yemek_adi
            textViewFiyat.text = "${yemek.yemek_fiyat}₺ x${yemek.yemek_siparis_adet}"

            val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
            Glide.with(imageViewYemek.context).load(resimUrl).into(imageViewYemek)

            buttonSil.setOnClickListener {
                viewModel.sepettenSil(yemek.sepet_yemek_id, yemek.kullanici_adi)
            }
        }
    }

    override fun getItemCount(): Int = yemekler.size

    fun updateList(yeniListe: List<SepetYemek>) {
        yemekler = yeniListe
        notifyDataSetChanged()
    }
}
