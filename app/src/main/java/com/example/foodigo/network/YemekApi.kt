package com.example.foodigo.network

import com.example.foodigo.data.model.*
import retrofit2.http.*

interface YemekApi {

    @GET("tumYemekleriGetir.php")
    suspend fun tumYemekleriGetir(): YemeklerCevap

    @FormUrlEncoded
    @POST("sepeteYemekEkle.php")
    suspend fun sepeteYemekEkle(
        @Field("yemek_adi") yemekAdi: String,
        @Field("yemek_resim_adi") resimAdi: String,
        @Field("yemek_fiyat") fiyat: Int,
        @Field("yemek_siparis_adet") adet: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): CrudCevap

    @FormUrlEncoded
    @POST("sepettekiYemekleriGetir.php")
    suspend fun sepettekiYemekleriGetir(
        @Field("kullanici_adi") kullaniciAdi: String
    ): SepetCevap

    @FormUrlEncoded
    @POST("sepettenYemekSil.php")
    suspend fun sepettenYemekSil(
        @Field("sepet_yemek_id") id: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): CrudCevap
}
