package com.example.foodigo.ui.viewmodel

import androidx.lifecycle.*
import com.example.foodigo.data.model.SepetYemek
import com.example.foodigo.data.repository.YemekRepository
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val repo = YemekRepository()

    private val _sepetListesi = MutableLiveData<List<SepetYemek>>()
    val sepetListesi: LiveData<List<SepetYemek>> get() = _sepetListesi

    private val _hata = MutableLiveData<String?>()
    val hata: LiveData<String?> get() = _hata

    fun sepetiYukle(kullaniciAdi: String) {
        viewModelScope.launch {
            try {
                val response = repo.sepettekiYemekleriGetir(kullaniciAdi)
                if (response.success == 1) {
                    _sepetListesi.value = response.sepet_yemekler
                } else {
                    _sepetListesi.value = emptyList()
                }
            } catch (e: Exception) {
                _hata.value = "Sepet alınamadı: ${e.localizedMessage}"
                _sepetListesi.value = emptyList() // ❗ eğer boş dönmüşse listeyi sıfırla
            }
        }
    }

    fun sepettenSil(sepetYemekId: Int, kullaniciAdi: String) {
        viewModelScope.launch {
            try {
                repo.sepettenYemekSil(sepetYemekId, kullaniciAdi)
                sepetiYukle(kullaniciAdi)
            } catch (e: Exception) {
                _hata.value = "Silme hatası: ${e.localizedMessage}"
            }
        }
    }

    fun hatayiTemizle() {
        _hata.value = null
    }
}
