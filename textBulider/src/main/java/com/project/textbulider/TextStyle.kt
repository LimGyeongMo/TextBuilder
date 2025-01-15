import com.project.textbulider.custonSpan.CustomLetterSpacingSpan

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.text.Layout
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.project.textbulider.custonSpan.CustomTypefaceSpan
import com.project.textbulider.custonSpan.CustomUnderlineSpan

class TextStyle private constructor() {

    private val attributes = mutableListOf<Any>()
    private var alignSpan: AlignmentSpan.Standard? = null

    val align: AlignmentSpan.Standard?
        get() = alignSpan

    val isAttributesEmpty: Boolean
        get() = attributes.isEmpty()

    fun getAttributes(): List<Any> = attributes.toList()

    // 텍스트 크기 설정
    fun textSize(size: Int): TextStyle = apply {
        attributes.add(AbsoluteSizeSpan(size, true))
    }

    // 배경 색상 설정
    fun backgroundColor(color: Int): TextStyle = apply {
        attributes.add(BackgroundColorSpan(color))
    }

    // 텍스트 색상 설정
    fun textColor(color: Int): TextStyle = apply {
        attributes.add(ForegroundColorSpan(color))
    }

    // 밑줄 설정
    fun underline(): TextStyle = apply {
        attributes.add(UnderlineSpan())
    }

    // 커스텀 밑줄 설정
    fun underline(context: Context, color: Int, thickness: Int): TextStyle = apply {
        val pixelThickness = (thickness * context.resources.displayMetrics.density).toInt()
        attributes.add(CustomUnderlineSpan(color, pixelThickness.toFloat()))
    }

    // 폰트 설정
    fun font(context: Context, fontResId: Int): TextStyle = apply {
        ResourcesCompat.getFont(context, fontResId)?.let {
            attributes.add(CustomTypefaceSpan(Typeface.create(it, Typeface.NORMAL)))
        }
    }

    // 텍스트 스타일 설정
    fun setStyle(style: Int): TextStyle = apply {
        attributes.add(StyleSpan(style))
    }

    // 링크 설정
    fun link(view: TextView, url: String): TextStyle = apply {
        setupTextViewForClick(view)
        val formattedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
            "https://$url"
        } else {
            url
        }
        attributes.add(object : ClickableSpan() {
            override fun onClick(widget: View) {
                view.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(formattedUrl)))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ds.linkColor
            }
        })
    }

    // 정렬 설정
    fun align(alignment: AlignmentSpan.Standard): TextStyle = apply {
        alignSpan = alignment
        attributes.add(alignment)
    }

    // 글자 간격 설정
    fun letterSpacing(spacing: Float): TextStyle = apply {
        attributes.add(CustomLetterSpacingSpan(spacing))
    }

    // 클릭 이벤트 설정
    fun setClickListener(view: TextView, onClickListener: View.OnClickListener): TextStyle = apply {
        setupTextViewForClick(view)
        attributes.add(object : ClickableSpan() {
            override fun onClick(widget: View) {
                onClickListener.onClick(view)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
            }
        })
    }

    private fun setupTextViewForClick(textView: TextView) {
        textView.apply {
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }

    companion object {
        val ALIGN_CENTER = AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER)
        val ALIGN_RIGHT = AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE)
        val ALIGN_LEFT = AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL)

        fun builder(): TextStyle = TextStyle()
    }
}
