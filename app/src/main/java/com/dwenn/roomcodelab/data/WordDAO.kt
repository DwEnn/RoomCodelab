package com.dwenn.roomcodelab.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface WordDAO {
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): List<Word>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}
