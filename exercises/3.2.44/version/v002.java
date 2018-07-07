

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
            }
            else {
                st.put(key, 1);
                distinct++;
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
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max))
                max = word;



        VisualAccumulator a = new VisualAccumulator(words, 14349);
        //VisualAccumulator a = new VisualAccumulator(words, stHeight.height() );
        for (int t = 1; t <= words; t++) {
            //StdOut.println("|| "+ stHeight.get(t));
            a.addDataValue(stHeight.get(t));
        }
/*
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



























public class ModFrequencyCounter {


    public class Element<Key extends Comparable<Key>, Value> {
        private final double time;         // time that Element is scheduled to occur
        private final Particle a, b;       // particles involved in Element, possibly null
        private final int countA, countB;  // collision counts at Element creation
                
        
        // create a new Element to occur at time t involving a and b
        public Element(Integer) {
            this.time = t;
            this.a    = a;
            this.b    = b;
            if (a != null) countA = a.count();
            else           countA = -1;
            if (b != null) countB = b.count();
            else           countB = -1;
        }

        // compare times when two Elements will occur
        public int compareTo(Element that) {
            if      (this.time < that.time) return -1;
            else if (this.time > that.time) return +1;
            else                            return  0;
        }

    }






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
            }
            else {
                st.put(key, 1);
                distinct++;
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
        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys())
            if (st.get(word) > st.get(max))
                max = word;



        VisualAccumulator a = new VisualAccumulator(words, 14349);
        //VisualAccumulator a = new VisualAccumulator(words, stHeight.height() );
        for (int t = 1; t <= words; t++) {
            //StdOut.println("|| "+ stHeight.get(t));
            a.addDataValue(stHeight.get(t));
        }
/*
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