package com.example.catphotogallery

import android.content.Context
import android.graphics.*

object BitmapUtil {
    fun createCircleBitmap(context: Context, imageResourceID: Int): Bitmap {
        val bitmap = BitmapFactory.decodeResource(context.resources, imageResourceID)
        return createCircleBitmap(bitmap)
    }

    fun createCircleBitmap(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        var diameter = bitmap.width
        if (height < width) {
            diameter = height
        }
        val output = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        canvas.drawARGB(0, 0, 0, 0)
        val color = Color.BLACK
        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = color
        val rect = Rect(0, 0, diameter, diameter)
        val rectf = RectF(rect)
        canvas.drawOval(rectf, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        bitmap.recycle()
        return output
    }
}