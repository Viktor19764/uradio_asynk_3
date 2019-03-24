package onion.qj7xm4nhktrrqen7.vsvit.sinh.prhr.java.uradio;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import android.widget.TextView;

import android.app.Service;

import android.os.IBinder;
import android.content.Intent;
import android.util.Log;

import android.widget.ImageButton;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.app.ActivityManager;
import android.content.Context;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;
import android.os.StrictMode;
import android.preference.PreferenceManager;

import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.util.DisplayMetrics;


public class UradioActivity extends Activity
{
    int widthDevice;
    private int count = 0;
    int buttonId;
    Button buttonFokused;
    ImageButton buttonInfo;
    //Button kontrol;
    Button buttonDuzhe;
    Button buttonZahPol;
    Button buttonUh;
    Button buttonMolode;
    Button buttonYaskrave;
    Button buttonNezalezhnist;
    Button buttonRep;
    Button buttonDzvony;
    Button buttonVezha;
    Button buttonRtrack;
    Button buttonKazky;
    Button buttonStop;
    SharedPreferences sPref;


    public static final String RADIO_PREFERENCES = "radiopref";


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // To keep this example simple, we allow network access
// in the user interface thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
        .permitAll().build();
        StrictMode.setThreadPolicy(policy);

//touch
DisplayMetrics displaymetrics = new DisplayMetrics();
getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
widthDevice = displaymetrics.widthPixels;
LinearLayout vv = (LinearLayout) this.findViewById(R.id.lyt_btns);
vv.setOnTouchListener(new OnTouchListener()
{
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if (event.getAction()==MotionEvent.ACTION_MOVE && (event.getX()*100/widthDevice < 10 || event.getX()*100/widthDevice > 90) && count < 1)
        {
            count++;
            Intent i = new Intent(UradioActivity.this, UradioActivity2.class);
            startActivity(i);
            return true;

        }
        else return false;


    }
});


//



        if (isMyServiceRunning(PlayService.class))
        {
            sPref = getApplicationContext().getSharedPreferences(RADIO_PREFERENCES, Activity.MODE_PRIVATE);
            String nameStation = sPref.getString("nameStation", "Виберіть станцію");
            final TextView textViewChange = (TextView) findViewById(R.id.current);
            textViewChange.setText(nameStation);
            if ((sPref.getInt("buttonId", 0) != 0) && (sPref.getInt("trigger", 0) == 1))
            {
                buttonId = sPref.getInt("buttonId", 0);
                buttonFokused = (Button) findViewById(buttonId);
                buttonFokused.setBackgroundResource(R.drawable.button_1_focused);
            }
        }
///info

        buttonInfo = (ImageButton) findViewById(R.id.info_button);
        buttonInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UradioActivity.this, About.class);
                startActivity(intent);
            }
        });
///

///two друга сторінкаstopService( new Intent(MusicAndroidActivity.this, Music.class));

  
        ImageButton buttonTwo = (ImageButton) findViewById(R.id.two);
        buttonTwo.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(UradioActivity.this, UradioActivity2.class);
                startActivity(i);

            }

        });

///

///1
        buttonDuzhe = (Button) findViewById(R.id.duzhe);
        buttonDuzhe.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonId = R.id.duzhe;
                Button buttonIdent = buttonDuzhe;
                String nameStation = "Дуже Радіо";
                String linkStation = "http://ua.uar.net:8000/duzhefm";
                buttonOperations (buttonId, buttonIdent, nameStation, linkStation, v);

            }

        });
///
///2
        buttonZahPol = (Button) findViewById(R.id.zahpol);
        buttonZahPol.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonId = R.id.zahpol;
                Button buttonIdent = buttonZahPol;
                String nameStation = "Західний Полюс";
                String linkStation = "http://online.1043.com.ua:8000/1043fm";
                buttonOperations (buttonId, buttonIdent, nameStation, linkStation, v);

            }
        });
