import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

class GNode<E>{
    public int num;
    public E info;
    public LinkedList<GNode> list;

    public GNode(int num, E info){
        this.num = num;
        this.info = info;
    }

    void addNeighbour(GNode<E> node){
        if(!list.contains(node)){
            list.add(node);
        }
    }

    void deleteNeighbour(GNode<E> node){
        if(list.contains(node)){
            list.remove(node);
        }
    }

    boolean hasNeighbour(GNode<E> node){
        return list.contains(node);
    }

}

class Graph<E> {
    public int n;
    public GNode<E> graph[];

    public Graph(int n, E[] infos){
        this.n = n;
        graph = new GNode[n];
        for(int i=0;i<n;i++){
            graph[i] = new GNode(i, infos[i]);
        }
    }

    boolean neighbours(int x, int y){
        return graph[x].hasNeighbour(graph[y]);
    }

    void addEdge(int x, int y){
        graph[x].addNeighbour(graph[y]);
    }

    void deleteEdge(int x, int y){
        graph[x].deleteNeighbour(graph[y]);
    }

    GNode getNode(E info){
        for(int i = 0;i<n;i++){
            if(graph[i].info == info){
                return graph[i];
            }
        }
        return null;
    }

    void addNode(E info){
        ++n;

        GNode<E> [] graphpom = new GNode[n];

        for(int i = 0;i<n;i++){
            graphpom[i] =graph[i];
        }
        graphpom[n-1] = new GNode(n-1, info);

        graph = graphpom;
    }

    void deleteNode(E info){
        GNode node = getNode(info);
        if(node == null){
            return;
        }else{
            for(int i=0;i<n;i++){
                if(graph[i].hasNeighbour(node)){
                    graph[i].deleteNeighbour(node);
                }
            }

            for(int i = node.num;i<n-1;i++){
                graph[i] = graph[i+1];
                graph[i].num = i;
            }
            n--;
        }

    }
    
    void dfs(int visited[], int start){
        visited[start] = 1;
        System.out.println("Node: " + graph[start].info);

        GNode<E> pom = graph[start];
        GNode<E> next;
        for(int i=0;i<pom.list.size();i++){
            next = pom.list.get(i);
            if(visited[next.num] == 0){
                dfs(visited, next.num);
            }
        }

    }
}


public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        input.nextLine();

        String[] data = input.nextLine().split(" ");

        Graph<String> graph = new Graph(n, data);

        graph.addEdge(0, 1);
        graph.addEdge(3, 4);
        graph.addEdge(2, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(0, 3);

        int [] visited = new int[n];

        for(int i=0;i<n;i++){
            visited[i] = 0;
        }
        graph.dfs(visited, 0);
        System.out.println(Arrays.toString(visited));
    }
}
