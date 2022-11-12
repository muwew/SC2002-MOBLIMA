package MOBLIMA.src.enums;
import java.util.Objects;

/**
 * Enum to store cineplex locations.
 */
public enum Cineplex {
    // Enumerated types
    JEM, WEST_MALL, JURONG_POINT;

    public String toString () {
        switch (this) {
            case JEM:
                return "JEM";

            case WEST_MALL:
                return "WEST_MALL";

            case JURONG_POINT:
                return "JURONG_POINT";

            default:
                return null;
        }
    }

    public String toCode(){
        switch (this) {
            case JEM:
                return "JE";

            case WEST_MALL:
                return "WM";

            case JURONG_POINT:
                return "JP";

            default:
                return null;
        }
    }
    public boolean equals (Cineplex otherCineplex) {
        return Objects.equals(this.toString(), otherCineplex.toString());
    }
}
