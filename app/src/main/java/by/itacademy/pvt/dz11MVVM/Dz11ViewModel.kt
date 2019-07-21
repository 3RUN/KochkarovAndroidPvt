package by.itacademy.pvt.dz11MVVM

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.pvt.dz9.CarRepository
import by.itacademy.pvt.dz9.entity.CoordParams
import by.itacademy.pvt.dz9.entity.Poi
import by.itacademy.pvt.dz9.provideCarRepository

class Dz11ViewModel : ViewModel() {

    private val carRepository: CarRepository = provideCarRepository()
    private var carsList: List<Poi> = emptyList()

    val state: MutableLiveData<Dz11State> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<Dz11State>()
    }

    val lastSelectedCar: MutableLiveData<Poi> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<Poi>()
    }

    fun loadCarsList(coordinate: CoordParams) {
        if (state.value is Dz11State.Ready) {
            return
        }
        state.value = Dz11State.Loading
        carRepository.getCarByCoord(coordinate, { list: List<Poi> ->
            carsList = list
            state.value = Dz11State.Ready
        }, { throwable: Throwable ->
            state.value = Dz11State.Error(throwable)
        })
    }

    fun selectCarById(id: String) {
        carsList.find { it.id == id }.apply { lastSelectedCar.value = this }
    }

    fun isNotEmpty(): Boolean = carsList.isNotEmpty()

    fun takeCars(): List<Poi> = carsList
}