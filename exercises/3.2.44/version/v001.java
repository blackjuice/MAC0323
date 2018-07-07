

public class ModFrequencyCounter {

    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen  = Integer.parseInt(args[0]);
        BST<String, Integer>    st          = new BST<String, Integer>();
        BST<Integer, Integer>   stHeight    = new BST<Integer, Integer>();

                    StdOut.println("args[0] = " + args[0]);
                    Stopwatch start = new Stopwatch();

        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();

            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
            stHeight.put(words, st.height());
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max))
                max = word;

        VisualAccumulator a = new VisualAccumulator(words, st.height());
        for (int t = 1; t <= words; t++) {
            int position = stHeight.get(t);
            a.addDataValue(position);
        }

        StdOut.println("**      st height = " + st.height());
        StdOut.println("**      stHeight height = " + stHeight.height());

        StdOut.println(a);
        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);

        if (args.length > 1) {
            for (String word : st.keys())
                StdOut.println(word);

                double time = start.elapsedTime();
                StdOut.println("time = " + time);
                StdOut.println();
    }
}



























public class ModFrequencyCounter {

    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen  = Integer.parseInt(args[0]);
        BST<String, Integer>    st          = new BST<String, Integer>();
        BST<Integer, Integer>   stHeight    = new BST<Integer, Integer>();

                    StdOut.println("args[0] = " + args[0]);
                    Stopwatch start = new Stopwatch();

        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();

            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
            stHeight.put(words, st.height());
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max))
                max = word;

        //VisualAccumulator a = new VisualAccumulator(words * 2, st.height());
        VisualAccumulator a = new VisualAccumulator(298255, st.height());
            int n = 0;
        for (int t = 1; t <= words; t++) {
            for (int position = 0; position < stHeight.get(t); position++)
                a.addDataValue(position);
                //int position = stHeight.get(t);
                //n++;
        }
                StdOut.println(a);
                //StdOut.println("**      n = " + n);

        StdOut.println("**      st height = " + st.height());
        StdOut.println("**      stHeight height = " + stHeight.height());

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);

        if (args.length > 1)
            for (String word : st.keys())
                StdOut.println(word);

                double time = start.elapsedTime();
                StdOut.println("time = " + time);
                StdOut.println();
    }
}