package com.george.mynotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.george.mynotification.next.NextActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Random;

import static android.app.Notification.VISIBILITY_PRIVATE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNotification();
            }
        });
        nextBtn = findViewById(R.id.button);
        nextBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(NotificationManager notificationManager) {
        String channelId = "my_service_channelId";
        String channelName = "Notification";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setImportance(NotificationManager.IMPORTANCE_HIGH);
        channel.setLockscreenVisibility(VISIBILITY_PRIVATE);
        notificationManager.createNotificationChannel(channel);
        return channelId;
    }

    private void displayNotification(){

        int notificationId = 7;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? createNotificationChannel(notificationManager) : "";

        Intent landingIntent = new Intent(this, LandingActivity.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        landingIntent.putExtra("notification_id",notificationId);
        PendingIntent landingPendentIntent = PendingIntent.getActivity(this, 0,
                landingIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent acceptIntent = new Intent(this, MyBroadcastReceiver.class);
        acceptIntent.setAction("ViewProfile");
        acceptIntent.putExtra("notification_id",notificationId);
        PendingIntent acceptPendentIntent = PendingIntent.getBroadcast(this, 0,
                acceptIntent, PendingIntent.FLAG_ONE_SHOT);

        /*Intent denyIntent = new Intent(this, LandingActivity.class);
        denyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        denyIntent.putExtra("notification_id2", notificationId);
        PendingIntent denyPendentIntent = PendingIntent.getActivity(this, 0,
                denyIntent, PendingIntent.FLAG_ONE_SHOT);*/

        Intent snoozeIntent2 = new Intent(this, MyBroadcastReceiver.class);
        snoozeIntent2.setAction("Accept");
        snoozeIntent2.putExtra("notification_id", notificationId);
        PendingIntent snoozePendingIntent2 =
                PendingIntent.getBroadcast(this, 0, snoozeIntent2,
                        PendingIntent.FLAG_ONE_SHOT);

        Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);
        snoozeIntent.setAction("Deny");
        snoozeIntent.putExtra("notification_id", notificationId);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, snoozeIntent,
                        PendingIntent.FLAG_ONE_SHOT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Sample notification");
        builder.setContentText("This is a demo notification, it's a test notification");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);
        builder.setContentIntent(landingPendentIntent);
        builder.addAction(R.drawable.ic_launcher_foreground, "Accept", snoozePendingIntent2);
        builder.addAction(R.drawable.ic_launcher_foreground,"Deny", snoozePendingIntent);
        builder.addAction(R.drawable.ic_launcher_foreground,"View profile",acceptPendentIntent);
        notificationManager.notify("cancel_notification",notificationId, builder.build());
    }


    @Override
    public void onClick(View v) {
        if (v == nextBtn){
            startActivity(new Intent(this, NextActivity.class));
        }
    }
}
