package MOBLIMA.src.Accounts;
import MOBLIMA.src.Accounts.Account;

import java.io.Serializable;

public class MovieAdmin extends Account implements Serializable {

    boolean canTop5ByRating;
    boolean canTop5BySales;
    public MovieAdmin(String username, String password) {
        super(username, password);
        canTop5BySales = false;
        canTop5ByRating = false;
    }

    public boolean isCanTop5ByRating() {
        return canTop5ByRating;
    }

    public void setCanTop5ByRating(boolean canTop5ByRating) {
        this.canTop5ByRating = canTop5ByRating;
    }

    public boolean isCanTop5BySales() {
        return canTop5BySales;
    }

    public void setCanTop5BySales(boolean canTop5BySales) {
        this.canTop5BySales = canTop5BySales;
    }
}