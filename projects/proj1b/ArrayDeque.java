public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    public ArrayDeque(){
        items = (T []) new Object[8];
        size = 0;
    }

    public ArrayDeque(ArrayDeque<T> other){
        items = (T []) new Object[other.size()];
        for (int i = 0; i < other.size(); i += 1) {
            this.addLast(other.get(i));
        }
    }

    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

	@Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        T[] a = (T []) new Object[items.length];
        System.arraycopy(items, 0, a, 1, size);
        a[0] = x;
        items = a;
        size += 1;
    }

	@Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = x;
        size += 1;
    }

	@Override
    public int size() {
        return size;
    }

	@Override
    public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

	@Override
    public T removeFirst() {
        T value = items[0];
        if (size <= items.length/4) {
            resize(size / 2);
        }
        T[] a = (T []) new Object[items.length];
        System.arraycopy(items, 1, a, 0, size - 1);
        items = a;
        size -= 1;
        return value;
    }

	@Override
    public T removeLast() {
        T value = items[size - 1];
        if (size <= items.length/4) {
            resize(size / 2);
        }
        items[size - 1] = null;
        size -= 1;
        return value;
    }

	@Override
    public T get(int index) {
        return items[index];
    }
}
