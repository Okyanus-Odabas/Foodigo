package com.example.foodigo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Yemek(
    val yemek_id: Int,
    val yemek_adi: String,
    val yemek_resim_adi: String,
    val yemek_fiyat: Int
) : Parcelable
