package com.example.foodigo.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodigo.data.model.Yemek
import com.example.foodigo.databinding.ItemYemekBinding

class YemekAdapter(private var yemekListesi: List<Yemek>, private val onItemClick: (Yemek) -> Unit) :
    RecyclerView.Adapter<YemekAdapter.YemekViewHolder>() {

    class YemekViewHolder(val binding: ItemYemekBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemYemekBinding.inflate(inflater, parent, false)
        return YemekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YemekViewHolder, position: Int) {
        val yemek = yemekListesi[position]
        with(holder.binding) {
            textViewYemekAdi.text = yemek.yemek_adi
            textViewFiyat.text = "${yemek.yemek_fiyat}₺"

            val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
            Glide.with(imageViewYemek.context)
                .load(resimUrl)
                .into(imageViewYemek)

            root.setOnClickListener {
                onItemClick(yemek)
            }
        }
    }


    override fun getItemCount(): Int = yemekListesi.size

    fun updateList(yeniListe: List<Yemek>) {
        yemekListesi = yeniListe
        notifyDataSetChanged()
    }
}
