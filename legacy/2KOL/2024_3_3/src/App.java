
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import javax.naming.ldap.StartTlsRequest;

class GraphInfo<E extends Comparable<E>> {
    E info;
    
    public GraphInfo(E info) {
        this.info = info;
    }
}

class Graph<E extends Comparable<E>> {
    private GraphInfo<E> infos[];
    public int n;
    private int mtx[][];
    
    public Graph(int n) {
        this.n = n;
        infos = new GraphInfo[n];
        mtx = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mtx[i][j] = 0;
            }
        }
    }
    
    void addEdge(int x, int y, int w) {
        mtx[x][y] = w;
        mtx[y][x] = w;
    }
    
    void deleteEdge(int x, int y) {
        mtx[x][y] = -1;
        mtx[y][x] = -1;
    }
    
    void setInfo(int pos, E info) {
        infos[pos] = new GraphInfo(info);
    }
    
    E getInfo(int pos) {
        return infos[pos].info;
    }
    
    int getIndex(E info) {
        for (int i = 0; i < n; i++) {
            if (infos[i].info == info) {
                return i;
            }
        }
        
        return -1;
    }
    
    boolean neighbours(int x, int y) {
        if (mtx[x][y] == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    void addNode(E info) {
        ++n;
        
        GraphInfo[] infospom = new GraphInfo[n];
        for (int i = 0; i < n - 1; i++) {
            infospom[i] = infos[i];
        }
        infospom[n - 1] = new GraphInfo(info);
        
        int[][] mtxpom = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                mtxpom[i][j] = mtx[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            mtxpom[n - 1][i] = 0;
            mtxpom[i][n - 1] = 0;
        }
        
        infos = infospom;
        mtx = mtxpom;
    }
    
    void deleteNode(E info) {
        int ind = getIndex(info);
        
        if (ind != n - 1) {
            for (int i = ind; i < n - 1; i++) {
                for (int j = 0; j < n; j++) {
                    mtx[i][j] = mtx[i + 1][j];
                }
            }
            for (int j = ind; j < n - 1; j++) {
                for (int i = 0; i < n; i++) {
                    mtx[i][j] = mtx[i][j + 1];
                }
            }
            
            for (int i = ind; i < n - 1; i++) {
                infos[i] = infos[i + 1];
            }
        }
        
        n--;
    }
    
    public void printMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mtx[i][j] + ", ");
            }
            System.out.println();
        }
    }
    
    void dfs(int visited[], int start) {
        visited[start] = 1;
        System.out.println("Node: " + infos[start].info);
        
        for (int i = 0; i < n; i++) {
            if (i != start) {
                if (mtx[start][i] > 0 && visited[i] == 0) {
                    dfs(visited, i);
                }
            }
        }
    }

    public void NajdiCiklus(int [] tezini, int [] visited, int current ,int start, int parent, int tezina){
        visited[current] = 1;
        for(int i=0;i<n;i++){
            if(mtx[current][i] > 0 &&  visited[i] == 0){
                NajdiCiklus(tezini, visited, i, start, current, tezina+mtx[current][i]);
            }else if(mtx[current][i] > 0 && visited[i] == 1 && parent != i){
                if((tezina+mtx[current][i])<tezini[start]){
                    tezini[start] = tezina+mtx[current][i];
                }
            }
        }
        visited[current] = 0;
    }
    
}

public class App {
    public static void main(String[] args) throws Exception {

        Graph<Integer> graph = new Graph<Integer>(6);
        for(int i=0;i<6;i++){
            graph.setInfo(i, i);
        }
        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 3);
        graph.addEdge(2, 1, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 1, 7);
        graph.addEdge(3, 5, 5);
        graph.addEdge(3, 4, 8);
        graph.addEdge(4, 5, 2);

        NajdiNajmalTezinskiCiklus(graph);
    }

    private static void NajdiNajmalTezinskiCiklus(Graph<Integer> graph){
        int [] tezini = new int[graph.n];
        int [] visited = new int[graph.n];
        Arrays.fill(visited, 0);
        for(int i=0;i<graph.n;i++){
            tezini[i] = Integer.MAX_VALUE;
            graph.NajdiCiklus(tezini, visited, i, i, -1, 0);
        }
        int min_tezina = Integer.MAX_VALUE;
        int indeks = 0;
        for(int i=0;i<tezini.length;i++){
            if(tezini[i] < min_tezina){
                min_tezina = tezini[i];
                indeks = i;
            }
            System.out.println(tezini[i]);
        }
        System.out.println(tezini[indeks]);
    }
}
