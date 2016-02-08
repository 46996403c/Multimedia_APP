package oscarxiii.multimediaapp;

import com.firebase.client.Firebase;

/**
 * Created by Usuario on 27/01/2016.
 */
public class users {
    private int birthYear;
    private String fullName;
    public users() {}
    public users(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }
    public long getBirthYear() {
        return birthYear;
    }
    public String getFullName() {
        return fullName;
    }
}
