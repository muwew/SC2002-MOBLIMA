package MOBLIMA.src.accounts;

import java.io.Serializable;

/**
 * Account for admins of the system.
 */
public class MovieAdmin extends Account implements Serializable {

    /**
     * Determines if users can view top 5 movies by rating.
     */
    boolean canTop5ByRating;

    /**
     * Determines if users can view top 5 movies by ticket sales.
     */
    boolean canTop5BySales;

    /**
     * Constructor, canTop5ByRating and canTop5BySales set to false by default.
     * @param username Username used to log in to account.
     * @param password Password used to log in to account.
     */
    public MovieAdmin(String username, String password) {
        super(username, password);
        canTop5BySales = false;
        canTop5ByRating = false;
    }

    /**
     * Gets boolean if users can view top 5 movies by rating.
     * @return Whether users can view top 5 movies by rating.
     */
    public boolean isCanTop5ByRating() {
        return canTop5ByRating;
    }

    /**
     * Sets if users can view top 5 movies by rating.
     * @param canTop5ByRating Whether users can view top 5 movies by rating.
     */
    public void setCanTop5ByRating(boolean canTop5ByRating) {
        this.canTop5ByRating = canTop5ByRating;
    }

    /**
     * Gets boolean if users can view top 5 movies by ticket sales.
     * @return Whether users can view top 5 movies by ticket sales.
     */
    public boolean isCanTop5BySales() {
        return canTop5BySales;
    }

    /**
     * Sets if users can view top 5 movies by ticket sales.
     * @param canTop5BySales Whether users can view top 5 movies by ticket sales.
     */
    public void setCanTop5BySales(boolean canTop5BySales) {
        this.canTop5BySales = canTop5BySales;
    }
}