///
///3
        buttonUh = (Button) findViewById(R.id.uh);
        buttonUh.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonId = R.id.uh;
                Button buttonIdent = buttonUh;
                String nameStation = "Ух Радіо";
                String linkStation = "http://online.uhradio.com.ua:8001/efir";
                buttonOperations (buttonId, buttonIdent, nameStation, linkStation, v);

            }
        });
///
///4
        buttonMolode = (Button) findViewById(R.id.molode);
        buttonMolode.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonId = R.id.molode;
                Button buttonIdent = buttonMolode;
                String nameStation = "Молоде Радіо";
                String linkStation = "http://molode.com.ua:8128/";
                buttonOperations (buttonId, buttonIdent, nameStation, linkStation, v);

            }
        });
///
///5
        buttonYaskrave = (Button) findViewById(R.id.yaskrave);
        buttonYaskrave.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonId = R.id.yaskrave;
                Button buttonIdent = buttonYaskrave;
                String nameStation = "Яскраве Радіо Вінниця";
                String linkStation = "http://yaskraveradio.fm:8000/vinnitsa-72.47FM128";
                buttonOperations (buttonId, buttonIdent, nameStation, linkStation, v);

            }
        });
///
///6
        buttonNezalezhnist = (Button) findViewById(R.id.nezalezhnist);
        buttonNezalezhnist.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonId = R.id.nezalezhnist;
                Button buttonIdent = buttonNezalezhnist;
                String nameStation = "Радіо Незалежність";
                //String linkStation = "http://82.207.109.117:8000/"; http://rad.radio-n.com:8000
                String linkStation = "http://rad.radio-n.com:8000";
                buttonOperations (buttonId, buttonIdent, nameStation, linkStation, v);

            }
        });
///
///7
        buttonRep = (Button) findViewById(R.id.rep);
        buttonRep.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonId = R.id.rep;
                Button buttonIdent = buttonRep;
                String nameStation = "Реп";
                String linkStation = "http://music.myradio.ua:8000/ukrainian-rap_news128.mp3";
                buttonOperations (buttonId, buttonIdent, nameStation, linkStation, v);

            }
        });
///
///8
        buttonDzvony = (Button) findViewById(R.id.dzvony);
        buttonDzvony.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonId = R.id.dzvony;
                Button buttonIdent = buttonDzvony;
                String nameStation = "Дзвони";
                String linkStation = "http://109.251.12.36:8000";
                buttonOperations (buttonId, buttonIdent, nameStation, linkStation, v);

            }
        });
///
///9
        buttonVezha = (Button) findViewById(R.id.vezha);
        buttonVezha.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonId = R.id.vezha;
                Button buttonIdent = buttonVezha;
                String nameStation = "Вежа";
                String linkStation = "http://62.122.207.161:8000/";
                buttonOperations (buttonId, buttonIdent, nameStation, linkStation, v);

            }
        });
///
///10

        buttonRtrack = (Button) findViewById(R.id.rtrack);
        buttonRtrack.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonId = R.id.rtrack;
                Button buttonIdent = buttonRtrack;
                String nameStation = "Радіо Трек";
                String linkStation = "http://online2.radiotrek.rv.ua:8000/MP3_128";
                buttonOperations (buttonId, buttonIdent, nameStation, linkStation, v);

            }
        });
///

///11

        buttonKazky = (Button) findViewById(R.id.kazky);
        buttonKazky.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonId = R.id.kazky;
                Button buttonIdent = buttonKazky;
                String nameStation = "Казки";
                String linkStation = "http://nrcu.gov.ua:8000/kazka-mp3";
                buttonOperations (buttonId, buttonIdent, nameStation, linkStation, v);

            }
        });
///


        // остановка службы

        buttonStop = (Button) findViewById(R.id.stop);
        buttonStop.setOnClickListener(new OnClickListener()
        {

            public void onClick(View v)
            {
                // TODO Auto-generated method stub

                stopService( new Intent(UradioActivity.this, PlayService.class));
                // зняття фону з кнопок
                if (buttonFokused != buttonStop && buttonFokused != null)
                {
                    buttonFokused.setBackgroundResource(R.drawable.button_1_background);
                    buttonFokused = null;
                }

                final TextView textViewToChange = (TextView) findViewById(R.id.current);
                textViewToChange.setText("Виберіть станцію.");
                SharedPreferences sPref = getPreferences(MODE_PRIVATE);
                Editor ed = sPref.edit();
                ed.putString("nameStation", "Виберіть станцію -");
                ed.commit();


            }
        });
    }

    public void onDestroy()
    {




        super.onDestroy();
        // TODO Auto-generated method st
        // stopService( new Intent(MusicAndroidActivity.this, Music.class));



    }
