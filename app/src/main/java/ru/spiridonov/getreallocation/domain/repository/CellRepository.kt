package ru.spiridonov.getreallocation.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.spiridonov.getreallocation.data.repository.CellLocation
import ru.spiridonov.getreallocation.domain.entity.CellInfo

interface CellRepository {

    suspend fun getLocationByCell(cellInfo: CellInfo):Flow<CellLocation?>
}