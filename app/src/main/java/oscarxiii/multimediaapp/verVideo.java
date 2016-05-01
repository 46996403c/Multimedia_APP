package oscarxiii.multimediaapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class verVideo extends AppCompatActivity {
    private static final int ACTIVITAT_SELECCIONAR_VIDEO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cargarVideo();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abriendo galeria de videos", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                cargarVideo();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void cargarVideo(){
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, ACTIVITAT_SELECCIONAR_VIDEO);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case ACTIVITAT_SELECCIONAR_VIDEO:
                if (resultCode == RESULT_OK) {
                    reproducirVideo(intent);
                }
        }
    }
    private void reproducirVideo(Intent intent){
        Uri selectedVideo = intent.getData();
        MediaPlayer.create(this, selectedVideo);
        final VideoView vv = (VideoView) findViewById(R.id.verVideo);
        vv.setVideoURI(selectedVideo);
        vv.start();
        final Button buttonPausa = (Button) findViewById(R.id.pausaBT);
        buttonPausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vv.isPlaying()){
                    vv.pause();
                    buttonPausa.setText("Play");
                }else if (!vv.isPlaying()){
                    vv.start();
                    buttonPausa.setText("Pausa");
                }
            }
        });
    }
}
