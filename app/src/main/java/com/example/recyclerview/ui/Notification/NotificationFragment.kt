package com.example.recyclerview.ui.Notification

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerview.R
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class NotificationFragment: Fragment() {

    val day: String = SimpleDateFormat("dd.MM.y").format(Calendar.getInstance().time)
    val time: String = SimpleDateFormat("hh:mm").format(Calendar.getInstance().time)


    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.notification_main, container, false)

        val btnToast: Button = root.findViewById(R.id.btn_toast)
        btnToast.setOnClickListener {
            Toast.makeText(context, "Сегодня $day, время $time \nНажата кнопка Toast", Toast.LENGTH_SHORT).show()
        }

        val btnDialog: Button = root.findViewById(R.id.btn_dialog)
        btnDialog.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Сегодня $day, время $time \nНажата кнопка AlertDialog").show()
        }

        val btnNotification: Button = root.findViewById(R.id.btn_notification)
        btnNotification.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                createNotificationAndroid8()
            else
                createNotificationAndroid7()

        }

        val btnSnackbar: Button = root.findViewById(R.id.btn_snackbar)
        btnSnackbar.setOnClickListener {
            val snackbar = Snackbar.make(it, "Сегодня $day, время $time \nНажата кнопка Snackbar", Snackbar.LENGTH_SHORT).show()
        }

        return root
    }


    private fun createNotificationAndroid7() {
        val NOTIFICATION_ID: Int = 101

        val notificationintent = Intent(context, NotificationFragment::class.java)
        val currentIntent = PendingIntent.getActivity(context, 0, notificationintent, PendingIntent.FLAG_CANCEL_CURRENT)

        val builder = NotificationCompat.Builder(context)
        builder.setContentIntent(currentIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Уведомление")
                .setContentText("Сегодня $day, время $time \nНажата кнопка Notification")

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationAndroid8() {
        val NOTIFICATION_ID: Int = 101
        val CHANNEL_ID = "TextChannel D"
        val CHANNEL_NAME = "TextChannelName"

        val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.description = "Test"

        val notificationintent = Intent(context, NotificationFragment::class.java)
        val currentIntent = PendingIntent.getActivity(context, 0, notificationintent, PendingIntent.FLAG_CANCEL_CURRENT)

        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
        builder.setContentIntent(currentIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Уведомление")
                .setContentText("Сегодня $day, время $time \nНажата кнопка Notification")

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

}





















