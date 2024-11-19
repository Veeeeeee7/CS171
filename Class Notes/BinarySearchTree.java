public class BinarySearchTree<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count; // <-- gives number of nodes in subtree
                           // topped by this node. this helps
                           // with size() and rank() methods

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node root;

    /////////////////////////////////////////////////////////////////////////
    // iterative version of get(key), which retrieves a node with a given key
    /////////////////////////////////////////////////////////////////////////

    public Value get(Key key) {
        Node n = root;
        while (n != null) {
            int cmp = key.compareTo(n.key);

            if (cmp < 0) // key < n.key
                n = n.left;

            else if (cmp > 0) // key > n.key
                n = n.right;

            else // key == n.key (found it!)
                return n.val;
        }
        return null; // key not found

        // Cost: The number of comparisons is equal to 1 + depth of node
    }

    public boolean contains(Key key) {
        return (get(key) != null);
    }

    ///////////////////////////////////////
    // recursive version of get(key)
    ////////////////////////////////////////

    /*
     * public Value get(Key key) {
     * return get(root, key);
     * }
     * 
     * public Value get(Node n, Key key) {
     * if (n == null)
     * return null; // key not found
     * 
     * int cmp = key.compareTo(n.key);
     * 
     * if (cmp < 0)
     * return get(n.left, key); // key < n.key
     * 
     * else if (cmp > 0)
     * return get(n.right, key); // key > n.key
     * 
     * else
     * return n.val; // key == n.key (found it!)
     * 
     * // Cost: number of comparisons = 1 + depth of node
     * // + recursive overhead
     * }
     */

    /////////////////////////////////////////////////////////////
    // recursive version of put(), which inserts a key-value pair
    /////////////////////////////////////////////////////////////

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node n, Key key, Value val) {
        if (n == null) { // base case, insert
            Node newNode = new Node(key, val);
            newNode.count = 1; // added to facilitate size()
            return newNode;
        }

        int cmp = key.compareTo(n.key);

        if (cmp < 0) // key < n.key
            n.left = put(n.left, key, val);

        else if (cmp > 0) // key > n.key
            n.right = put(n.right, key, val);

        else // key == n.key (overwrite)
            n.val = val;

        n.count = 1 + size(n.left) + size(n.right); // see size method below,
                                                    // n.count gives size of subtree
                                                    // topped by node n

        return n; // links on path to insertion point are rebuilt
                  // and the link to the new node is added.

        // Cost: number of comparisons = 1 + depth of node.
    }

    //////////////////////////////////////////////////////////////
    // methods needed for traversing the tree "in-order" by key...
    //////////////////////////////////////////////////////////////

    public Iterable<Key> keysInOrder() {
        Queue<Key> q = new QueueArray<Key>();
        enqueueKeysInOrderFromNode(root, q);
        return q;
    }

    private void enqueueKeysInOrderFromNode(Node n, Queue<Key> q) {
        if (n == null)
            return;
        enqueueKeysInOrderFromNode(n.left, q);
        q.enqueue(n.key);
        enqueueKeysInOrderFromNode(n.right, q);
    }

    ///////////////////////////////////////////////////////////
    // methods needed for traversing tree in "pre-order" by key
    ///////////////////////////////////////////////////////////
    public Iterable<Key> keysPreOrder() {
        Queue<Key> q = new QueueArray<Key>();
        enqueueKeysPreOrderFromNode(root, q);
        return q;
    }

    private void enqueueKeysPreOrderFromNode(Node n, Queue<Key> q) {
        if (n == null)
            return;
        q.enqueue(n.key);
        enqueueKeysPreOrderFromNode(n.left, q);
        enqueueKeysPreOrderFromNode(n.right, q);
    }

    ////////////////////////////////////////////////////////////
    // methods needed for traversing tree in "post-order" by key
    ////////////////////////////////////////////////////////////
    public Iterable<Key> keysPostOrder() {
        Queue<Key> q = new QueueArray<Key>();
        enqueueKeysPostOrderFromNode(root, q);
        return q;
    }

    private void enqueueKeysPostOrderFromNode(Node n, Queue<Key> q) {
        if (n == null)
            return;
        enqueueKeysPostOrderFromNode(n.left, q);
        enqueueKeysPostOrderFromNode(n.right, q);
        q.enqueue(n.key);
    }

    ////////////////////////////////////////
    // Default key collection is in-order //
    ////////////////////////////////////////

    public Iterable<Key> keys() {
        return keysInOrder();
    }

    ///////////////////////////////////////////////////////////////////////
    // size() returns the number of nodes
    // size(Node n) returns the number of nodes of the subtree rooted by n
    ///////////////////////////////////////////////////////////////////////

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        return ((n == null) ? 0 : n.count);
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    //////////////////////////////////////////////////////////////
    // rank(key) counts how many keys are less than the given key
    //////////////////////////////////////////////////////////////

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node n) {
        if (n == null)
            return 0; // base case: tree is empty

        int cmp = key.compareTo(n.key);

        if (cmp < 0) // key < n.key
            return rank(key, n.left);

        else if (cmp > 0) // key > n.key
            return 1 + size(n.left) + rank(key, n.right);

        else // base case: key == n.key
            return size(n.left);
    }

    ///////////////////////////////////////////////
    // Select(rank) returns the key of a given rank
    ///////////////////////////////////////////////

    public Key select(int rank) {
        if (rank < 0) // negative ranks don't make sense
            return null;

        if (rank > size()) // ranks larger than the number of nodes
            return null; // don't make sense either

        Node n = select(root, rank);

        return n.key;
    }

    private Node select(Node n, int rank) {
        if (n == null) // if the subtree is empty, we
            return null; // can't return anything

        int numToLeft = size(n.left);

        if (numToLeft > rank) // we are too far to the right...
            return select(n.left, rank); // so search left for the same rank

        else if (numToLeft < rank) // we are too far left...
            return select(n.right, rank - numToLeft - 1); // so search right, for a rank
                                                          // that reflects number left
                                                          // we no longer have to count

        else // numToLeft == rank,
            return n; // so we found it!
    }

    //////////////////////////////
    // methods that delete nodes..
    //////////////////////////////

    private Node deleteMin(Node n) {
        if (n.left == null) // current node is the min
            return n.right;

        n.left = deleteMin(n.left); // there are nodes smaller than
                                    // the current node (and they are
                                    // to the left)

        n.count = 1 + size(n.left) + size(n.right); // update count of nodes at or
                                                    // below this one

        return n;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node n, Key key) { // using Hibbard Deletion...

        if (n == null) // there is nothing to delete
            return null;

        int cmp = key.compareTo(n.key);

        if (cmp < 0) // key < n.key,
            n.left = delete(n.left, key); // so search left

        else if (cmp > 0) // key > n.key,
            n.right = delete(n.right, key); // so search right

        else { // found it! node to delete is n

            if (n.right == null) // n has no right child, so return left
                                 // child
                return n.left; // note, if n has no left child either
                               // this returns null

            Node t = n; // protect n from garbage collection
                        // we'll need it later...

            n = select(t.right, 0); // let n get the key and value of
                                    // the minimum node in the right
                                    // subtree -- this will be the
                                    // successor of the previous n
                                    // we'll update the left and
                                    // right links next...

            n.right = deleteMin(t.right); // removes the aforementioned
                                          // successor from the right subtree
                                          // of the old n (stored in t)
                                          // then this tree is put to the
                                          // right of the new n.

            n.left = t.left; // make the new n's left link
                             // the same as the original n's left link
        }
        n.count = size(n.left) + size(n.right) + 1; // update the count for this new n
                                                    // Note: deleteMin() updated other
                                                    // counts affected
        return n;

        // Sadly, repeated deletions end up affecting the shape of the tree in a bad
        // way.
        // The tree becomes more and more assymetric, which affects performance for many
        // operations.
        //
        // The cost for a single deletion over the long haul is O(sqrt(n)).
        //
        // A long standing open problem in computer science is to find a simple and
        // efficient delete for binary search trees...
    }

}
