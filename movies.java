import enums.*;

public class movies {
    private String movieTitle;
    private movieType movieType;
    private MovieSlot[] movieSlots;
    private Reviews[] movieReviews;
    private showingStatus movieShowingStatus;
    private String movieSynopsis;
    private String movieDirector;
    private String[] movieCast;
    private float overallMovieRating;
    private watchRatings movieWatchRating;


    public String getMovieTitle() {
        return this.movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public movieType getMovieType() {
        return this.movieType;
    }

    public void setMovieType(movieType movieType) {
        this.movieType = movieType;
    }

    public MovieSlot[] getMovieSlots() {
        return this.movieSlots;
    }

    public void setMovieSlots(MovieSlot[] movieSlots) {
        this.movieSlots = movieSlots;
    }

    public Reviews[] getMovieReviews() {
        return this.movieReviews;
    }

    public void setMovieReviews(Reviews[] movieReviews) {
        this.movieReviews = movieReviews;
    }

    public showingStatus getMovieShowingStatus() {
        return this.movieShowingStatus;
    }

    public void setMovieShowingStatus(showingStatus movieShowingStatus) {
        this.movieShowingStatus = movieShowingStatus;
    }

    public String getMovieSynopsis() {
        return this.movieSynopsis;
    }

    public void setMovieSynopsis(String movieSynopsis) {
        this.movieSynopsis = movieSynopsis;
    }

    public String getMovieDirector() {
        return this.movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String[] getMovieCast() {
        return this.movieCast;
    }

    public void setMovieCast(String[] movieCast) {
        this.movieCast = movieCast;
    }

    public float getOverallMovieRating() {
        return this.overallMovieRating;
    }

    public void setOverallMovieRating(float overallMovieRating) {
        this.overallMovieRating = overallMovieRating;
    }

    public watchRatings getMovieWatchRating() {
        return this.movieWatchRating;
    }

    public void setMovieWatchRating(watchRatings movieWatchRating) {
        this.movieWatchRating = movieWatchRating;
    }

    // public void addReview(String comment, float rating){
    //     this.Reviews = comment;
    // }

}
