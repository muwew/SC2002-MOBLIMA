package MOBLIMA.src;
import java.util.Objects;

public enum Location {
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

    public boolean equals (Location otherLocation) {
        return Objects.equals(this.toString(), otherLocation.toString());
    }
}
