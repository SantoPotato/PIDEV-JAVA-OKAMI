package entities;
import java.sql.Date;
//import Entities.User;
/**
 *
 * @author abirk
 */
public class Client extends User {

    public Client() {

        super();
    }

    public Client(int id_user, String first_Name, String last_Name, String user_Name, String password, String email, int phone_number, String gender,  String role) {
        super(id_user, first_Name, last_Name, user_Name, password, email, phone_number, gender,    role);

    }

    @Override
    public String toString() {
        return "Client{" + super.toString() + '}';
    }
}