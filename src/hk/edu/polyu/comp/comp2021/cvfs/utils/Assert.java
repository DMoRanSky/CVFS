package hk.edu.polyu.comp.comp2021.cvfs.utils;

/**
 * Assertion class: to throw some format Exception
 */
public class Assert {
    /**
     * @param u: String (need to be all letter)
     * @throws Exception -
     */
    public static void checkLetter(String u) throws Exception {
        for (Character c : u.toCharArray()) {
            if (!Character.isLetter(c))
                throw new Exception(u + " must only contains letter");
        }
    }

    /**
     * @param u: String (needed to be all digit or letter)
     * @throws Exception -
     */
    public static void AssertDigitLetter(String u) throws Exception {
        for (Character c : u.toCharArray()) {
            if (!(Character.isLetter(c) || Character.isDigit(c)))
                throw new Exception(u + " must only contains letter and digits");
        }
    }

    /**
     * @param u: String (need to be remove " from both side)
     * @return -
     * @throws Exception -
     */
    public static String RevDoubleQuote(String u) throws Exception {
        if (u.length() >= 3 && u.charAt(0) == '\"' && u.charAt(u.length() - 1) == '\"')
            return u.substring(1, u.length() - 1);
        throw new Exception("Val must be a string in the double\n" +
                " quote");
    }

    /**
     * @param u: String
     * @param l: length need to be l
     * @throws Exception -
     */
    public static void AssertLength(String u, int l) throws Exception {
        if (u.length() > l) throw new Exception("Name can not be greater than " + l);
    }

    /**
     * @param type : name (type name need to be one of four type)
     * @throws Exception -
     */
    public static void AssertType(String type) throws Exception {
        String[] tp = {"txt", "java", "html", "css"};
        boolean ok = false;
        for (String s : tp) {
            if (s.equals(type)) ok = true;
        }
        if (!ok) throw new Exception(type + " is not a valid type");
    }
}
