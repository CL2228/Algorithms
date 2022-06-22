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

    public void union(int i, int j) {
        validateIndex(i);
        validateIndex(j);
        int ancI = anc(i), ancJ = anc(j);
        if (weight[ancI] >= weight[ancJ]) {
            weight[ancI] += weight[ancJ];
            data[ancJ] = ancI;
        } else {
            weight[ancJ] += weight[ancI];
            data[ancI] = ancJ;
        }
    }

    public boolean connected(int i, int j) {
        validateIndex(i);
        validateIndex(j);
        return anc(i) == anc(j);
    }

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
