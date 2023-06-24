package ru.spiridonov.getreallocation.domain.repository

import android.telephony.CellInfoGsm
import android.telephony.CellInfoLte
import android.telephony.CellInfoWcdma
import ru.spiridonov.getreallocation.domain.entity.CellInfo

interface CellInfoRepository {
    fun getCurrentCellInfo(): List<CellInfo>

    fun getCellInfo(
        infoGsm: CellInfoGsm? = null,
        infWcfmao: CellInfoWcdma? = null,
        infoLte: CellInfoLte? = null
    ): CellInfo
}