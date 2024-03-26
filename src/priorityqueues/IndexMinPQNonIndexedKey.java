/**
 * sometime keys on PQ is not indexed,
 * and it is necessary to enable arbitrary insertions and deletions
 * Here we maintain data with HashMap
 *
 */

package priorityqueues;

import java.util.Map;

public class IndexMinPQNonIndexedKey <Key extends Comparable<Key>> {

    int maxN;
    int n;
    Key[] pq;
    Map<Key, Integer> key2idx;

    public IndexMinPQNonIndexedKey(int maxN) {
        this.maxN = maxN;
        this.n = 0;
        this.pq = (Key[]) new Comparable[maxN + 1];
    }

    public int size() { return n; }

    public boolean isEmpty() { return n == 0; }

    

    private void insert(Key key) {

    }


    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    private boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key keyI = pq[i], keyJ = pq[j];

        pq[j] = keyI;
        pq[i] = keyJ;
        key2idx.put(keyI, j);
        key2idx.put(keyJ, i);
    }
}

