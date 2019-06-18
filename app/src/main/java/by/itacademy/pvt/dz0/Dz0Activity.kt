package by.itacademy.pvt.dz0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.itacademy.pvt.R
import kotlinx.android.synthetic.main.activity_dz0.*

class Dz0Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz0)

        textA.setOnClickListener {
            swapText()
            swapColor()
        }

        textB.setOnClickListener {
            swapText()
            swapColor()
        }

        button.setOnClickListener {
            swapText()
            swapColor()
        }
    }

    private fun swapText() {
        val str = textA.text
        textA.text = textB.text
        textB.text = str
    }

    private fun swapColor() {
        val temp = textA.background
        textA.background = textB.background
        textB.background = temp
    }
}