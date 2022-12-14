package MOBLIMA.src.enums;
import java.io.Serializable;
import java.util.Objects;

/**
 * Enum to store movie types.
 */
public enum MovieType implements Serializable {
    DIGITAL, BLOCKBUSTER, THREE_D, EXCLUSIVE, IMAX_THREE_D;

    public String toString () {
        switch (this) {
            case DIGITAL:
                return "Digital";

            case BLOCKBUSTER:
                return "Blockbuster";

            case THREE_D:
                return "3D";

            case EXCLUSIVE:
                return "Exclusive";

            case IMAX_THREE_D:
                return "IMAX 3D";

            default:
                return null;
        }
    }

    public boolean equals (MovieType otherMovieType) {
        return Objects.equals(this.toString(), otherMovieType.toString());
    }
}
