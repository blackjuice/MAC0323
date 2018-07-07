/*************************************************************************
 *  Compilation:  javac ModFrequencyCounter.java
 *  Execution:    java ModFrequencyCounter L < input.txt
 *  Dependencies: ST.java StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/31elementary/tnyTale.txt
 *                http://algs4.cs.princeton.edu/31elementary/tale.txt
 *                http://algs4.cs.princeton.edu/31elementary/leipzig100K.txt
 *                http://algs4.cs.princeton.edu/31elementary/leipzig300K.txt
 *                http://algs4.cs.princeton.edu/31elementary/leipzig1M.txt
 *
 *  Read in a list of words from standard input and print out
 *  the most frequently occurring word that has length greater than
 *  a given threshold.
 *
 *  % java ModFrequencyCounter 1 < tinyTale.txt
 *  it 10
 *
 *  % java ModFrequencyCounter 8 < tale.txt
 *  business 122
 *
 *  % java ModFrequencyCounter 10 < leipzig1M.txt
 *  government 24763
 *
 *
 *************************************************************************/


public class ModFrequencyCounter {

    /**
     * Reads in a command-line integer and sequence of words from
     * standard input and prints out a word (whose length exceeds
     * the threshold) that occurs most frequently to standard output.
     * It also prints out the number of words whose length exceeds
     * the threshold and the number of distinct such words.
     */
    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        BST<String, Integer> st = new BST<String, Integer>();


            //Queue<Key> queue = new Queue<Key>();

                    StdOut.println("args[0] = " + args[0]);
                    Stopwatch start = new Stopwatch();
                    int comparison = 0;


        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            // key = key.toLowerCase();
            // key = key.replaceAll("[^a-zA-Z]", "");
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
                        //comparison++;
            }
                        //StdOut.println("** st height = " + st.height());
                        //StdOut.println("** st size ?? = " + st.size());      
                            // nmber of elements in tree
        }

                    VisualAccumulator a = new VisualAccumulator(words, 1.0);

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }



/*    
*/
                            for (int t = 0; t < words; t++)
                                //a.addDataValue(t);
                                //a.addDataValue(comparison);
                                a.addDataValue(st.size());
                                //StdOut.println("comparison = " + comparison);








        StdOut.println(a);                          // impressao de mean()
        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
                    //StdOut.println("comparison = " + comparison);
                    //StdOut.println("st height = " + st.height());
                    //StdOut.println("st keys() = " + st.keys()); // imprime todos os elementos

        if (args.length > 1) {
            for (String word : st.keys()) 
            StdOut.println(word);
        }

        double time = start.elapsedTime();
        StdOut.println("time = " + time);
        StdOut.println();
    }
}































public class ModFrequencyCounter {

    /**
     * Reads in a command-line integer and sequence of words from
     * standard input and prints out a word (whose length exceeds
     * the threshold) that occurs most frequently to standard output.
     * It also prints out the number of words whose length exceeds
     * the threshold and the number of distinct such words.
     */
    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        BST<String, Integer> st = new BST<String, Integer>();

                    //Queue<Key> queue = new Queue<Key>();
                    StdOut.println("args[0] = " + args[0]);
                    Stopwatch start = new Stopwatch();
                    //int comparison = 0;

/*
                    while (!StdIn.isEmpty()) {
                        String key = StdIn.readString();
                        words++;
                    }
                    VisualAccumulator a = new VisualAccumulator(words, 1.0);
                    VisualAccumulator a = new VisualAccumulator(words, 1.0);
*/

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            // key = key.toLowerCase();
            // key = key.replaceAll("[^a-zA-Z]", "");
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
                        //comparison++;
            }
                                a.addDataValue(st.height());
                            //StdOut.println("** st height = " + st.height());
                            //StdOut.println("** st size ?? = " + st.size());      
                            // nmber of elements in tree
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }

