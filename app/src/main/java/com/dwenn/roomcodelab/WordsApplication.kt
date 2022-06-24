package com.dwenn.roomcodelab

import android.app.Application
import com.dwenn.roomcodelab.data.WordRepository
import com.dwenn.roomcodelab.data.WordRoomDatabase

class WordsApplication : Application() {
    val dataBase by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(dataBase.wordDao()) }
}
