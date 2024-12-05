class Movie {

    public String id;
    public String longName;
    public String shortName;
    public String released;
    public String runtime;

    public Movie(String id, String longName, String shortName, String released, String runtime) {
        this.id = id;
        this.longName = longName;
        this.shortName = shortName;
        this.released = released;
        this.runtime = runtime;
    }

    public String toString() {
        return "" + id + "\t" +
                (released.equals("\\N") ? "-" : released) + "\t" +
                (runtime.equals("\\N") ? "-" : runtime + "min") + "\t" +
                longName;
    }
}