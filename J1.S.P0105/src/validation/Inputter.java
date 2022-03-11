package validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                        return "";
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
     * @param isEmpty
     * @return
     */
    public static int inputInteger(String msg, boolean isNegative, boolean isEmpty) {
        int result;
        while (true) {
            try {
                String string = inputString(msg, isEmpty);
                if (string.isEmpty()) {
                    return -1;
                }
                result = Integer.parseInt(string);
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
    
    /**
     *
     * @param msg
     * @param isNegative
     * @return
     */
    public static double inputDouble(String msg, boolean isNegative, boolean isEmpty) {
        double result;
        while (true) {
            try {
                String str = inputString(msg, isEmpty);
                
                if (isEmpty && str.isEmpty()) {
                    return 0;
                }
                
                result = Double.parseDouble(str);
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
     * delete space between name, capitalize the first letter in name
     *
     * @param s
     * @return
     */
    public static String beautyName(String s) {
        StringTokenizer stk = new StringTokenizer(s, " ");
        String result = "";
        while (stk.hasMoreElements()) {
            String string = stk.nextToken();
            result += string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase() + " ";
        }
        return result.trim();
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
     * @param min
     * @param max
     * @param isEmpty
     * @return
     */
    public static int inputIntegerInRange(String msg, int min, int max, boolean isEmpty) {
        int result;
        while (true) {
            try {
                String string = inputString(msg, isEmpty);

                if (string.isEmpty()) {
                    return -1;
                } else {
                    result = Integer.parseInt(string);
                }
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
     * @param yesMsg
     * @param noMsg
     * @return
     */
    public static boolean inputYesNo(String msg, String yesMsg, String noMsg) {
        String result = "";
        while (!result.equalsIgnoreCase(yesMsg) && !result.equalsIgnoreCase(noMsg)) {
            result = inputString(msg, true);
            if (result.isEmpty()) {
                return false;
            }
        }
        return result.equalsIgnoreCase(yesMsg);
    }

    public static String inputPassword(String msg) {
        while (true) {
            System.out.print(msg);
            String ans = inputString(msg, false);
            if (checkPasswordValid(ans)) {
                return ans;
            } else {
                System.err.println("The password must contain at least 6 characters, including uppercase letters \n"
                        + "lower case letters, numeric characters and 1 special character. ");
            }
        }

    }

    //the password must contain at least 6 characters, including uppercase letters, 
    //lower case letters, numeric characters and 1 special character. 
    public static boolean checkPasswordValid(String password) {
        String specialCharacter = "~`!@#$%^&*()-_+={}[]|\\/:;\"''<>.,?";
        if (password.length() < 6) {
            return false;
        }
        if (password.toLowerCase().equals(password)) {
            return false;
        }
        boolean isContainSpecial = false;
        boolean isContainDigit = false;
        for (int i = 0; i < password.length(); i++) {
            if (specialCharacter.contains(password.substring(i, i + 1))) {
                isContainSpecial = true;
            }
            if (password.substring(i, i + 1).matches("\\d")) {
                isContainDigit = true;
            }
        }
        return (isContainDigit && isContainSpecial);
    }

    // get date from screen
    public static Date getDate(String msg, boolean isEmpty) {
        Date ans;
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        while (true) {
            try {
                String input = inputString(msg, isEmpty);
                if (isEmpty && input == "") {
                    return null;
                } 
                ans = simpleDateFormat.parse(input);
                return ans;
            } catch (ParseException e) {
            }
        }
    }
}
