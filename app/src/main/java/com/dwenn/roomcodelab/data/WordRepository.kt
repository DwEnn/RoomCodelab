package com.dwenn.roomcodelab.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor.
// Pass in the DAO instead of the whole database, because you only needs access to the DAO
class WordRepository(
    private val wordDAO: WordDAO
) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDAO.getAlphabetizedWords()

    // 기본적으로 Room 에서는 메인 스레드에서 일시 중단 쿼리를 실행하므로
    // 메인 스레드에서 장시간 실행되는 데이터베이스 작업을 수행하지 않도록 하기 위해
    // 다른 작업을 구현할 필요가 없습니다.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDAO.insert(word)
    }
}
