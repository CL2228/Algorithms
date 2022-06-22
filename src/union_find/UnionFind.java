package union_find;

public class UnionFind  {

    int[] data;
    int[] weight;
    int cap;

    public UnionFind(int cap) {
        this.cap = cap;
        this.data = new int[cap];
        this.weight = new int[cap];
        for (int i = 0; i < cap; i++) {
            data[i] = i;
            weight[i] = 1;
        }
    }

    /**
     * connect two indices
     *
     * In Union Find, the way to connect two indices is to connect their roots
     *
     * Because we desire the UF tree to be as balanced as possible,
     * we always put the root of the smaller as the child of the bigger tree
     * that's the reason why we need to maintain the weight array
     *
     * @param i one index
     * @param j another index
     */
    public void union(int i, int j) {
        validateIndex(i);
        validateIndex(j);
        int ancI = anc(i), ancJ = anc(j);
        if (ancI == ancJ) return;

        if (weight[ancI] >= weight[ancJ]) {
            weight[ancI] += weight[ancJ];
            data[ancJ] = ancI;
        } else {
            weight[ancJ] += weight[ancI];
            data[ancI] = ancJ;
        }
    }

    /**
     * The way to determine whether two indices are connected is to detect whether they share the same root
     * @param i given index
     * @param j given index
     * @return whether they are connected
     */
    public boolean connected(int i, int j) {
        validateIndex(i);
        validateIndex(j);
        return anc(i) == anc(j);
    }

    /**
     * find the root of an index
     * In Union find, an index always points to its parent index (unless itself is the root)
     * @param i: given index
     * @return the root index
     */
    private int anc(int i) {
        while (data[i] != i) {
            i = data[i];
        }
        return i;
    }

    private void validateIndex(int i) {
        assert i >= 0 && i < cap;
    }


    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
    }
}
