package com.example.advancedkotlinassignment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun Context.showNotification(channelID: String = "", id: Int = 0) {

    val mNotificationBuilder = NotificationCompat.Builder(this, channelID)
        .setSmallIcon(android.R.drawable.ic_dialog_alert)
        .setContentTitle("Notification")
        .setContentText("This is my notification")

    val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "High Basic Notification Channel"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val description = "This is a basic notification"

        val channel = NotificationChannel(channelID, name, importance)
        channel.setDescription(description)

        mNotificationManager.createNotificationChannel(channel)

    }

    mNotificationManager.notify(id, mNotificationBuilder.build())
}

fun ImageView.loadImageFromUrl(url: String) {

    Glide.with(this)
        .load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.i("BIGBRAIN", "$e")

                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

        })
        .into(this)
}


//Courtesy of Vivek
fun RequestBuilder<Drawable>.onSuccess(onSuccess: () -> Unit): RequestBuilder<Drawable> {

    this.addListener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }
        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onSuccess.invoke()
            return false
        }
    })
    return this
}

fun RequestBuilder<Drawable>.onFailure(onFailure: () -> Unit): RequestBuilder<Drawable> {

    this.addListener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onFailure.invoke()
            return false
        }
        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }
    })
    return this
}



