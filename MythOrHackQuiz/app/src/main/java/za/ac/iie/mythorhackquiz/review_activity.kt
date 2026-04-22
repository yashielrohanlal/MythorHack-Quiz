package za.ac.iie.mythorhackquiz

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class review_activity : AppCompatActivity() {

    private lateinit var tvReview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        tvReview = findViewById(R.id.tvReview)

        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")
        val explanations = intent.getStringArrayExtra("explanations")

        val builder = StringBuilder()

        if (questions != null && answers != null && explanations != null) {
            for (i in questions.indices) {
                builder.append("Question ${i + 1}:\n")
                builder.append("${questions[i]}\n")
                builder.append("Answer: ${if (answers[i]) "Hack (True)" else "Myth (False)"}\n")
                builder.append("Explanation: ${explanations[i]}\n\n")
            }
        } else {
            builder.append("No review data available.")
        }

        tvReview.text = builder.toString()
    }
}