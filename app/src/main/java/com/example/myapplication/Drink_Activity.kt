package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import java.text.SimpleDateFormat
import java.util.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Drink_Activity : AppCompatActivity() {

    private lateinit var db: TrackerDatabase
    private lateinit var progressTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_drink)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = TrackerDatabase.getInstance(this)

        findViewById<ImageButton>(R.id.imageButtonHomes).setOnClickListener {
            val intent = Intent(this, Home_Activity::class.java)
            startActivity(intent)
        }

        progressTextView = findViewById(R.id.textView38)

        findViewById<ImageButton>(R.id.imageView23).setOnClickListener {
            val sdf = SimpleDateFormat("'Date\n'dd-MM-yyyy ")
            val currentDateAndTime = sdf.format(Date())
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    db.trackerDao().deleteWaterIntakeByDate(currentDateAndTime.toString())
                }
                withContext(Dispatchers.Main) {
                    progressTextView.text = "Deleted"
                }
            }
        }
    }
}