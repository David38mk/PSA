import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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
    
        public void findPath(int[] visited, int source, int destination, ArrayList<Integer> path) {
            visited[source] = 1;
            path.add(source);

            GNode<E> pom = graph[source];
            GNode<E> next;

            for(int i=0;i<pom.list.size(); i++){
                next = pom.list.get(i);

                if(visited[next.num]==0){
                    if(next.num != destination){
                        //pridvizi napred
                        findPath(visited, next.num, destination, path);
                        path.remove(path.size()-1);
                    }else{
                        path.add(destination);
                        System.out.println(path);
                        path.remove(path.size()-1);
                    }
                }
            }
            visited[source] = 0;
        }
    }
    
    
    public class App {
        public static void main(String[] args) throws Exception {
            String[] names = {"N1","N2","N3","N4"};
    
            Graph graph = new Graph(4, names);
            graph.addEdge(0, 1);
            graph.addEdge(0, 2);
            graph.addEdge(0, 3);
            graph.addEdge(2, 0);
            graph.addEdge(2, 1);
            graph.addEdge(1, 3);
    
            int[] visited = new int[graph.n];
            Arrays.fill(visited,0);
    
            ArrayList<Integer> path = new ArrayList<>();
            graph.findPath(visited,2,3,path);
    }
}