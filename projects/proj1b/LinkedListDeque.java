public class LinkedListDeque<T> implements Deque<T> {
    private class DNode {
        public T item;
        public DNode prev, next;

        public DNode(T i, DNode p, DNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private DNode sentinel = new DNode(null, null, null);
    private int size;

    public LinkedListDeque() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel.prev = new DNode(x, sentinel, sentinel);
        sentinel.next = sentinel.prev;
        size = 1;
    }

    public LinkedListDeque(LinkedListDeque<T> other){
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for (int i = 0; i < other.size(); i += 1) {
            this.addLast( other.get(i));
        }
    }

	@Override
    public void addFirst(T x) {
        sentinel.next = new DNode(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

	@Override
    public void addLast(T x) {
        sentinel.prev = new DNode(x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

	@Override
    public int size() {
        return size;
    }

	@Override
    public void printDeque() {
        DNode p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

	@Override
    public T removeFirst() {
        T value = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return value;
    }

	@Override
    public T removeLast() {
        T value = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return value;
    }

	@Override
    public T get(int index) {
        DNode p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    private T getRecursive(int index, DNode p) {
        if (index == 0) {
            return p.item;
        }
        else {
            return getRecursive(index - 1, p.next);
        }
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }
}
