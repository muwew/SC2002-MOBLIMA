package MOBLIMA.src;

import java.io.Serializable;

public class Review implements Serializable {
    private String user;
    private double rating;
    private String comment;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
