import java.lang.reflect.Array;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedList;

class GNode<E>{
    public int num;
    public E info;
    public LinkedList<GNode> list;

    public GNode(int num, E info){
        this.num = num;
        this.info = info;
        this.list = new LinkedList<>();
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
        graph[y].addNeighbour(graph[x]);
    }

    void deleteEdge(int x, int y){
        graph[x].deleteNeighbour(graph[y]);
        graph[y].deleteNeighbour(graph[x]);
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
    
    public void printaj(){
        for(int i=0;i<n;i++){
            System.out.print(graph[i].info+" ");
            for(int j=0;j<graph[i].list.size();j++){
                System.out.print(graph[i].list.get(j).info+", ");
            }
            System.out.println("");
        }
    }

}

public class App {
    public static void main(String[] args) throws Exception {
        Character [] niza = {'A','B','C','D','O','A','F','G'};
        
        Graph<Character> graph1 = new Graph<Character>(8, niza);

        graph1.addEdge(0, 1);
        graph1.addEdge(0, 3);
        graph1.addEdge(0, 4);
        graph1.addEdge(4, 2);
        graph1.addEdge(4, 1);
        graph1.addEdge(4, 3);
        graph1.addEdge(3, 2);
        graph1.addEdge(1, 2);
        graph1.addEdge(1, 5);
        graph1.addEdge(2, 5);
        graph1.addEdge(5, 6);
        graph1.addEdge(5, 7);


        @SuppressWarnings("unchecked")
        Graph<Character> graph2 = kopiraj(graph1.getNode('A'));

        graph2.printaj();


        
    }
        
    private static Graph<Character> kopiraj(GNode<Character> start){
        ArrayList<GNode<Character>> jazli = new ArrayList<GNode<Character>>();
        napuni_lista(jazli, start);
        Character [] infos = new Character[jazli.size()];
        for(int i=0;i<jazli.size();i++){
            infos[i] = jazli.get(i).info;
            jazli.get(i).num = i;
        }
        Graph<Character> pomGraph = new Graph<Character>(infos.length, infos);
        for(int i=0;i<jazli.size();i++){
            for(int j =0;j<jazli.get(i).list.size();j++){
                pomGraph.addEdge(i, jazli.get(i).list.get(j).num);
            }
        }
        return pomGraph;
    }

    @SuppressWarnings("unchecked")
    private static void napuni_lista(ArrayList<GNode<Character>> jazli, GNode<Character> start){
        if(jazli.contains(start)) {
            return;
        }
        jazli.add(start);
        for (GNode<Character> neighbor : start.list) {
            napuni_lista(jazli, neighbor);
        }
    }
}
