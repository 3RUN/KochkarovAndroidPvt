package by.itacademy.pvt.dz6

import by.itacademy.pvt.dz6.entity.Student
import java.util.UUID

object Dz6StudentProvider {
    private var studentMap = mutableMapOf<UUID, Student>()
    private var isLoaded = false

    fun initStudents() {
        if (isLoaded) {
            return
        }
        isLoaded = true

        addStudent(
            "Ivan Ivanov",
            "http://i.imgur.com/H981AN7.jpg",
            23
        )

        addStudent(
            "Petr Petrov",
            "https://introweska.files.wordpress.com/2016/12/macaca_nigra_self-portrait_rotated_and_cropped.jpg",
            32
        )

        addStudent(
            "Andrey Baranov",
            "https://avatarfiles.alphacoders.com/916/91685.jpg",
            17
        )

        addStudent(
            "Ilya Kazakov",
            "https://lolzteam.net/data/avatars/l/272/272785.jpg",
            53
        )

        addStudent(
            "Masha Petrenko",
            "https://s00.yaplakal.com/pics/pics_original/4/9/0/12708094.jpg",
            26
        )

        addStudent(
            "Noland Ritcher",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTEfYS-RllN8jZn9W-J7IlhG-okqH-3dMzuUlml2chgXpsWpKs",
            19
        )
    }

    fun setStudentAll(map: MutableMap<UUID, Student>) {
        studentMap = map
    }

    fun getStudent(id: UUID): Student {
        return studentMap.get(id)!!
    }

    fun getStudentAll(): MutableMap<UUID, Student> {
        return studentMap
    }

    fun getStudentFrom(map: MutableMap<UUID, Student>): List<Student> {
        var list = mutableListOf<Student>()
        for (student in map) {
            list.add(student.value)
        }
        return list
    }

    fun getStudentAsList(): List<Student> {
        var list = mutableListOf<Student>()
        for (student in studentMap) {
            list.add(student.value)
        }
        return list
    }

    fun addStudent(name: String, url: String, age: Int) {
        val student = Student(
            UUID.randomUUID(),
            name,
            url,
            age
        )
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
        studentMap.put(student.id, student)
    }
}