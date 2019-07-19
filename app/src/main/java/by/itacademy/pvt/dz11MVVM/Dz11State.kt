package by.itacademy.pvt.dz11MVVM

sealed class Dz11State {
    object Loading : Dz11State()
    object Ready : Dz11State()
    class Error(val throwable: Throwable) : Dz11State()
}