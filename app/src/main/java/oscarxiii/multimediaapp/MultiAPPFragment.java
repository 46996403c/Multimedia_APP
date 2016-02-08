package oscarxiii.multimediaapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class MultiAPPFragment extends Fragment {

    public MultiAPPFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_multi_app, container, false);
        Firebase.setAndroidContext(getContext());

        Firebase myFirebaseRef = new Firebase("https://appmultimediaxiii.firebaseio.com/");
        myFirebaseRef.child("message").setValue("¡¡tu hermana!!");


        Firebase alanRef = myFirebaseRef.child("users").child("alanisawesome");
        users alan = new users("Alan Turing", 1912);
        alanRef.setValue(alan);

        Firebase postRef = myFirebaseRef.child("posts");
        Map<String, String> post1 = new HashMap<String, String>();
        post1.put("author", "gracehop");
        post1.put("title", "Announcing COBOL, a New Programming Language");
        postRef.push().setValue(post1);
        Map<String, String> post2 = new HashMap<String, String>();
        post2.put("author", "alanisawesome");
        post2.put("title", "The Turing Machine");
        postRef.push().setValue(post2);


        // Get a reference to our posts
        Firebase ref = new Firebase("https://docs-examples.firebaseio.com/web/saving-data/fireblog/posts");

        // Attach an listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        return view;
    }

}
