package ru.spiridonov.getreallocation.domain.entity

import ru.spiridonov.getreallocation.config.Config


data class CellInfo(
    val token: String = Config.OPENCELLID_API_KEY,
    var radio: String,
    var mcc: Int,
    var mnc: Int,
    var cells: List<Cell>,
    val address: Int
)
