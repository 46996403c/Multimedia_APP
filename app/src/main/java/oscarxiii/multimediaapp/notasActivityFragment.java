package oscarxiii.multimediaapp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class notasActivityFragment extends Fragment {

    public notasActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_notas, container, false);

        Firebase.setAndroidContext(getContext());

        final EditText titulo = (EditText) view.findViewById(R.id.tituloNota);
        final EditText nota = (EditText) view.findViewById(R.id.cuerpoNota);

        final Button button = (Button) view.findViewById(R.id.BtAñadirNota);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Firebase myFirebaseRef = new Firebase("https://appmultimediaxiii.firebaseio.com/");

                Firebase postRef = myFirebaseRef.child("notas");
                Map<String, String> post1 = new HashMap<String, String>();
                post1.put("Titulo", String.valueOf(titulo.getText()));
                post1.put("Nota", String.valueOf(nota.getText()));
                postRef.push().setValue(post1);

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
}
