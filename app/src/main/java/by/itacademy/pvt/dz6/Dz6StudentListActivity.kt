package by.itacademy.pvt.dz6

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Dz6StudentProvider.addStudent
import by.itacademy.pvt.dz6.Dz6StudentProvider.getStudentAsList
import by.itacademy.pvt.dz6.entity.Student
import java.util.UUID

class Dz6StudentListActivity : Activity(), Dz6ListAdapter.ClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6list)

        val recycleView = findViewById<RecyclerView>(R.id.recycleView)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(this)

        // add students into singleton
        initStudentsMap()

        val students = getStudentAsList()
        recycleView.adapter = Dz6ListAdapter(students, this)
    }

    override fun onStudentClick(student: Student) {
        Toast.makeText(this, student.id.toString(), Toast.LENGTH_SHORT).show()
    }

    fun initStudentsMap() {
        addStudent(
            Student(
                UUID.randomUUID(),
                "Ivan Ivanov",
                "http://i.imgur.com/H981AN7.jpg",
                23
            )
        )

        addStudent(
            Student(
                UUID.randomUUID(),
                "Petr Petrov",
                "https://introweska.files.wordpress.com/2016/12/macaca_nigra_self-portrait_rotated_and_cropped.jpg",
                32
            )
        )

        addStudent(
            Student(
                UUID.randomUUID(),
                "Andrey Baranov",
                "https://avatarfiles.alphacoders.com/916/91685.jpg",
                17
            )
        )

        addStudent(
            Student(
                UUID.randomUUID(),
                "Ilya Kazakov",
                "https://lolzteam.net/data/avatars/l/272/272785.jpg",
                53
            )
        )

        addStudent(
            Student(
                UUID.randomUUID(),
                "Masha Petrenko",
                "https://s00.yaplakal.com/pics/pics_original/4/9/0/12708094.jpg",
                26
            )
        )

        addStudent(
            Student(
                UUID.randomUUID(),
                "Noland Ritcher",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTEfYS-RllN8jZn9W-J7IlhG-okqH-3dMzuUlml2chgXpsWpKs",
                19
            )
        )
    }
}