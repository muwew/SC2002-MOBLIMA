import java.util.Objects;

public enum TicketType {
    MON_TO_WED, THURS, FRI_BEFORE_6PM, FRI_AFTER_6PM, WEEKENDS_AND_PH;

    public String toString () {
        switch (this) {
            case MON_TO_WED:
                return "Mon to Wed";

            case THURS:
                return "Thurs";

            case FRI_BEFORE_6PM:
                return "Fri before 6pm";

            case FRI_AFTER_6PM:
                return "Fri after 6pm";

            case WEEKENDS_AND_PH:
                return "Weekends and PH";

            default:
                return null;
        }
    }

    public boolean equals (TicketType otherTicketType) {
        return Objects.equals(this.toString(), otherTicketType.toString());
    }
}
