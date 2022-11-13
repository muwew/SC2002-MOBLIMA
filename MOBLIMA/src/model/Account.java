package MOBLIMA.src.model;
import java.io.Serializable;

/**
 * Base class for the accounts.
 */
public abstract class Account implements Serializable {
    /**
     * Username used to log in to account.
     */
    private String username;

    /**
     * Password used to log in to account.
     */
    private String password;

    /**
     * Constructor.
     * @param username Username used to log in to account.
     * @param password Password used to log in to account.
     */
    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username of account.
     * @return Username of account.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of account.
     * @param username New username of account.
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Gets the password of account.
     * @return Password of account.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of account.
     * @param password New password of account.
     */
    public void setPassword(String password){
        this.password = password;
    }

}
