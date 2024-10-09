package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Home_Activity
import com.example.myapplication.R

class MainC : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main3)

        findViewById<ImageButton>(R.id.imageButton14).setOnClickListener {
            val intent = Intent(this, Home_Activity::class.java)
            startActivity(intent)
        }
        findViewById<ImageButton>(R.id.imageButton11).setOnClickListener {
            val intent = Intent(this, MainB::class.java)
            startActivity(intent)
        }
    }
}