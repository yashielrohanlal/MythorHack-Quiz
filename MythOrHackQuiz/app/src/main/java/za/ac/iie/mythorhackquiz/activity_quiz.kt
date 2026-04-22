package za.ac.iie.mythorhackquiz

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class activity_quiz : AppCompatActivity() {

    // ✅ Data (must be OUTSIDE onCreate)
    private val questions = arrayOf(
        "Putting your phone in rice fixes water damage",
        "Drinking water helps your concentration",
        "Charging your phone overnight destroys your battery",
        "Using dark mode saves battery",
        "Cracking your knuckles causes arthritis"
    )

    private val answers = arrayOf(
        false, true, false, true, false
    )

    private val explanations = arrayOf(
        "Rice doesn't effectively fix water damage",
        "Hydration improves brain function",
        "Modern phones prevent overcharging and damage",
        "Dark mode saves battery on an OLED screen",
        "There is no scientific proof suggesting cracking your knuckles causes arthritis"
    )

    private var currentIndex = 0
    private var score = 0

    // ✅ UI variables
    private lateinit var tvQuest: TextView
    private lateinit var tvFeedback: TextView
    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var nextBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Link UI
        tvQuest = findViewById(R.id.tvQuest)
        tvFeedback = findViewById(R.id.tvFeedback)
        trueBtn = findViewById(R.id.trueBtn)
        falseBtn = findViewById(R.id.falseBtn)
        nextBtn = findViewById(R.id.nextBtn)

        loadQuestion()

        trueBtn.setOnClickListener { checkAnswer(true) }
        falseBtn.setOnClickListener { checkAnswer(false) }

        nextBtn.setOnClickListener {
            currentIndex++

            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                intent.putExtra("questions", questions)
                intent.putExtra("answers", answers)
                intent.putExtra("explanations", explanations)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadQuestion() {
        tvQuest.text = questions[currentIndex]
        tvFeedback.text = ""
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correct = answers[currentIndex]

        if (userAnswer == correct) {
            tvFeedback.text = "Correct! ${explanations[currentIndex]}"
            score++
        } else {
            tvFeedback.text = "Wrong! ${explanations[currentIndex]}"
        }
    }
}