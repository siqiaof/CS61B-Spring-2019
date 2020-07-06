import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        CharacterComparator cc1 = new OffByOne();
        CharacterComparator cc5 = new OffByN(5);
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("Racecar"));
        assertFalse(palindrome.isPalindrome("hello world"));

        assertTrue(palindrome.isPalindrome("flake", cc1));
        assertFalse(palindrome.isPalindrome("noon", cc1));

        assertTrue(palindrome.isPalindrome("afwaf", cc5));
        assertFalse(palindrome.isPalindrome("fhwfh", cc5));

    }
}