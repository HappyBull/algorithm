package dayExercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zouzh
 * @date 2022/4/6 23:41
 */

//最小高度树
    //常用的解法有深搜和广搜，配合回溯法
public class Question310 {

    //这里使用深搜
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<Integer>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        /* 找到与节点 0 最远的节点 x */
        int x = findLongestNode(0, parent, adj);
        /* 找到与节点 x 最远的节点 y */
        int y = findLongestNode(x, parent, adj);
        /* 求出节点 x 到节点 y 的路径 */
        List<Integer> path = new ArrayList<Integer>();
        parent[x] = -1;
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        int m = path.size();
        if (m % 2 == 0) {
            ans.add(path.get(m / 2 - 1));
        }
        ans.add(path.get(m / 2));
        return ans;
    }

    public int findLongestNode(int u, int[] parent, List<Integer>[] adj) {
        int n = adj.length;
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[u] = 0;
        dfs(u, dist, parent, adj);
        int maxdist = 0;
        int node = -1;
        for (int i = 0; i < n; i++) {
            if (dist[i] > maxdist) {
                maxdist = dist[i];
                node = i;
            }
        }
        return node;
    }

    public void dfs(int u, int[] dist, int[] parent, List<Integer>[] adj) {
        for (int v : adj[u]) {
            if (dist[v] < 0) {
                dist[v] = dist[u] + 1;
                parent[v] = u;
                dfs(v, dist, parent, adj);
            }
        }
    }

    public static void main(String[] args) {

    }
}
