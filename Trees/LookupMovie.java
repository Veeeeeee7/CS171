import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class LookupMovie {

    static final int MAX_NUMBER_OF_MOVIES_TO_DISPLAY = 20;

    public static void main(String[] args) throws FileNotFoundException {

        Movies movies = new Movies();

        // // use dialog to let user locate movies file on their local machine
        // final JFileChooser fc = new JFileChooser();
        // int returnVal = fc.showOpenDialog(null);

        // // exit program if no file chosen
        // if (returnVal != JFileChooser.APPROVE_OPTION)
        // System.exit(0);

        // File file = fc.getSelectedFile();
        File file = new File("moviesDB.txt");
        System.out.println("Preparing to read " + file + "...");
        Scanner scanner = new Scanner(new FileInputStream(file));

        // start a timer to see how long it takes to build the collection..
        long start = System.currentTimeMillis();

        int i = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] fields = line.split("\t");

            String id = fields[0].trim();
            String shortName = fields[1].trim();
            String longName = fields[2].trim();
            String released = fields[3].trim();
            String runtime = fields[4].trim();

            Movie movie = new Movie(id, longName, shortName, released, runtime);

            movies.put(movie);

            i++;

            if (i % 10000 == 0) {
                System.out.println("Inserted " + i + " movies into the collection.");
            }
        }

        // finished building the collection, so check the timer..
        long end = System.currentTimeMillis();

        System.out.println("Building collection of movies complete. Inserted " + i +
                " movies. Elapsed time = " + (end - start) / 1000 +
                " seconds.");

        Scanner input = new Scanner(System.in);

        System.out.println("Enter prefix of movie to search for:" +
                " q to quit");

        while (input.hasNext()) {
            String prefix = input.nextLine().trim();

            if (prefix.equals("q"))
                break;

            ArrayList<Movie> matchingMovies = movies.getFromPrefix(prefix);

            int numMoviesFound = matchingMovies.size();

            if (numMoviesFound > 0) {
                if (numMoviesFound <= MAX_NUMBER_OF_MOVIES_TO_DISPLAY)
                    for (Movie movie : matchingMovies) {
                        System.out.println(movie);
                    }
                else {
                    System.out.println("" + numMoviesFound + " movies found (try a longer prefix?)");
                }
            } else {
                System.out.println("Not Found");
            }
        }
    }
}