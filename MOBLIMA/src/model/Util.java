package MOBLIMA.src.model;

public class Util {
    /**
     * Method to format printing of long strings such as Movie's synopsis.
     * @param str String to be printed.
     * @param width Width for printing.
     * @return Formatted string.
     */
    public static String printLongString(String str, int width) {
        StringBuilder sb = new StringBuilder();

        while (true) {
            if (str.length() <= width) {
                sb.append(str);
                break;
            }

            int cutOff = 0;
            int temp = 0;
            while(((temp = str.indexOf(" ", ++temp)) < width) && (temp >= 0))
            {
                cutOff = temp;
            }

            //Extra check in case the word itself is longer than the wrap
            if(cutOff == 0)
            {
                sb.append(str);
                return sb.toString();
            }
            String newString = str.substring(0, cutOff);

            sb.append(newString);
            sb.append("\n\t\t");
            str = str.substring(cutOff);
        }

        return sb.toString();
    }

    /**
     * Method to print a separator.
     */
    public static void printSep(){
        System.out.println("================================================");
    }
}
