package com.example.shishusnehhealthcare

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class VaccineReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        NotificationHelper(context).showNotification()
    }
}