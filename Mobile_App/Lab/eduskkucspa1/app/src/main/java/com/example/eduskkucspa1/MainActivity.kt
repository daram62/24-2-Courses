package com.example.eduskkucspa1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val dictionary = mutableListOf<String>()
    private val grayLetters = mutableSetOf<Char>()
    private val yellowLetters = mutableSetOf<Char>()
    private val greenLetters = mutableSetOf<Char>()
    private lateinit var answerWord: String

    private var attemptCount: Int = 0

    private lateinit var wordListAdapter: WordListAdapter
    private lateinit var grayAdapter: LetterListAdapter
    private lateinit var yellowAdapter: LetterListAdapter
    private lateinit var greenAdapter: LetterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextWord: EditText = findViewById(R.id.editTextWord)
        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)
        val listViewWords: ListView = findViewById(R.id.listViewWords)
        val listViewGray: ListView = findViewById(R.id.listViewGray)
        val listViewYellow: ListView = findViewById(R.id.listViewYellow)
        val listViewGreen: ListView = findViewById(R.id.listViewGreen)

        wordListAdapter = WordListAdapter(this, mutableListOf())
        listViewWords.adapter = wordListAdapter

        grayAdapter = LetterListAdapter(this, mutableListOf())
        listViewGray.adapter = grayAdapter

        yellowAdapter = LetterListAdapter(this, mutableListOf())
        listViewYellow.adapter = yellowAdapter

        greenAdapter = LetterListAdapter(this, mutableListOf())
        listViewGreen.adapter = greenAdapter

        loadDictionary()

        selectRandomAnswer()

        showMinimumAttempts()

        buttonSubmit.setOnClickListener {
            val userInput = editTextWord.text.toString().uppercase()

            attemptCount++
            Log.d("Wordle", "Now try: $attemptCount")

            if (!dictionary.contains(userInput)) {
                Toast.makeText(this, "Word '$userInput' not in dictionary!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Guess(userInput)

            editTextWord.text.clear()
        }
    }

    private fun loadDictionary() {
        val inputStream = resources.openRawResource(R.raw.wordle_words)
        inputStream.bufferedReader().use { bufferedReader ->
            bufferedReader.forEachLine { line ->
                val word = line.trim()
                dictionary.add(word.uppercase())
            }
        }
    }

    private fun selectRandomAnswer() {
        answerWord = dictionary[Random.nextInt(dictionary.size)]
        Log.d("Wordle", "answer: $answerWord")
    }

    private fun Guess(guess: String) {
        val guessLetters = guess.toCharArray()
        val answerLetters = answerWord.toCharArray()
        val letterStatuses = MutableList(5) { Status.GRAY }
        val answerLetterCount = mutableMapOf<Char, Int>()

        for (c in answerLetters) {
            answerLetterCount[c] = answerLetterCount.getOrDefault(c, 0) + 1
        }

        for (i in 0..4) {
            if (guessLetters[i] == answerLetters[i]) {
                letterStatuses[i] = Status.GREEN
                answerLetterCount[guessLetters[i]] = answerLetterCount.getOrDefault(guessLetters[i], 0) - 1
                greenLetters.add(guessLetters[i])
                yellowLetters.remove(guessLetters[i])
                grayLetters.remove(guessLetters[i])
            }
        }

        for (i in 0..4) {
            if (letterStatuses[i] != Status.GREEN && answerLetters.contains(guessLetters[i]) && answerLetterCount.getOrDefault(guessLetters[i], 0) > 0) {
                letterStatuses[i] = Status.YELLOW
                answerLetterCount[guessLetters[i]] = answerLetterCount.getOrDefault(guessLetters[i], 0) - 1
                if (!yellowLetters.contains(guessLetters[i]) && !greenLetters.contains(guessLetters[i])) {
                    yellowLetters.add(guessLetters[i])
                }
            }
        }

        for (i in 0..4) {
            if (letterStatuses[i] == Status.GRAY) {
                grayLetters.add(guessLetters[i])
            }
        }

        val word = Word(guessLetters.mapIndexed { index, c ->
            LetterStatus(c, letterStatuses[index])
        })
        wordListAdapter.addWord(word)

        updateLetterLists()

        if (guess == answerWord) {
            Toast.makeText(this, "You got the answer!", Toast.LENGTH_SHORT).show()
            Log.d("Wordle", "Attemp counts = $attemptCount")
            updateMinimumAttempts(attemptCount, guess)
        }
    }

    private fun updateLetterLists() {
        grayAdapter.clear()
        yellowAdapter.clear()
        greenAdapter.clear()

        grayLetters.forEach { letter ->
            if (!yellowLetters.contains(letter) && !greenLetters.contains(letter)) {
                grayAdapter.add(LetterItem(letter, Status.GRAY))
            }
        }

        yellowLetters.forEach { letter ->
            if (!greenLetters.contains(letter)) {
                yellowAdapter.add(LetterItem(letter, Status.YELLOW))
            }
        }

        greenLetters.forEach { letter ->
            greenAdapter.add(LetterItem(letter, Status.GREEN))
        }

        grayAdapter.notifyDataSetChanged()
        yellowAdapter.notifyDataSetChanged()
        greenAdapter.notifyDataSetChanged()
    }

    private fun showMinimumAttempts() {
        val sharedPref = getSharedPreferences("WordlePrefs", Context.MODE_PRIVATE)
        val minAttempts = sharedPref.getInt("minAttempts", -1)
        val minWord = sharedPref.getString("minWord", null)

        Log.d("show_minattempts", "minAttempts: $minAttempts")
        Log.d("show_minword", "minWord: $minWord")

        if (minAttempts != -1 && !minWord.isNullOrEmpty()) {
            Toast.makeText(this, "The MIN attempts: $minWord - $minAttempts", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "This is the first attempt.", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateMinimumAttempts(currentAttempts: Int, word: String) {
        val sharedPref = getSharedPreferences("WordlePrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val minAttempts = sharedPref.getInt("minAttempts", -1)

        if (minAttempts == -1 || currentAttempts < minAttempts) {
            editor.putInt("minAttempts", currentAttempts)
            editor.putString("minWord", word)
            editor.apply()
            Toast.makeText(this, "New record! $word - $currentAttempts attempts", Toast.LENGTH_LONG).show()
            Log.d("updateMinimumAttempts", "Update minimum: $word - $currentAttempts")
        }
        else {
            Log.d("updateMinimumAttempts", "dont Update")
        }
    }
}