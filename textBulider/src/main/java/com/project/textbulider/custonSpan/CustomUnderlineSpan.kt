package com.project.textbulider.custonSpan

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.text.TextPaint
import android.text.style.ReplacementSpan

class CustomUnderlineSpan(// 밑줄 색상
    private val color: Int, // 밑줄 두께
    private val thickness: Float
) : ReplacementSpan() {
    override fun updateDrawState(tp: TextPaint) {
        // 텍스트 스타일은 변경하지 않음
    }

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fm: FontMetricsInt?): Int {
        // 텍스트의 크기 계산 (기본적으로 텍스트 폭을 반환)
        return Math.round(paint.measureText(text, start, end))
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        // 텍스트 그리기
        canvas.drawText(text, start, end, x, y.toFloat(), paint)

        // 밑줄 그리기
        val underlinePaint = Paint(paint) // 기존 Paint 복사
        underlinePaint.color = color // 밑줄 색상 설정
        underlinePaint.style = Paint.Style.STROKE // 외곽선 스타일
        underlinePaint.strokeWidth = thickness // 밑줄 두께 설정

        // 밑줄 위치 계산
        val underlineY = y + thickness // 밑줄의 Y 위치 설정
        canvas.drawLine(x, underlineY, x + paint.measureText(text, start, end), underlineY, underlinePaint)
    }
}