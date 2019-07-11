package by.itacademy.pvt.dz9

import by.itacademy.pvt.dz9.entity.CoordParams
import by.itacademy.pvt.dz9.entity.Poi

interface CarRepository {

    fun getCarByCoord(
        params: CoordParams,
        onSuccess: (List<Poi>) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}