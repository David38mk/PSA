import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class Edge {
    public int from, to;
    public float weight;
    
    public Edge(int from, int to, float weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class GNeighbour<E extends Comparable<E>> {
    public GNode<E> node;
    public float weight;
    
    public GNeighbour(GNode<E> node) {
        this.node = node;
        this.weight = 0;
    }
    
    public GNeighbour(GNode<E> node, float weight) {
        this.node = node;
        this.weight = weight;
    }
}

class GNode<E extends Comparable<E>> {
    public int num;
    public E info;
    public LinkedList<GNeighbour<E>> list;
    
    public GNode(int num, E info) {
        this.num = num;
        this.info = info;
        list = new LinkedList<GNeighbour<E>>();
    }
    
    public void addNeighbour(GNode<E> node, float weight) {
        list.add(new GNeighbour(node, weight));
    }
    
    public void deleteNeighbour(GNode<E> node) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).node.info.equals(node.info)) {
                list.remove(i);
            }
        }
    }
    
    public boolean hasNeighbour(GNode<E> node) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).node.info.equals(node.info)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void updateNeighbourWeight(GNode<E> node, float weight) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).node.info.equals(node.info)) {
                list.get(i).weight = weight;
            }
        }
    }
}

class Graph<E extends Comparable<E>> {
    int n;
    GNode<E> graph[];
    
