import java.util.Random;

public class QuickSorter<Item extends Comparable<Item>> {
    Item[] a;

    private boolean less(Item v, Item w) {
        return v.compareTo(w) < 0;
    }

    private void exchange(int i, int j) {
        Item temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void sort(Item[] a) {
        this.a = a;
        shuffle();
        sort(0, a.length);
    }

    private void sort(int low, int high) {
        if (high <= low) {
            return;
        }
        int pivotIndex = partition(low, high);
        sort(low, pivotIndex - 1);
        sort(pivotIndex + 1, high);

    }

    private int partition(int low, int high) {
        int i = low;
        int j = high;
        while (true) {
            while (less(a[++i], a[low])) {
                if (i == high) {
                    break;
                }
            }
            while (less(a[low], a[--j])) {
                if (j == low) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exchange(i, j);
        }
        exchange(i, j);
        return j;
    }

    private void shuffle() {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            int r = i + random.nextInt(a.length - i);
            exchange(i, r);
        }
    }
}
