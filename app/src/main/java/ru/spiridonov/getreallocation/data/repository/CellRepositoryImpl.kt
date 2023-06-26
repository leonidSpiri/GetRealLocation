package ru.spiridonov.getreallocation.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.spiridonov.getreallocation.data.mapper.DtoMapper
import ru.spiridonov.getreallocation.data.network.ApiFactory
import ru.spiridonov.getreallocation.domain.entity.CellInfo
import ru.spiridonov.getreallocation.domain.repository.CellRepository

class CellRepositoryImpl : CellRepository {
    private val apiService = ApiFactory.apiService
    private val dtoMapper = DtoMapper()
    override suspend fun getLocationByCell(cellInfo: CellInfo)= flow {
        val response = apiService.getLocationByCellInfo(cellInfo)
        val cellLocation = response.body()
            ?.let { dtoMapper.mapCellLocationResponseModelToCCellLocation(it) }
        emit(cellLocation)
    }.flowOn(Dispatchers.IO)
}