/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0011;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Legion
 */
public class P0011 {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        // TODO code application logic here       
        boolean isRepeat ;
        //loop until user stop the program
        do {
            System.out.println("=====Change Base System======");
            //step1: choose input base system
            int baseIn = getBase("Get base in:");
            //step2:choose output base system
            int baseOut = getBase("Get base out:");
            //step3:input value
            String valueIn = inputValue(baseIn);
            //step4:change base number
            String valueOut = changeBase(baseIn, baseOut, valueIn);
            //step5:print the output
            if (valueIn.equals("0")) {
                display(baseIn, baseOut, valueIn, "0");
            } else {
                display(baseIn, baseOut, valueIn, valueOut);
            } 
            
            //step6:ask user if they want to repeat the process
            isRepeat = inputYesNo();
        } while (isRepeat);

    }

    //change the value from baseIn to baseOut
    public static String changeBase(int baseIn, int baseOut, String value) {
        value = value.trim();
        if (baseIn == baseOut)//not change base
        {
            return value;
        }

        if (baseIn == 10 && baseOut == 2)//change decimal number to binary number
        {
            return changeBaseDecTo(value, baseOut + "");
        }

        if (baseIn == 2 && baseOut == 10)//change binary number to decimal number
        {
            return changeBaseBinToDec(value);
        }

        if (baseIn == 10 && baseOut == 16)//change decimal number to hexadecimal number
        {
            return changeBaseDecTo(value, baseOut + "");
        }

        if (baseIn == 16 && baseOut == 10)//change hexa number to decimal number
        {
            return changeBaseToDec(value, "" + baseOut);
        }

        if (baseIn == 2 && baseOut == 16)//change binary number to hexa number
        {
            return changeBaseBinToHex(value);
        }

        if (baseIn == 16 && baseOut == 2)//change hexa number to binary number
        {
            return changeBaseToDec(value, "" + baseOut);
        }
        return null;
    }

    //change the value from base hexa to binary
    public static String changeBaseHexToBin(String value) {
        value = value.trim();
        Map<String, String> map = new HashMap<>();
        map.put("0", "0000");
        map.put("1", "0001");
        map.put("2", "0010");
        map.put("3", "0011");
        map.put("4", "0100");
        map.put("5", "0101");
        map.put("6", "0110");
        map.put("7", "0111");
        map.put("8", "1000");
        map.put("9", "1001");
        map.put("A", "1010");
        map.put("B", "1011");
        map.put("C", "1100");
        map.put("D", "1101");
        map.put("E", "1110");
        map.put("F", "1111");
        String answer = "";
        //loop from begin to end of value
        for (int i = 0; i < value.length(); i++) {
            answer += map.get(String.valueOf(value.charAt(i)));
        }
        return answer;
    }

    //change the value from base  binary to hexa
    public static String changeBaseBinToHex(String value) {
        value = value.trim();
        Map<String, String> map = new HashMap<String, String>();
        map.put("0000", "0");
        map.put("0001", "1");
        map.put("0010", "2");
        map.put("0011", "3");
        map.put("0100", "4");
        map.put("0101", "5");
        map.put("0110", "6");
        map.put("0111", "7");
        map.put("1000", "8");
        map.put("1001", "9");
        map.put("1010", "A");
        map.put("1011", "B");
        map.put("1100", "C");
        map.put("1101", "D");
        map.put("1110", "E");
        map.put("1111", "F");
        //split value into groups of 4
        while ((value.length() % 4) != 0) {
            value = "0" + value;
        }
        String answer = "";
        //loop from begin to end of value
        for (int i = 0; i < value.length(); i += 4) {
            answer = answer + map.get(value.substring(i, i + 4));
        }
        
        
        return answer;
    }
    
    //change the value from base binary to decimal
    public static String changeBaseBinToDec(String value) {
        value = value.trim();
        BigInteger answer = new BigInteger("0");
        //loop from begin to end of value
        for (int i = 0; i < value.length(); i++) {
            
            answer = answer.multiply(new BigInteger("2"));
            //case the bit of value is 1
            if (value.charAt(i) == '1') {
                answer = answer.add(new BigInteger("1"));
            }
        }
        //System.out.println(" = "+ answer);
        return answer.toString();
    }

    //change the value from base hexa to another base
    public static String changeBaseToDec(String value, String baseout) {
        value = value.trim();
        BigInteger answer = new BigInteger("0");
        String remainder = "0123456789ABCDEF";
        //loop from begin to end of value
        for (int i = 0; i < value.length(); i++) {
            answer = answer.multiply(new BigInteger(baseout));
            answer = answer.add(new BigInteger(Integer.toString(remainder.indexOf(value.charAt(i)))));
        }
        //System.out.println(" = "+ answer);
        return answer.toString();
    }

    //change the value from base decimal to another base
    public static String changeBaseDecTo(String value, String base) {
        value = value.trim();
        String answer = "";
        BigInteger decNum = new BigInteger(value);
        //loop until the decNum equal 0
        while (decNum.compareTo(new BigInteger("0")) > 0) { 
            answer = decNum.mod(new BigInteger(base)).toString() + answer;
            decNum = decNum.divide(new BigInteger(base));
        }
        return answer;
    }

    public static void display(int bin, int bout, String vin, String vout) {
        String basei = "";
        // the base is binary
        if (bin == 2) {
            basei = "bin";
        } else if (bin == 10)//the base is decimal
        {
            basei = "dec";
        } else //the base is hexadecimal
        {
            basei = "hex";
        }
        String baseo;
        // the base is binary
        if (bout == 2)
        {
            baseo = "bin";
        } else if (bout == 10)//the base is decimal
        {
            baseo = "dec";
        } else //the base is hexadecimal
        {
            baseo = "hex";
        }
        System.out.println("=>" + vin + " (" + basei + ") = " + vout + " (" + baseo + ")");
    }

    public static int getBase(String mess) {
        System.out.println("1: binary");
        System.out.println("2: decimal");
        System.out.println("3: hexadecimal");

        System.out.print(mess);
        int choose = 0;
        String data = "";
        //check until the input is correct
        while (true) {
            //check the wrong input: out of range, null, string
            try {
                data = scan.nextLine();
                choose = Integer.parseInt(data);//exception if the data is not an integer
                //base is binary
                if (choose == 1) {
                    return 2;
                }
                //base is decimal
                if (choose == 2) {
                    return 10;
                }
                //base is hexadecimal
                if (choose == 3) {
                    return 16;
                }
                System.err.println("The input is not a base!Re-enter:");
            } catch (Exception e)//the input is not an integer
            {
                System.err.println("The input is not correct!Re-enter:");
            }
        }
    }

    public static String inputValue(int base) {

        String ans;
        //get value until the value is correct with the base
        while (true) {
            try {
                System.out.print("Input value:");
                ans = scan.nextLine();
                //no input
                if (ans.isEmpty()) {
                    System.err.println("No input, re-enter:");
                } else if (base == 2)//the case base=2
                {
                    if (ans.matches("[0-1]+"))//the value only contain 0 and 1
                    {
                        return ans;
                    }
                } else if (base == 10)//the case base=10
                {
                    if (ans.matches("[0-9]+"))//the value contain 0 to 9
                    {
                        return ans;
                    }
                } else if (base == 16)//the case base=16
                {
                    if (ans.matches("[0-9A-F]+"))//the value contain 0 to 9 and A to F
                    {
                        return ans;
                    }
                }
                System.err.print("Invalid value!");
            } catch (Exception e) {
                System.err.print("Invalid value!Re-enter:");
            }
        }
    }

    public static boolean inputYesNo() {

        String continu = "";
        //check until user enter y or n
        while (true) {
            try {
                System.out.print("Do you want to repeat?(y/n): ");
                continu = scan.nextLine();
                if (continu.isEmpty())//no input
                {
                    System.out.print("Choose y or n:");
                } else if (continu.equals("y")) {//correct input
                    return true;
                } else if (continu.equals("n")) {//correct input
                    return false;
                }
                System.out.println("Choose y or n:");
            } catch (Exception e) {
                System.err.println("Re-enter:");
            }
        }
    }

}
