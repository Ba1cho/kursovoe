package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date

class Set_Activity : AppCompatActivity() {

    private lateinit var db: TrackerDatabase
    private lateinit var progressTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_set)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sdf = SimpleDateFormat("'Date\n'dd-MM-yyyy ")
        val currentDateAndTime = sdf.format(Date())
        progressTextView = findViewById(R.id.textView17)
        db = TrackerDatabase.getInstance(this)

        findViewById<ImageButton>(R.id.imageView7).setOnClickListener {
            val goal = 5
            val date = currentDateAndTime.toString()

            lifecycleScope.launch(Dispatchers.IO) {
                db.trackerDao().insert(TrackerEntity(goal = goal, date = date))
                withContext(Dispatchers.Main) {
                    progressTextView.text = "Прогресс на $date: $goal стаканов"
                }
            }
        }
    }
}
