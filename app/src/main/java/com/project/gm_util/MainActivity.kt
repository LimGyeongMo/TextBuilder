package com.project.gm_util

import TextStyle
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.textbulider.TextBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val view: TextView = findViewById()

        TextBuilder.builder()
            .setDefaultAttribute(
                TextStyle.builder()
                    .textSize(16)
                    .textColor(color = R.color.black)
                    .align(TextStyle.ALIGN_CENTER)
            )
            .text("blah blah blah blah")
            .space()
            .text("blah blah blah blah",
                TextStyle.builder()
                    .textSize(22)
//                    .font(this, R.font.dddddd)
                    .setClickListener(view, onClickListener = {
                        Log.d("dsadas", "dsadsadas")
                    })
            )
            .newLine()
            .text("vvvvvv",
                TextStyle.builder()
                    .link(view, "naver.com")
            )
    }
}