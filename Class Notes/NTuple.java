import java.util.Iterator;

public class NTuple<Item> implements Iterable<Item> {
    private Item[] items;

    public NTuple(Item[] itemsToCopy) {
        items = (Item[]) (new Object[itemsToCopy.length]);

        for (int i = 0; i < itemsToCopy.length; i++) {
            items[i] = itemsToCopy[i];
        }
    }

    public String toString() {
        String s = "(";
        for (int i = 0; i < items.length; i++) {
            s += items[i] + ",";
        }
        s = s.substring(0, s.length() - 1);
        s += ")";
        return s;
    }

    @Override
    public Iterator<Item> iterator() {
        return (new Iterator<Item>() {
            int pos = items.length - 1;

            @Override
            public boolean hasNext() {
                return pos >= 0;
            }

            @Override
            public Item next() {
                pos--;
                return NTuple.this.items[pos + 1];

            }
        });
    }

    public static void main(String[] args) {
        String[] strArray = { "hi", "hello", "hola" };
        NTuple<String> nTuple = new NTuple<String>(strArray);
        System.out.println(nTuple);
    }
}
