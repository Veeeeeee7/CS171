public class Sorters<Item extends Comparable<Item>> {
    Item[] a;

    private boolean less(Item a, Item b) {
        return a.compareTo(b) < 0;
    }

    private void exchange(int i, int j) {
        Item temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
