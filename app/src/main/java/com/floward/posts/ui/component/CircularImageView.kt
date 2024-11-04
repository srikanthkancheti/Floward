package com.floward.posts.ui.component

import android.content.Context
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class CircularImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {
    override fun onDraw(canvas: Canvas) {
        val drawable = drawable ?: return
        val bitmap = (drawable as BitmapDrawable).bitmap
        val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val paint = Paint().apply {
            isAntiAlias = true
            setShader(shader)
        }
        val radius = width / 2f
        canvas.drawCircle(radius, radius, radius, paint)
    }
}