import java.util.Objects;

public enum SeatType {
    NORMAL_SEAT, COUPLE_SEAT, ELITE_SEAT, ULTIMA_SEAT;

    public String toString () {
        switch (this) {
            case NORMAL_SEAT:
                return "Normal Seat";

            case COUPLE_SEAT:
                return "Couple Seat";

            case ELITE_SEAT:
                return "Elite Seat";

            case ULTIMA_SEAT:
                return "Ultima Seat";

            default:
                return null;
        }
    }

    public boolean equals (SeatType otherSeatType) {
        return Objects.equals(this.toString(), otherSeatType.toString());
    }
}
