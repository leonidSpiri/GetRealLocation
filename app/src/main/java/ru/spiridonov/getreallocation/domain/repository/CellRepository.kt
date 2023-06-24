package ru.spiridonov.getreallocation.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.spiridonov.getreallocation.domain.entity.CellInfo

interface CellRepository {

    fun getLocationByCellInfo(cellInfo: CellInfo):Flow<CellInfo>
}