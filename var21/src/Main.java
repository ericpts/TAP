import java.io.*;
import java.util.*;

public class Main {

    final static String fin = "date.in";
    final static String fout = "date.out";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File(fin));


        class Cube {
            Integer index;
            Integer length;
            Integer color;
        }

        final int n = sc.nextInt();
        final int p = sc.nextInt();

        Cube[] cubes = new Cube[n];

        for(int i = 0; i < n; ++i) {
            cubes[i] = new Cube();
            cubes[i].index = i;
            cubes[i].length = sc.nextInt();
            cubes[i].color = sc.nextInt();
        }

        sc.close();

        Arrays.sort(cubes, (cube, t1) -> ((Integer)cube.length).compareTo((Integer)t1.length));

        int lastColor = -1;
        List<Integer> chosen = new LinkedList<>();

        for(int i = 0; i < n; ++i) {
            if (cubes[i].color == lastColor)
                chosen.remove(chosen.size() - 1);

            chosen.add(i);
            lastColor = cubes[i].color;
        }

        Integer height = 0;
        for(Integer i: chosen)
            height += cubes[i].length;

        Writer wrt = new FileWriter(fout);
        wrt.write(height.toString() + "\n");

        Collections.reverse(chosen);
        for(Integer i: chosen)
            wrt.write(((Integer)cubes[i].index + 1) + " ");
        wrt.write("\n");

        wrt.close();
    }
}
