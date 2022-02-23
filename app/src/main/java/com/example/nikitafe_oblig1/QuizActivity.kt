package com.example.nikitafe_oblig1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nikitafe_oblig1.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private val questionList: MutableList<Question> = mutableListOf(Question("Miyazaki er skaperen av blockbuster hiten spirited away", true),
        Question("REOL er en musikkgruppe på 4 personer og ble oppløst i 2017", false),
        Question("stardew valley ble programmert i C#", true))
    private var currentQuestion = 0
    private var playerPoints = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvQuestions.text = questionList[0].question

        // når brukeren klikker på true
        binding.btTrue.setOnClickListener {
            checkCorrect(true)
        }
        // bruker klikker på false
        binding.btFalse.setOnClickListener {
            checkCorrect(false)
        }
        // bruker klikker på reset
        binding.btReset.setOnClickListener {
            currentQuestion = 0
            playerPoints = 0
            binding.tvPoints.text = "Poeng: $playerPoints"
            binding.tvQuestions.text = questionList[currentQuestion].question
            binding.btFalse.isClickable = true
            binding.btTrue.isClickable = true
            binding.btFalse.alpha = 1F
            binding.btTrue.alpha = 1F
            binding.root.setBackgroundResource(R.drawable.miyazaki)
        }
    }

    private fun checkCorrect(trueOrFalse: Boolean) {
        if (trueOrFalse == questionList[currentQuestion].trueOrFalse) {
            playerPoints++
            binding.root.setBackgroundResource(R.drawable.megumin)
        } else {
            binding.root.setBackgroundResource(R.drawable.aqua)
        }

        binding.tvPoints.text = "Poeng: $playerPoints"
        currentQuestion++
        if (currentQuestion >= questionList.size) {
            binding.btFalse.isClickable = false
            binding.btTrue.isClickable = false
            binding.btFalse.alpha = 0.25F
            binding.btTrue.alpha = 0.25F
            binding.tvPoints.text = "Poeng: $playerPoints/${questionList.size}"
            if (playerPoints < questionList.size) {
                binding.tvPoints.text = "${binding.tvPoints.text}\nIkke verst men du kan gjøre bedre"
            } else {
                binding.tvPoints.text = "${binding.tvPoints.text}\nDu fikk full pot!"
            }
        } else {
            binding.tvQuestions.text = questionList[currentQuestion].question
        }
    }
}