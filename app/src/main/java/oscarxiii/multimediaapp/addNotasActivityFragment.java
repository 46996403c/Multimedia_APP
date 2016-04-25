package oscarxiii.multimediaapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class addNotasActivityFragment extends Fragment implements LocationListener {
    Double lon;
    Double lat;
    public addNotasActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_add_notas, container, false);

        LocationManager manager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);

        Firebase.setAndroidContext(getContext());

        final EditText titulo = (EditText) view.findViewById(R.id.tituloNota);
        final EditText nota = (EditText) view.findViewById(R.id.cuerpoNota);

        final Button button = (Button) view.findViewById(R.id.BtAñadirNota);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Firebase myFirebaseRef = new Firebase("https://appmultimediaxiii.firebaseio.com/");

                /*Firebase postRef = myFirebaseRef.child("notas");
                Map<String, String> post = new HashMap<>();
                post.put("Titulo", String.valueOf(titulo.getText()));
                post.put("Nota", String.valueOf(titulo.getText()));
                post.put("Longitud", String.valueOf(lon));
                post.put("Latitud", String.valueOf(lat));
                postRef.push().setValue(post);*/

                Firebase postRef = myFirebaseRef.child("notas");
                infoNota fireNota = new infoNota(String.valueOf(titulo.getText()),String.valueOf(nota.getText()), String.valueOf(lon), String.valueOf(lat));
                postRef.push().setValue(fireNota);

                Snackbar.make(view, "Nota añadida", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                // Attach an listener to read the data at our posts reference
                myFirebaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        System.out.println(snapshot.getValue());
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        System.out.println("The read failed: " + firebaseError.getMessage());
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getContext(), "Latitud: "+location.getLatitude()+"\nLongitud: "+location.getLongitude(), Toast.LENGTH_SHORT).show();
        this.lon = location.getLongitude();
        this.lat = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getContext(), "GPS Activado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getContext(), "GPS Desactivado", Toast.LENGTH_SHORT).show();
    }
}
