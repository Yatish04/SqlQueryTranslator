import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Christopher on 16/01/2017.
 */
final public class WordNet {

    /**
     * Cache IDs with related synsets, and nouns with related IDs. Because a noun
     * may appear in multiple synsets and have multiple IDs.
     */
    private HashMap<Integer, String[]> idToSynset = new HashMap<>();
    private HashMap<String, Stack<Integer>> nounToIds = new HashMap<>();
    private int synsetsNum;
    private Digraph DG;
    private SAP dgSap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypername) {
        if (synsets == null || hypername == null)
            throw new NullPointerException();
        // process the synsets file
        In in = new In(synsets);
        String line;
        String[] fields;
        int synsetId = 0;
        String[] nouns = null;
        while ((line = in.readLine()) != null) {

            fields = line.split(",");
            synsetId = Integer.parseInt(fields[0]);
            nouns = fields[1].split(" ");
            idToSynset.put(synsetId, nouns);
            for (String s : nouns) {
                if (nounToIds.containsKey(s)) nounToIds.get(s).push(synsetId);
                else {
                    Stack<Integer> stk = new Stack<>();
                    stk.push(synsetId);
                    nounToIds.put(s, stk);
                }
            }

        }
        synsetsNum = synsetId + 1;
        // process the hypername file
        DG = new Digraph(synsetsNum);
        in = new In(hypername);
        while ((line = in.readLine()) != null) {
            fields = line.split(",");
            for (int i = 1; i < fields.length; i++) {
                DG.addEdge(Integer.parseInt(fields[0]), Integer.parseInt(fields[i]));
            }
        }
        // validate the DG is a single rooted DAG
        validateDG(DG);
        // generate the SAP instance
        dgSap = new SAP(DG);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounToIds.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new NullPointerException();
        return nounToIds.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    /**
     * Two nouns distance is the shortest ancestral path from any synsets of nounA
     * to any synsets of nounB. The implementation is pretty straightforward, just
     * use the corresponding method in SAP class.
     */
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new NullPointerException();
        if (!isNoun(nounA)) throw new IllegalArgumentException(nounA + " not noun");
        if (!isNoun(nounB)) throw new IllegalArgumentException(nounB + " not noun");
        Stack<Integer> synsetsA = nounToIds.get(nounA);
        Stack<Integer> synsetsB = nounToIds.get(nounB);
        return dgSap.length(synsetsA, synsetsB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new NullPointerException();
        if (!isNoun(nounA)) throw new IllegalArgumentException(nounA + " not noun");
        if (!isNoun(nounB)) throw new IllegalArgumentException(nounB + " not noun");
        Stack<Integer> synsetsA = nounToIds.get(nounA);
        Stack<Integer> synsetsB = nounToIds.get(nounB);
        int ancestor = dgSap.ancestor(synsetsA, synsetsB);
        StringBuilder sb = new StringBuilder();
        if (idToSynset.containsKey(ancestor)) {
            for (String s : idToSynset.get(ancestor)) {
                sb.append(s);
                sb.append(" ");
            }
            return sb.substring(0, sb.length() - 1);
        } else {
            return null;
        }
    }

    /**
     * A single rooted DAG means: 1) only one source has no outdegree;
     * 2) it has no cycles.
     */
    private void validateDG(Digraph G) {

        int sources = 0;
        for (int i = 0; i < DG.V(); i++) {
            if (DG.outdegree(i) == 0) sources++;
        }
        if (sources != 1) throw new IllegalArgumentException(sources + " root(s)");
        DirectedCycle dc = new DirectedCycle(G);
        if (dc.hasCycle()) throw new IllegalArgumentException("not acyclic");
    }

    public String findhypernym(String noun,String check)
    {
        int length = this.distance(noun, check);
        String ancestor = this.sap(noun, check);
        System.out.println("\nFor "+noun+" and "+check+" the ancestor is "+ancestor);
        if (ancestor.contains(check))
        {
            System.out.println("Hypernym found:   ancestor:" + ancestor + " check: " + check);
            return check;
        }
        return "Not found";
    }
}

