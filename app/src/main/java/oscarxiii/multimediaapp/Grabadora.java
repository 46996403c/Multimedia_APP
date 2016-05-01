package oscarxiii.multimediaapp;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class Grabadora extends AppCompatActivity {
    private static String nomrbreGrabacion = null;
    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    boolean startRecord = true;
    boolean startPlay = true;

    public Grabadora() {
        nomrbreGrabacion = Environment.getExternalStorageDirectory().getAbsolutePath();
        nomrbreGrabacion += "/audioRecord.3gp";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grabadora);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button buttonRecord = (Button) findViewById(R.id.grabarBT);
        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startRecord==true) {
                    try {
                        empezarGrabacion();
                        buttonRecord.setText("Parar de grabar");
                        Snackbar.make(view, "Grabando audio", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        startRecord = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error al grabar");
                    }
                } else {
                    pararGrabacion();
                    buttonRecord.setText("Empezar a grabar");
                    Snackbar.make(view, "Parando grabacion de audio", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    startRecord = true;
                }
            }
        });

        final Button buttonPlay = (Button) findViewById(R.id.reproducirBT);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startPlay) {
                    empezarReproduccion();
                    buttonPlay.setText("Parar de reproducir");
                    Snackbar.make(view, "Escuchando audio", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    startPlay = false;
                } else {
                    pararReproduccion();
                    buttonPlay.setText("Empezar a reproducir");
                    Snackbar.make(view, "Parando reproduccion de audio", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    startPlay = true;
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void empezarGrabacion() throws IOException {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(nomrbreGrabacion);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.prepare();
        mRecorder.start();
    }

    private void pararGrabacion() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    private void empezarReproduccion() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(nomrbreGrabacion);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            System.out.println("Error al reproducir");
        }
    }

    private void pararReproduccion() {
        mPlayer.release();
        mPlayer = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
