/////////////////////////////////////////////////////////////////////////////////
public class SortableList<Item extends Comparable> {
    /////////////////////////////////////////////////////////////////////////////////
    public class Node {
        Item item;
        Node next;

        Node(Item item) {
            this.item = item;
        }
    }

    private Node head;
    private Node tail;

    public void add(Item item) { // this metadd a new node containing item at the head of the list
        /////////////////////////////////////
        // ADD CODE HERE TO ADD A NEW NODE //
        // CONTAINING item TO THE HEAD OF //
        // THIS LIST. NOTICE THIS SINGLY //
        // LINKED LIST KEEPS A "TAIL" //
        // MAKE SURE IT GETS UPDATED //
        // APPROPRIATELY, WHEN ADDING //
        // NODES TO THIS LIST //
        /////////////////////////////////////
        Node n = new Node(item);
        if (head == null) {
            tail = n;
            head = n;
            n.next = null;
            return;
        }
        n.next = head;
        head = n;

    }

    private boolean less(Item item1, Item item2) {
        return item1.compareTo(item2) < 0;
    }

    private void exch(Node n, Node m) { // swaps items inside nodes n and m (not the references)
        Item item = n.item;
        n.item = m.item;
        m.item = item;
    }

    private Node partition(Node lo, Node hi) {
        /////////////////////////////////////////
        // ADD CODE HERE TO PARTITION THE //
        // SUB-LIST FROM NODE lo to NODE hi //
        // IN A MANNER THAT WILL REQUIRE ONLY //
        // A SINGLE PASS THROUGH THE LIST FROM //
        // lo TO hi (HINT: YOU WILL NEED //
        // TO USE AN ALTERNATIVE TO HOARE'S //
        // PARTIONING - SEE NOTES) //
        // //
        // THIS METHOD SHOULD RETURN THE NODE //
        // BEFORE THE PIVOT AFTER PARTITIONING //
        // COMPLETES. IF THE PIVOT ENDS UP AT //
        // THE BEGINNING, RETURN null //
        /////////////////////////////////////////
        Node start = lo;
        Node current = lo;
        while (current != hi) {
            if (less(current.item, hi.item) || current.item.equals(hi.item)) {
                exch(start, current);
                // System.out.println(this);
                start = start.next;
            }
            current = current.next;
        }
        if (less(hi.item, start.item)) {
            exch(hi, start);
        }
        return start;
    }

    public void sort() {

        ////////////////////////////////
        // ADD APPROPRIATE CODE HERE. //
        // DO NOT MODIFY THE METHOD //
        // SIGNATURE (I.E., THIS //
        // METHOD SHOULD CONTINUE TO //
        // HAVE NO EXPLICIT //
        // PARAMETERS) //
        ////////////////////////////////
        if (head == null || tail == null || head.equals(tail)) {
            return;
        }
        sort(head, tail);
    }

    private void sort(Node lo, Node hi) {
        if (hi == null || lo == null) {
            return;
        }
        // System.out.println(this);
        // System.out.println("lo: " + lo.item + " hi: " + hi.item);
        if (lo.equals(hi)) {
            return;
        }
        if (hi.next != null && hi.next.equals(lo)) {
            return;
        }
        Node n = partition(lo, hi);

        Node mid = lo;
        if (!mid.equals(n)) {
            while (!mid.next.equals(n)) {
                mid = mid.next;
            }
        }
        // System.out.println("lo: " + lo.item + " mid: " + mid.item + " hi: " +
        // hi.item);
        sort(lo, mid);
        sort(n.next, hi);
    }

    ////////////////////////////////////
    // YOU MAY ADD ONE ADDITIONAL //
    // INSTANCE METHOD DIRECTLY BELOW //
    // IF DESIRED, BUT NO MORE THAN //
    // THAT //
    ////////////////////////////////////

    public String toString() {
        String s = "";
        for (Node n = head; n != null; n = n.next) {
            s += n.item + (n.next != null ? "->" : "");
        }
        return s;
    }

    public static SortableList<Character> convertStringToSortableList(String s) {
        SortableList<Character> list = new SortableList<Character>();
        for (int i = 0; i < s.length(); i++) {
            list.add(s.charAt(i));
        }
        return list;
    }

    public static void main(String[] args) {
        String[] words = { "hungry", "ambidextrous", "zombies", "eat", "brains",
                "with", "both", "hands" };
        for (String word : words) {
            SortableList<Character> list = convertStringToSortableList(word);
            list.sort();
            System.out.println(list);
        }
    }
}

// HELPED BY: NOBODY