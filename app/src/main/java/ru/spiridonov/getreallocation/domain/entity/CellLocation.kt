package ru.spiridonov.getreallocation.domain.entity

data class CellLocation(
    val status: String,
    val message: String?,
    val accuracy: Int? = null,
    val address: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
) {
    fun isSuccess() = status == "ok"
}