package MOBLIMA.src;
import java.io.Serializable;
import java.util.*;

public abstract class Account implements Serializable {
    private String username;
    private String password;

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