/*    
*/
                            //for (int t = 0; t < words; t++)
                                //a.addDataValue(t);
                                //a.addDataValue(comparison);
                                //StdOut.println("comparison = " + comparison);


        StdOut.println(a);                          // impressao de mean()
        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
                    //StdOut.println("comparison = " + comparison);
                    StdOut.println("st height = " + st.height());
                    //StdOut.println("st keys() = " + st.keys()); // imprime todos os elementos

        if (args.length > 1) {
            for (String word : st.keys()) 
            StdOut.println(word);
        }

        double time = start.elapsedTime();
        StdOut.println("time = " + time);
        StdOut.println();
    }
}





























// no idea here


public class ModFrequencyCounter {

    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        BST<String, Integer> st = new BST<String, Integer>();
        BST<String, Integer> stHeight = new BST<String, Integer>();

                    StdOut.println("args[0] = " + args[0]);
                    Stopwatch start = new Stopwatch();
/*
                    while (!StdIn.isEmpty()) {
                        String key = StdIn.readString();
                        words++;
                    }
                    VisualAccumulator a = new VisualAccumulator(words, 1.0);
*/

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            // key = key.toLowerCase();
            // key = key.replaceAll("[^a-zA-Z]", "");
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
                            //stHeight.put(key, stHeight.height());
            }
            else {
                st.put(key, 1);
                distinct++;
                            //stHeight.put(key, stHeight.height());
                            //comparison++;
                            //if (stHeight.height() == -1) StdOut.println("-1 *****");
                            int altura = stHeight.height(); altura++;
                            StdOut.println("*** altura = " + altura);
                            stHeight.put(key, altura);
                            //stHeight.put(key, stHeight.height());
            }
                            StdOut.println("** stHeight height = " + stHeight.height());
                            StdOut.println("** st height = " + st.height());
                            //a.addDataValue(st.height());
                            //StdOut.println("** st size ?? = " + st.size());      
                            // nmber of elements in tree
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }

        VisualAccumulator a = new VisualAccumulator(words, 1.0);



                        for (int t = 0; t < words; t++) {

                            
                        }

/*    
                            int leveltimes = 0;
                        for (String position : st.keys()) {
                        //for (String position : st.levelOrder()) {
                        //for (String position : stHeight.levelOrder()) {
                        //for (String position : stHeight.keys())
                            leveltimes++;
                        }
                            StdOut.println("leveltimes ***** = " + leveltimes);

                            
                            StdOut.println("list: " + position + " " + stHeight.get(position));
                            StdOut.println("stHeight height = " + stHeight.height());
                            StdOut.println("st height = " + st.height());
                                a.addDataValue(t);
                                a.addDataValue(comparison);
                                StdOut.println("comparison = " + comparison);
        StdOut.println(a);                          // impressao de mean()
                    //StdOut.println("comparison = " + comparison);
                    //StdOut.println("st keys() = " + st.keys()); // imprime todos os elementos
*/


        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);

        if (args.length > 1) {
            for (String word : st.keys()) 
            //for (String word : st.levelOrder()) 
                StdOut.println(word);
        }

        double time = start.elapsedTime();
        StdOut.println("time = " + time);
        StdOut.println();
    }
}





























public class ModFrequencyCounter {

    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        BST<String, Integer> st = new BST<String, Integer>();
        BST<Integer, Integer> stHeight = new BST<Integer, Integer>();

                    int altura = 0;
                    StdOut.println("args[0] = " + args[0]);
                    Stopwatch start = new Stopwatch();

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            // key = key.toLowerCase();
            // key = key.replaceAll("[^a-zA-Z]", "");
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
                            
                            //altura = stHeight.height(); altura += 2;
            }
                            //stHeight.put(words, altura);
                            //stHeight.put(words, stHeight.height());
                            //StdOut.println("** stHeight height = " + stHeight.height());

        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }




        //VisualAccumulator a = new VisualAccumulator(words, 1.0);
        

