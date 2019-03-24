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


public class UradioActivity2 extends Activity
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
    Button buttonStop;
    public static final String RADIO_PREFERENCES = "radiopref";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_two);

        

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
            finish();
            return true;

        }
        else return false;


    }
});


//

        if (isMyServiceRunning(PlayService.class))
        {
            SharedPreferences sPref = getApplicationContext().getSharedPreferences(RADIO_PREFERENCES, Activity.MODE_PRIVATE);
            String nameStation = sPref.getString("nameStation", "Виберіть станцію");
            final TextView textViewChange = (TextView) findViewById(R.id.current);
            textViewChange.setText(nameStation);
            if ((sPref.getInt("buttonId", 0) != 0) && (sPref.getInt("trigger", 0) == 2))
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
                Intent intent = new Intent(UradioActivity2.this, About.class);
                startActivity(intent);
            }
        });
///

///two перша сторінка
/*
  
       Button buttonTwo = (Button) findViewById(R.id.two);
        buttonTwo.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(this, About.class);
         startActivity(i);

            }

        });*/

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
                String nameStation = "Сяйво";
                buttonDuzhe.setText(nameStation);
                String linkStation = "http://stream.ntktv.ua:8000/syaivo.mp3";
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
                String nameStation = "Стрий ФМ";
                buttonZahPol.setText(nameStation);
                String linkStation = "http://online.fm.stryi.com:8000/stryi-fm_mp3";
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
                String nameStation = "Maximum FM";
                buttonUh.setText(nameStation);
                String linkStation = "http://icecastdc.luxnet.ua/maximum";
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
                String nameStation = "Радіо Такт";
                buttonMolode.setText(nameStation);
                String linkStation = "http://radiotakt.com.ua:8000/takt.mp3";
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
                String nameStation = "Голос свободи";
                buttonYaskrave.setText(nameStation);
                String linkStation = "http://89.184.83.113:8000/autodj";
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
                String nameStation = "Країна ФМ";
                buttonNezalezhnist.setText(nameStation);
                String linkStation = "http://185.65.245.34:8000/kiev";
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
                String nameStation = "Рок";
                buttonRep.setText(nameStation);
                String linkStation = "http://185.65.245.34:8000/rock";
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
                String nameStation = "Радіо Релакс";
                buttonDzvony.setText(nameStation);
                String linkStation = "http://online-radiorelax.tavrmedia.ua/RadioRelax";
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
                String nameStation = "Культура";
                buttonVezha.setText(nameStation);
                String linkStation = "http://nrcu.gov.ua:8000/ur3-mp3-m";
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
                String nameStation = "УР-1";
                buttonRtrack.setText(nameStation);
                String linkStation = "http://91.194.250.169:8000/ur1-mp3";
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

                stopService( new Intent(UradioActivity2.this, PlayService.class));
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
        ActivityManager manager = (ActivityManager) getSystemService(UradioActivity2.ACTIVITY_SERVICE);
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
                stopService( new Intent(UradioActivity2.this, PlayService.class));
            }

            Intent intent = new Intent(UradioActivity2.this, PlayService.class);
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
            ed.putInt("trigger", 2);
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

