package com.dwenn.roomcodelab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dwenn.roomcodelab.data.WordRoomDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WordRoomDatabase.getDatabase(this)
    }
}
