package ru.spiridonov.getreallocation.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.spiridonov.getreallocation.data.repository.CellInfoRepositoryImpl
import ru.spiridonov.getreallocation.data.repository.CellRepositoryImpl
import ru.spiridonov.getreallocation.domain.entity.CellInfo
import ru.spiridonov.getreallocation.domain.usecases.GetCurrentCellInfoUseCase
import ru.spiridonov.getreallocation.domain.usecases.GetLocationByCellUseCase

class MainViewModel(context: Application) : AndroidViewModel(context) {
    private val cellInfoRepository = CellInfoRepositoryImpl(context)
    private val cellRepository = CellRepositoryImpl()
    private val getLocationByCellUseCase = GetLocationByCellUseCase(cellRepository)
    val getCurrentCellInfo = GetCurrentCellInfoUseCase(cellInfoRepository)

    private var job: Job? = null

    private val _locationLiveData = MutableLiveData<MainActivityState>()
    val locationLiveData: LiveData<MainActivityState>
        get() = _locationLiveData

    fun fetchLocation(cellInfo: CellInfo) {
        job?.cancel()

        job = viewModelScope.launch {
            getLocationByCellUseCase(cellInfo)
                .onStart {
                    _locationLiveData.value = MainActivityState.Loading
                }
                .catch {
                    it.printStackTrace()
                    _locationLiveData.value = MainActivityState.Error(it.message.toString())
                }
                .collect { cellLocation ->
                    cellLocation?.let {
                        if (cellLocation.isSuccess())
                            _locationLiveData.value = MainActivityState.Success(cellLocation)
                        else
                            _locationLiveData.value =
                                MainActivityState.Error(cellLocation.message.toString())
                    }
                }
        }
    }

}