import java.io.*;
import java.util.*;

public class Main {

    static final String fin = "date.in";
    static final String fout = "date.out";

    static class Entry {
        Integer index;
        Integer length;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File(fin));

        final int n = sc.nextInt();
        final int p = sc.nextInt();

        Entry[] entries = new Entry[n];

        for(int i = 0; i < n; ++i) {
            entries[i] = new Entry();
            entries[i].index = i + 1;
            entries[i].length = sc.nextInt();
        }
        sc.close();

        Arrays.sort(entries, new Comparator<Entry>() {
            @Override
            public int compare(Entry entry, Entry t1) {
                return entry.length.compareTo(t1.length);
            }
        });

        ArrayList<List<Entry>> pipelines = new ArrayList<>(p);

        for(int i = 0; i < p; ++i) {
            pipelines.add(new LinkedList<>());
        }

        int at = 0;
        for(int i = 0; i < n; ++i) {
            pipelines.get(at).add(entries[i]);
            at++;
            if (at == p)
                at = 0;
        }

        Writer wrt = new FileWriter(new File(fout));

        for(int i = 0; i < p; ++i) {
            for(Entry e: pipelines.get(i)) {
                wrt.write(e.index.toString() + " ");
            }
            wrt.write("\n");
        }

        wrt.close();
    }
}
