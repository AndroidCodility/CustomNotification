package com.codility.customnotification.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.text.format.DateUtils
import android.widget.RemoteViews
import com.codility.customnotification.R
import com.codility.customnotification.activities.NotificationViewActivity

/**
 * Created by Govind on 3/1/2018.
 */
class CustomNotification {

    /*
    * Simple Notification Method
    * */
    fun showNotification(context: Context, title: String, message: String) {
        val intent = Intent(context, NotificationViewActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("msg", message)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        //Create Notification using NotificationCompat.Builder
        val builder = NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(context.getString(R.string.notification))
                .setContentText(context.getString(R.string.notification_msg))
                // Add an Action Button below Notification
                .addAction(R.drawable.abc_ic_arrow_drop_right_black_24dp, "Action Button", pendingIntent)
                // Set PendingIntent into Notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        // Create Notification Manager
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        // Build Notification with Notification Manager
        notificationManager.notify(0, builder.build())
    }

    /*
    * Custom Notification Method
    * */
    fun showCustomNotification(context: Context, title: String, message: String) {
        val expandedView = RemoteViews(context.packageName, R.layout.custom_expanded_notification)
        expandedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME))
        expandedView.setTextViewText(R.id.notification_title, title)
        expandedView.setTextViewText(R.id.notification_message, message)

        val collapsedView = RemoteViews(context.packageName, R.layout.custom_collapse_notification)
        collapsedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME))

        val intent = Intent(context, NotificationViewActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("msg", message)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context)
                // these are the three things a NotificationCompat.Builder object requires at a minimum
                .setSmallIcon(R.drawable.ic_whatshot)
                .setContentTitle("NOTIFICATION_TITLE")
                .setContentText("CONTENT_TEXT")
                // notification will be dismissed when tapped
                .setAutoCancel(true)
                // tapping notification will open NotificationViewActivity
                .setContentIntent(pendingIntent)
                // setting the custom collapsed and expanded views
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                // setting style to DecoratedCustomViewStyle() is necessary for custom views to display
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())

        // retrieves android.app.NotificationManager
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
        notificationManager.notify(0, builder.build())

    }
}