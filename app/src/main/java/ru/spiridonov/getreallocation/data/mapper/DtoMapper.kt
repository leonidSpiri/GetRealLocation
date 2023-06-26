package ru.spiridonov.getreallocation.data.mapper

import ru.spiridonov.getreallocation.data.network.model.CellLocationResponseModel
import ru.spiridonov.getreallocation.domain.entity.CellLocation

class DtoMapper {

    fun mapCellLocationResponseModelToCCellLocation(responseModel: CellLocationResponseModel)
     = CellLocation(
        status = responseModel.status,
        message = responseModel.message,
        accuracy = responseModel.accuracy,
        address = responseModel.address,
        latitude = responseModel.latitude,
        longitude = responseModel.longitude
     )
}