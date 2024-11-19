import java.util.Arrays;
import java.util.Stack;

public class QuickSorterWithFewerRecursiveCalls<Item extends Comparable<Item>> {

    Item[] a;

    private boolean less(Item v, Item w) {
        return (v.compareTo(w) < 0);
    }

    private void exch(int i, int j) {
        Item temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private int partition(int lo, int hi) {

        int i = lo; // note, this will get incremented to (lo+1) before it is used
        int j = hi + 1; // similarly, this will get decremented to hi before it is used

        while (true) {

            while (less(a[++i], a[lo])) // find position of item on left to swap
                if (i == hi)
                    break;

            while (less(a[lo], a[--j])) // find position of item on right to swap
                if (j == lo)
                    break;

            if (i >= j)
                break; // check if arrows (positions i and j) cross and we are
                       // ready for final exchange

            exch(i, j); // swap the elements at positions i and j
        }

        exch(lo, j); // final exchange (with pivot)

        return j; // return index of item now known to be in place
    }

    public void sort1(Item[] a) {
        this.a = a;
        sort1(0, a.length - 1);
    }

    private void sort1(int lo, int hi) {

        //////////////////////
        // Insert Code Here //
        //////////////////////
        if (lo >= hi) {
            return;
        }
        int i = partition(lo, hi);

        while (i <= hi) {
            // System.out.println(i);
            sort1(lo, i - 1);
            lo = i;
            if (i >= hi - 1) {
                return;
            }
            i = partition(i + 1, hi);
            // for (int j = 0; j < a.length; j++) {
            // System.out.print(a[j]);
            // }
            // System.out.println();
        }

    }

    public void sort2(Item[] a) {
        this.a = a;
        sort2(0, a.length - 1);
    }

    private void sort2(int lo, int hi) {
        Stack<Integer> s = new Stack<Integer>();

        //////////////////////
        // Insert Code Here //
        //////////////////////

        s.push(0);
        s.push(a.length - 1);

        while (!s.isEmpty()) {
            hi = s.pop();
            lo = s.pop();
            if (lo < hi) {
                int i = partition(lo, hi);
                s.push(i + 1);
                s.push(hi);

                s.push(lo);
                s.push(i - 1);
            }
        }
    }

    public static void main(String[] args) {
        Character[] a = { 'K', 'R', 'A', 'T', 'E', 'L', 'E', 'P', 'U', 'I', 'M', 'Q',
                'C', 'X', 'O', 'S' };
        Character[] b = { 'K', 'R', 'A', 'T', 'E', 'L', 'E', 'P', 'U', 'I', 'M', 'Q',
                'C', 'X', 'O', 'S' };
        QuickSorterWithFewerRecursiveCalls<Character> sorter = new QuickSorterWithFewerRecursiveCalls<Character>();
        sorter.sort1(a);
        System.out.println("Sorted (w/ 1 recursive call): " + Arrays.toString(a));
        sorter.sort2(b);
        System.out.println("Sorted (iterative quicksort): " + Arrays.toString(b));
    }
}

// HELPED BY: NOBODY