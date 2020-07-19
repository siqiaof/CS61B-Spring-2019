public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        char[] charInput = input.toCharArray();
        StringBuilder stb = new StringBuilder();
        int i = 0;
        for (; i < pattern.length(); i++) {
            stb.append(charInput[i]);
        }
        RollingString in = new RollingString(stb.toString(), pattern.length());
        RollingString pa = new RollingString(pattern, pattern.length());
        if (in.hashCode() == pa.hashCode()) {
            if (in.equals(pa)) {
                return 0;
            }
        }
        for (; i < input.length(); i++) {
            in.addChar(charInput[i]);
            if (in.hashCode() == pa.hashCode()) {
                if (in.equals(pa)) {
                    return i - pattern.length() + 1;
                }
            }
        }
        return -1;
    }
}
