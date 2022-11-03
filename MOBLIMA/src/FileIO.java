package MOBLIMA.src;
import java.io.*;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIO{

    public static void updateMovieList(ArrayList<Movie> movieList, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            System.out.println("saving movie data to " + fileName + " ...");
            oos.writeObject(movieList);

            oos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }
    public static ArrayList<Movie> readMovieList(String fileName){
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.print("reading movie data from " + fileName + " ...\n");
            /*int i = ois.readInt();
            String today = (String) ois.readObject();
            Date date = (Date) ois.readObject();*/

            movieList = (ArrayList<Movie>) ois.readObject();

            //System.out.println("Movie : " + movie.getMovieTitle());
            //System.out.printf("Input data: %s", movie.getMovieTitle());
            ois.close();
            return movieList;
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return movieList;
    }

    public static void updateAccountList(ArrayList<Account> adminList, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            System.out.println("saving admin data to " + fileName + " ...");
            oos.writeObject(adminList);

            oos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }
    public static ArrayList<Account> readAccountList(String fileName){
        ArrayList<Account> adminList = new ArrayList<Account>();

        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.print("reading account data from " + fileName + " ...\n");

            adminList = (ArrayList<Account>) ois.readObject();

            //System.out.println("Movie : " + movie.getMovieTitle());
            //System.out.printf("Input data: %s", movie.getMovieTitle());
            ois.close();
            return adminList;
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return adminList;
    }

    public static void main(String[] args) {
        /*ArrayList<Movie> movieList = new ArrayList<Movie>();
        Movie movie = new Movie("avatar");
        MovieSlot movieSlot = new MovieSlot("3 Jan", "1700-1900", "JEM");
        ArrayList<MovieSlot> movieSlots = new ArrayList<MovieSlot>();
        movieSlots.add(movieSlot);
        movie.setMovieSlot(movieSlots);
        Movie movie2 = new Movie("interstellar");
        movieList.add(movie);
        movieList.add(movie2);
        updateMovieList(movieList, "movielist.txt");
        movieList = readMovieList("movielist.txt");
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println(movieList.get(i).getMovieTitle());
        }*/

        ArrayList<Account> adminList = new ArrayList<Account>();
        MovieAdmin admin = new MovieAdmin("admin", "password");
        adminList.add(admin);
        updateAccountList(adminList, "accounts.txt");

        adminList = readAccountList("accounts.txt");
        for (int i = 0; i < adminList.size(); i++) {
            System.out.println(adminList.get(i).getUsername());
        }

    } // main
} //class


