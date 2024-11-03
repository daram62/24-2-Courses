package com.example.eduskkucspa1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

// 데이터 클래스
data class Word(val letters: List<LetterStatus>)
data class LetterStatus(val letter: Char, val status: Status)
enum class Status {
    GREEN,
    YELLOW,
    GRAY
}

class WordListAdapter(context: Context, private val words: MutableList<Word>) :
    ArrayAdapter<Word>(context, 0, words) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val word = words[position]
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_word, parent, false)

        val letter1 = view.findViewById<TextView>(R.id.textViewLetter1)
        val letter2 = view.findViewById<TextView>(R.id.textViewLetter2)
        val letter3 = view.findViewById<TextView>(R.id.textViewLetter3)
        val letter4 = view.findViewById<TextView>(R.id.textViewLetter4)
        val letter5 = view.findViewById<TextView>(R.id.textViewLetter5)

        val letters = listOf(letter1, letter2, letter3, letter4, letter5)

        for (i in 0 until 5) {
            val letterStatus = word.letters[i]
            letters[i].text = letterStatus.letter.toString().uppercase()
            when (letterStatus.status) {
                Status.GREEN -> {
                    letters[i].setBackgroundColor(Color.parseColor("#FF99F691")) // Green 배경
                    letters[i].setTextColor(Color.parseColor("#000000")) // 검은색 텍스트
                }
                Status.YELLOW -> {
                    letters[i].setBackgroundColor(Color.parseColor("#FFFFE46F")) // Yellow 배경
                    letters[i].setTextColor(Color.parseColor("#000000")) // 검은색 텍스트
                }
                Status.GRAY -> {
                    letters[i].setBackgroundColor(Color.parseColor("#FF787C7E")) // Gray 배경
                    letters[i].setTextColor(Color.parseColor("#FFFFFF")) // 흰색 텍스트
                }
            }
        }

        return view
    }

    fun addWord(word: Word) {
        words.add(word)
        notifyDataSetChanged()
    }
}