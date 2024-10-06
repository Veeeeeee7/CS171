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
        if (size == 0) {
            head = null;
            return;
        }

        head = new Node();
        head.value = (int) (Math.random() * bound);
        Node current = head;
        for (int i = 0; i < size - 1; i++) {
            Node n = new Node();
            n.value = (int) (Math.random() * bound);
            current.next = n;
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public String toString() {

        //////////////////////
        // Insert code here //
        //////////////////////
        // Node n = head;
        // if (n == null) {
        // return "";
        // }
        // StringBuilder sb = new StringBuilder();
        // while (n.next != null) {
        // sb.append(n.value + "->");
        // n = n.next;
        // }
        // sb.append(n.value + "->");
        // return sb.toString();

        Node n = head;
        StringBuilder sb = new StringBuilder();
        while (n != null) {
            sb.append(n.value + "->");
            n = n.next;
        }
        return sb.toString();
    }

    public void removeAdjacentDuplicates() {

        //////////////////////
        // Insert code here //
        //////////////////////
        if (head == null) {
            return;
        }

        Node n = head;
        while (head.next != null && head.next.value == head.value) {
            head = head.next;
        }

        while (n != null && n.next != null) {
            if (n.next.value == n.value) {
                n.next = n.next.next;
            } else {
                n = n.next;
            }
        }
    }

    public void removeAdjacentDuplicatesR() {

        //////////////////////
        // Insert code here //
        //////////////////////
        head = removeAdjacentDuplicatesRecursiveHelper(head);
    }

    /////////////////////////////////////////////////////////////////////////////
    // You may insert no more than one additional private instance method here //
    /////////////////////////////////////////////////////////////////////////////

    private Node removeAdjacentDuplicatesRecursiveHelper(Node n) {
        if (n == null) {
            return null;
        }
        if (n.next == null) {
            return n;
        }

        if (n.value == n.next.value) {
            return removeAdjacentDuplicatesRecursiveHelper(n.next);
        } else {
            n.next = removeAdjacentDuplicatesRecursiveHelper(n.next);
            return n;
        }
    }

    public int getMax() {

        //////////////////////
        // Insert code here //
        //////////////////////
        Node n = head;
        // if (n == null) {
        // return Integer.MIN_VALUE;
        // }
        int max = n.value;
        while (n != null) {
            max = Math.max(max, n.value);
            n = n.next;
        }
        return max;
    }

    public void removeMaxValues() {

        //////////////////////
        // Insert code here //
        //////////////////////
        Node n = head;
        if (n == null) {
            return;
        }
        int max = n.value;
        while (n != null) {
            max = Math.max(max, n.value);
            n = n.next;
        }

        while (head != null && head.value == max) {
            head = head.next;
        }

        n = head;
        while (n != null && n.next != null) {
            if (n.next.value == max) {
                n.next = n.next.next;
            } else {
                n = n.next;
            }
        }
    }

    public int itemInTheMiddle() {

        //////////////////////
        // Insert code here //
        //////////////////////

        // if (head == null) {
        // return null;
        // }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.value;
    }

    public static void main(String[] args) {
        RandomList list = new RandomList(4, 15);
        System.out.println("Random list:" + System.lineSeparator() + list +
                System.lineSeparator());
        list.removeAdjacentDuplicates();
        System.out.println("List with adjacent duplicates removed:" +
                System.lineSeparator() + list + System.lineSeparator());

        RandomList list2 = new RandomList(4, 15);
        System.out.println("Another Random list:" + System.lineSeparator() + list2 +
                System.lineSeparator());
        list2.removeAdjacentDuplicatesR();
        System.out.println("List with adjacent duplicates removed recursively:" +
                System.lineSeparator() + list2 + System.lineSeparator());

        RandomList list3 = new RandomList(4, 15); // make a new list
        System.out.println("A Third Random list:" + System.lineSeparator() + list3 +
                System.lineSeparator());
        System.out.println("Successively removing max values:");
        while (!list3.isEmpty()) {
            list3.removeMaxValues();
            System.out.println(list3);
        }

        RandomList list4 = new RandomList(100, (int) (5 + 20 * Math.random())); //
        // make a new list of random size
        System.out.println("A Fourth Random list:" + System.lineSeparator() + list4 +
                System.lineSeparator());
        System.out.println("The item in the middle is " + list4.itemInTheMiddle());
    }
}

// HELPED BY: NOBODY