import java.util.Random;

public class RandomList {

    private class Node {
        int value;
        Node next;
    }

    private Node head;

    public RandomList(int bound, int size) {

        //////////////////////
        // Insert code here //
        //////////////////////
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public String toString() {

        //////////////////////
        // Insert code here //
        //////////////////////
    }

    public void removeAdjacentDuplicates() {

        //////////////////////
        // Insert code here //
        //////////////////////
    }

    public void removeAdjacentDuplicatesR() {

        //////////////////////
        // Insert code here //
        //////////////////////
    }

    /////////////////////////////////////////////////////////////////////////////
    // You may insert no more than one additional private instance method here //
    /////////////////////////////////////////////////////////////////////////////

    public int getMax() {

        //////////////////////
        // Insert code here //
        //////////////////////
    }

    public void removeMaxValues() {

        //////////////////////
        // Insert code here //
        //////////////////////
    }

    public int itemInTheMiddle() {

        //////////////////////
        // Insert code here //
        //////////////////////
    }

    public static void main(String[] args) {
        RandomList list = new RandomList(4,15);
        System.out.println("Random list:"  + System.lineSeparator()+ list + System.lineSeparator());
        list.removeAdjacentDuplicates();
        System.out.println("List with adjacent duplicates removed:" +
                           System.lineSeparator() + list + System.lineSeparator());

        RandomList list2 = new RandomList(4,15);
        System.out.println("Another Random list:"  + System.lineSeparator()+ list2 + System.lineSeparator());
        list2.removeAdjacentDuplicatesR();
        System.out.println("List with adjacent duplicates removed recursively:" +
                           System.lineSeparator() + list2 + System.lineSeparator());

        RandomList list3 = new RandomList(4,15); // make a new list
        System.out.println("A Third Random list:" + System.lineSeparator() + list3 + System.lineSeparator());
        System.out.println("Successively removing max values:");
        while (! list3.isEmpty()) {
            list3.removeMaxValues();
            System.out.println(list3);
        }

        RandomList list4 = new RandomList(100,(int) (5+20*Math.random()); // make a new list of random size
        System.out.println("A Fourth Random list:" + System.lineSeparator() + list3 + System.lineSeparator());
        System.out.println("The item in the middle is " + list4.itemInTheMiddle());
    }
}
