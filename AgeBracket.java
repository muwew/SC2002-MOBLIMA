import java.util.Objects;

public enum AgeBracket {
    STUDENT, CHILD, ADULT;

    public String toString () {
        switch (this) {
            case STUDENT:
                return "Student";

            case CHILD:
                return "Child";

            case ADULT:
                return "Adult";

            default:
                return null;
        }
    }

    public boolean equals (AgeBracket otherAgeBracket) {
        return Objects.equals(this.toString(), otherAgeBracket.toString());
    }
}
