package onion.qj7xm4nhktrrqen7.vsvit.sinh.prhr.java.uradio;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import android.app.Notification;
import android.widget.RemoteViews;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.app.ActivityManager;
import java.util.List;
import android.content.ComponentName;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;
import 	java.lang.Thread;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;

// import android.support.annotation.NonNull;


public class PlayService extends Service  implements OnPreparedListener, OnCompletionListener, OnErrorListener, OnAudioFocusChangeListener
{
    // public static int key = 0;
///

    //String value0;
    //int counter;
    public String value;
///headset
    private MusicIntentReceiver myReceiver;
    private boolean headsetConnected = false;
    //OnAudioFocusChangeListener mOnAudioFocusChangeListener;
///
    //private static String value;
    //private static MediaPlayer mPlayer = null;
///check pause
    boolean isStoped = false;
    boolean isDucked = false;
    int c_currentVolume;

    AudioManager audioManager;


    public static final String RADIO_PREFERENCES = "radiopref_service";
    MediaPlayer mPlayer = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }



    @Override
    public void onCreate()
    {

///phonefocus

///
        super.onCreate();
        // startForeground(R.string.app_name, new Notification());



///audiofocus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //mOnAudioFocusChangeListener = new OnAudioFocusChangeListener();
///headset
        myReceiver = new MusicIntentReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(myReceiver, filter);
///

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
///
        value = "http://ua.uar.net:8000/duzhefm";

        //if (intent.getAction().equals("com.prgguru.example.Music"))
        //value0 = "http://ua.uar.net:8000/duzhefm";
        // String nameStation = "Виберіть станцію";

        if (intent != null && intent.getExtras() != null)
        {
            value = intent.getStringExtra("radioResource");
            String nameStation = intent.getStringExtra("nameStation");
            //value0 = value;
            // nameStation = intent.getStringExtra("nameStation");
///
            SharedPreferences mySharedPreferences = getApplicationContext().getSharedPreferences(RADIO_PREFERENCES, Activity.MODE_PRIVATE);
            Editor ed = mySharedPreferences.edit();
            ed.remove("linkStation");
            ed.commit();
            ed.putString("linkStation", value);
            ed.apply();

            ed.remove("nameStation");
            ed.commit();
            ed.putString("nameStation", nameStation);
            ed.commit();

            // Create PendingIntent
            Intent resultIntent = new Intent(this, UradioActivity.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,               PendingIntent.FLAG_UPDATE_CURRENT);

            Notification notification;
            NotificationCompat.Builder bBuilder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ura)
            .setContentTitle("УРадіо")
            .setPriority(Notification.PRIORITY_MAX)
            .setContentText(nameStation).setOngoing(true)
            .setContentIntent(resultPendingIntent);
            notification = bBuilder.build();
            notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
            notification.flags |= Notification.FLAG_NO_CLEAR;
            notification.flags |= Notification.FLAG_ONGOING_EVENT;
            startForeground(R.string.app_name, notification);  //54312 is the notification id
///



        }
        else
        {
            SharedPreferences mySharedPreferences = getApplicationContext().getSharedPreferences(RADIO_PREFERENCES, Activity.MODE_PRIVATE);
            String linkStation = mySharedPreferences.getString("linkStation", "http://online.1043.com.ua:8000/1043fm");
            value = linkStation;

            String nnameStation = mySharedPreferences.getString("nameStation", "всвіт українського радіо");
            mySharedPreferences = null;
            //зупинка сервісу, якщо не вдалос оновити станцію
            if (nnameStation.equals("всвіт українського радіо")) stopSelf();
            // Create PendingIntent
            Intent resultIntent = new Intent(this, UradioActivity.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,               PendingIntent.FLAG_UPDATE_CURRENT);

            Notification notification;
            NotificationCompat.Builder bBuilder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ura)
            .setContentTitle("УРадіо")
            .setPriority(Notification.PRIORITY_MAX)
            .setContentText(nnameStation).setOngoing(true)
            .setContentIntent(resultPendingIntent);
            notification = bBuilder.build();
            notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
            notification.flags |= Notification.FLAG_NO_CLEAR;
            notification.flags |= Notification.FLAG_ONGOING_EVENT;
            startForeground(R.string.app_name, notification);  //54312 is the notification id

        }
