package com.example.internshiptask.model
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable


@Parcelize
data class Category(
    @SerializedName("a")
    var englishtitle: String? = null,
    @SerializedName("i")
    var id: Int? = null,
    @SerializedName("n")
    var hindititle: String? = null,
    @SerializedName("p")
    var categoryImage: String? = null,
    var isSelected:Boolean = false
) : Parcelable