package MOBLIMA.src;
import java.io.Serializable;
import java.util.*;

public class MovieSlot implements Serializable {
    //private String date; //2change
    private Calendar aDate;
    //private String time; //2change
    private Cinema cinema;
    private MovieType movieType;


    public MovieSlot(int m, int d, int h, int min, Cinema cinema){
        this.aDate = new GregorianCalendar(2022, m, d, h, min);
        this.cinema = cinema;
        this.movieType = MovieType.DIGITAL;
    }
    public MovieType getMovieType(){
        return movieType;
    }

    public void setMovieType(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Movie type: 1)DIGITAL 2)THREE_D 3)BLOCKBUSTER 4)EXCLUSIVE 5)IMAX_THREE_D");
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                this.movieType = MovieType.DIGITAL;
                break;
            case 2:
                this.movieType = MovieType.THREE_D;
                break;
            case 3:
                this.movieType = MovieType.BLOCKBUSTER;
                break;
            case 4:
                this.movieType = MovieType.EXCLUSIVE;
                break;
            case 5:
                this.movieType = MovieType.IMAX_THREE_D;
                break;
        }
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Calendar getaDate() {
        return aDate;
    }

    public void setaDate(Calendar aDate) {
        this.aDate = aDate;
    }
}
