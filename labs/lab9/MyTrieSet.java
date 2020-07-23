import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private class Node {
        private boolean isKey;
        private HashMap<Character, Node> map = new HashMap<>();

        public Node(boolean isKey) {
            this.isKey = isKey;
        }
    }

    private Node root;

    public MyTrieSet() {
        root = new Node(false);
    }

    @Override
    public void clear() {
        root = new Node(false);
    }

    @Override
    public boolean contains(String key) {
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return curr.isKey;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    private void colHelp(String s, List<String> x, Node n) {
        if (n.isKey) {
            x.add(s);
        }
        for (char c : n.map.keySet()) {
            colHelp(s + c, x, n.map.get(c));
        }
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            curr = curr.map.get(prefix.charAt(i));
        }
        List<String> x = new ArrayList<>();
        for (char c : curr.map.keySet()) {
            colHelp(prefix + c, x, curr.map.get(c));
        }
        return x;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
        List<String> l = t.keysWithPrefix("he");
        for (String s : l) {
            System.out.println(s);
        }
    }
}
