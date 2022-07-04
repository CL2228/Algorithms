package priorityqueues;

import java.util.PriorityQueue;
import java.util.Random;

public class MinPQ {

    int[] data;
    int N;
    int CAP;

    /**
     * initialize PriorityQueue
     * @param cap, the capacity of PQ
     */
    public MinPQ(int cap) {
        this.data = new int[cap + 1];
        this.N = 0;
    }

    public MinPQ() {
        this.data = new int[8];
        this.N = 0;
    }

    /**
     * add an element to priority queue
     * @param val given value
     */
    public void add(int val) {
        data[++N] = val;
        swim(N);
        expand();
    }

    /**
     * remove an element from the peek of PQ
     * @return
     */
    public int remove() {
        int val = data[1];
        exch(1, N--);
        data[N + 1] = 0;
        sink(1);
        shrink();
        return val;
    }

    public int peek() {
        return data[1];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void expand() {
        if (N < data.length - 1) return;

        int[] aux = new int[2 * data.length];
        System.arraycopy(data, 0, aux, 0, data.length);
        data = aux;
    }

    private void shrink() {
        if (data.length <= 8 || N > data.length / 4) {
            return;
        }
        int[] aux = new int[data.length / 2];
        System.arraycopy(data, 0, aux, 0, N + 1);
        data = aux;
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j + 1 <= N && greater(j, j + 1)) j += 1;
            if (greater(k, j)) {
                exch(k, j);
                k = j;
            } else {
                break;
            }
        }
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k/2, k);
            k /= 2;
        }
    }


    private boolean greater(int i, int j) {
        return data[i] > data[j];
    }

    private void exch(int i, int j) {
        int swap = data[i];
        data[i] = data[j];
        data[j] = swap;
    }



    public static void main(String[] args) {
        PriorityQueue<Integer> systemPQ = new PriorityQueue<>();
        MinPQ pq = new MinPQ();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int num = rand.nextInt(10000);
            systemPQ.add(num);
            pq.add(num);
            System.out.println("Testing add:" + num + "  systemPK:" + systemPQ.peek() + "   pqPK: "
                    + pq.peek() + "   valid:" + (systemPQ.peek() == pq.peek()));
        }
        System.out.println("\n\n Testing remove");
        while (!systemPQ.isEmpty()) {
            int systemPk = systemPQ.remove(), pqPK = pq.remove();
            System.out.println("Testing remove:  systemPK:" + systemPk + "   pqPK: "
                    + pqPK + "   valid:" + (pqPK == systemPk));
        }
    }
}












