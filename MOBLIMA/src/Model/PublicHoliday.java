package MOBLIMA.src.Model;

import java.io.Serializable;
import java.util.*;

public class PublicHoliday implements Serializable {
    // Attributes
    private Calendar date;
    private String name;

    // Constructor
    public PublicHoliday (int m, int d, String name) {
        this.date = new GregorianCalendar(2022, m-1, d, 0, 0);
        this.name = name;
    }

    // Getters and setters
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Methods
//    public void addPH (String name, int m, int d, ArrayList<PublicHoliday> phList) {
//        PublicHoliday newPH = new PublicHoliday(m, d, name);
//        phList.add(newPH);
//        System.out.println("New public holiday " + name + " has been added successfully into the system.");
//    }
//
//    public void removePH (String name, ArrayList<PublicHoliday> phList) {
//        for (PublicHoliday i: phList) {
//            if (Objects.equals(i.name, name)) {
//                phList.remove(i);
//                break;
//            }
//        }
//        System.out.println("Public holiday " + name + " has been removed successfully from the system.");
//    }
//
////    public Calendar findPH (ArrayList<PublicHoliday> phList, )
}
