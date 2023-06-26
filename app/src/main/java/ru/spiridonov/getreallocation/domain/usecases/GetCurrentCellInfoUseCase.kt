package ru.spiridonov.getreallocation.domain.usecases

import ru.spiridonov.getreallocation.domain.repository.CellInfoRepository

class GetCurrentCellInfoUseCase(
    private val repository: CellInfoRepository
) {
    operator fun invoke() = repository.getCurrentCellInfo()
}