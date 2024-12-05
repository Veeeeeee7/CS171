import java.util.Hashtable;
import java.util.Random;

public class UniqueFun {

    final static int WINDOW_SIZE = 5;

    public static void main(String[] args) {
        Random random = new Random();
        random.setSeed(1234567890);
        int i, c;
        String s;

        int numStrings = 15;
        String[] names = { "alice", "bob", "cindy", "doug", "elli", "fred", "ginny", "harold" };
        IndexedStringCollection strs = new IndexedStringCollection(numStrings);
        for (i = 0; i < numStrings; i++)
            strs.put(i, names[random.nextInt(names.length)]);

        Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
        /////////////////////////////////////
        // ADD CODE HERE AND ONLY HERE. //
        // USE ONLY THE VARIABLES DECLARED //
        // ABOVE, OR ONE ADDITIONAL STRING //
        // VARIABLE AS MIGHT BE NEEDED TO //
        // CONSTRUCT A FOR-EACH LOOP. //
        /////////////////////////////////////
        System.out.println(strs);
        c = 5;
        for (i = 0; i < 5; i++) {
            s = strs.get(i);
            if (ht.containsKey(s)) {
                ht.put(s, ht.get(s) + 1);
            } else {
                ht.put(s, 1);
            }

            if (ht.get(s) > 1) {
                c -= ht.get(s);
            }
        }

        System.out.print(c + " ");
        for (i = 5; i < 15; i++) {
            s = strs.get(i - 5);
            if (ht.get(s) == 2) {
                ht.put(s, ht.get(s) - 1);
                c += 2;
            } else if (ht.get(s) > 2) {
                ht.put(s, ht.get(s) - 1);
                c++;
            } else {
                ht.remove(s);
            }

            s = strs.get(i);

            if (ht.containsKey(s)) {
                ht.put(s, ht.get(s) + 1);
            } else {
                ht.put(s, 1);
            }

            if (ht.get(s) == 2) {
                c -= 2;
            } else if (ht.get(s) > 2) {
                c--;
            }
            System.out.print(c + " ");
        }
    }
}

// HELPED BY: NOBODY