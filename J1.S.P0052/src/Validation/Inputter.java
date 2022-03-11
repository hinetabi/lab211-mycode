package Validation;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Legion
 */
public class Inputter {

    private final static String ERRM = "Wrong input!";
    private final static String ERRM_NULL = "Input can not be null!";
    private final static String ERRM_NOTANINTEGER = "Input is not an integer!";
    private final static String ERRM_NOTAPOSITIVE = "Input must be positive!";
    private final static String ERRM_NOTMATCH = "Input is in wrong form!";
    private final static String ERRM_NOTINRANGE = "Number not in range!";

    /**
     *
     * @param msg
     * @param isEmpty
     * @return
     */
    public static String inputString(String msg, boolean isEmpty) {
        Scanner sc = new Scanner(System.in);
        String result;
        while (true) {
            try {
                System.out.print(msg);
                result = sc.nextLine();
                if (result.replaceAll("\\s+", "").isEmpty()) {
                    if (isEmpty) {
                        return null;
                    } else {
                        System.err.println(ERRM_NULL);
                    }
                } else {
                    return result;
                }
            } catch (Exception e) {
                System.err.println(ERRM);
            }
        }
    }

    /**
     *
     * @param msg
     * @param isNegative
     * @return
     */
    public static int inputInteger(String msg, boolean isNegative) {
        int result;
        while (true) {
            try {
                result = Integer.parseInt(inputString(msg, false));
                if (result < 0) {
                    if (isNegative) {
                        return result;
                    } else {
                        System.err.println(ERRM_NOTAPOSITIVE);
                    }
                } else {
                    return result;
                }
            } catch (NumberFormatException e) {
                System.err.println(ERRM_NOTANINTEGER);
            }
        }
    }
    
    /**
     *
     * @param msg
     * @param isNegative
     * @return
     */
    public static double inputDouble(String msg, boolean isNegative) {
        double result;
        while (true) {
            try {
                result = Double.parseDouble(inputString(msg, false));
                if (result < 0) {
                    if (isNegative) {
                        return result;
                    } else {
                        System.err.println(ERRM_NOTAPOSITIVE);
                    }
                } else {
                    return result;
                }
            } catch (NumberFormatException e) {
                System.err.println(ERRM_NOTANINTEGER);
            }
        }
    }

    //input string in form as regex
    /**
     *
     * @param msg
     * @param regex
     * @return
     */
    public static String inputStringInForm(String msg, String regex) {
        String result;
        while (true) {
            result = inputString(msg, false);
            if (result.matches(regex)) {
                return result;
            } else {
                System.err.println(ERRM_NOTMATCH);
            }
        }
    }

    /**
     *
     * @param s
     * @return
     */
    public static String normalize(String s) {
        StringTokenizer stk = new StringTokenizer(s, " ");
        String result = stk.nextToken();
        while (stk.hasMoreElements()) {
            result += " " + stk.nextToken();
        }
        return result;
    }

    /**
     *
     * @param msg
     * @param min
     * @param max
     * @return
     */
    public static int inputIntegerInRange(String msg, int min, int max) {
        int result;
        while (true) {
            try {
                result = Integer.parseInt(inputString(msg, false));
                if (result >= min && result <= max) {
                    return result;
                } else {
                    System.err.println(ERRM_NOTINRANGE);
                }
            } catch (NumberFormatException e) {
                System.err.println(ERRM_NOTANINTEGER);
            }
        }
    }

    /**
     * 
     * @param msg
     * @return 
     */
    public static boolean inputYesNo(String msg) {
        String result = "";
        while (!result.equalsIgnoreCase("y") && !result.equalsIgnoreCase("n")) {
            result = inputString(msg, false);
        }
        return result.equalsIgnoreCase("y");
    }
}
