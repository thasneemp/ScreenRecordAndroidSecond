package test.launcher.mummu.androidscreenrecordsecond;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import test.launcher.mummu.androidscreenrecordsecond.camerautils.CameraHeadService;
import test.launcher.mummu.androidscreenrecordsecond.camerautils.WaterMarkHeadService;
import test.launcher.mummu.androidscreenrecordsecond.services.ScreenRecorderService;

/**
 * Created by muhammed on 7/20/2016.
 */
public class NotificationBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(ScreenRecorderService.ACTION_STOP)) {
            final Intent stopIntent = new Intent(context, ScreenRecorderService.class);
            stopIntent.setAction(ScreenRecorderService.ACTION_STOP);
            context.startService(stopIntent);
            context.stopService(new Intent(context, WaterMarkHeadService.class));
            context.stopService(new Intent(context, CameraHeadService.class));
            createUpdatedNotification(context, ScreenRecorderService.ACTION_STOP);


        } else if (intent.getAction().equalsIgnoreCase(ScreenRecorderService.ACTION_RESUME)) {
            final Intent resumeIntent = new Intent(context, ScreenRecorderService.class);
            resumeIntent.setAction(ScreenRecorderService.ACTION_RESUME);
            context.startService(resumeIntent);
            createUpdatedNotification(context, ScreenRecorderService.ACTION_RESUME);


        } else if (intent.getAction().equalsIgnoreCase(ScreenRecorderService.ACTION_PAUSE)) {
            final Intent pauseIntent = new Intent(context, ScreenRecorderService.class);
            pauseIntent.setAction(ScreenRecorderService.ACTION_PAUSE);
            context.startService(pauseIntent);

            createUpdatedNotification(context, ScreenRecorderService.ACTION_PAUSE);
        }

    }

    private void createUpdatedNotification(Context context, String actionPause) {
        String message;
        String button;
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.cancel(MainActivity.NOTIFICATION_ID);
        if (actionPause.equalsIgnoreCase(ScreenRecorderService.ACTION_STOP)) {

            return;
        }

        // Sets up the Snooze and Dismiss action buttons that will appear in the
        // big view of the notification.
        Intent dismissIntent = new Intent(context, NotificationBroadCastReceiver.class);
        dismissIntent.setAction(ScreenRecorderService.ACTION_STOP);
        PendingIntent stopPendingIntent = PendingIntent.getBroadcast(context, 0, dismissIntent, 0);

        PendingIntent pausePendingIntent;
        if (actionPause.equalsIgnoreCase(ScreenRecorderService.ACTION_PAUSE)) {
            Intent snoozeIntent = new Intent(context, NotificationBroadCastReceiver.class);
            snoozeIntent.setAction(ScreenRecorderService.ACTION_RESUME);
            pausePendingIntent = PendingIntent.getBroadcast(context, 0, snoozeIntent, 0);
            message = "Recording paused";
            button = "Resume";
        } else {
            Intent snoozeIntent = new Intent(context, NotificationBroadCastReceiver.class);
            snoozeIntent.setAction(ScreenRecorderService.ACTION_PAUSE);
            pausePendingIntent = PendingIntent.getBroadcast(context, 0, snoozeIntent, 0);
            message = "Recording resumed";
            button = "Pause";
        }



        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_camera_black_dp)
                        .setContentTitle(message)
                        .setContentText("Your screen is now recording").setOngoing(true)
                        .setDefaults(Notification.DEFAULT_ALL) // requires VIBRATE permission

                        .setStyle(new NotificationCompat.BigTextStyle())
                        .addAction(R.drawable.ic_action_pause, button, pausePendingIntent)
                        .addAction(R.drawable.ic_action_stop, "Stop", stopPendingIntent);


// Builds the notification and issues it.
        mNotifyMgr.notify(MainActivity.NOTIFICATION_ID, builder.build());


    }
}
