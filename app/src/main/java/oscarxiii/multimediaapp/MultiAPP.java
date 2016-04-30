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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiAPP extends AppCompatActivity {

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
                Snackbar.make(view, "Abriendo camara", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                dispatchTakePictureIntent();
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multi_ap, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //======================= SECCION DE CAMARA PARA TOMAR UNA FOTO ==============================\\
    String mCurrentPhotoPath;
    private File createImageFile() throws IOException {
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
    static final int REQUEST_TAKE_PHOTO = 1;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
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

}
