package by.itacademy.pvt.dz6.entity

import java.util.UUID

data class Student(
    val id: UUID,
    val name: String,
    val url: String,
    val age: Int
)