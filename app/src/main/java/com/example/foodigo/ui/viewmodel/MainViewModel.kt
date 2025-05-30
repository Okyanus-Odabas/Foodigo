package com.example.foodigo.ui.viewmodel

import androidx.lifecycle.*
import com.example.foodigo.data.model.Yemek
import com.example.foodigo.data.repository.YemekRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repo = YemekRepository()

    private val _yemekListesi = MutableLiveData<List<Yemek>>()
    val yemekListesi: LiveData<List<Yemek>> get() = _yemekListesi

    private val _hataMesaji = MutableLiveData<String?>()
    val hataMesaji: LiveData<String?> get() = _hataMesaji

    fun yemekleriYukle() {
        viewModelScope.launch {
            try {
                val response = repo.tumYemekleriGetir()
                if (response.success == 1) {
                    _yemekListesi.value = response.yemekler
                } else {
                    _hataMesaji.value = "Veri alınamadı."
                }
            } catch (e: Exception) {
                _hataMesaji.value = "Hata oluştu: ${e.localizedMessage}"
            }
        }
    }

    fun hataMesajiniTemizle() {
        _hataMesaji.value = null
    }
}
