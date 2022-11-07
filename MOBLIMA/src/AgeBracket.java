package MOBLIMA.src;
import java.util.Objects;

public enum AgeBracket {
    STUDENT, CHILD, ADULT, SENIOR_CTZ;

    public String toString () {
        switch (this) {
            case STUDENT:
                return "Student";

            case CHILD:
                return "Child";

            case ADULT:
                return "Adult";

            case SENIOR_CTZ:
                return "Senior Citizen";

            default:
                return null;
        }
    }

    public boolean equals (AgeBracket otherAgeBracket) {
        return Objects.equals(this.toString(), otherAgeBracket.toString());
    }
}
