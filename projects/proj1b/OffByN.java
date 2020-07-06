public class OffByN implements CharacterComparator {
    private int diff;

    public OffByN(int N) {
        diff = N;
    }

    public boolean equalChars(char x, char y) {
        if (Math.abs(y - x) == diff) {
            return true;
        } else {
            return false;
        }
    }
}
