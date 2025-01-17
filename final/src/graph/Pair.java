package graph;

/**
 * Implementation of a list of pairs to be used for graph connections. It uses
 * generic types, such that the list may be used for multiple applications.
 *
 * @param <A> Some type
 * @param <B> Some other type, can be the same as A.
 * @author mxa487, nxb484
 */
public class Pair<A, B> {
    private A fst;
    private B snd;

    /**
     * Initialise a pair (a,b) with types A and B
     *
     * @param fst First member of pair.
     * @param snd Second member of pair.
     */
    public Pair(A fst, B snd) {
        this.setFst(fst);
        this.setSnd(snd);
    }

    /**
     * Return the first value of the pair.
     *
     * @return The first value.
     */
    public A getFst() {
        return fst;
    }

    /**
     * Set the first value of the pair.
     *
     * @param fst A value of type A.
     */
    public void setFst(A fst) {
        this.fst = fst;
    }

    /**
     * Return the second value of the pair.
     *
     * @return The second value.
     */
    public B getSnd() {
        return snd;
    }

    /**
     * Set the second value of teh pair.
     *
     * @param snd A value of type B.
     */
    public void setSnd(B snd) {
        this.snd = snd;
    }


}