//

    private boolean isMyServiceRunning(Class<?> serviceClass)
    {
        ActivityManager manager = (ActivityManager) getSystemService(UradioActivity.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
        {
            if (serviceClass.getName().equals(service.service.getClassName()))
            {
                return true;
            }
        }
        return false;
    }
//
    private boolean isConnectedToSrvr(String llinkStation)
    {
        try
        {
            URL myUrl = new URL(llinkStation);
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
            connection.setConnectTimeout(3000);
            int code = connection.getResponseCode();

            connection.disconnect();
            myUrl = null;
            if(code == 200)
            {
                return true;
            }
            else
            {
                return false;
            }

        }
        catch (Exception e)
        {
            // Handle your exceptions
            return false;
        }


    }
//

    private void buttonOperations (int buttonId, Button buttonIdent, String nameStation, String linkStation, View v)

    {

        boolean isConnectedToServer;
        isConnectedToServer = isConnectedToSrvr(linkStation);


        if ((buttonFokused != buttonIdent) && isConnectedToServer == true)
        {

            if (isMyServiceRunning(PlayService.class))
            {
                stopService( new Intent(UradioActivity.this, PlayService.class));
            }

            Intent intent = new Intent(UradioActivity.this, PlayService.class);
            intent.putExtra("radioResource", linkStation);
            intent.putExtra("nameStation", nameStation);
            // intent.putExtra("nameStation", nameStation);
            startService(intent);
            Toast.makeText(this, "Хвилинку ...", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Станцію підключено!", Toast.LENGTH_LONG).show();
            //запуск фейксервісу  ForegroundService
            //Intent intentF = new Intent(UradioActivity.this, ForegroundService.class);

            // startService(intentF);
            //stopService( new Intent(UradioActivity.this, ForegroundService.class));
            //
            final TextView textViewToChange = (TextView) findViewById(R.id.current);
            textViewToChange.setText(nameStation);
            // для виявлення роботи сервісу і підставлення назви станції
            if (buttonFokused != buttonIdent && buttonFokused != null)
            {
                buttonFokused.setBackgroundResource(R.drawable.button_1_background);
            }
            v.setBackgroundResource(R.drawable.button_1_focused);
            buttonFokused = buttonIdent;
            //запис назви станції у файл загальних налаштувань
            SharedPreferences sPref = getApplicationContext().getSharedPreferences(RADIO_PREFERENCES, Activity.MODE_PRIVATE);
            Editor ed = sPref.edit();
            ed.remove("nameStation");
            ed.commit();
            ed.remove("buttonId");
            ed.commit();
            ed.remove("trigger");
            ed.commit();
            // Editor ed = sPref.edit();
            ed.putString("nameStation", nameStation);
            ed.commit();
            ed.putInt("buttonId", buttonId);
            ed.commit();
            ed.putInt("trigger", 1);
            ed.commit();
        }
        else if (isConnectedToServer == false)
        {
            Toast.makeText(this, "Відсутнє з’єднання з сервером", Toast.LENGTH_LONG).show();
            //stopService( new Intent(UradioActivity.this, PlayService.class));
            v.setBackgroundResource(R.drawable.button_1_selected);
            //buttonFokused = null;
            
        }
        else
        {
            Toast.makeText(this, "Все добре! Іде завантаження ...", Toast.LENGTH_LONG).show();
            v.setBackgroundResource(R.drawable.button_1_focused);
        }

    }




    // функція для передачі змінної у сервіс
    // public static Context getContextOfApplication(){
    //   return contextOfApplication;
    //   }

}

