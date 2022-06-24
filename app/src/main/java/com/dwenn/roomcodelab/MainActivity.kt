package com.dwenn.roomcodelab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dwenn.roomcodelab.data.WordRoomDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WordRoomDatabase.getDatabase(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        with(recyclerView) {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}
