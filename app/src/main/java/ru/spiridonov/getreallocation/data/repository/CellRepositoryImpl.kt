package ru.spiridonov.getreallocation.data.repository

import kotlinx.coroutines.flow.Flow
import ru.spiridonov.getreallocation.domain.entity.CellInfo
import ru.spiridonov.getreallocation.domain.repository.CellRepository

class CellRepositoryImpl : CellRepository {
    override fun getLocationByCellInfo(cellInfo: CellInfo): Flow<CellInfo> {
        TODO("Not yet implemented")
    }
}