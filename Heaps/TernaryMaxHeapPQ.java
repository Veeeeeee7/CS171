import java.util.ArrayDeque;
import java.util.Iterator;

public class TernaryMaxHeapPQ<Item extends Comparable<Item>> implements Iterable<Item> {

    Item[] items;
    int size;

    public TernaryMaxHeapPQ(int capacity) {
        items = (Item[]) (new Comparable[capacity + 1]);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean less(int v, int w) {
        return (items[v]).compareTo(items[w]) < 0;
    }

    private void exch(int i, int j) {
        Item tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    public void put(Item item) {
        items[size + 1] = item;
        size++;
        swim(size);
    }

    public Item remove() {
        Item n = items[1];
        items[1] = items[size];
        items[size] = null;
        size--;
        sink(1);
        return n;
    }

    private void sink(int k) {
        ///////////////////
        // ADD CODE HERE //
        ///////////////////
        int child1 = 3 * k - 1;
        int child2 = 3 * k;
        int child3 = 3 * k + 1;

        while ((child1 <= size && less(k, child1)) || (child2 <= size && less(k, child2)) || (child3 <= size
                && less(k, child3))) {
            int largestChild = child1;
            if (child2 <= size && less(child1, child2)) {
                largestChild = child2;
                if (child3 <= size && less(child2, child3)) {
                    largestChild = child3;
                }
            } else if (child3 <= size && less(child1, child3)) {
                largestChild = child3;
            }

            Item temp = items[largestChild];
            items[largestChild] = items[k];
            items[k] = temp;
            k = largestChild;
            child1 = 3 * k - 1;
            child2 = 3 * k;
            child3 = 3 * k + 1;
        }
    }

    private void swim(int k) {
        ///////////////////
        // ADD CODE HERE //
        ///////////////////
        int parent = k % 3 == 2 ? k / 3 + 1 : k / 3;
        // System.out.println("PARENT INDEX: " + parent);
        while (k > 1 && less(parent, k)) {
            Item temp = items[parent];
            items[parent] = items[k];
            items[k] = temp;
            k = parent;
            parent = k % 3 == 2 ? k / 3 + 1 : k / 3;
        }
    }

    private Iterable<Item> itemsPostOrder() {
        ArrayDeque<Item> q = new ArrayDeque<Item>();
        enqueueItemsPostOrder(1, q);
        return q;
    }

    private void enqueueItemsPostOrder(int k, ArrayDeque<Item> q) {
        ///////////////////
        // ADD CODE HERE //
        ///////////////////
        if (3 * k - 1 <= size) {
            enqueueItemsPostOrder(3 * k - 1, q);
        }
        if (3 * k <= size) {
            enqueueItemsPostOrder(3 * k, q);
        }
        if (3 * k + 1 <= size) {
            enqueueItemsPostOrder(3 * k + 1, q);
        }
        q.add(items[k]);

    }

    @Override
    public Iterator<Item> iterator() {
        return itemsPostOrder().iterator();
    }

    public String toString() {
        String s = "";
        for (int i = 1; i <= size; i++) {
            s += items[i] + " ";
        }
        return s;
    }

    public String postOrderString() {
        String s = "";
        for (Item item : itemsPostOrder()) {
            s += item + " ";
        }
        return s;
    }

    public static void main(String[] args) {
        Integer[] nums = { 81, 94, 15, 74, 93, 98, 9, 0, 96, 55, 88, 33, 77, 43, 71, 36, 88, 12, 11, 6, 58, 69, 83, 67,
                0, 66, 50 };
        System.out.println("integers ordered as put into priority queue:");
        for (Integer j : nums)
            System.out.print(j + " ");
        System.out.println();

        TernaryMaxHeapPQ<Integer> pq = new TernaryMaxHeapPQ<Integer>(30);
        for (int i = 0; i < 27; i++) {
            pq.put(nums[i]);
        }
        System.out.println();

        System.out.println("internal array (in order):" + System.lineSeparator() + pq);
        System.out.println();

        System.out.println("heap traversed 'post-order':" + System.lineSeparator() + pq.postOrderString());
        System.out.println();

        System.out.println("elements seen upon repeated removals:");
        while (!pq.isEmpty()) {
            System.out.print(pq.remove() + " ");
        }
    }
}
// HELPED BY: NOBODY