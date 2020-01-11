package tk.vky.epublearning.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
public class StringUtils {

    static public int LEFT = 1;
    static public int RIGHT = 2;

    /**
     * To split a String into an array of strings based on the delimeter.
     *
     * @param mainString String that needs to be split
     * @param delim Delimiter to use. If you do not have a textQualifier set it
     * to '*'
     * @param textQualifier delimeters wrapped inside a textQualifier are
     * ignored as delimeters
     * @return Returns the array of Strings after splitting
     */
    static public String[] split(String mainString, char delim, char textQualifier) {
        ArrayList<String> finalArray = new ArrayList<>();
        String tmpString = "";
        boolean hasTextQualifier = false;
        if (textQualifier != '*') { // the * is set when you dont have a textQualifier
            hasTextQualifier = true;
        }
        boolean insideText = false;
        for (int x = 0; x < mainString.length(); x++) {
            if (mainString.charAt(x) == textQualifier && hasTextQualifier) {
                if (insideText) {
                    insideText = false;
                } else {
                    insideText = true;
                }
            } else if (mainString.charAt(x) == delim && (!insideText && hasTextQualifier || !hasTextQualifier)) {
                finalArray.add(tmpString.trim());
                tmpString = "";
            } else {
                tmpString += mainString.charAt(x);
            }
        }
        finalArray.add(tmpString);
        String[] returnArray = new String[finalArray.size()];
        for (int x = 0; x < finalArray.size(); x++) {
            returnArray[x] = finalArray.get(x);
        }
        return returnArray;
    }

    /**
     * Rounds a double to the No of digits
     *
     * @param originalNumber double to be rounded
     * @param noOfDigits no of digits to round to
     * @return returns the rounded number in String format
     */
    static public String roundString(double originalNumber, int noOfDigits) {
        String tmp = Double.toString(round(originalNumber, noOfDigits));
        if (tmp.length() - 1 - tmp.indexOf(".") < noOfDigits) {
            return pad(tmp, '0', noOfDigits + 1 + tmp.indexOf("."), RIGHT);
        } else {
            return tmp;
        }
    }

    /**
     * Rounds a double to the No of digits
     *
     * @param originalNumber double to be rounded
     * @param noOfDigits no of digits to round to
     * @return returns the rounded number
     */
    static public double round(double originalNumber, int noOfDigits) {
        double finalNumber = Math.round(originalNumber * Math.pow(10, noOfDigits));
        return finalNumber / (Math.pow(10, noOfDigits));
    }

    /**
     * Pads a string with the padChar either on the left or the right
     *
     * @param str String to pad
     * @param padChar pad character to use
     * @param len total length of the output string including the padded
     * characters
     * @param direction direction of padding. Use either LEFT or RIGHT
     * @return returns the output String
     */
    public static String pad(String str, char padChar, int len, int direction) {
        // direction = 1 means pad on the left side
        // direction = 2 means pad on the right side

        // len indicates the total length that you want the string to be when returned
        StringBuffer pad = new StringBuffer();
        pad.append(padChar);
        String newString = str;
        while (newString.length() < len) {
            if (direction == 1) {
                newString = pad + newString;
            } else if (direction == 2) {
                newString = newString + pad;
            }
        }
        return (newString);
    }

