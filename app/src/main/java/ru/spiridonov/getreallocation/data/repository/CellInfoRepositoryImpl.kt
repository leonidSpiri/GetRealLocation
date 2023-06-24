package ru.spiridonov.getreallocation.data.repository

import android.telephony.CellInfoGsm
import android.telephony.CellInfoLte
import android.telephony.CellInfoWcdma
import ru.spiridonov.getreallocation.domain.entity.CellInfo
import ru.spiridonov.getreallocation.domain.repository.CellInfoRepository

class CellInfoRepositoryImpl: CellInfoRepository {
    override fun getCurrentCellInfo(): List<CellInfo> {
        TODO("Not yet implemented")
    }

    override fun getCellInfo(
        infoGsm: CellInfoGsm?,
        infWcfmao: CellInfoWcdma?,
        infoLte: CellInfoLte?
    ): CellInfo {
        TODO("Not yet implemented")
    }
}