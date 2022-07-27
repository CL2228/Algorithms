package priorityqueues;

public class IndexMinPQ <Key extends Comparable<Key>> {

    private int maxN;       // maximum number of elements on PQ
    private int n;          // current number of elements on PQ
    private int[] pq;       // pq array, pq[index] = i, key[i] = the key on index_th position on PQ
    private int[] qp;       // inverse of pq, qp[i] = index, index is the position on PQ
    private Key[] keys;     // keys[i] is the key of ith item

    public IndexMinPQ(int maxN) {
        this.maxN = maxN;
        this.n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;             // initialization, there is no key yet
    }

    /**
     * whether the PQ is empty
     * @return boolean value
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * check if the PQ contains key i
     * @param i the index
     * @return boolean value
     */
    public boolean contains(int i) {
        return qp[i] != -1;
    }

    /**
     * get the size of PQ
     * @return the size
     */
    public int size() { return n; }

    /**
     * return the index of the key that on top of PQ
     * @return the index of the key that on top of PQ
     */
    public int minIndex() {
        return pq[1];
    }

    /**
     * insert a key to PQ
     * @param i index if key
     * @param key key
     */
    public void insert(int i, Key key) {
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    /**
     * get the minimum key on PQ
     * @return
     */
    public Key minKey() {
        return keys[pq[1]];
    }

    /**
     * delete the minimum key and return its index
     * @return index
     */
    public int deleteMin() {
        int min = pq[1];
        exch(1, n--);
        sink(1);
        qp[min] = -1;
        keys[min] = null;
        return min;
    }

    /**
     * increase the priority of key i on PQ by replacing a higher-priority key
     * @param i the index of the key
     * @param key the key
     */
    public void decreaseKey(int i, Key key) {
        keys[i] = key;
        int index = qp[i];
        swim(index);
    }

    /**
     * decrease the priority of key i
     * @param i
     * @param key
     */
    public void increaseKey(int i, Key key) {
        keys[i] = key;
        sink(qp[i]);
    }


    /**
     * remove key i
     * @param i
     */
    public void delete(int i) {
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }


    /********************************************************
     * General Helper Functions
     ********************************************************/
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    /**
     * exchange the positions of the ith and jth items on PQ
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        // pq[i] -> keyI, pq[j] -> keyJ
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        // qp[pq[i]] is always i, qp[pq[j]] is always j
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /********************************************************
     * PQ Helper Functions
     ********************************************************/
    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j + 1 <= n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
}
