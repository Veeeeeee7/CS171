import java.util.HashMap;

/* This class behaves similar to an array of String objects. You must 
   specify the length -- how many strings it can store. You can "get"
   the string at index i with get(i). You can put the string s into
   the slot at index i with put(i). You can ask the object how many
   strings it can hold with length().  You can even use toString() to
   print a text version of the collection, much like Arrays.toString().

   However, here is the weird part -- you can only call get(i) twice
   for any index i.  Any more than that, and the class throws an 
   exception!  

   It also throws an exception if you try to get something
   at an illegal index -- only use i between 0 (inclusive) and length 
   (exclusive) -- but at least that exception should feel more normal. */

public class IndexedStringCollection {

    public final int MAX_ACCESSES = 2;

    private class Pair {
        String s;
        int accessesLeft;

        public Pair(String s, int accessesLeft) {
            this.s = s;
            this.accessesLeft = MAX_ACCESSES;
        }
    }

    int length;
    HashMap<Integer, Pair> hm;

    public IndexedStringCollection(int length) {
        hm = new HashMap<Integer, Pair>();
        this.length = length;
    }

    public void put(Integer i, String s) {
        if ((i < 0) || (i > this.length))
            throw new RuntimeException("whoops. index " + i + " out of bounds for length " + this.length);
        hm.put(i, new Pair(s, MAX_ACCESSES));
    }

    public String get(Integer i) {
        Pair pair = hm.get(i);
        if (pair.accessesLeft <= 0)
            throw new RuntimeException("whoops. accessed element too many times!");
        pair.accessesLeft--;
        return pair.s;
    }

    public int length() {
        return this.length;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < this.length; i++) {
            s += hm.get(i).s + (i == this.length - 1 ? "" : ",");
        }
        s += "]";
        return s;
    }
}