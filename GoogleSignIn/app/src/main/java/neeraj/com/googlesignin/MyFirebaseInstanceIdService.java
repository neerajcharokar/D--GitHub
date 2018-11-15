package neeraj.com.googlesignin;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService
{
    public static final String REG_TOKEN="REG_TOKEN";
    public static final String CHANNEL_ID="CHANNEL_ID";
    //@Override
    /*public void onNewToken(String s)
    {
            String recent_token=FirebaseInstanceId.getInstance().getToken();
            Log.e(REG_TOKEN,recent_token);
    }*/


    /*@Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        Intent intent=new Intent(this,Welcome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle("FCM Notification");
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());
    }*/

    @Override
    public void onTokenRefresh() {
        String recent_token=FirebaseInstanceId.getInstance().getToken();
        Log.e("Reg_Token",recent_token);
    }
}
