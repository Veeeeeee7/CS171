public class ListReverser<Item> {
    private class Node {
        Item item;
        Node next;
    }

    Node head;

    public void add(Item item) {
        Node n = new Node();
        n.item = item;
        n.next = head;
        head = n;
    }

    public void reverseR() {
        head = reverseR(head);
    }

    public Node reverseR(Node start) {
        if (start == null || start.next == null) {
            return start;
        }
        Node secondToLast = start.next;
        Node reversedRestStart = reverseR(start.next);
        start.next = null;
        secondToLast.next = start;
        return reversedRestStart;
    }

    public String toString() {
        String s = "";
        for (Node n = head; n != null; n = n.next) {
            s += n.item + "->";
        }
        return s;
    }

    public static void main(String[] args) {
        ListReverser<Integer> lr = new ListReverser<>();
        for (int i = 0; i < 10; i++) {
            lr.add(i);
        }
        System.out.println(lr);
        lr.reverseR();
        System.out.println(lr);
    }
}