    public Graph(int n) {
        this.n = n;
        
        graph = new GNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new GNode(i, null);
        }
    }
    
    public Graph(int n, E[] infos) {
        this.n = n;
        
        graph = new GNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new GNode(i, infos[i]);
        }
    }
    
    boolean hasNeighbour(int x, int y) {
        return graph[x].hasNeighbour(graph[y]);
    }
    
    void addEdge(int x, int y, float w) {
        if (graph[x].hasNeighbour(graph[y])) {
            graph[x].updateNeighbourWeight(graph[y], w);
        } else {
            graph[x].addNeighbour(graph[y], w);
        }
    }
    
    void deleteEdge(int x, int y) {
        graph[x].deleteNeighbour(graph[y]);
    }
    
    public Edge[] kruskal() {
        Edge[] fin = new Edge[n - 1];
        
        Edge[] edges = getAllEdges();
        sort(edges);
        
        int[] roditel = new int[n];
        for (int i = 0; i < n; i++) {
            roditel[i] = i;
        }
        
        int dodadeni = 0, i = 0;
        
        while (dodadeni < n - 1) {
            Edge e = edges[i++];
            
            if (roditel[e.from] != roditel[e.to]) {
                promeni(e.from, e.to, roditel);
                fin[dodadeni++] = e;
            }
        }
        
        return fin;
    }
    
    public Edge[] getAllEdges() {
        int vk = 0, ind = 0;
        
        for (int i = 0; i < n; i++) {
            vk += graph[i].list.size();
        }
        
        Edge[] edges = new Edge[vk];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[i].list.size(); j++) {
                edges[ind++] = new Edge(graph[i].num, 
                        graph[i].list.get(j).node.num,
                        graph[i].list.get(j).weight);
            }
        }
        
        return edges;
    }
    
    public void sort(Edge[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                if (edges[i].weight > edges[j].weight) {
                    Edge pom = edges[i];
                    edges[i] = edges[j];
                    edges[j] = pom;
                }
            }
        }
    }
    
    public void promeni(int from, int to, int[] roditel) {
        int pomal, pogolem;
        
        if (from < to) {
            pomal = roditel[from];
            pogolem = roditel[to];
        } else {
            pomal = roditel[to];
            pogolem = roditel[from];
        }
        
        for (int i = 0; i < roditel.length; i++) {
            if (roditel[i] == pogolem) {
                roditel[i] = pomal;
            }
        }
    }
    
    public Edge[] prim(int start) {
        Edge[] fin = new Edge[n - 1];
        int dodadeni = 0;
        
        boolean[] voDrvo = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            voDrvo[i] = false;
        }
        
        voDrvo[start] = true;
        
        while (dodadeni < n - 1) {
            Edge e = findMin(voDrvo);
            voDrvo[e.from] = voDrvo[e.to] = true;
            fin[dodadeni++] = e;
        }
        
        return fin;
    }
    
    public Edge findMin(boolean[] voDrvo) {
        float min_t = Float.MAX_VALUE;
        int from_n = -1, to_n = -1;
        
        for (int i = 0; i < n; i++) {
            if (voDrvo[i] == true) {
                for (int j = 0; j < graph[i].list.size(); j++) {
                    if (voDrvo[graph[i].list.get(j).node.num] == false &&
                            min_t > graph[i].list.get(j).weight) {
                        min_t = graph[i].list.get(j).weight;
                        from_n = graph[i].num;
                        to_n = graph[i].list.get(j).node.num;
                    }
                }
            }
        }
        
        return new Edge(from_n, to_n, min_t);
    }
    
    public float[] dijkstra(int start) {
        float[] dist = new float[n];
        boolean[] fin = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            fin[i] = false;
            dist[i] = -1;
        }
        
        fin[start] = true;
        dist[start] = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[start].list.size(); j++) {
                if (fin[graph[start].list.get(j).node.num] == false) {
                    if (dist[graph[start].list.get(j).node.num] == -1) {
                        dist[graph[start].list.get(j).node.num] = dist[start] + 
                                graph[start].list.get(j).weight;
                    } else if (dist[graph[start].list.get(j).node.num] > 
                            dist[start] + graph[start].list.get(j).weight) {
                        dist[graph[start].list.get(j).node.num] = 
                                dist[start] + graph[start].list.get(j).weight;
                    }
                }
            }
            
            float min_cena = Float.MAX_VALUE;
            for (int k = 0; k < n; k++) {
                if (dist[k] != -1 && fin[k] == false) {
                    if (min_cena > dist[k]) {
                        min_cena = dist[k];
                        start = k;
                    }
                }
            }
            
            fin[start] = true;
        }
        
        
        return dist;
    }
    
    public float[] bellman_ford(int start) {
        float[] dist = new float[n];
        Arrays.fill(dist, Float.MAX_VALUE);
        
        dist[start] = 0;
        
        Edge[] edges = getAllEdges();
        
        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                int node_out = edge.from;
                int node_in = edge.to;
                float link_weight = edge.weight;
                
                if (dist[node_out] != Float.MAX_VALUE && 
                        dist[node_in] > dist[node_out] + link_weight) {
                    
                    if (i == n - 1) {
                        return new float[]{-1};
                    }
                    
                    dist[node_in] = dist[node_out] + link_weight;
                }
            }
        }
        
        return dist;
    }


    void dfs(int visited[],ArrayList<Integer> lista, int start, int end, float tezina, float tekovnaTezina) {
        visited[start] = 1;
        lista.add(start);       
        GNode<E> pom = graph[start];
        GNode<E> next;
        
        for (int i = 0; i < pom.list.size(); i++) {
            next = pom.list.get(i).node;
            tekovnaTezina+=pom.list.get(i).weight;
            if (visited[next.num] == 0) {
                if(next.num == end && (Math.abs(tekovnaTezina - tezina) < 1e-6) ){
                    lista.add(next.num);
                    for(int j=0;j<lista.size();j++){
                        System.out.print(lista.get(j)+", ");
                    }
                }
                
                dfs(visited,lista, next.num, end, tezina, tekovnaTezina);
            }
            tekovnaTezina-=pom.list.get(i).weight;
        }
        lista.remove(lista.size()-1);
        visited[start]=0;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Integer [] infos = {1,2,3,4,5,6,7};
        Graph<Integer> graph = new Graph<Integer>(7, infos);

        graph.addEdge(0, 2,3);
        graph.addEdge(0, 3,7);
        graph.addEdge(2, 1,4);
        graph.addEdge(3, 4,8);
        graph.addEdge(3, 6,9);
        graph.addEdge(6, 5,2);
        graph.addEdge(1, 0,5);
        graph.addEdge(0, 6, 4);
        int [] visited = new int[graph.n];
        ArrayList<Integer> lista = new ArrayList<Integer>();
        for(int i=0;i<graph.n;i++){
            visited[i] = 0;
        }
        float [] pateki = graph.dijkstra(3);
        for(int i=0;i<pateki.length;i++){
            System.out.println(pateki[i]);
        }

        graph.dfs(visited, lista, 3,0, pateki[0], 0);
    }
}
