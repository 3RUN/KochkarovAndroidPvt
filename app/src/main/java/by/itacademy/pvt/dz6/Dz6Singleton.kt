package by.itacademy.pvt.dz6

import by.itacademy.pvt.dz6.entity.Student
import java.util.UUID

object Dz6Singleton {
    private val studentMap = mutableMapOf<UUID, Student>()

    fun getStudent(id: UUID): Student? {
        return studentMap[id]
    }

    fun addStudent(student: Student) {
        studentMap.put(student.id, student)
    }

    fun removeStudent(student: Student) {
        studentMap.remove(student.id)
    }

    fun removeAllStudents() {
        studentMap.clear()
    }

    fun replaceStudent(student: Student) {
        removeStudent(student)
        addStudent(student)
    }
}