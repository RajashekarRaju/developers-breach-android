package com.developerbreach.developerbreach.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class AppFirebaseService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val title: String? = remoteMessage.notification?.title
        val body: String? = remoteMessage.notification?.body
        sendFirebaseNotification(title, body, applicationContext)
    }

    override fun onNewToken(token: String) {
        // Triggers when a new token is generated.
    }
}