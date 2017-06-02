package basic.unionFind;

/**
 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 * imaging an in-tree, or child to parent tree structure when understanding this union preorderFind algorithm
 * union preorderFind has three operations:
 *
 * makeSet: singletons of elements
 * preorderFind: preorderFind representative of a set that x is in
 * union: taking two representatives, and merge them into one set
 * Created by 212595974 on 4/4/2017.
 */
public class FriendCircle {

        class UnionFind {
            private int count = 0;
            //parent is representative of a connected set
            private int[] parent, rank;

            //this is makeSet
            public UnionFind(int n) {
                count = n;
                parent = new int[n];
                rank = new int[n];
                //reflective: it connects to itself
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int findRec(int x) {

                if (parent[x] !=x){
                    parent[x] = findRec(parent[x]);}
                return parent[x];
            }
//optimization: adjust parent/representative while finding
            public int find(int p) {
                //the nodes on p's path to root node are changed to have parent to point to their original grand parent
                while (p != parent[p]) {
                    parent[p] = parent[parent[p]];    // path compression by halving
                    p = parent[p];
                }
                return p;
            }

            /**
             * The second improvement, called path compression, is a way of flattening the structure of the tree
             * whenever Find is used on it. The idea is that each node visited on the way to a root node may as well
             * be attached directly to the root node; they all share the same representative.
             * @param p
             * @param q
             */
            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) return;
                //joining smaller set to bigger set.just a way to determine to two set should be merged. smaller set requires less modification
                //union by rank to avoid unbalance
                if (rank[rootQ] > rank[rootP]) {
                    parent[rootP] = rootQ;
                }
                else {
                    parent[rootQ] = rootP;
                    if (rank[rootP] == rank[rootQ]) {
                        rank[rootP]++;
                    }
                }
                count--;
            }

            public int count() {
                return count;
            }
        }

        public int findCircleNum(int[][] M) {
            int n = M.length;
            UnionFind uf = new UnionFind(n);
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (M[i][j] == 1) uf.union(i, j);
                }
            }
            return uf.count();
        }
}

