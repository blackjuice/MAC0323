

public class ModFrequencyCounter {

    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen  = Integer.parseInt(args[0]);
        BST<String, Integer>    st          = new BST<String, Integer>();
        BST<Integer, Integer>   stHeight    = new BST<Integer, Integer>();

                    StdOut.println("args[0] = " + args[0]);
                    Stopwatch start = new Stopwatch();

        while (!StdIn.isEmpty()) {
            int comparison = 0;
            String key = StdIn.readString();

            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
                //stHeight.put(words, st.height());
            }
            else {
                st.put(key, 1);
                distinct++;
                //stHeight.put(words, st.height());
            }

                //while (!st.contains(key))
                //    comparison++;
            //comparison = st.size();
            //comparison = stHeight.keys();
            //comparison = stHeight.size();
            //stHeight.put(words, st.height());
            //stHeight.put(words, st.get(key));
            //stHeight.put(words, st.rank(key));
            //StdOut.println(key + " || " + st.rank(key));
            //StdOut.println(words + " || " + st.rank(key));
            //stHeight.put(words, st.height());
            //stHeight.put(words, st.rank(key));
            //stHeight.put(words, st.rank(key) / (st.height() + 1) );
            //int n = (int)Math.ceil(Math.log(st.rank(key)) / Math.log(2));
            int n = (int)(Math.log(st.rank(key) + 1) / Math.log(2));
            stHeight.put(words, n);
            //StdOut.println("st.rank(key) = " + st.rank(key) + " | n = " + n);
        }

        //StdOut.println("**      stHeight height = " + stHeight.height());

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) // return the distinct
            if (st.get(word) > st.get(max))
                max = word;



        //VisualAccumulator a = new VisualAccumulator(words, 14349);
        //VisualAccumulator a = new VisualAccumulator(words, stHeight.height() );
        //VisualAccumulator a = new VisualAccumulator(words, st.height() );
        VisualAccumulator a = new VisualAccumulator(words, st.height() );
        for (int t = 1; t <= words; t++) {
            //StdOut.println(t + " => "+ stHeight.get(t));
            a.addDataValue(stHeight.get(t));
        }

/*
        // print keys in order using select
        StdOut.println("Testing select");
        StdOut.println("--------------------------------");
        for (int i = 0; i <= st.size(); i++)
            StdOut.println(i + " " + st.select(i)); 
        StdOut.println();


        int n = 0;
        for (String word : st.keys()) {
            n++;
            StdOut.println(n + " || " + st.rank(word));
        }


        VisualAccumulator a = new VisualAccumulator(words * 2, st.height());
        VisualAccumulator a = new VisualAccumulator(298255, st.height());
            int n = 0;
            for (int position = 0; position < stHeight.get(t); position++)
                a.addDataValue(position);
                //int position = stHeight.get(t);
                //n++;
                //StdOut.println("**      n = " + n);

*/
                StdOut.println(a);

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