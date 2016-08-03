package croma.com.foody.Util;

import android.support.annotation.Nullable;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//String class
//Validation class for email, alphanumeric
public final class StringUtil {



    public static final String WHITE_SPACE_REGEX = "(\\w)(\\s+)([\\.,])";
    public static final String EMAIL_REGEX = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";


    private StringUtil() {
        throw new Error("Do not need instantiate!");
    }


    /**
     * remove Special Symbol from String
     * @param string
     * @return
     */
    public static String removeSpecialSymbol(String string){
        return string.replaceAll("[^a-zA-Z0-9]", "");
    }


    /**
     * remove white spaces from String
     *
     * @param string
     * @return
     */
    public static String removeWhiteSpaces(String string) {
        return string.replaceAll(WHITE_SPACE_REGEX, "$1$3");
    }


    /**
     * Check For Valid Email
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }


    /**
     * check for string contains alphanumeric charcters or not
     *
     * @param s
     * @return
     */
    public static boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9]*$";
        return s.matches(pattern);
    }


    /**
     * Check for special Symbols
     *
     * @param password
     * @return
     */
    public static boolean isContainSpecialSymbol(String password) {

        Pattern regex = Pattern.compile("[$&+,:;=?@#|]");
        Matcher matcher = regex.matcher(password);
        return matcher.find();
    }


    /**
     * remove double quote from string
     *
     * @param text
     * @return
     */
    public static String removeDoubleQuotes(String text) {

        return text.replace("\"", "");
    }


    /**
     * @param data
     * @return
     */
    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }


    /**
     * @param s
     * @return
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return d;
    }


    /**
     * @param sourceString
     * @param keyword
     * @return
     */
    public static String keywordMadeRed(String sourceString, String keyword) {
        String result = "";
        if (sourceString != null && !"".equals(sourceString.trim())) {
            if (keyword != null && !"".equals(keyword.trim())) {
                result = sourceString.replaceAll(keyword,
                        "<font color=\"red\">" + keyword + "</font>");
            } else {
                result = sourceString;
            }
        }
        return result;
    }


    /**
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }


    /**
     * @param data
     * @return
     */
    public static boolean isMobileNumber(String data) {
        String expr = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        return data.matches(expr);
    }

    /**
     * @param data
     * @return
     */
    public static boolean isNumberLetter(String data) {
        String expr = "^[A-Za-z0-9]+$";
        return data.matches(expr);
    }

    /**
     * @param data
     * @return
     */
    public static boolean isNumber(String data) {
        String expr = "^[0-9]+$";
        return data.matches(expr);
    }

    /**
     * @param data
     * @return
     */
    public static boolean isLetter(String data) {
        String expr = "^[A-Za-z]+$";
        return data.matches(expr);
    }

    /**
     * @param data
     * @return
     */
    public static boolean isPostCode(String data) {
        String expr = "^[0-9]{6,10}";
        return data.matches(expr);
    }

    public static boolean isNullOrEmpty(@Nullable String string) {
        return string == null || string.length() == 0; // string.isEmpty() in Java 6
    }


}
