package MOBLIMA.src;
import MOBLIMA.src.model.MovieAdmin;
import MOBLIMA.src.model.MovieGoer;
import MOBLIMA.src.enums.CinemaClass;
import MOBLIMA.src.enums.Cineplex;
import MOBLIMA.src.enums.MovieShowingStatus;
import MOBLIMA.src.enums.Rated;
import MOBLIMA.src.model.*;

import java.io.*;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class to read and update text files used to store the data required.
 */
public class DataManager {

    /**
     * Method to update the data.
     * @param list Object to be stored in text file.
     * @param fileName Name of text file to be stored to.
     */
    public static void updateData(Object list, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(list);
            //System.out.println("Saving data to " + fileName + " ...");
            oos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }

    /**
     * Method to read the data.
     * @param fileName Name of text file that stores data.
     * @return Objects stored in text file.
     */
    public static Object readData(String fileName){
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object list = ois.readObject();
            //System.out.print("Reading data from " + fileName + " ...\n");
            ois.close();
            return list;
        } catch (IOException e) {
            System.out.println("File input error");
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Initialises data in files.
     *
     * @param args
     */


    public static void main(String[] args) {
        ArrayList<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie("Avatar");
        movie.setMovieShowingStatus(MovieShowingStatus.NOW_SHOWING);
        ArrayList<String> cast = new ArrayList<>();
        cast.add("Sam Worthington"); cast.add("Zoe Saldana"); cast.add("Stephen Lang"); cast.add("Michelle Rodriguez"); cast.add("Sigourney Weaver");
        MovieDetails movieDetails = new MovieDetails(cast, "James Cameron",
                "On the lush alien world of Pandora live the Na'vi, beings who appear primitive but are highly evolved. Because the planet's environment is poisonous, human/Na'vi hybrids, called Avatars, must link to human minds to allow for free movement on Pandora. Jake Sully (Sam Worthington), a paralyzed former Marine, becomes mobile again through one such Avatar and falls in love with a Na'vi woman (Zoe Saldana). As a bond with her grows, he is drawn into a battle for the survival of her world.",
                "English", Rated.PG, 162, "17/12/2009", -1, new ArrayList<>());
        movie.setMovieDetails(movieDetails);
        Cinema cinema1 = new Cinema(CinemaClass.NORMAL, Cineplex.JEM, 1);
        Cinema cinema2 = new Cinema(CinemaClass.NORMAL, Cineplex.WEST_MALL, 1);
        ArrayList<MovieSlot> movieSlotList = new ArrayList<>();
        movieSlotList.add(new MovieSlot(9, 2, 17, 0, cinema1, movie));
        movieSlotList.add(new MovieSlot(9, 2, 15, 0, cinema1, movie));
        movieSlotList.add(new MovieSlot(9, 2, 14, 0, cinema1, movie));
        movieSlotList.add(new MovieSlot(11, 8, 14, 0, cinema2, movie));
        movie.setMovieSlot(movieSlotList);
        movieList.add(movie);
        updateData(movieList, "movielist.txt");

        ArrayList<MovieGoer> userList = new ArrayList<>();
        MovieGoer movieGoer = new MovieGoer("guest", "xavier", 91234567, "guest@gmail,com", "password", new ArrayList<Booking>(), 22);
        userList.add(movieGoer);
        updateData(userList, "useraccounts.txt");
        ArrayList<MovieAdmin> adminList = new ArrayList<>();
        MovieAdmin admin = new MovieAdmin("admin", "password");
        adminList.add(admin);
        updateData(adminList, "adminaccounts.txt");

        updateData(new ArrayList<TicketDetails>(), "ticketlist.txt");
        updateData(new ArrayList<PublicHoliday>(), "phlist.txt");

    }
}


