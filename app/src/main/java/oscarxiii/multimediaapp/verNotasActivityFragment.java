package oscarxiii.multimediaapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class verNotasActivityFragment extends Fragment {

 /*   public verNotasActivityFragment() {
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_notas, container, false);
        Firebase.setAndroidContext(getContext());

        Firebase ref = new Firebase("https://appmultimediaxiii.firebaseio.com/");
        Firebase notasRef = ref.child("notas");

        ListView firBaseList = (ListView) view.findViewById(R.id.notasLV);
        FirebaseListAdapter adapter = new FirebaseListAdapter<infoNota>(getActivity(), infoNota.class, android.R.layout.two_line_list_item, notasRef) {
            @Override
            protected void populateView(View v, infoNota model, int position) {
                ((TextView) v.findViewById(android.R.id.text1)).setText(model.getTitulo());
                ((TextView) v.findViewById(android.R.id.text2)).setText("Nota: "+model.getNota()+"\nLatitud: "+model.getLatitud()+"\nLongitud :"+model.getLongitud());
            }
        };
        firBaseList.setAdapter(adapter);

        return view;
    }
}
