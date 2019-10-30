package randomgraphs.model;

import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author xorez
 */
public class Graph {

    private HashMap<Integer, Node> node;
    private HashMap<Integer, Edge> edge;
    private HashMap<Integer, Stack> stack;
    int numEdges;
    int numNodes;
    double minExpansionPrim = 0.0;
    double minExpansionKruskalD = 0.0;
    double minExpansionKruskalI = 0.0;

    public HashMap<Integer, Stack> getStack() {
        return stack;
    }

    public void setStack(HashMap<Integer, Stack> stack) {
        this.stack = stack;
    }

    public HashMap<Integer, Node> getNode() {
        return node;
    }

    public void setNode(HashMap<Integer, Node> node) {
        this.node = node;
    }

    public HashMap<Integer, Edge> getEdge() {
        return edge;
    }

    public void setEdge(HashMap<Integer, Edge> edge) {
        this.edge = edge;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public void setNumNodos(int numNodes) {
        this.numNodes = numNodes;
    }

    public void setNodes(HashMap<Integer, Node> node) {
        this.node = node;
    }

    public void setEdges(HashMap<Integer, Edge> edge) {
        this.edge = edge;
    }

    public HashMap<Integer, Edge> getEdges() {
        return this.edge;
    }

    public HashMap<Integer, Node> getNodes() {
        return this.node;
    }

    public void setG(HashMap<Integer, Node> node, HashMap<Integer, Edge> edge) {
        this.node = (HashMap) node;
        this.edge = (HashMap) edge;
    }

    public Graph(HashMap<Integer, Node> node, HashMap<Integer, Edge> edge) {
        this.node = new HashMap();
        for (int i = 0; i < node.size(); i++) {
            this.node.put(i, new Node(node.get(i)));
        }
        this.edge = new HashMap();
        for (int i = 0; i < edge.size(); i++) {
            this.edge.put(i, new Edge(edge.get(i)));
        }
    }

    public Graph() {
        this.node = new HashMap();
        this.edge = new HashMap();
    }

    public Graph(Graph graph) {
        this.node = new HashMap();
        for (int i = 0; i < graph.getNode().size(); i++) {
            this.node.put(i, new Node(graph.getNode().get(i)));
        }
        this.edge = new HashMap();
        for (int i = 0; i < graph.getEdge().size(); i++) {
            this.edge.put(i, new Edge(graph.getEdge().get(i)));
        }
    }

    public Graph values(double min, double max) {

        int numEdges = this.edge.size();
        for (int i = 0; i < numEdges; i++) {
            this.edge.get(i).setP(Math.random() * (max - min) + min);
        }
        Graph graph = new Graph(this.node, this.edge);
        return graph;
    }

    public static void modifier(Graph graph) {
        for (int i = 0; i < graph.getNodes().size(); i++) {
            graph.getNodes().get(i).setF(true);
        }
    }
    
    public void randomEdgeValues(float min, float max){
         for (int i = 0; i < this.edge.size(); i++) {
            this.edge.get(i).setP(Math.random()*(max-min)+min);
        }
       // GrafoCreacion G = new GrafoCreacion(this.nodo,this.arista);
    }

    public double getMinExpansionPrim() {
        return minExpansionPrim;
    }

    public void setMinExpansionPrim(double minExpansionPrim) {
        this.minExpansionPrim = minExpansionPrim;
    }

    public double getMinExpansionKruskalD() {
        return minExpansionKruskalD;
    }

    public void setMinExpansionKruskalD(double minExpansionKruskalD) {
        this.minExpansionKruskalD = minExpansionKruskalD;
    }

    public double getMinExpansionKruskalI() {
        return minExpansionKruskalI;
    }

    public void setMinExpansionKruskalI(double minExpansionKruskalI) {
        this.minExpansionKruskalI = minExpansionKruskalI;
    }
    
    
    
    
    
}
