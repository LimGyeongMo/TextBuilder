package com.project.textbulider.custonSpan

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

class CustomTypefaceSpan(typeface: Typeface?) : TypefaceSpan("") {
    private val customTypeface: Typeface = typeface ?: Typeface.DEFAULT

    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeface(ds)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeface(paint)
    }

    private fun applyCustomTypeface(paint: Paint) {
        val oldTypeface = paint.typeface
        val oldStyle = oldTypeface?.style ?: 0
        val fakeStyle = oldStyle and customTypeface.style.inv()

        if ((fakeStyle and Typeface.BOLD) != 0) {
            paint.isFakeBoldText = true
        }

        if ((fakeStyle and Typeface.ITALIC) != 0) {
            paint.textSkewX = -0.25f
        }

        paint.setTypeface(customTypeface)
    }

    override fun getTypeface(): Typeface? {
        return customTypeface
    }
}