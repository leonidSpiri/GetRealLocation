package ru.spiridonov.getreallocation.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.spiridonov.getreallocation.data.network.model.CellLocationResponseModel
import ru.spiridonov.getreallocation.domain.entity.CellInfo

interface ApiService {

    @POST("v2/process.php")
    suspend fun getLocationByCellInfo(
        @Body cellInfo: CellInfo
    ): Response<CellLocationResponseModel>

}