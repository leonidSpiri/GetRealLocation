package ru.spiridonov.getreallocation.domain.entity

import ru.spiridonov.getreallocation.config.Config


data class CellInfo(
    val token: String = Config.OPENCELLID_API_KEY,
    var radio: String? = null,
    var mcc: Int? = null,
    var mnc: Int? = null,
    var cells: List<Cell>? = null,
    val address: Int? = null
)
