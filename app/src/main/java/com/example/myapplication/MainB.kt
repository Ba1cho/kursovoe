package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Home_Activity
import com.example.myapplication.R

class MainB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main2)

        findViewById<ImageButton>(R.id.imageButton13).setOnClickListener {
            val intent = Intent(this, MainC::class.java)
            startActivity(intent)
        }
        findViewById<ImageButton>(R.id.imageButton12).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}