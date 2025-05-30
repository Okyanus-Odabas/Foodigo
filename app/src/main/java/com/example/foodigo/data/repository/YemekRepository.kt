package com.example.foodigo.data.repository

import com.example.foodigo.data.model.*
import com.example.foodigo.network.RetrofitInstance

class YemekRepository {

    private val api = RetrofitInstance.api

    suspend fun tumYemekleriGetir(): YemeklerCevap {
        return api.tumYemekleriGetir()
    }

    suspend fun sepeteYemekEkle(
        yemekAdi: String,
        resimAdi: String,
        fiyat: Int,
        adet: Int,
        kullaniciAdi: String
    ): CrudCevap {
        return api.sepeteYemekEkle(yemekAdi, resimAdi, fiyat, adet, kullaniciAdi)
    }

    suspend fun sepettekiYemekleriGetir(kullaniciAdi: String): SepetCevap {
        return try {
            RetrofitInstance.api.sepettekiYemekleriGetir(kullaniciAdi)
        } catch (e: Exception) {
            // Eğer JSON boş ya da hatalıysa, sıfır ürün dön
            SepetCevap(emptyList(), 0)
        }
    }


    suspend fun sepettenYemekSil(id: Int, kullaniciAdi: String): CrudCevap {
        return api.sepettenYemekSil(id, kullaniciAdi)
    }
}
