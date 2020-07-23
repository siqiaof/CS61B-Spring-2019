package bearmaps;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private static class Node<T> {
        private final T item;
        private final double pri;

        public Node(T item, double pri) {
            this.item = item;
            this.pri = pri;
        }

        public T item() {
            return item;
        }

        public double priority() {
            return pri;
        }
    }

    private Node<T>[] heap = new Node[8];
    private int size;
    private HashMap<T, Integer> items;

    public ArrayHeapMinPQ() {
        size = 0;
        items = new HashMap<>();
    }

    private void doubleSize() {
        Node<T>[] temp = new Node[2 * heap.length];
        System.arraycopy(heap, 1, temp, 1, heap.length - 1);
        heap = temp;
    }

    private void halfSize() {
        Node<T>[] temp = new Node[heap.length/2];
        System.arraycopy(heap, 1, temp, 1, temp.length - 1);
        heap = temp;
    }

    private void shiftUp(int position) {
        while (position != 1 && heap[position].priority() < heap[position/2].priority()) {
            Node<T> temp = heap[position/2];
            heap[position/2] = heap[position];
            heap[position] = temp;
            items.replace(heap[position].item(), position);
            position /= 2;
        }
        items.put(heap[position].item(), position);
    }

    private void shiftDown(int position) {
        Node<T> temp;
        while (position * 2 <= size) {
            if (size >= position * 2 + 1) {
                if (heap[position * 2].priority() < heap[position * 2 + 1].priority()) {
                    if (heap[position].priority() < heap[position * 2].priority()) {
                        break;
                    } else {
                        temp = heap[position];
                        heap[position] = heap[position * 2];
                        items.replace(heap[position].item, position);
                        heap[position * 2] = temp;
                        position = position * 2;
                    }
                } else {
                    if (heap[position].priority() < heap[position * 2 + 1].priority()) {
                        break;
                    } else {
                        temp = heap[position];
                        heap[position] = heap[position * 2 + 1];
                        items.replace(heap[position].item, position);
                        heap[position * 2 + 1] = temp;
                        position = position * 2 + 1;
                    }
                }
            } else {
                if (heap[position].priority() < heap[position * 2].priority()) {
                    break;
                } else {
                    temp = heap[position];
                    heap[position] = heap[position * 2];
                    items.replace(heap[position].item, position);
                    heap[position * 2] = temp;
                    position = position * 2;
                }
            }
        }
        items.replace(heap[position].item, position);
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        if (size()/(double) heap.length >= 0.75) {
            doubleSize();
        }
        size += 1;
        heap[size] = new Node<>(item, priority);
        shiftUp(size);
    }

    @Override
    public boolean contains(T item) {
        return items.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return heap[1].item();
    }

    @Override
    public T removeSmallest() {
        T returnValue = getSmallest();
        items.remove(returnValue);
        Node<T> temp = heap[size];
        heap[size] = null;
        heap[1] = temp;
        size -= 1;
        shiftDown(1);

        if (size / (double) heap.length <= 0.5) {
            halfSize();
        }
        return returnValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        } else {
            heap[items.get(item)] = new Node<>(item, priority);
            shiftUp(items.get(item));
            shiftDown(items.get(item));
        }
    }

    public void printSimple() {
        int depth = ((int) (Math.log(heap.length) / Math.log(2)));
        int level = 0;
        int itemsUntilNext = (int) Math.pow(2, level);
        for (int j = 0; j < depth; j++) {
            System.out.print(" ");
        }

        for (int i = 1; i <= size(); i++) {
            System.out.printf("%2.1f ", heap[i].priority());
            if (i == itemsUntilNext) {
                System.out.println();
                level++;
                itemsUntilNext += Math.pow(2, level);
                depth--;
                for (int j = 0; j < depth; j++) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }
}
