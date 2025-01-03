import java.util.Arrays;
import java.util.Scanner;

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
}

public class App {

    public static void main(String[] args) throws Exception {
        Scanner input  = new Scanner(System.in);

        int n = input.nextInt();
        input.nextLine();

        Graph<String> graph = new Graph(n);

        String [] data = input.nextLine().split(" ");

        for(int i=0;i<n;i++){
            graph.setInfo(i, data[i]);
        }

        int edge_count = input.nextInt();
        input.nextLine();

        for(int i=0;i<edge_count;i++){
            graph.addEdge(input.nextInt(), input.nextInt());
        }

        graph.printMtx();

        int [] visited = new int[n];

        for(int i=0;i<n;i++){
            visited[i] = 0;
        }

        System.out.println(Arrays.toString(visited));

    }
}
