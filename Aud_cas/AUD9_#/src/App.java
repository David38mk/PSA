import java.util.Arrays;

class GraphInfo<E extends Comparable<E>> {
    E info;

    public GraphInfo(E info){
        this.info = info;
    }
}

class Graph<E extends Comparable<E>>{
    private  GraphInfo<E> infos[];
    private  int n;
    private  int mtx[][];

    public Graph(int n){
        this.n = n;
        infos = new GraphInfo[n];
        mtx = new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                mtx[i][j]=0;
            }
        }
    }

    void addEdge(int x, int y){
        mtx[x][y] = 1;
        mtx[y][x] = 1;
    }

    void deleteEdge(int x, int y){
        mtx[x][y] = 0;
        mtx[y][x] = 0;
    }

    void setInfo(int pozz, E info){
        infos[pozz] = new GraphInfo(info);
    }

    E getInfo(int pozz){
        return infos[pozz].info;
    }

    int getIndex(E info){
        for(int i=0;i<n;i++){
            if(infos[i].info==info){
                return i;
            }
        }
        return -1;
    }

    boolean neighbours(int x, int y){
        if(mtx[x][y]==1){
            return true;
        }else{
            return false;
        }
    }

    void addNode(E info){
        ++n;
        GraphInfo[] infospom = new GraphInfo[n];
        for(int i=0;i<n-1;i++){
            infospom[i]=infos[i];
        }
        infospom[n-1] = new GraphInfo(info);

        int[][] mtxpom = new int[n][n];
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-1;j++){
                mtxpom[i][j] = mtx[i][j];
            }
        }

        for(int i=0;i<n;i++){
            mtxpom[n-1][i]=0;
            mtxpom[i][n-1]=0;
        }

        infos = infospom;
        mtx = mtxpom;
    }

    void deleteNode(E info){
        int ind = getIndex(info);

        if (ind != n-1) {
            for(int i=ind;i<n-1;i++){
                for(int j = 0;j<n;j++){
                    mtx[i][j] = mtx[i+1][j];
                }
            }
            for(int j=ind;j<n-1;j++){
                for(int i = 0;i<n;i++){
                    mtx[i][j] = mtx[i][j+1];
                }
            }

            for(int i=ind; i<n-1; i++){
                infos[i] = infos[i+1];
            }
        }
        n--;
    }

    public void printMtx(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(mtx[i][j]+", ");
            }
            System.out.println();
        }
    }

    void dfs(int visited[], int start){
        visited[start] = 1;
        System.out.println("Node: " + infos[start].info);

        for(int i=0;i<n;i++){
            if(i != start){
                if(mtx[i][start] > 0 && visited[i] == 0){
                    dfs(visited, i);
                }
            }
        }
    }
    
        public boolean  detectIfCycleExists(int[] visited, int start, int parent) {
            visited[start] = 1;
            System.out.println(infos[start].info);

            for(int i=0;i<n;i++){
                if(mtx[start][i]>0){
                    if(visited[i]==0){
                        if(detectIfCycleExists(visited, i, start)){
                            return true;
                        }
                    }else if(visited[i] == 1 && parent != i){
                        return true;
                    }
                }
            }
            return false;
        }
    }
    
    public class App {
    
        public static void main(String[] args) throws Exception {
            int num_nodes = 5;
            Graph<String> graph = new Graph(num_nodes);
    
            for(int i=0;i<num_nodes;i++){
                graph.setInfo(i, "N"+i);
            }

            /*graph.addEdge(1, 2);
            graph.addEdge(2, 4);
            graph.addEdge(0, 1);
            graph.addEdge(1, 4);*/

            graph.addEdge(0, 1);
            graph.addEdge(1, 2);
            graph.addEdge(1, 3);
            graph.addEdge(0, 4);
            graph.addEdge(1, 4);
    
            int[] visited = new int[num_nodes];
            Arrays.fill(visited,0);
    
            System.out.println(graph.detectIfCycleExists(visited,0,-1));
    }
}
