package MOBLIMA.src.model;

import java.io.Serializable;

/**
 * Class that stores review information.
 */
public class Review implements Serializable {
    /**
     * Username of account that left the review.
     */
    private String user;

    /**
     * Rating left by user.
     */
    private double rating;

    /**
     * Comments left by user.
     */
    private String comment;

    /**
     * Constructors.
     */
    public Review(){
        user = "";
        rating = -1;
        comment = "";
    }
    public Review(String user, double rating, String comment){
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    /**
     * Gets username of user that left review.
     * @return Username.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets username of user that left review.
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets rating user left.
     * @return Rating.
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets rating for review.
     * @param rating Rating.
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Gets comment user left.
     * @return Comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment for review.
     * @param comment Comment.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
