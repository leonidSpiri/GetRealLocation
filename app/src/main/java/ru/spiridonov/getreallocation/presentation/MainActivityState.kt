package ru.spiridonov.getreallocation.presentation

import ru.spiridonov.getreallocation.domain.entity.CellLocation

sealed class MainActivityState {
    object Loading : MainActivityState()
    data class Error(val message: String) : MainActivityState()
    data class Success(val response: CellLocation) : MainActivityState()
}
