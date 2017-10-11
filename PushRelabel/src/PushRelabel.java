public class PushRelabel {
    
  int[][] capacityMatrix;

  public void init(int nodes) {
	  capacityMatrix = new int[nodes][nodes];
  }

  public void addEdge(int s, int t, int capacity) {
	  capacityMatrix[s][t] = capacity;
  }

  public int maxFlow(int s, int t) {
    int n = capacityMatrix.length;
    // Intialize all heights, h
    int[] h = new int[n];
    h[s] = n - 1;

    int[] maxh = new int[n];

    int[][] f = new int[n][n];
    int[] e = new int[n];

    // Intialize the preflow and excess of every edge
    for (int i = 0; i < n; ++i) {
      f[s][i] = capacityMatrix[s][i];
      f[i][s] = -f[s][i];
      e[i] = capacityMatrix[s][i];
    }

    // 
    for (int sz = 0;;) {
     // Loop running for the first time 
      if (sz == 0) {
        for (int i = 0; i < n; ++i)
          if (i != s && i != t && e[i] > 0) {
            if (sz != 0 && h[i] > h[maxh[0]])
              sz = 0;
            maxh[sz++] = i;
          }
      }
      if (sz == 0)
        break;
      while (sz != 0) {             //while there exists v != t such that ef(v) > 0
        int i = maxh[sz - 1];       // Intialize i to maximum of h 
        boolean pushed = false;     // Its is not pushed yet
        // go through all the nodes
        for (int j = 0; j < n && e[i] != 0; ++j) {
          if (h[i] == h[j] + 1 && capacityMatrix[i][j] - f[i][j] > 0) { //if ef(v) > 0 and h(w) < h(v) and (v,w) is a residual edge 
            int df = Math.min(capacityMatrix[i][j] - f[i][j], e[i]);       // (v,w) is a residual edge
            f[i][j] += df;
            f[j][i] -= df;
            e[i] -= df;
            e[j] += df;
            if (e[i] == 0)
              --sz;
            pushed = true;                                  // mark as pushed
          }
        }
        if (!pushed) {                                      // Relabel operation since it can't be pushed
          h[i] = Integer.MAX_VALUE;
          for (int j = 0; j < n; ++j)
            if (h[i] > h[j] + 1 && capacityMatrix[i][j] - f[i][j] > 0)  //if ef(v) > 0 and for all (v,w) in Ef h(w) >= h(v)
              h[i] = h[j] + 1;
          if (h[i] > h[maxh[0]]) {
            sz = 0;
            break;
          }
        }
      }
    }

    int flow = 0;
    for (int i = 0; i < n; i++)
      flow += f[s][i];

    return flow;
  }

  
  public static void main(String[] args) {
    int[][] capacity = { { 0,16,13,0,0,0}, { 0,0,10,12,0,0}, { 0, 4,0,0,14,0},{0,0,9,0,0,20},{0,0,0,7,0,4},{0,0,0,0,0,0} };
    int n = capacity.length;
    PushRelabel flow = new PushRelabel();
    flow.init(n);
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        if (capacity[i][j] != 0)
          flow.addEdge(i, j, capacity[i][j]);
    System.out.println("Maximum flow is: " + flow.maxFlow(0, 5));
  }
    
}
