package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides a method to check if a string matches certain conditions.
 * 
 * @see Checker#checkDateFormat(java.lang.String) 
 * @see Checker#checkIntegerNumber(java.lang.String) 
 * @see Checker#checkSpecialCharacter(java.lang.String) 
 * @see Checker#checkValidDepartment(java.lang.String) 
 * @see Checker#checkValidDoubleNumber(java.lang.String) 
 * @see Checker#checkValidPhoneNumber(java.lang.String) 
 * @see Checker#checkValidStringWithBlank(java.lang.String) 
 * @see Checker#checkValidStringWithoutBlank(java.lang.String) 
 * @author N.Minh
 */
public class Checker {

    /**
     * Return true if string has special character(s).
     *
     * @param str
     * @return true if string has special character(s).
     */
    public static boolean checkSpecialCharacter(String str) {
        String regex = "[!@#$%^&*(),'.?\":{}|<>]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
    }

    /**
     * Return true if imported string is not empty, does not have special
     * character(s).
     *
     * @param str
     * @return true if imported string is not empty, does not have special
     * character(s).
     */
    public static boolean checkValidStringWithBlank(String str) {

        boolean check1 = false;
        boolean check2 = false;
        if (!str.isEmpty()) {
            check1 = true;
        } else {
            System.out.println("Cannot be left blank.");
        }

        if (checkSpecialCharacter(str) == false) {
            check2 = true;
        } else {
            System.out.println("Cannot contain special character.");
        }

        return check1 && check2;

    }

    /**
     * Return true if imported string is not empty and is
     * an integer number, does not have special character(s) or blank inside.
     *
     * @param str
     * @return true if imported string is not empty is an integer number, does not have special character(s) or blank inside.
     */
    public static boolean checkIntegerNumber(String str) {
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;
        boolean check4 = true;

        if (!str.isEmpty()) {
            check1 = true;
        } else {
            System.out.println("Cannot be left blank.");
        }

        if (!checkSpecialCharacter(str)) {
            check2 = true;
        } else {
            System.out.println("Number cannot have special character.");
        }

        if (!str.contains(" ")) {
            check3 = true;
        } else {
            System.out.println("Number must not contain blank.");
        }

        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                System.out.println("Number cannot contain letter.");
                check4 = false;
                break;
            }
        }

        return check1 && check2 && check3 && check4;
    }

    /**
     * Return true if imported string is not empty and is
     * an double number, does not have special character(s) or blank inside.
     *
     * @param str
     * @return true if imported string is not empty and is an double number, does not have special character(s) or blank inside.
     * character(s).
     */
    public static boolean checkValidDoubleNumber(String str) {
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;
        boolean check4 = true;
        if (!str.isEmpty()) {
            check1 = true;
        } else {
            System.out.println("Cannot be left blank.");
        }
        if (checkSpecialCharacter(str)) {
            System.out.println("Number cannot have special character.");
        } else {
            check2 = true;
        }
        if (!str.contains(" ")) {
            check3 = true;
        } else {
            System.out.println("Cannot contain blank. Please try again.");
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                System.out.println("Number cannot contain letter.");
                check4 = false;
                break;
            }
        }
        return check1 && check2 && check3 && check4;
    }

    /**
     * Return true if imported string is not empty, does not have special
     * character(s) and blank inside.
     *
     * @param str
     * @return true if imported string is not empty, does not have special
     * character(s) and blank inside.
     */
    public static boolean checkValidStringWithoutBlank(String str) {
        boolean check1 = false;
        boolean check2 = false;

        if (checkValidStringWithBlank(str)) {
            check1 = true;
        }
        if (!str.contains(" ")) {
            check2 = true;
        } else {
            System.out.println("Cannot contain blank. Please try again.");
        }

        return check1 && check2;
    }

    /**
     * Return true if imported string is not empty, does not have special
     * character(s) and has length from 3 to 50.
     *
     * @param str
     * @return true if imported string is not empty, does not have special character(s) and has length from 3 to 50..
     */
    public static boolean checkValidDepartment(String str) {
        boolean check1 = false;
        boolean check2 = false;

        if (checkValidStringWithBlank(str)) {
            check1 = true;
        }

        if ((str.length() >= 3) && (str.length() <= 50) && (!(str.length() == 0))) {
            check2 = true;
        } else {
            System.out.println("Department must contain 3-50 characters.");
        }

        return check1 && check2;
    }

    /**
     * Return true if the imported phone number meets the 
     * conditions in ... and starts with "0".
     * @param PhoneNumber
     * @return true if the imported phone number meets the conditions in ... and starts with "0".
     */
    public static boolean checkValidPhoneNumber(String PhoneNumber) {
        boolean check1 = false;
        boolean check2 = false;

        if (checkValidStringWithoutBlank(PhoneNumber)) {
            check1 = true;
        }
        if (PhoneNumber.startsWith("0")) {
            check2 = true;
        } else {
            System.out.println("Invalid phone number.");
        }

        return check1 && check2;
    }

    /**
     * Return true if the imported date is valid with
     * formate "dd/MM/yyyy" and accuracy of date field.
     * @param date
     * @return 
     */
    public static boolean checkDateFormat(String date) {

        String dateFormatPattern = "dd/MM/yyyy";
        SimpleDateFormat dateFormate = new SimpleDateFormat(dateFormatPattern);
        dateFormate.setLenient(false);                          //prevent accepting invalid dateformat.
        
        try {
            if(date.matches("[0-9]{2}/[0-1][0-9]/[1-9][0-9]{3}")){
                dateFormate.parse(date);
                return true;
            }else{
                System.out.println("Wrong date format!");
                return false;
            }
            
        } catch (ParseException e) {
            System.out.println("Error occur: " + e.getMessage());
            return false;
        }
    }
}
