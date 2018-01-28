import edu.princeton.cs.algs4.*;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Christopher on 15/01/2017.
 */

/**
 * Shortest Ancestral Path
 */
final public class SAP {

    private Digraph DG;
    private Map<VexPair, ResPair> cache;
    private static final int INFINITY = Integer.MAX_VALUE;

    /**
     * In order to save last queries, create a new class pair to hold the query inputs.
     * Be careful to implement both equals and hashCode methods.
     */
    private static class VexPair {
        private HashSet<Integer> vexV = new HashSet<>();
        private HashSet<Integer> vexW = new HashSet<>();
        public VexPair(int v, int w) {
            vexV.add(v);
            vexW.add(w);
        }

        public VexPair(Iterable<Integer> v, Iterable<Integer> w) {
            for (int vex : v) {
                vexV.add(vex);
            }
            for (int vex : w) {
                vexW.add(vex);
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            final VexPair vp = (VexPair) obj;

            return vexV.equals(vp.vexV) && vexW.equals(vp.vexW);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;

            result = prime * result + vexV.hashCode();
            result = prime * result + vexW.hashCode();
            return result;
        }
    }

    /**
     * Pair class for storing the length and ancestor.
     */
    private static class ResPair {
        public int length;
        public int ancestor;
        public ResPair(int length, int ancestor) {
            this.length = length;
            this.ancestor = ancestor;
        }
    }

    /**
     * Use a LinkedHashMap for LRUCache, just to override the removeEldestEntry method.
     * However if we don't provide the UID for serializable class, we have to suppress
     * the "serial" warning as below.
     */
    @SuppressWarnings("serial")
    private static <K, V> Map<K, V> lruCache(final int maxSize) {
        return new LinkedHashMap<K, V>(maxSize*4/3, 0.75f,true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
    }

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new NullPointerException();
        cache = lruCache(G.V());
        DG = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || v >= DG.V()) throw new IndexOutOfBoundsException("Input: " + v);
        if (w < 0 || w >= DG.V()) throw new IndexOutOfBoundsException("Input: " + w);
        ResPair resPair = findAncestorAndLength(v, w);
        return resPair.length;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path;
    // -1 if no such path
    public int ancestor(int v, int w) {
        if (v < 0 || v >= DG.V()) throw new IndexOutOfBoundsException("Input: " + v);
        if (w < 0 || w >= DG.V()) throw new IndexOutOfBoundsException("Input: " + w);
        ResPair resPair = findAncestorAndLength(v, w);
        return resPair.ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w;
    // -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new NullPointerException();
        validateVertices(v);
        validateVertices(w);
        ResPair resPair = findAncestorAndLength(v, w);
        return resPair.length;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new NullPointerException();
        validateVertices(v);
        validateVertices(w);
        ResPair resPair = findAncestorAndLength(v, w);
        return resPair.ancestor;
    }

    /**
     * To find the two vertices' ancestor and the shortest path is straightforward.
     * Build two BreadthFirstDirectedPaths for future queries, just iterate all the
     * vertices linearly and see if the input vertex has the shortest path to that
     * vertex. Then find the common shortest path ancestor for both vertices.
     */
    private ResPair findAncestorAndLength(int v, int w) {
        VexPair vp = new VexPair(v, w);
        if (cache.containsKey(vp)) return cache.get(vp);
        ResPair resPair;
        BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(DG, v);
        BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(DG, w);
        int minLen = INFINITY;
        int ances = -1;
        for (int i = 0; i < DG.V(); i++) {
            if (bfdpV.hasPathTo(i) && bfdpW.hasPathTo(i)) {
                int temp = bfdpV.distTo(i) + bfdpW.distTo(i);
                if (temp < minLen) {
                    minLen = temp;
                    ances = i;
                }
            }
        }
        if (minLen != INFINITY) {
            resPair = new ResPair(minLen, ances);
            cache.put(vp, resPair);
        } else {
            resPair = new ResPair(-1, -1);
        }
        return resPair;
    }

    /**
     * To find the two set of vertices' ancestor and the shortest path is straightforward.
     * Build two BreadthFirstDirectedPaths for future queries, just iterate all the
     * vertices linearly and see if the set of vertices has the shortest path to that
     * vertex. Then find the common shortest path ancestor for both set of vertices.
     */
    private ResPair findAncestorAndLength(Iterable<Integer> v, Iterable<Integer> w) {
        VexPair vp = new VexPair(v, w);
        if (cache.containsKey(vp)) return cache.get(vp);
        ResPair resPair;
        BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(DG, v);
        BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(DG, w);
        int minLen = INFINITY;
        int ances = -1;
        for (int i = 0; i < DG.V(); i++) {
            if (bfdpV.hasPathTo(i) && bfdpW.hasPathTo(i)) {
                int temp = bfdpV.distTo(i) + bfdpW.distTo(i);
                if (temp < minLen) {
                    minLen = temp;
                    ances = i;
                }
            }
        }
        if (minLen == INFINITY) {
            resPair = new ResPair(-1, -1);
        } else {
            resPair = new ResPair(minLen, ances);
            cache.put(vp, resPair);
        }
        return resPair;
    }

    /**
     * To see if a vertex is valid, throw exception when invalid.
     */
    private void validateVertices(Iterable<Integer> v) {
        for (int i : v) {
            if (i < 0 || i >= DG.V()) throw new IndexOutOfBoundsException(i + " in inputs");
        }
    }

    // for unit test
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        Bag<Integer> v = new Bag<>();
        Bag<Integer> w = new Bag<>();
        v.add(7);
        //v.add(3);
        w.add(11);
        w.add(7);
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        StdOut.printf("3, 7 length = %d, ancestor = %d\n", sap.length(3, 7), sap.ancestor(3, 7));

    }
}

