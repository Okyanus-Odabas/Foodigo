package com.example.foodigo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodigo.data.model.Yemek
import com.example.foodigo.data.repository.YemekRepository
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val repo = YemekRepository()

    fun sepeteEkle(yemek: Yemek, adet: Int, kullaniciAdi: String) {
        viewModelScope.launch {
            try {
                repo.sepeteYemekEkle(
                    yemekAdi = yemek.yemek_adi,
                    resimAdi = yemek.yemek_resim_adi,
                    fiyat = yemek.yemek_fiyat,
                    adet = adet,
                    kullaniciAdi = kullaniciAdi
                )
            } catch (e: Exception) {
                // Gerekirse hata gösterimi burada yapılır
            }
        }
    }
}
