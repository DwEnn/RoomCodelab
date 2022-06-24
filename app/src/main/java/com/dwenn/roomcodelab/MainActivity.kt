package com.dwenn.roomcodelab

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dwenn.roomcodelab.data.Word
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    private val getNewWord =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                it.data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { word ->
                    val newWord = Word(word)
                    wordViewModel.insert(newWord)
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        with(recyclerView) {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        wordViewModel.allWords.observe(this) { list ->
            // Update the cached copy of the words in the adapter
            adapter.submitList(list)
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            getNewWord.launch(Intent(this, NewWordActivity::class.java))
        }
    }
}
