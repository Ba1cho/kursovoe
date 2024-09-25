package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
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

class Home_Activity : AppCompatActivity() {

    private lateinit var db: TrackerDatabase
    private lateinit var progressTextView: TextView
    private lateinit var currentTV: TextView
    private lateinit var list: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressTextView = findViewById(R.id.textView15)
        currentTV = findViewById(R.id.textView8)
        list = findViewById(R.id.textView4)

        db = TrackerDatabase.getInstance(this)

        findViewById<ImageButton>(R.id.imageButton4).setOnClickListener {
            val intent = Intent(this, Drink_Activity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.imageButton7).setOnClickListener {
            val intent = Intent(this, Analisis_Activity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.imageButton6).setOnClickListener {
            val intent = Intent(this, Set_Activity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.imageButton8).setOnClickListener {
            lifecycleScope.launch {
                val allSummaries = withContext(Dispatchers.IO) {
                    db.trackerDao().getAllSummaries()
                }
                displaySummaries(allSummaries)
            }
        }

        val sdf = SimpleDateFormat("'Date\n'dd-MM-yyyy ")
        val currentDateAndTime = sdf.format(Date())

        findViewById<ImageButton>(R.id.imageView24).setOnClickListener {
            lifecycleScope.launch {
                val progress = withContext(Dispatchers.IO) {
                    val currentProgress = db.trackerDao().getProgressByDate(currentDateAndTime)
                    db.trackerDao().updateProgress(currentDateAndTime, currentProgress + 1)
                    currentProgress + 1
                }
                progressTextView.text = progress.toString()
            }
        }

    }
    private fun displaySummaries(summaries: List<TrackerSummary>) {
        val listLayout = findViewById<LinearLayout>(R.id.list)
        listLayout.removeAllViews() // Удаляем все предыдущие представления

        for (summary in summaries) {
            val textView = TextView(this).apply {
                text = "Date: ${summary.date}, Progress: ${summary.progress}"
                textSize = 16f
                setPadding(8, 8, 8, 8)
            }
            listLayout.addView(textView)
        }
    }
}
