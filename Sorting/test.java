public class test {
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
            if (less(current.item, hi.item)) {
                exch(start, current);
                start = start.next;
            }
            current = current.next;
        }
        if (less(start.item, hi.item)) {
            exch(hi, start);
        }
        return start;
    }
}
