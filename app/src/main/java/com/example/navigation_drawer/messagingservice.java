package com.example.navigation_drawer;

import android.app.PendingIntent;
import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class messagingservice extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        shownot(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

    }
    public void shownot(String title,String message)
    {
        Intent intent  = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"MyNotifications")
                .setContentTitle(title).setSmallIcon(R.drawable.ic_launcher_background).setAutoCancel(true).setContentText(message).setContentIntent(pendingIntent);
        NotificationManagerCompat manager= NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());

    }
}
