import java.util.ArrayList;

public class Movies {

    private class Node {
        private Movie movie;
        private Node left;
        private Node right;
        private int size;
        private boolean color;

        private String key() {
            return this.movie.longName + this.movie.id;
        }

        private Movie val() {
            return this.movie;
        }

        Node(Movie movie, int size, boolean color) {
            this.movie = movie;
            this.size = size;
            this.color = color;
        }
    }

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private boolean isRed(Node x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.size;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(Movie movie) {
        root = put(root, movie);
        root.color = BLACK;
    }

    private Node put(Node n, Movie movie) {

        if (n == null)
            return new Node(movie, 1, RED);

        String nKey = n.key().toLowerCase();

        // if (movie.longName.toLowerCase().equals("Free Willy"))
        // System.out.println(nKey);

        int cmp = (movie.longName.toLowerCase()).compareTo(nKey);
        if (cmp < 0)
            n.left = put(n.left, movie);
        else if (cmp > 0)
            n.right = put(n.right, movie);
        else
            n.movie = movie;

        if (isRed(n.right) && !isRed(n.left))
            n = rotateLeft(n);
        if (isRed(n.left) && isRed(n.left.left))
            n = rotateRight(n);
        if (isRed(n.left) && isRed(n.right))
            flipColors(n);

        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    /////////////////////////////////////////////////////////////////////////////////
    // ADD CODE ONLY TO THE BODIES OF THE THREE METHODS BELOW AND NOWHERE ELSE ...
    // //

    private boolean nodeKeyMatchesPrefix(String prefix, Node n) {
        /////////////////////////////////
        // ADD CODE HERE AS IS HELPFUL //
        /////////////////////////////////
        return n.key().toLowerCase().startsWith(prefix.toLowerCase());
    }

    private void enqueueKeysMatchingPrefixInOrderFromNode(String prefix, Node n, ArrayList<Movie> q) {
        /////////////////////////////////
        // ADD CODE HERE AS IS HELPFUL //
        /////////////////////////////////
        if (n == null) {
            return;
        }
        if (nodeKeyMatchesPrefix(prefix, n)) {
            q.add(n.val());
        }

        enqueueKeysMatchingPrefixInOrderFromNode(prefix, n.left, q);

        enqueueKeysMatchingPrefixInOrderFromNode(prefix, n.right, q);

    }

    public ArrayList<Movie> getFromPrefix(String prefix) {
        ///////////////////////////////////////////////////////////////////////////////////
        // ADD CODE HERE TO FIND AND RETURN, IN AN ArrayList<Movie>, ALL MOVIE OBJECTS
        // //
        // THAT MATCH THE GIVEN PREFIX. MATCHING SHOULD BE DONE IN A CASE-INSENSITIVE //
        // WAY, BUT LEAVING UNCHANGED THE ORIGINAL CAPITALIZATION PRESENT WHEN put() //
        ///////////////////////////////////////////////////////////////////////////////////
        ArrayList<Movie> ans = new ArrayList<>();
        enqueueKeysMatchingPrefixInOrderFromNode(prefix, root, ans);
        return ans;
    }
}

// HELPED BY: NOBODY