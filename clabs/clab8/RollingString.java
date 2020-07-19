/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;
    char[] rollingString;
    int hash;

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {
        assert(s.length() == length);
        rollingString = s.toCharArray();
        hash = 0;
        int i = 0;
        for (char c : rollingString) {
            hash += (int) c * Math.pow(UNIQUECHARS, i);
            i += 1;
        }
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        hash -= rollingString[0];
        char[] temp = rollingString;
        System.arraycopy(temp,1, rollingString, 0, temp.length-1);
        rollingString[temp.length-1] = c;
        hash = (int) (hash/UNIQUECHARS + (int) c * Math.pow(UNIQUECHARS, length() - 1));
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(rollingString);
        return strb.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        return rollingString.length;
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o == null) {
            return false;
        } else if (o.getClass() != this.getClass()) {
            return false;
        }
        RollingString other = (RollingString) o;
        return this.toString().equals(other.toString());
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        return hash % PRIMEBASE;
    }
}
