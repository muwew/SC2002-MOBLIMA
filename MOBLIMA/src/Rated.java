package MOBLIMA.src;
public enum Rated {

    G, PG, PG13, NC16, M18, R21;

    public String toString () {
        switch (this) {
            case G:
                return "G";

            case PG:
                return "PG";

            case PG13:
                return "PG13";

            case NC16:
                return "NC16";

            case M18:
                return "M18";

            case R21:
                return "R21";

            default:
                return null;
        }
    }
}
