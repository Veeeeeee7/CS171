import java.util.Iterator;

import javax.management.RuntimeErrorException;

public class LinkedListH<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private Node first;
    private int size;

    public LinkedListH() {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = first;
        first = node;
    }

    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new RuntimeException("nothing to remove");
        }
        Item item = first.item;
        first = first.next;
        return item;
    }

    public Iterator<Item> iterator() {
        return (new Iterator<Item>() {
            Node node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Item next() {
                Item item = node.item;
                node = node.next;
                return item;
            }
        });
    }

    public void addLast(Item item) {
        Node node = new Node();
        node.item = item;

        Node n = first;

        if (this.isEmpty()) {
            first = node;
        } else {
            while (n.next != null) {
                n = n.next;
            }
            n.next = node;
        }
        size++;
    }

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new RuntimeException("tried to remove from an empty list");
        } else if (size == 1) {
            Item item = first.item;
            first = null;
            size--;
            return item;
        } else {
            Node n = first;
            while (n.next.next != null) {
                n = n.next;
            }
            Item item = n.next.item;
            n.next = null;
            size--;
            return item;
        }
    }

    public boolean contains(Item item) {
        for (Node n = first; n != null; n = n.next) {
            if (n.item.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String s = "";
        for (Item item : this) {
            s += item + "->";
        }
        return s;
    }

    public static void main(String[] args) {
        LinkedListH<String> lst = new LinkedListH<>();

        lst.addFirst("A");
        lst.addFirst("B");
        lst.addFirst("C");

        System.out.println(lst);
    }
}
