package com.ashitech.shakey;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    String[] quotes;

    TextView tvQuotes;

    TextView tvPages;

    SensorManager manager;

    Sensor accel;
    double  x,y,z;
    final double SHAKE_THRESHOLD = 14.5;
    double lastUpdate = 0;

    int i = 0;
    public void buttonOperation(View view){
        if(view.getId() == R.id.btnNext){
            {
                quatesNext();
            }

        }
        else{
            quatesPrev();
            }
        }
        public void quatesNext(){
            tvQuotes.setText(quotes[++i]);
            tvPages.setText(i+"/15");
        }
        public void quatesPrev(){
            tvQuotes.setText(quotes[--i]);
            tvPages.setText(i+"/15");
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvQuotes = findViewById(R.id.tvQuates);
        tvPages = findViewById(R.id.tvPages);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        quotes = getResources().getStringArray(R.array.quotes_array);
        accel=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this,accel,SensorManager.SENSOR_DELAY_NORMAL);



//        quates[0]="\"Quotes Start's From Here\"";
//        quates[1]=" \"There is no elevator to success — you have to take the stairs.\"";
//        quates[2]="\"Plant your garden and decorate your own soul, instead of waiting for someone to bring you flowers.\"";
//        quates[3]=" \"Do what you feel in your heart to be right, for you'll be criticized anyway.\"";
//        quates[4]="\"If you want to go fast, go alone. If you want to go far, go together.\"";
//        quates[5]="\"Do not set yourself on fire in order to keep others warm.\"";
//        quates[6]="\"It's supposed to be hard. If it were easy, everyone would do it.\"";
//        quates[7]="\"Life may not be the party we hoped for, but while we're here, we should dance.\"";
//        quates[8]="\"Do not go where the path may lead, go instead where there is no path and leave a trail.\"";
//        quates[9]="\"Love all, trust a few, do wrong to none.\"";
//        quates[10]="\"Nothing in life is to be feared; it is only to be understood. Now is the time to understand more so that we may fear less.\"";
//        quates[11]=" \"Believe you can and you're halfway there.\"";
//        quates[12]="\"It's not about how hard you can hit; it's about how hard you can get hit and keep moving forward.\"";
//        quates[13]="\"You can't go around building a better world for people. Only people can build a better world for people. Otherwise it's just a cage.\"";
//        quates[14]=" \"The woman who follows the crowd will usually go no further than the crowd. The woman who walks alone is likely to find herself in places no one has been before.\"";
//        quates[15]=" \"Be who you are and say what you feel, because those who mind don't matter and those who matter don't mind.\"";
        tvQuotes.setText(quotes[0]);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

            double curTime = System.currentTimeMillis();
            // only allow one update every 100ms.

                System.out.println(lastUpdate);
                System.out.println(curTime);

                x =  sensorEvent.values[0];
                y =  sensorEvent.values[1];
                z =  sensorEvent.values[2];

                System.out.println(x);
                System.out.println(y);
                System.out.println(z);

                double speed = Math.abs(x+y+z );
                //tvQuates.setText(String.valueOf(speed));
        if ((curTime - lastUpdate) > 3500) {
            lastUpdate = curTime;

                if (speed >= SHAKE_THRESHOLD) {
                   // Log.d("sensor", "shake detected w/ speed: " + speed);
                        quatesNext();
                    Toast.makeText(this, "Shake Detected",Toast.LENGTH_SHORT).show();
                }
                           }
        }



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
