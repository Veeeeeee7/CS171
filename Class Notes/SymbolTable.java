public interface SymbolTable<Key, Value> {

    void put(Key key, Value value); // inserts a key-value pair into the table

    boolean contains(Key key); // returns true if the table contains the specified key

    Value get(Key key); // returns the value paired with given key

    void delete(Key key); // removes the given key (and its associated value)
                          // from the table

    boolean isEmpty(); // returns true if the table is empty

    int size(); // returns the number of key-value pairs in the table

    Iterable<Key> keys(); // returns an Iterable collection of all keys in the table
}
