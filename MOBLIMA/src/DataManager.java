package MOBLIMA.src;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;

public class DataManager {

    public static void updateData(Object list, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(list);
            System.out.println("Saving data to " + fileName + " ...");
            oos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }
    public static Object readData(String fileName){
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object list = ois.readObject();
            System.out.print("Reading data from " + fileName + " ...\n");
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

    public static void main(String[] args) {
        ArrayList<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie("Avatar");
        movie.setMovieShowingStatus(MovieShowingStatus.NOW_SHOWING);
        ArrayList<String> cast = new ArrayList<>();
        cast.add("Sam Worthington"); cast.add("Zoe Saldana"); cast.add("Stephen Lang"); cast.add("Michelle Rodriguez"); cast.add("Sigourney Weaver");
        MovieDetails movieDetails = new MovieDetails(cast, "James Cameron",
                "On the lush alien world of Pandora live the Na'vi, beings who appear primitive but are highly evolved...",
                "English", Rated.PG, 162, "17 December 2009", 4.6, new ArrayList<Double>(), new ArrayList<String>());
        movie.setMovieDetails(movieDetails);
        movieList.add(movie);
        updateData(movieList, "movielist.txt");


        // add example admin
        ArrayList<MovieAdmin> adminList = new ArrayList<>();
        MovieAdmin admin = new MovieAdmin("admin", "password");
        adminList.add(admin);
        updateData(adminList, "adminaccounts.txt");

        // add example moviegoer
        ArrayList<MovieGoer> movieGoerList = new ArrayList<>();
        MovieGoer guest = new MovieGoer("guest", "password");
        guest.setAge(21);
        guest.setAgeBracket(guest.age);
        guest.setEmailAddress("guest@gmail.com");
        guest.setMobileNumber("80009000");
        movieGoerList.add(guest);
        updateData(movieGoerList, "accounts.txt");

    } // main
} //class


