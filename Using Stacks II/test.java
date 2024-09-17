import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(5);
        list.add(1);
        int a = 0;
        int power = (int) Math.pow(10, 3 - 1);
        for (int c : list) {
            a += c * power;
            power /= 10;
        }
        System.out.println(a);
    }
}
