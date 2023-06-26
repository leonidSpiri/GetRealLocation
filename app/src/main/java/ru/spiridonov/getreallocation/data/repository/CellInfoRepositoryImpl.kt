package ru.spiridonov.getreallocation.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.telephony.CellInfoGsm
import android.telephony.CellInfoLte
import android.telephony.CellInfoWcdma
import android.telephony.TelephonyManager
import android.util.Log
import ru.spiridonov.getreallocation.domain.entity.Cell
import ru.spiridonov.getreallocation.domain.entity.CellInfo
import ru.spiridonov.getreallocation.domain.entity.RadioTypeEnum
import ru.spiridonov.getreallocation.domain.repository.CellInfoRepository

class CellInfoRepositoryImpl(
    private val context: Context
) : CellInfoRepository {

    @SuppressLint("MissingPermission")
    override fun getCurrentCellInfo(): List<CellInfo> {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val allCellInfo = telephonyManager.allCellInfo
Log.d("CellInfoRepositoryImpl", "allCellInfo: $allCellInfo")
        return allCellInfo.mapNotNull {
            when (it) {
                is CellInfoGsm -> getCellInfo(infoGsm = it)
                is CellInfoWcdma -> getCellInfo(infWcfmao = it)
                is CellInfoLte -> getCellInfo(infoLte = it)
                else -> null
            }
        }
    }

    private fun getCellInfo(
        infoGsm: CellInfoGsm? = null,
        infWcfmao: CellInfoWcdma? = null,
        infoLte: CellInfoLte? = null
    ): CellInfo {
        val cellInfo = CellInfo()
        infoGsm?.cellIdentity?.let {
            val (mcc, mnc) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Pair(it.mccString?.toInt() ?: 0, it.mncString?.toInt() ?: 0)
            } else {
                Pair(it.mcc, it.mnc)
            }
            cellInfo.mcc = mcc
            cellInfo.mnc = mnc
            cellInfo.cells = listOf(Cell(it.lac, it.cid, it.psc))
            cellInfo.radio = RadioTypeEnum.GSM.name

            Log.d("CellInfoRepositoryImpl", "cellInfo: $cellInfo")
            return cellInfo
        }
        infWcfmao?.cellIdentity?.let {
            val (mcc, mnc) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Pair(it.mccString?.toInt() ?: 0, it.mncString?.toInt() ?: 0)
            } else {
                Pair(it.mcc, it.mnc)
            }
            cellInfo.mcc = mcc
            cellInfo.mnc = mnc
            cellInfo.cells = listOf(Cell(it.lac, it.cid, it.psc))
            cellInfo.radio = RadioTypeEnum.CDMA.name
            Log.d("CellInfoRepositoryImpl", "cellInfo: $cellInfo")
            return cellInfo
        }
        infoLte?.cellIdentity?.let {
            val (mcc, mnc) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Pair(it.mccString?.toInt() ?: 0, it.mncString?.toInt() ?: 0)
            } else {
                Pair(it.mcc, it.mnc)
            }
            cellInfo.mcc = mcc
            cellInfo.mnc = mnc
            cellInfo.cells = listOf(Cell(it.tac, it.ci, it.pci))
            cellInfo.radio = RadioTypeEnum.LTE.name
            Log.d("CellInfoRepositoryImpl", "cellInfo: $cellInfo")
            return cellInfo
        }
        return cellInfo
    }
}