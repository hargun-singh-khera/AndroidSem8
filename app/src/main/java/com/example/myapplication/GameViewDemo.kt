package com.example.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View

class GameViewDemo(context: Context): View(context) {
    var bg1: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.img1)
    var bg2: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.img7)
    var x = 100
    var y = 20

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val r1 = Rect(0,0,bg1.width,bg1.height)
        val r2 = Rect(0,0,canvas.width,canvas.height)
        canvas.drawBitmap(bg1,r1,r2,null)

        canvas.drawBitmap(bg2,0f+x, (canvas.height-bg1.height).toFloat()-150f-y,null)
//        canvas.drawBitmap(c1,890f-y,(canvas.height-c1.height).toFloat()-230f-x,null)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startMove()
            MotionEvent.ACTION_UP -> stopJump()
        }
        return true
    }
    private fun startMove() {
        x = x + 150
        y = y + 110
        invalidate()
    }
    private fun stopJump() {
        y = y - 110
        invalidate()
    }
}