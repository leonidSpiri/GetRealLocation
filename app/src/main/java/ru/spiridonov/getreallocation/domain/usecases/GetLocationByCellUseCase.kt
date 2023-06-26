package ru.spiridonov.getreallocation.domain.usecases

import ru.spiridonov.getreallocation.domain.entity.CellInfo
import ru.spiridonov.getreallocation.domain.repository.CellRepository

class GetLocationByCellUseCase(
    private val repository: CellRepository
) {
    suspend operator fun invoke(cellInfo: CellInfo) = repository.getLocationByCell(cellInfo)
}