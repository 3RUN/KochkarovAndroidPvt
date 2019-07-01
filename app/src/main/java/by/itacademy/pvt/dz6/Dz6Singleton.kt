package by.itacademy.pvt.dz6

import by.itacademy.pvt.dz6.entity.Student

object Dz6Singleton {
    val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun removeStudent(student: Student) {
        students.remove(student)
    }

    fun getStudentIndex(student: Student): Int {
        return students.indexOf(student)
    }

    fun getStudentByIndex(index: Int): Student {
        return students[index]
    }

    fun clearStudents() {
        students.clear()
    }
}