    /**
     * Cleans the InputStream by removing single quote, double quote and new
     * line characters.
     *
     * @param is InputStream that needs to be cleaned.
     * @return returns the cleaned String
     */
    public static InputStream cleanInputStreamForUpload(InputStream is) {
        boolean insideFlag = false;
        StringBuilder outputString = new StringBuilder();
        char[] charBuf = new char[1];
        InputStreamReader isr = new InputStreamReader(is);
        try {
            while (isr.read(charBuf) != -1) {
                switch (charBuf[0]) {
                    case '"': // double quotes
                        if (insideFlag) {
                            insideFlag = false;
                        } else {
                            insideFlag = true;
                        }
                        break;
                    case '\'': // single quote
                        outputString.append('`');
                        break;
                    case ',': // comma
                        if (insideFlag) {
                            outputString.append('.');
                        } else {
                            outputString.append(charBuf[0]);
                        }
                        break;
                    case '\n': // new line character
                        if (insideFlag) {
                            outputString.append(' ');
                        } else {
                            outputString.append(charBuf[0]);
                        }
                        break;
                    default: // any other character
                        outputString.append(charBuf[0]);
                        break;
                }
            }
        } catch (IOException e) {
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                }
            }
        }
        return IOUtils.toInputStream(outputString.toString(), Charset.defaultCharset());
    }

    /**
     * Checks if a String is either null or empty
     *
     * @param input String to check
     * @return returns true if the String was null or empty
     */
    public static boolean isBlank(String input) {
        if (input == null) {
            return true;
        } else {
            return input.isEmpty();
        }
    }

    /**
     * Checks if a String is a valid Mobile no. Must start with 7,8 or 9 and be
     * comprised of 10 digits.
     *
     * @param input String to check
     * @return true if it is a valid Mobile no and false if it is not
     */
    public static boolean isValidMobileNo(String input) {
        if (isBlank(input)) {
            return false;
        } else if (input.length() != 10) {
            return false;
        } else {
            return input.matches("[7-9][0-9]{9}");
        }
    }

    /**
     * Converts an Integer array into a String that is comma separated.
     *
     * @param intArray Int array to convert
     * @return converted String
     */
    public static String convertToString(Integer[] intArray) {
        StringBuilder sb = new StringBuilder();
        for (Integer i : intArray) {
            if (i != null) {
                sb.append("'").append(i).append("',");
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Converts a String array into a String that is comma separated
     *
     * @param strArray String array to convert
     * @return converted String
     */
    public static String convertToString(String[] strArray) {
        StringBuilder sb = new StringBuilder();
        for (String i : strArray) {
            if (i != null) {
                sb.append("'").append(i).append("',");
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Checks if the given string is a valid number. The String can only contain
     * digits and '.'
     *
     * @param input String to check
     * @return true if it is a number and false if it is not
     */
    public static boolean isNumber(String input) {
        Pattern pattern = Pattern.compile(".*[^0-9].*");
        if (StringUtils.isBlank(input)) {
            return false;
        } else if (pattern.matcher(input).matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if a given string consists of the digits and is of a certain
     * length
     *
     * @param input String to check
     * @param len the length the String must be of
     * @return true if the given input consists only of number and is of given
     * `len`
     */
    public static boolean isNumber(String input, int len) {
        Pattern digitPattern = Pattern.compile("\\d{" + len + "}");
        if (StringUtils.isBlank(input)) {
            return false;
        } else {
            return digitPattern.matcher(input).matches();
        }
    }

    /**
     * Adds the ordinal suffix to a number in caps. So 11 will become 11 TH, 22
     * will become 22 ND etc
     *
     * @param i integer that you want to add ordinal suffix to
     * @return returns the converted string
     */
    public static String appendOrdinalSuffixToInt(int i) {
        int j = i % 10;
        int k = i % 100;
        if (j == 1 && k != 11) {
            return i + " ST";
        }
        if (j == 2 && k != 12) {
            return i + " ND";
        }
        if (j == 3 && k != 13) {
            return i + " RD";
        }
        return i + " TH";
    }

    /**
     * Replaces single quotes and double quotes with the escaped sequence for
     * those quotes
     *
     * @param s String to check
     * @return escaped String
     */
    public static String escapeQuotes(String s) {
        CharSequence source1 = "\'";
        CharSequence target1 = "\\\'";
        CharSequence source2 = "\"";
        CharSequence target2 = "\\\"";
        return s.replace(source1, target1).replace(source2, target2);
    }

    public static boolean isValidEmail(String emailId) {
        String regEx = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Matcher matcherObj = Pattern.compile(regEx).matcher(emailId);
        if (matcherObj.matches()) {
            return true;
        } else {
            return false;
        }

    }

}