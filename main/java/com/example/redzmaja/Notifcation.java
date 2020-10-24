package com.example.redzmaja;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notifcation extends Application {
    public static final String KANAL_ID = "Rezervisano";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();

    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel kanal1 = new NotificationChannel(
                    KANAL_ID,
                    "Kanal1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            kanal1.setDescription("Резервација");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(kanal1);
        }
    }
}
