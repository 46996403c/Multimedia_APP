package oscarxiii.multimediaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiAPP extends AppCompatActivity {
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_TAKE_VIDEO = 1;
    String mCurrentPhotoPath;
    String mCurrentMoviesPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abriendo bloc de notas para añadir una nueva", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //Intent i = new Intent(MultiAPP.this, addNotasActivity.class);
                startActivity(new Intent(MultiAPP.this, addNotasActivity.class));
            }
        });

        Button buttonAddNotas = (Button) findViewById(R.id.addNotas);
        buttonAddNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abriendo bloc de notas para añadir una nueva", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //Intent i = new Intent(MultiAPP.this, addNotasActivity.class);
                startActivity(new Intent(MultiAPP.this, addNotasActivity.class));
            }
        });

        Button buttonVerNotas = (Button) findViewById(R.id.verNotas);
        buttonVerNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abriendo bloc de notas", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //Intent i = new Intent(MultiAPP.this, addNotasActivity.class);
                startActivity(new Intent(MultiAPP.this, verNotasActivity.class));
            }
        });

        Button buttonHacerFoto = (Button) findViewById(R.id.hacerFoto);
        buttonHacerFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abriendo camara de fotos", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                tomarFoto();
            }
        });

        Button buttonVerFoto = (Button) findViewById(R.id.verFoto);
        buttonVerFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abriendo galeria", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //Intent i = new Intent(MultiAPP.this, addNotasActivity.class);
                startActivity(new Intent(MultiAPP.this, verFoto.class));
            }
        });

        Button buttonHacerVideo = (Button) findViewById(R.id.hacerVideo);
        buttonHacerVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abriendo camara de video", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                tomarVideo();
            }
        });

        Button buttonMap = (Button) findViewById(R.id.mapa);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abriendo galeria", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //Intent i = new Intent(MultiAPP.this, addNotasActivity.class);
                startActivity(new Intent(MultiAPP.this, Mapa.class));
            }
        });

        Button buttonVerVideo = (Button) findViewById(R.id.verVideo);
        buttonVerVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abriendo galeria", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //Intent i = new Intent(MultiAPP.this, addNotasActivity.class);
                startActivity(new Intent(MultiAPP.this, verVideo.class));
            }
        });

        Button buttonGrabadora = (Button) findViewById(R.id.hacerAudio);
        buttonGrabadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abriendo grabadora de audio", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                startActivity(new Intent(MultiAPP.this, Grabadora.class));
            }
        });
    }

    //======================= SECCION DE CAMARA PARA TOMAR UNA FOTO ==============================\\
    private File guardarFoto() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
    private void tomarFoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = guardarFoto();
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.out.print("=========ERROR AL TOMAR LA FOTO============");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }else {
                System.out.print("=========ERROR AL GUARDAR LA FOTO============");
            }
        }
    }
    //======================= FIN SECCION DE CAMARA PARA TOMAR UNA FOTO ==============================\\

    //======================= SECCION DE CAMARA PARA GRABAR UN VIDEO ==============================\\
    private File guardarVideo() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "MP4_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".mp4",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentMoviesPath = "file:" + image.getAbsolutePath();
        return image;
    }
    private void tomarVideo() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File videoFile = null;
            try {
                videoFile = guardarVideo();
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.out.print("=========ERROR AL TOMAR EL VIDEO============");
            }
            // Continue only if the File was successfully created
            if (videoFile != null) {
                takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
                takeVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(takeVideoIntent, REQUEST_TAKE_VIDEO);
            }else {
                System.out.print("=========ERROR AL GUARDAR EL VIDEO============");
            }
        }
    }
    //======================= FIN SECCION DE CAMARA PARA GRABAR UN VIDEO ==============================\\
}
