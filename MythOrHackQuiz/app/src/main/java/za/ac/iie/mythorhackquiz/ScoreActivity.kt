package za.ac.iie.mythorhackquiz

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        val reviewBtn = findViewById<Button>(R.id.reviewBtn)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")
        val explanations = intent.getStringArrayExtra("explanations")

        tvScore.text = "Score: $score / $total"

        tvMessage.text = if (score >= total * 0.7) {
            "Master Hacker!"
        } else {
            "Stay Safe Online!"
        }

        reviewBtn.setOnClickListener {
            val intent = Intent(this, review_activity::class.java)
            intent.putExtra("questions", questions)
            intent.putExtra("answers", answers)
            intent.putExtra("explanations", explanations)
            startActivity(intent)
        }
    }
}