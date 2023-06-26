package ru.spiridonov.getreallocation.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CellLocationResponseModel(
    @Expose
    val status: String,
    @Expose
    val message: String?,
    @Expose
    val accuracy: Int? = null,
    @Expose
    val address: String? = null,

    @SerializedName("lat")
    @Expose
    val latitude: Double? = null,

    @SerializedName("lon")
    @Expose
    val longitude: Double? = null
)