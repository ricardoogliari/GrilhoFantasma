package helloworld.teste.com.grilhofantasma;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements SensorEventListener{

    private TextView txtSensorLuz;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSensorLuz = (TextView) findViewById(R.id.txtSensorLuz);

        SensorManager sMgr = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sMgr.registerListener(this, sMgr.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] leitura = sensorEvent.values;
        txtSensorLuz.setText("Valor: "+leitura[0]);

        if (leitura[0] < 15){
            tocaMidia();
        } else {
            paraMidia();
        }
    }

    public void paraMidia(){
        if (mp != null) {
            mp.stop();
            mp.reset();
            mp.release();
            mp = null;
        }
    }

    public void tocaMidia(){
        if (mp != null && mp.isPlaying()) {
            return;
        }
        mp = MediaPlayer.create(this, R.drawable.grilo);
        mp.start();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}
}
