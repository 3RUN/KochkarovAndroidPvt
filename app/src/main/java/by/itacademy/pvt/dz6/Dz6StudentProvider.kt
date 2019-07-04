package by.itacademy.pvt.dz6

import by.itacademy.pvt.dz6.entity.Student
import java.util.UUID

object Dz6StudentProvider {
    private var studentMap = mutableMapOf<UUID, Student>()

    fun setStudentAll(map: MutableMap<UUID, Student>) {
        studentMap = map
    }

    fun getStudent(id: UUID): Student? {
        return studentMap[id]
    }

    fun getStudentAll(): MutableMap<UUID, Student> {
        return studentMap
    }

    fun getStudentAsList(): List<Student> {
        var list = mutableListOf<Student>()
        for (student in studentMap) {
            list.add(student.value)
        }
        return list
    }

    fun addStudent(student: Student) {
        studentMap.put(student.id, student)
    }

    fun removeStudent(student: Student) {
        studentMap.remove(student.id)
    }

    fun removeStudentAll() {
        studentMap.clear()
    }

    fun replaceStudent(student: Student) {
        removeStudent(student)
        addStudent(student)
    }
}