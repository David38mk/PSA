
import java.util.Arrays;
import java.util.LinkedList;



class Edge{
    public int from, to;
    public float weight;

    public Edge(int from, int to, float weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class GNeighbour<E>{
    public GNode<E> node;
    public float weight;

    public  GNeighbour(GNode<E> node){
        this.node = node;
    }

    public GNeighbour(GNode<E> node, float weight){
        this.node = node;
        this.weight = weight;
    }
}

class GNode<E> {
    public int num;
    public E info;
    public LinkedList<GNeighbour<E>> list;

    public GNode(int num, E info){
        this.num = num;
        this.info = info;
        list = new LinkedList<GNeighbour<E>>();
    }

    public void addNeighbour(GNode<E> node , float weight){
        list.add(new GNeighbour(node,weight));
    }

    public void deleteNeigbour(GNode<E> node){
        GNeighbour pom = new GNeighbour(node);
        if(list.contains(pom)){
            list.remove(pom);
        }
    }

    public boolean hasNeighbour(GNode<E> node){
        GNeighbour pom = new GNeighbour(node);
        return list.contains(pom);
    }

    public void updateNeighbourWeight(GNode<E> node, float weight){
        for(int i=0;i<list.size();i++){
            if(list.get(i).node.equals(node)){
                list.get(i).weight = weight;
                break;
            }
        }
    }
 
}

class Graph<E> {
    int n;
    GNode<E> graph[];

    public Graph(int n){
        this.n=n;
        graph = new GNode[n];
        for(int i=0;i<n;i++){
            graph[i] = new GNode(i, null);
        }
    }

    public Graph(int n, E[] infos){
        this.n=n;
        graph = new GNode[n];
        for(int i=0;i<n;i++){
            graph[i] = new GNode(i, infos[i]);
        }
    }

    boolean hasNeighbour(int x, int y){
        return graph[x].hasNeighbour(graph[y]);
    }

    void addEdge(int x, int y, float w){
        if(graph[x].hasNeighbour(graph[y])){
            graph[x].updateNeighbourWeight(graph[y], w);
        }else{
            graph[x].addNeighbour(graph[y], w);
        }
    }

    void deleteEdge(int x, int y){
        graph[x].deleteNeigbour(graph[y]);
    }

    //Kruskal
    public Edge[] kruskal(){
        Edge[] fin = new Edge[n-1];

        Edge[] edges = getAllEdges();
        sort(edges);

        int[] roditel = new int[n];
        for(int i=0;i<n;i++){
            roditel[i] = i;
        }

        int dodadeni = 0, i = 0;

        while(dodadeni<n-1){
            Edge e = edges[i++];

            if(roditel[e.from]!=roditel[e.to]){
                promeni(e.from, e.to, roditel);
                fin[dodadeni++] = e;
            }

        }

        return fin;
    }

    public Edge[] getAllEdges(){
        int vk = 0, ind=0;

        for(int i=0;i<n;i++){
            vk+= graph[i].list.size();
        }

        Edge[] edges = new Edge[vk];

        for(int i=0;i<n;i++){
            for(int j=0;j<graph[i].list.size();j++){
                edges[ind++] = new Edge(graph[i].num, graph[i].list.get(j).node.num, graph[i].list.get(j).weight);
            }
        }

        return edges;
    }

    public void sort(Edge[] edges){
        for(int i=0;i< edges.length-1;i++){
            for(int j = i+1; j<edges.length;j++){
                if(edges[i].weight>edges[j].weight){
                    Edge pom = edges[i];
                    edges[i] = edges[j];
                    edges[j] = pom;
                }
            }
        }
    }

    public void promeni(int from, int to, int[] roditel){
        int pomal, pogolem;

        if(from < to){
            pomal = roditel[from];
            pogolem = roditel[to];
        }else{
            pomal = roditel[to];
            pogolem = roditel[from];
        }

        for(int i=0; i< roditel.length; i++){
            if(roditel[i] == pogolem){
                roditel[i] = pomal;
            }
        }
    }

    //Prim
    public Edge[] prim(int start) {
        Edge[] fin = new Edge[n-1];
        int dodadeni = 0;

        boolean[] voDrvo = new boolean[n];

        for(int i=0;i<n;i++){
            voDrvo[i] = false;
        }

        voDrvo[start] = true;

        while (dodadeni < n-1){
            Edge e = findMin(voDrvo);
            voDrvo[e.from] = voDrvo[e.to] = true;
            fin[dodadeni++] = e;

        }


        return fin;
    } 

    public Edge findMin(boolean[] voDrvo){
        float min_t = Float.MIN_VALUE;
        int from_n=-1, to_n=-1;

        for(int i=0;i<n;i++){
            if(voDrvo[i] == true){
                for(int j=0;j<graph[i].list.size();j++){
                    if(voDrvo[graph[i].list.get(j).node.num] == false && min_t >graph[i].list.get(j).weight){
                        min_t = graph[i].list.get(j).weight;
                        from_n = graph[i].num;
                        to_n = graph[i].list.get(j).node.num;
                    }
                }
            }
        }

        return new Edge(from_n,to_n,min_t);
    }

    //Dijkstra
    public float [] dijkstra(int start){
        float[] dist = new float[n];
        boolean [] fin = new boolean[n];
        for(int i=0;i<n;i++){
            fin[i] = false;
            dist[i] = -1;
        }

        fin[start] = true;
        dist[start] = 0;

        for(int i=0;i<n;i++){
            for(int j=0;j< graph[start].list.size();j++){
                if(fin[graph[start].list.get(j).node.num] == false){
                    if (dist[graph[start].list.get(j).node.num]==-1) {
                        dist[graph[start].list.get(j).node.num] = dist[start] + graph[start].list.get(j).weight; 
                    }else if(dist[graph[start].list.get(j).node.num] > dist[start] + graph[start].list.get(j).weight){
                        dist[graph[start].list.get(j).node.num]=dist[start] + graph[start].list.get(j).weight;
                    }
                }
            }

            float min_cena = Float.MAX_VALUE;
            for(int k = 0;k<n;k++){
                if(dist[k] != -1 && fin[k] == false){
                    if(min_cena > dist[k]){
                        min_cena = dist[k];
                        start=k;
                    }
                }
            }

            fin[start] = true;
        }
        return dist;
    }

    //Bellman-Ford
    public float[] bellman_ford(int start){
        float[] dist = new float[n];
        Arrays.fill(dist, Float.MAX_VALUE);

        dist[start] = 0;

        Edge[] edges = getAllEdges();

        for(int i=0;i<n;i++){
            for(Edge edge : edges){
                int node_out = edge.from;
                int node_in = edge.to;
                float link_weigth = edge.weight;
                
                if(dist[node_out]!= Float.MAX_VALUE && dist[node_in]>dist[node_out]+link_weigth){
                    if(i == n-1){
                        return new float[]{-1};
                    }
                    
                    dist[node_in] = dist[node_out] + link_weigth;
                }
            }
        }

        return dist;
    }
}



public class App {
    public static void main(String[] args) throws Exception {
        //Zadaca so skalila
        /*String[] names1 = {"NS","N0","N1","N2","NE"};

        Graph<String> graph1 = new Graph(names1.length,names1);

        graph1.addEdge(0, 1, 10);
        graph1.addEdge(0, 2, 15);
        graph1.addEdge(1, 2, 15);
        graph1.addEdge(1, 3, 20);
        graph1.addEdge(2, 3, 20);
        graph1.addEdge(2, 4, 0);
        graph1.addEdge(3, 4, 0);

        float[] distances1 = graph1.dijkstra(0);
        System.out.println(distances1[names1.length-1]);*/

        




        
    }
}