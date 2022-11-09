package MOBLIMA.src.Enums;
import java.util.Objects;

public enum CinemaClass {
    NORMAL, PLATINUM;

    public String toString () {
        switch (this) {
            case NORMAL:
                return "Normal";

            case PLATINUM:
                return "Platinum";

            default:
                return null;
        }
    }

    public boolean equals (CinemaClass otherCinemaClass) {
        return Objects.equals(this.toString(), otherCinemaClass.toString());
    }
}
