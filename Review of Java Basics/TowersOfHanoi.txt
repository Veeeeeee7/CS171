import java.util.Scanner;

public class TowersOfHanoi {

    ///////////////////////////////////
    // ADD ANY NEEDED CONSTANTS HERE //
    ///////////////////////////////////

    ////////////////////////////////////////////
    // ADD ANY NEEDED INSTANCE VARIABLES HERE //
    ////////////////////////////////////////////
    private int maxNumDisks;
    private int[][] diskWidths;

    //////////////////////////////////////
    // ADD ANY NEEDED CONSTRUCTORS HERE //
    //////////////////////////////////////

    public TowersOfHanoi(int numDisks) {
        maxNumDisks = numDisks;
        diskWidths = new int[3][maxNumDisks];
        int index = 0;
        int width = maxNumDisks;
        while (index < maxNumDisks) {
            diskWidths[0][index] = width;
            index++;
            width--;
        }
    }

    private int baseWidth() {
        return 2 * maxNumDisks + 1;
    }

    private String pad(int diskWidth) {
        String s = (diskWidth == 0 ? " " : "");
        for (int i = 0; i < diskWidth; i++)
            s += "\u2587";
        int padding = (this.baseWidth() - diskWidth) / 2;
        for (int i = 0; i < padding; i++)
            s = " " + s + " ";
        return s;
    }

    public String toString() {
        // draw disks..
        String s = "";
        for (int level = maxNumDisks - 1; level >= 0; level--) {
            for (int tower = 0; tower < 3; tower++) {
                s += pad(diskWidths[tower][level]);
            }
            s += System.lineSeparator();
        }

        // draw "floor" and number the piles/pegs..
        for (int i = 0; i < 3 * this.baseWidth(); i++)
            s += "=";
        s += System.lineSeparator();
        for (int i = 0; i < this.baseWidth() / 2; i++)
            s += " ";
        s += "0";
        for (int i = 0; i < this.baseWidth() - 1; i++)
            s += " ";
        s += "1";
        for (int i = 0; i < this.baseWidth() - 1; i++)
            s += " ";
        s += "2";
        return s;
    }

    /////////////////////////////////////////////////////
    // ADD ANY ADDITIONAL NEEDED INSTANCE METHODS HERE //
    /////////////////////////////////////////////////////

    private boolean won() {
        if (diskWidths[2][maxNumDisks - 1] != 0) {
            return true;
        }
        return false;
    }

    private boolean legalMove(int from, int to) {
        int topDiskOnFrom = 0;
        int topDiskOnTo = 0;

        int indexFrom = 0;
        int smallestDisk = 0;
        while (indexFrom < maxNumDisks && diskWidths[from][indexFrom] != 0) {
            smallestDisk = diskWidths[from][indexFrom];
            indexFrom++;
        }
        topDiskOnFrom = smallestDisk;

        int indexTo = 0;
        smallestDisk = 0;
        while (indexTo < maxNumDisks && diskWidths[to][indexTo] != 0) {
            smallestDisk = diskWidths[to][indexTo];
            indexTo++;
        }
        topDiskOnTo = smallestDisk;

        if (indexFrom == 0) {
            return false;
        }

        if (indexTo == 0) {
            return true;
        }

        return topDiskOnFrom < topDiskOnTo;
    }

    private void move(int from, int to) {
        int toIndex = 0;
        while (toIndex < maxNumDisks && diskWidths[to][toIndex] != 0) {
            toIndex++;
        }

        int fromIndex = 0;
        while (fromIndex < maxNumDisks && diskWidths[from][fromIndex] != 0) {
            fromIndex++;
        }

        diskWidths[to][toIndex] = diskWidths[from][fromIndex - 1];
        diskWidths[from][fromIndex - 1] = 0;
    }

    public static void main(String[] args) {
        System.out.println("How many disks do you want to use?");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Scanner lineScanner = new Scanner(line);
        int numDisks = lineScanner.nextInt();
        lineScanner.close();

        System.out.println(
                System.lineSeparator() + "Goal: Move all the disks from pile 0 to pile 2" + System.lineSeparator());
        System.out.println("Restrictions: You can only move a top-most disk " + System.lineSeparator() +
                "from one pile onto another pile that is either " + System.lineSeparator() +
                "empty or whose top-most disk is larger than the one " + System.lineSeparator() +
                "moved" + System.lineSeparator());
        System.out.println("To move a disk from pile X to pile Y: " + System.lineSeparator() +
                "Type 'XY' and then hit 'enter'" + System.lineSeparator());

        TowersOfHanoi toh = new TowersOfHanoi(numDisks);
        System.out.println(toh);

        while (!toh.won() && scanner.hasNextLine()) {

            line = scanner.nextLine();

            int from = line.charAt(0) - '0';
            int to = line.charAt(1) - '0';

            if (toh.legalMove(from, to)) {
                toh.move(from, to);
                System.out.println(toh);
            } else {
                System.out.println("ILLEGAL MOVE");
            }
        }
        System.out.println("YOU DID IT!");
        scanner.close();
    }
}