package com.george.mynotification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import static android.content.Context.NOTIFICATION_SERVICE;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String whichAction = intent.getAction();
        context.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

        if (whichAction.equals("Deny")) {
            NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            manager.cancel("cancel_notification",7);
        }else if (whichAction.equals("Accept")){
            NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            manager.cancel("cancel_notification",7);
            //context.startActivity(new Intent(context, LandingActivity.class));
            Intent in = new Intent(context,NotificationActivity.class);
            in.setAction("chacha");
            in.putExtra("message", "accept");
            context.startActivity(in);
        }else if (whichAction.equals("ViewProfile")){
            NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            manager.cancel("cancel_notification",7);
            Intent in = new Intent(context,NotificationActivity.class);
            in.putExtra("message", "viewProfile");
            context.startActivity(in);
        }
    }
}
