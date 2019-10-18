package com.example.advancedkotlinassignment

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_show_notification.setOnClickListener {
            this.showNotification()
            Glide.with(this)
                .load("https://i.imgur.com/R390EId.jpg")
                .onSuccess {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                .onFailure {
                    Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
                }
                .into(iv)
        }
    }

}
