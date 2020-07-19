import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V>{
    private class Node {
        private K key;
        private V val;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private LinkedList<Node>[] buckets;
    private final int initialSize;
    private int bucketSize;
    private final double loadFactor;
    private Set<K> keys;

    public MyHashMap() {
        initialSize = 16;
        bucketSize = initialSize;
        loadFactor = 0.75;
        buckets = (LinkedList<Node>[]) new LinkedList[initialSize];
        keys = new HashSet<>();
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        bucketSize = initialSize;
        loadFactor = 0.75;
        buckets = (LinkedList<Node>[]) new LinkedList[initialSize];
        keys = new HashSet<>();
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        bucketSize = initialSize;
        this.loadFactor = loadFactor;
        buckets = (LinkedList<Node>[]) new LinkedList[initialSize];
        keys = new HashSet<>();
    }

    @Override
    public void clear() {
        buckets = (LinkedList<Node>[]) new LinkedList[bucketSize];
        keys = new HashSet<>();
    }

    @Override
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % bucketSize;
    }

    @Override
    public V get(K key) {
        LinkedList<Node> bucket = buckets[hash(key)];
        if (bucket != null) {
            for (Node n : bucket) {
                if (key.equals(n.key)) {
                    return n.val;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return keys.size();
    }

    private void resize() {
        MyHashMap<K, V> newMap = new MyHashMap(bucketSize*2);
        for (K key: keys) {
            newMap.putWithoutCheck(key, get(key));
        }
        buckets = newMap.buckets;
        keys = newMap.keySet();
        bucketSize *= 2;
    }

    private void putWithoutCheck(K key, V value) {
        LinkedList<Node> bucket = buckets[hash(key)];
        if (bucket == null) {
            buckets[hash(key)] = new LinkedList<>();
            buckets[hash(key)].add(new Node(key, value));
        } else {
            bucket.add(new Node(key, value));
        }
        keys.add(key);
    }

    @Override
    public void put(K key, V value) {
        LinkedList<Node> bucket = buckets[hash(key)];
        if (containsKey(key)) {
            for (Node n : bucket) {
                if (n.key == key) {
                    n.val = value;
                }
            }
        } else {
            if (bucket == null) {
                buckets[hash(key)] = new LinkedList<>();
                buckets[hash(key)].add(new Node(key, value));
            } else {
                bucket.add(new Node(key, value));
            }
            keys.add(key);
        }
        if (size()/(double) bucketSize >= loadFactor) {
            resize();
        }
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }
}
