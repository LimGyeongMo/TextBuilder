package com.project.textbulider

import TextStyle
import android.text.Layout
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.AlignmentSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import com.project.textbulider.custonSpan.CustomTypefaceSpan
import kotlin.math.abs

object TextBuilder {
    private val sections = mutableListOf<TextSection>()
    private var defaultTextStyle: TextStyle? = null
    private var alignmentSpan: Any? = null

    @JvmStatic
    fun builder(): TextBuilder = this

    private data class TextSection(val text: String, val attributes: List<Any>?)

    fun setDefaultAttribute(textStyle: TextStyle?): TextBuilder = apply {
        defaultTextStyle = textStyle
    }

    fun text(text: String, textStyle: TextStyle? = defaultTextStyle): TextBuilder = apply {
        val attributes = textStyle?.getAttributes()?.toList()
        sections.add(TextSection(text, attributes))
    }

    fun space(count: Int = 1): TextBuilder = text(" ".repeat(abs(count)))
    fun newLine(count: Int = 1): TextBuilder = text("\n".repeat(abs(count)))
    fun tab(count: Int = 1): TextBuilder = text("\t".repeat(abs(count)))

    fun setAlign(alignment: Any?): TextBuilder = apply {
        if (alignment is AlignmentSpan) alignmentSpan = alignment
    }

    fun build(): SpannableStringBuilder {
        val builder = SpannableStringBuilder()
        sections.forEach { builder.append(it.text) }

        alignmentSpan?.let {
            builder.setSpan(it, 0, builder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        var currentPosition = 0
        sections.forEach { section ->
            section.attributes?.forEach { attribute ->
                val span = cloneSpan(attribute)
                builder.setSpan(span, currentPosition, currentPosition + section.text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            currentPosition += section.text.length
        }

        return builder
    }

    private fun cloneSpan(span: Any): Any = when (span) {
        is ForegroundColorSpan -> ForegroundColorSpan(span.foregroundColor)
        is BackgroundColorSpan -> BackgroundColorSpan(span.backgroundColor)
        is AbsoluteSizeSpan -> AbsoluteSizeSpan(span.size, span.dip)
        is StyleSpan -> StyleSpan(span.style)
        is UnderlineSpan -> UnderlineSpan()
        is CustomTypefaceSpan -> CustomTypefaceSpan(span.getTypeface())
        else -> span
    }

    val ALIGN_CENTER: Any = AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER)
    val ALIGN_OPPOSITE: Any = AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE)
    val ALIGN_NORMAL: Any = AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL)
}