///
///startplay

        if(mPlayer != null)
        {
            try
            {
                // mPlayer.stop();
                mPlayer.release();
            }
            finally
            {
                mPlayer = null;
            }
        }

        mPlayer = new MediaPlayer();


        mPlayer.setLooping(false);

        try
        {
            mPlayer.setDataSource(value);

        }
        catch (IllegalArgumentException e)
        {
            Toast.makeText(this, "Відсутній зв’язок із сервером", Toast.LENGTH_LONG).show();
        }
        catch (SecurityException e)
        {
            Toast.makeText(this, "Відсутній зв’язок із сервером", Toast.LENGTH_LONG).show();
        }
        catch (IllegalStateException e)
        {
            Toast.makeText(this, "Відсутній зв’язок із сервером", Toast.LENGTH_LONG).show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);



        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnErrorListener(this);




        try
        {
            if (reqAudioFocus())
            {
                mPlayer.prepareAsync();
            }
        }
        catch (IllegalStateException e)
        {

        }

//audiofocus

///endplay



///
        return super.onStartCommand(intent, flags, startId);
        // return START_NOT_STICKY; // Means we started the service, but don't want it to
        // restart in case it's killed.
    }

    @Override
    public void onDestroy()
    {

///phonefocus

        audioManager.abandonAudioFocus(this);
/// headset
        unregisterReceiver(myReceiver);
///

        if(mPlayer != null)
        {
            try
            {
                //mPlayer.stop();
                mPlayer.release();
            }
            finally
            {
                mPlayer = null;
            }
        }
        super.onDestroy();
    }


    @Override
    public void onPrepared(MediaPlayer mp)
    {
        mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {
        //mp.release();
        mp.reset();
        while(true)
        {
            try
            {
                mp.setDataSource(value);
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp.prepare();
                mp.start();
                break;
            }
            catch (Exception eek)
            {
                try
                {
                    Thread.sleep(5000);
                }
                catch (InterruptedException eir) {}
            }

        }

    };


    @Override
    public boolean onError(MediaPlayer mp, int what, int extra)
    {
        //mp.release();
        mp.start();
        return true;

    }
///audiofocus
    private boolean reqAudioFocus()
    {
        boolean gotFocus = false;
        int audioFocus = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC,
                         AudioManager.AUDIOFOCUS_GAIN);
        if (audioFocus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
        {
            gotFocus = true;
        }
        else
        {
            gotFocus = false;
        }
        return gotFocus;
    }
///audiofocus   change
    @Override
    public void onAudioFocusChange(int focusChange)
    {
        switch (focusChange)
        {
        case AudioManager.AUDIOFOCUS_GAIN:
            try
            {
                if (isDucked)
                {
                    isDucked = false;
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, c_currentVolume, 0);
                }
                else if (isStoped)
                {
                    isStoped = false;
                    mPlayer.reset();
                    mPlayer.setDataSource(value);
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mPlayer.prepare();
                    mPlayer.start();
                }
                else mPlayer.start();


            }
            catch (Exception eaf) {}
            break;
        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
            // Resume your media player here
            c_currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            float percent_ = 0.5f;
            int seventyVolume_ = (int) (c_currentVolume*percent_);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume_, 0);
            isDucked = true;
            break;
        case AudioManager.AUDIOFOCUS_LOSS:
            //mPlayer.release();
            mPlayer.stop();
            isStoped = true;
            break;
        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            mPlayer.pause();// Pause your media player here
            //isPaused = true;

            break;
        }

    }



///headset
    private class MusicIntentReceiver extends BroadcastReceiver
    {
        @Override public void onReceive(Context context, Intent intent)
        {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG))
            {
                if (headsetConnected && intent.getIntExtra("state", -1) == 0)
                {
                    headsetConnected = false;
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);

                }
                else if (!headsetConnected && intent.getIntExtra("state", -1) == 1)
                {
                    headsetConnected = true;
                    int ccurrentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    int mmaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                    float percent = 0.5f;
                    int seventyVolume = (int) (mmaxVolume*percent);
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, seventyVolume, 0);
                }
            }
        }
    }

}




