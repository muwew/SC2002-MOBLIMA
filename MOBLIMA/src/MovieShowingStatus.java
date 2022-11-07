package MOBLIMA.src;
import java.util.Objects;

public enum MovieShowingStatus {
        // Enumerated types
        COMING_SOON, PREVIEW, NOW_SHOWING, END_SHOWING;

        public String toString () {
            switch (this) {
                case COMING_SOON:
                    return "COMING_SOON";

                case PREVIEW:
                    return "PREVIEW";

                case NOW_SHOWING:
                    return "NOW_SHOWING";

                case END_SHOWING:
                    return "END_SHOWING";

                default:
                    return null;
            }
        }

    }

