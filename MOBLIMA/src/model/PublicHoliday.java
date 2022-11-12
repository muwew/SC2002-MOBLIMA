package MOBLIMA.src.model;

import java.io.Serializable;
import java.util.*;

/**
 * Class that stores public holiday details.
 */
public class PublicHoliday implements Serializable {
    // Attributes
    /**
     * Date of public holiday.
     */
    private Calendar date;

    /**
     * Name of public holiday.
     */
    private String name;



    // Constructor

    /**
     * Constructor.
     * @param m Month.
     * @param d Day.
     * @param name Name.
     */
    public PublicHoliday (int m, int d, String name) {
        this.date = new GregorianCalendar(2022, m-1, d, 0, 0);
        this.name = name;
    }

    // Getters and setters

    /**
     * Gets date.
     * @return Date.
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * Sets date.
     * @param date Date.
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Gets name of holiday.
     * @return Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of holiday.
     * @param name Name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
