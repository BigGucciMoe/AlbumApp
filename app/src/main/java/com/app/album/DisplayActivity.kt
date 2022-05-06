package com.app.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {

    lateinit var title : TextView
    lateinit var url : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val intent = intent

        val extra = intent.extras

        title = findViewById(R.id.title)
        url = findViewById(R.id.url)

        title.text = extra?.get("title").toString()
        url.text = extra?.get("url").toString()

    }
}