package by.itacademy.pvt.dz2

import com.squareup.picasso.Transformation
import android.graphics.Color
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.BitmapShader

class CircleTransform : Transformation {

    private val boarderColor = Color.WHITE
    private val boarderRadius = 15

    private var x: Int = 0
    private var y: Int = 0

    override fun transform(source: Bitmap): Bitmap {

        val size = Math.min(source.width, source.height)

        x = (source.width - size) / 2
        y = (source.height - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }

        val bitmap = Bitmap.createBitmap(size, size, source.config)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true

        val r = size / 2f
        val paintBg = Paint()

        paintBg.color = boarderColor
        paintBg.isAntiAlias = true

        canvas.drawCircle(r, r, r, paintBg)
        canvas.drawCircle(r, r, r - boarderRadius, paint)

        squaredBitmap.recycle()
        return bitmap
    }

    override fun key() = "circle(x=$x,y=$y)"
}