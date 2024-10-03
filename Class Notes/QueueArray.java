import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class QueueArray<Item> implements Iterable<Item>, Queue<Item> {
    private Item[] items;
    private int head;
    private int tail;
    private int size;
    private int modCount;

    public QueueArray() {
        items = (Item[]) (new Object[1]); // java cant make a generic array

        head = 0;
        tail = 0;
        size = 0;

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size();
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) (new Object[capacity]);
        for (int i = 0; i < size; i++) {
            newArray[i] = items[(head + i) % items.length]; // translate indices to the beginning
        }
        items = newArray;
        head = 0;
        tail = size;
    }

    @Override
    public void enqueue(Item item) {
        if (size == items.length - 1) {
            resize(2 * items.length);
        }
        items[tail++] = item;
        if (tail == items.length) { // loops back around
            tail = 0;
        }
        size++;
        modCount++;
    }

    @Override
    public Item dequeue() {
        Item itemToReturn = items[head];
        items[head] = null;
        head = head == items.length - 1 ? 0 : head + 1;
        if (size != 0 && size == items.length / 4) {
            this.resize(items.length / 2);
        }
        size--;
        modCount--;
        return itemToReturn;
    }

    @Override
    public Iterator<Item> iterator() {
        return (new Iterator<Item>() {
            private int pos = head;
            private int count = 0;
            private int savedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != savedModCount)
                    throw new ConcurrentModificationException();
                return count < size;
            }

            @Override
            public Item next() {
                if (modCount != savedModCount)
                    throw new ConcurrentModificationException();
                Item item = items[pos++];
                if (pos == items.length) {
                    pos = 0;
                }
                count++;
                return item;
            }
        });
    }

    public static void main(String[] args) {
        QueueArray<String> q = new QueueArray<>();
        q.enqueue("AA");
        q.enqueue("BB");
        q.enqueue("CC");
        q.enqueue("DD");

        for (String s : q) {
            System.out.println(s);
        }

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}
