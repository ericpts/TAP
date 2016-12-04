import java.io.*;
import java.util.*;

public class Main {

    final static String fin = "date.in";
    final static String fout = "date.out";

    static private class UnionFind {

        int[] next;
        boolean[] viz;

        UnionFind(int n) {
            next = new int[n];
            for(int i = 0; i < n; ++i)
                next[i] = i - 1;

            viz = new boolean[n];
            for(int i = 0; i < n; ++i)
                viz[i] = false;
        }

        int find(int x) {
            if (x == -1 || !viz[x]) {
                return x;
            }

            return next[x] = find(next[x]);
        }
    }

    /* Solutie:
    sortam activitatile dupa profit (descrescator)
    si o activitate incercam sa o asignam unui moment de timp
    cat mai apropiat de deadline
     */

    public static void main(String[] args) throws IOException {
        class Activitate {
            Integer profit;
            Integer limit;
            Integer index;

            Activitate(Integer p, Integer l, Integer i) {
                profit = p;
                limit = l;
                index = i;
            }

            Integer compareTo(Activitate a) {
                return profit.compareTo(a.profit);
            }
        }

        Scanner sc = new Scanner(new File(fin));
        final int n = sc.nextInt();

        List<Activitate> activitati = new LinkedList<Activitate>();

        for(int i = 0; i < n; ++i) {
            int p = sc.nextInt();
            int t = sc.nextInt();

            activitati.add(new Activitate(p, t - 1, i));
        }
        sc.close();

        activitati.sort(Activitate::compareTo);

        UnionFind uf = new UnionFind(n);

        List<Activitate> taken = new LinkedList<Activitate>();

        for(int i = n - 1; i >= 0; --i) {
            Activitate now = activitati.get(i);
            int pos = uf.find(now.limit);

            if (pos != -1) {
                taken.add(now);
                uf.viz[pos] = true;
            }
        }

        Integer profit = 0;
        for(Activitate a: taken) {
            profit += a.profit;
        }

        taken.sort((activitate, t1) -> activitate.limit.compareTo(t1.limit));

        Writer writer = new FileWriter(fout);
        writer.write(profit.toString() + "\n");

        for(Activitate a: taken) {
            Integer i = a.index + 1;
            writer.write(i.toString() + " ");
        }
        writer.write("\n");

        writer.close();
    }
}
