import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

/**
 * Created by Christopher on 17/01/2017.
 */

/**
 * Find a noun which has the largest distance from the rest of nouns in the input.
 */
final public class Outcast {

    private WordNet wordNet;

    private static class NounPair {
        public String nounA;
        public String nounB;

        public NounPair(String nounA, String nounB) {
            this.nounA = nounA;
            this.nounB = nounB;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;
            if (obj.getClass() != getClass()) return false;
            final NounPair np = (NounPair) obj;
            return (np.nounA.equals(nounA) && np.nounB.equals(nounB)) ||
                    (np.nounA.equals(nounB) && np.nounB.equals(nounA));
        }

        @Override
        public int hashCode() {
            return 31 + 31 * (nounA.hashCode() + nounB.hashCode());
        }
    }

    public Outcast(WordNet wordNet) {
        if (wordNet == null) throw new NullPointerException();
        this.wordNet = wordNet;
    }

    /**
     * Iterate over the input set of nouns to calculate the max distance, but to
     * cache the distance between two nouns for time!.
     */
    public String outcast(String[] nouns) {
        int dist = -1;
        int idx = -1;
        HashMap<NounPair, Integer> cache = new HashMap<>();
        for (int i = 0; i < nouns.length; i++) {
            int temp = 0;
            for (String n : nouns) {
                if (nouns[i].equals(n)) continue;
                NounPair np = new NounPair(nouns[i], n);
                if (cache.containsKey(np)) temp += cache.get(np);
                else {
                    int tempDist = wordNet.distance(nouns[i], n);
                    cache.put(np, tempDist);
                    temp += tempDist;
                }
            }
            if (temp > dist) {
                dist = temp;
                idx = i;
            }
        }
        if (idx >= 0 && idx < nouns.length) return nouns[idx];
        else return null;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}

