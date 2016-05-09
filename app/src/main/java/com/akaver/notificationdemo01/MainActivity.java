package com.akaver.notificationdemo01;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;
    private BroadcastReceiver mBroadcastReceiver;
    private TextView textViewBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        textViewBroadcast = (TextView) findViewById(R.id.textViewBroadcast);

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                textViewBroadcast.setText((new Date()).toString());
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("notification-broadcast");
        registerReceiver(mBroadcastReceiver, intentFilter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void buttonSimpleNotification(View view) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_location_on_white_24dp)
                        .setContentTitle("Title goes here")
                        .setContentText("Content text is this!!! " + (new Date()));

        mNotificationManager.notify(0, mBuilder.build());
    }

    public void buttonSimpleNotificationWithCancel(View view) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_location_on_white_24dp)
                        .setContentTitle("Title goes here")
                        .setContentText("Content text is this!!! " + (new Date()))
                        .setAutoCancel(true)
                        .setColor(0x00800000)
                        .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(),0));

        mNotificationManager.notify(1, mBuilder.build());
    }


    public void buttonSpecial(View view){
        Intent intent = new Intent(this, SpecialActivity.class);
        startActivity(intent);
    }

    public void buttonRegular(View view) {
        Intent intent = new Intent(this, RegularActivity.class);
        startActivity(intent);
    }


    public void buttonSpecialNotification(View view){
        Intent mNotifyIntent = new Intent(this, SpecialActivity.class);
        mNotifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent mNotifyPendingIntent = PendingIntent.getActivity(
                this,
                0,
                mNotifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_location_on_white_24dp)
                        .setContentTitle("Title!")
                        .setContentText("Special! " + (new Date()))
                        .setAutoCancel(true)
                        .setColor(0x00008000)
                        .setContentIntent(mNotifyPendingIntent);

        mNotificationManager.notify(2, mBuilder.build());
    }

    public void buttonRegularNotification(View view){
        Intent mNotifyIntent = new Intent(this, RegularActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addParentStack(RegularActivity.class);

        stackBuilder.addNextIntent(mNotifyIntent);

        PendingIntent mNotifyPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_location_on_white_24dp)
                        .setContentTitle("Title!")
                        .setContentText("Regular! " + (new Date()))
                        .setAutoCancel(true)
                        .setColor(0x00000080)
                        .setContentIntent(mNotifyPendingIntent);

        mNotificationManager.notify(3, mBuilder.build());

    }


    public void buttonNotificationAction(View view){
        Intent mNotifyIntent = new Intent(this, RegularActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addParentStack(RegularActivity.class);

        stackBuilder.addNextIntent(mNotifyIntent);

        PendingIntent mNotifyPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_location_on_white_24dp)
                        .setContentTitle("Title!")
                        .setContentText("Actions! " + (new Date()))
                        .setAutoCancel(true)
                        .setColor(0x00000080)
                        .addAction(R.drawable.ic_location_on_white_24dp_small,"Action",mNotifyPendingIntent)
                        .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(),0));

        mNotificationManager.notify(3, mBuilder.build());

    }


    public void buttonNotificationBroadcast(View view){
        Intent mNotifyIntent = new Intent("notification-broadcast");


        PendingIntent mNotifyPendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                mNotifyIntent,
                0
        );

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_location_on_white_24dp)
                        .setContentTitle("Title!")
                        .setContentText("Special with button! " + (new Date()))
                        .setAutoCancel(true)
                        .setColor(0x00008000)
                        .addAction(R.drawable.ic_location_on_white_24dp_small,"AddWP",mNotifyPendingIntent)
                        .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(),0));

        mNotificationManager.notify(2, mBuilder.build());
    }

}
