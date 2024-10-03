import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class StackArray<Item> implements Stack<Item>, Iterable<Item> {
    private Item[] items;
    private int size;
    private int modCount;

    public StackArray() {
        items = (Item[]) (new Object[1]);
        size = 0;
        modCount = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) (new Object[capacity]);
        for (int i = 0; i < size; i++) {
            newArray[i] = items[i];
        }
        items = newArray;
    }

    @Override
    public void push(Item item) {
        if (size == items.length) {
            this.resize(size * 2);
        }
        items[size++] = item;
        modCount++;
    }

    @Override
    public Item pop() {
        if (this.isEmpty()) {
            return null;
        }
        Item itemToReturn = items[--size];
        items[size] = null;
        if (size != 0 && size == items.length / 4) {
            this.resize(items.length / 2);
        }
        modCount--;
        return itemToReturn;
    }

    @Override
    public Item peek() {
        if (this.isEmpty()) {
            return null;
        }
        return items[size - 1];
    }

    @Override
    public Iterator<Item> iterator() {
        return (new Iterator<Item>() {

            private int nextPos = 0;
            private int savedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != savedModCount)
                    throw new ConcurrentModificationException();
                return nextPos < size;
            }

            @Override
            public Item next() {
                if (modCount != savedModCount)
                    throw new ConcurrentModificationException();
                return items[nextPos++];
            }

        });
    }

    public static void main(String[] args) {
        Stack<String> myStack = new StackArray<String>();
        myStack.push("aa");
        myStack.push("bb");
        myStack.push("cc");
        myStack.push("dd");
        // for (String s : myStack) {
        // System.out.println(s);
        // }
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
    }
}
