package entities;

/**
 *
 * @author abirk
 */
public class Artiste extends User {

    public Artiste() {

        super();
    }

    public Artiste(int id_user, String first_Name, String last_Name, String user_Name, String password, String email, int phone_number, String gender,   String role) {
        super(id_user, first_Name, last_Name, user_Name, password, email, phone_number, gender,  role);
    }

    @Override
    public String toString() {
        return "Artiste{" + super.toString() + '}';
    }
}