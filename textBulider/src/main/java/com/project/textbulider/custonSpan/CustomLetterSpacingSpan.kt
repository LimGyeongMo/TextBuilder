package com.project.textbulider.custonSpan

import android.text.TextPaint
import android.text.style.MetricAffectingSpan

class CustomLetterSpacingSpan(private val letterSpacing: Float) : MetricAffectingSpan() {
    override fun updateMeasureState(p: TextPaint) {
        applyLetterSpacing(p)
    }

    override fun updateDrawState(tp: TextPaint) {
        applyLetterSpacing(tp)
    }

    private fun applyLetterSpacing(paint: TextPaint) {
        paint.letterSpacing = letterSpacing
    }
}