/*
        for (Integer position : stHeight.keys()) {
            StdOut.println(position + " " + stHeight.get(position));
            //a.addDataValue(position);
        }



        //for (String position : st.levelOrder()) {
        //for (String position : stHeight.levelOrder()) {
        //for (String position : stHeight.keys())
            //leveltimes++;

        for (int t = 0; t < words; t++) {
            //a.addDataValue(t);
            //a.addDataValue(comparison);
            //StdOut.println("comparison = " + comparison);
        }
*/


        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);

        if (args.length > 1) {
            for (String word : st.keys()) 
            //for (String word : st.levelOrder()) 
                StdOut.println(word);
        }

        double time = start.elapsedTime();
        StdOut.println("time = " + time);
        StdOut.println();
    }
}































public class ModFrequencyCounter {

    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        BST<String, Integer> st = new BST<String, Integer>();
        BST<Integer, Integer> stHeight = new BST<Integer, Integer>();

                    int altura = 0;
                    StdOut.println("args[0] = " + args[0]);
                    Stopwatch start = new Stopwatch();

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            // key = key.toLowerCase();
            // key = key.replaceAll("[^a-zA-Z]", "");
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
                            
                            //altura = stHeight.height(); altura += 2;
            }
                            //StdOut.println("        " + st.height());
                            stHeight.put(words, st.height());
                            //stHeight.put(words, stHeight.height());
                            //StdOut.println("key = " + words + " || stHeight height = " + st.height());
                            //StdOut.println("key = " + words + " || value = " + stHeight.get(words));

        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }


        //StdOut.println("**      here: " + stHeight.get(2));
/*
        for (Integer position : stHeight.keys()) {
            //StdOut.println(position + " " + stHeight.get(position));
            //a.addDataValue(position);
            StdOut.println(".");
        }
                int position = 61;
                StdOut.println("**            " + position + " " + stHeight.get(position));
*/
        VisualAccumulator a = new VisualAccumulator(words, 50.0);
        for (int t = 1; t <= words; t++) {
            int position = stHeight.get(t);
            StdOut.println("key, " + t + " = " + position);
            a.addDataValue(position);
        }









                StdOut.println("**      st height = " + st.height());
                StdOut.println("**      stHeight height = " + stHeight.height());
        StdOut.println(a);
        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);


        if (args.length > 1) {
            //for (String word : st.levelOrder()) 
            for (String word : st.keys()) 
                StdOut.println(word);
        }

                double time = start.elapsedTime();
                StdOut.println("time = " + time);
                StdOut.println();
    }
}



























public class ModFrequencyCounter {

    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        BST<String, Integer> st = new BST<String, Integer>();
        BST<Integer, Integer> stHeight = new BST<Integer, Integer>();

                    int altura = 0;
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
                            
                            //altura = stHeight.height(); altura += 2;
            }
                            //StdOut.println("        " + st.height());
                            stHeight.put(words, st.height());
                            //stHeight.put(words, stHeight.height());
                            //StdOut.println("key = " + words + " || stHeight height = " + st.height());
                            //StdOut.println("key = " + words + " || value = " + stHeight.get(words));

        }

        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }

/*
        //VisualAccumulator a = new VisualAccumulator(words, 50.0);



        //VisualAccumulator a = new VisualAccumulator(words, 50.0);
        VisualAccumulator a = new VisualAccumulator(words, st.height());
        for (int t = 1; t <= words; t++) {
            for (int position = 0; position < stHeight.get(t); position++)
                //int position = stHeight.get(t);
                //StdOut.println("key, " + t + " = " + position);
                a.addDataValue(position);
        }
*/
        VisualAccumulator a = new VisualAccumulator(words, st.height());
        for (int t = 1; t <= words; t++) {
            int position = stHeight.get(t);
            //StdOut.println("key, " + t + " = " + position);
            a.addDataValue(position);
        }





                StdOut.println("**      st height = " + st.height());
                StdOut.println("**      stHeight height = " + stHeight.height());
        StdOut.println(a);
        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);

        //int num = 0;
        if (args.length > 1) {
            //for (String word : st.levelOrder()) 
            for (String word : st.keys())  {
                StdOut.println(word);
                //num++;
            }
        }
        //StdOut.println("**  num = " + num);

                double time = start.elapsedTime();
                StdOut.println("time = " + time);
                StdOut.println();
    }
}