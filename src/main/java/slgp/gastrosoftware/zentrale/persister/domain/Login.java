package slgp.gastrosoftware.zentrale.persister.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Login implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String passwort;

    public Login (String username, String passwort) {
        this.username = username;
        this.passwort = passwort;
    }

    public Login() {
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPasswort() {
        return passwort;
    }
    public void setPasswort(String password) {
        this.passwort = passwort;
    }

    public int hashCode() {
        return Objects.hash(username, passwort);
    }

    public boolean eqauls(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof Login)) {
            return false;
        }

        Login logi = (Login) obj;

        return this.getUsername() == logi.getUsername() && this.getPasswort() == logi.getPasswort();

    }

    public String toString() {
        return "Login [username=" + username + ", passwort=" + passwort + "]";
    }




}
