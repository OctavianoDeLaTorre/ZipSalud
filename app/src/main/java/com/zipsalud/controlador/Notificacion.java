/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/

/******************************************************************************
 * created by Octaviano De La Torre Enriquez 2018                             *
 ******************************************************************************/

package com.zipsalud.controlador;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class Notificacion {

    private NotificationCompat.Builder mBuilder;
    private NotificationManager notificationManager;
    private Context context;

    public Notificacion(Context context) {
        this.context = context;
        notificationManager = (NotificationManager) (NotificationManager) context.getApplicationContext().getSystemService(context.NOTIFICATION_SERVICE);
    }

    public void mostrar(Intent i, int icono, String titulo, String mensaje ){
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);

        mBuilder =new NotificationCompat.Builder(context.getApplicationContext())
                .setContentIntent(pendingIntent)
                .setSmallIcon(icono)
                .setContentTitle(titulo)
                .setContentText(mensaje)
                .setVibrate(new long[] {100, 250, 100, 500})
                .setAutoCancel(true);

        notificationManager.notify(1, mBuilder.build());
    }
}
