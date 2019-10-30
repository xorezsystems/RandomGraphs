package randomgraphs.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;
import randomgraphs.model.Edge;
import randomgraphs.model.Graph;
import randomgraphs.model.Node;

/**
 *
 * @author xorez
 */
public class GraphAlgorithm {

    public static Graph genErdosRenyi(int numNodes, int numEdges, boolean directed, boolean auto) {
        HashMap<Integer, Node> node = new HashMap<>();
        HashMap<Integer, Edge> edge = new HashMap<>();
        int L = 0, flag = 1, newEdges;

        for (int i = 0; i < numNodes; i++) {
            node.put(i, new Node(i));
        }

        int edge1 = (int) (Math.random() * numNodes), edge2 = (int) (Math.random() * numNodes);

        edge.put(0, new Edge(node.get(edge1).getId(), node.get(edge2).getId()));

        while (edge1 == edge2 && flag == 0) {
            edge1 = (int) (Math.random() * numNodes);
            edge2 = (int) (Math.random() * numNodes);
            edge.put(0, new Edge(node.get(edge1).getId(), node.get(edge2).getId()));
        }
        node.get(edge1).connect();
        node.get(edge2).connect();
        if (edge1 != edge2) {
            node.get(edge1).aumCoor(1);
        }
        node.get(edge2).aumCoor(1);
        newEdges = 1;

        while (newEdges < numEdges) {
            edge1 = (int) (Math.random() * numNodes);
            edge2 = (int) (Math.random() * numNodes);

            if (edge1 != edge2 || flag == 1) {
                int flag2 = 1;
                int p = 0;

                while (flag2 == 1 && p < newEdges) {
                    int a = edge.get(p).getId1(), b = edge.get(p).getId2();

                    if ((edge1 == a && edge2 == b) || (edge1 == b && edge2 == a)) {
                        flag2 = 0;
                    }
                    p++;
                }
                if (flag2 == 1) {
                    edge.put(newEdges, new Edge(node.get(edge1).getId(), node.get(edge2).getId()));
                    node.get(edge1).connect();
                    node.get(edge2).connect();
                    if (edge1 != edge2) {
                        node.get(edge1).aumCoor(1);
                    }
                    node.get(edge2).aumCoor(1);
                    newEdges++;
                }
            }
        }
        Graph graphErdosRenyi = new Graph(node, edge);
        return graphErdosRenyi;
    }

    public static Graph genGilbert(int numNodes, float probability, boolean directed, boolean auto) {
        HashMap<Integer, Node> node = new HashMap<>();
        HashMap<Integer, Edge> edge = new HashMap<>();

        int flag = 0, numEdges = 0;

        for (int i = 0; i < numNodes; i++) {
            node.put(i, new Node(i));
        }

        for (int i = 0; i < numNodes; i++) {
            for (int j = i; j < numNodes; j++) {
                if (j != i || flag == 1) {
                    if (Math.random() <= probability) {
                        edge.put(numEdges, new Edge(node.get(i).getId(), node.get(j).getId()));
                        node.get(i).connect();
                        node.get(j).connect();
                        if (i != j) {
                            node.get(i).aumCoor(1);
                        }
                        node.get(j).aumCoor(1);
                        numEdges++;
                    }
                }
            }
        }
        Graph graphGilbert = new Graph(node, edge);
        return graphGilbert;
    }

    public static Graph genGeografico(int numNodes, float connectivity, boolean directed, boolean auto) {
        HashMap<Integer, Node> node = new HashMap<>();
        HashMap<Integer, Edge> edge = new HashMap<>();
        int flag = 1, numEdges = 0;;
        double dimension = Math.sqrt(2);

        for (int i = 0; i < numNodes; i++) {
            node.put(i, new Node(i, Math.random(), Math.random()));
        }
        for (int i = 0; i < numNodes; i++) {
            for (int k = i; k < numNodes; k++) {
                if (k != i || flag == 1) {
                    dimension = Math.sqrt((Math.pow(node.get(k).getX() - node.get(i).getX(), 2) + Math.pow(node.get(k).getY() - node.get(i).getY(), 2)));
                    if (dimension <= connectivity) {
                        edge.put(numEdges, new Edge(node.get(i).getId(), node.get(k).getId()));
                        node.get(i).aumCoor(1);
                        node.get(i).connect();
                        if (k != i) {
                            node.get(k).aumCoor(i);
                            node.get(k).connect();
                        }
                        numEdges++;
                    }
                }
            }
        }
        Graph graphGeografico = new Graph(node, edge);
        return graphGeografico;
    }

    public static Graph genBarabasiAlbert(int numNodes, double coordinate, boolean directed, boolean auto) {
        HashMap<Integer, Node> node = new HashMap<>();
        HashMap<Integer, Edge> edge = new HashMap<>();;
        int flag = 0, numEdges = 0;

        for (int i = 0; i < numNodes; i++) {
            node.put(i, new Node(i));
        }

        for (int i = 0; i < numNodes; i++) {
            int k = 0;
            while (k <= i && node.get(i).getCoor() <= coordinate) {
                if (k != i || flag == 1) {
                    if (Math.random() <= 1 - node.get(k).getCoor() / coordinate) {
                        edge.put(numEdges, new Edge(node.get(i).getId(), node.get(k).getId()));
                        node.get(i).aumCoor(1);
                        node.get(i).connect();

                        if (k != i) {
                            node.get(k).aumCoor(1);
                            node.get(k).connect();
                        }
                        numEdges++;
                    }
                }
                k++;
            }
        }
        Graph graphBarabasiAlbert = new Graph(node, edge);
        return graphBarabasiAlbert;
    }

    public void generateGraphFile(String nombre, Graph g) {
        FileWriter file = null;
        PrintWriter pw = null;
        System.out.println(g.getNodes().size());
        try {
            file = new FileWriter(nombre + ".gv");

            pw = new PrintWriter(file);
            pw.println("graph G{");
            for (int i = 0; i < g.getNodes().size(); i++) {
                pw.println(g.getNodes().get(i).getId() + "  " + "[Label = \"" + g.getNodes().get(i).getId() + " (" + String.format("%.2f", g.getNodes().get(i).getW()) + ")\"]" + ";");
            }
            pw.println();
            for (int i = 0; i < g.getEdges().size(); i++) {
                pw.println(g.getEdges().get(i).getId1() + "--" + g.getEdges().get(i).getId2() + "  " + "[\"" + String.format("%.2f", g.getEdges().get(i).getP()) + "\"]" + ";");
            }
            pw.println("}");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != file) {
                    file.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static Graph BFS(Graph graph, Node initialNode) {
        Graph graphBFS = new Graph();
        HashMap<Integer, HashMap> mainLayer = new HashMap();
        HashMap<Integer, Node> subLayer1 = new HashMap();
        HashMap<Integer, Node> subLayer2 = new HashMap();
        HashMap<Integer, Node> edgeAux = new HashMap();
        HashMap<Integer, Edge> nodeAux = new HashMap();
        int numL = 0, aux = 0, cv = 0;

        graph.getNodes().get(initialNode.getId()).setF(true);
        subLayer1.put(0, graph.getNodes().get(initialNode.getId()));
        mainLayer.put(numL, (HashMap) subLayer1.clone());
        edgeAux.put(cv, graph.getNodes().get(initialNode.getId()));

        for (int x = 0; x <= initialNode.getId(); x++) {
            subLayer2.clear();
            int num = -1;
            for (int i = 0; i < subLayer1.size(); i++) {
                for (int j = 0; j < graph.getEdge().size(); j++) {
                    if (subLayer1.get(i).getId() == graph.getEdges().get(j).getId1() && graph.getNodes().get(graph.getEdges().get(j).getId2()).getF() == false) {
                        graph.getNodes().get(graph.getEdges().get(j).getId2()).setF(true);
                        num++;
                        subLayer2.put(num, graph.getNodes().get(graph.getEdges().get(j).getId2()));
                        nodeAux.put(cv, graph.getEdges().get(j));
                        cv++;
                        edgeAux.put(aux + 1, graph.getNodes().get(graph.getEdges().get(j).getId2()));
                    }
                    if (subLayer1.get(i).getId() == graph.getEdges().get(j).getId2() && graph.getNodes().get(graph.getEdges().get(j).getId1()).getF() == false) {
                        graph.getNodes().get(graph.getEdges().get(j).getId1()).setF(true);
                        num++;
                        subLayer2.put(num, graph.getNodes().get(graph.getEdges().get(j).getId1()));
                        nodeAux.put(cv, graph.getEdges().get(j));
                        cv++;
                        edgeAux.put(cv, graph.getNodes().get(graph.getEdges().get(j).getId1()));
                    }
                }
            }
            numL++;
            subLayer1 = (HashMap) subLayer2.clone();
            mainLayer.put(numL, (HashMap) subLayer2.clone());
        }
        for (int i = 0; i < graph.getNode().size(); i++) {
            graph.getNode().get(i).setVisited(false);
        }

        graphBFS.setG(edgeAux, nodeAux);
        return graphBFS;
    }

    public static Graph DFS_R(Graph graph, Node initialNode) {
        HashMap<Integer, Node> nodeAux = new HashMap();
        HashMap<Integer, Edge> edgeAux = new HashMap();
        Graph dfsR = new Graph(nodeAux, edgeAux);
        Graph graphAux;

        boolean[][] AdjMatrix = new boolean[graph.getNode().size()][graph.getNode().size()];
        for (int i = 0; i < graph.getEdge().size(); i++) {
            AdjMatrix[graph.getEdge().get(i).getId1()][graph.getEdge().get(i).getId2()] = true;
            AdjMatrix[graph.getEdge().get(i).getId2()][graph.getEdge().get(i).getId1()] = true;
        }
        graph.getNode().get(initialNode.getId()).setVisited(true);
        dfsR.getNode().put(0, new Node(graph.getNode().get(initialNode.getId())));
        for (int i = 0; i < graph.getNode().size(); i++) {
            if (AdjMatrix[initialNode.getId()][i] == true && graph.getNode().get(i).getVisited() == false) {
                graphAux = DFS_R(graph, graph.getNode().get(i));
                int aux = dfsR.getNode().size();
                for (int j = 0; j < graphAux.getNode().size(); j++) {
                    dfsR.getNode().put(aux + j, graphAux.getNode().get(j));
                }
                dfsR.getEdge().put(dfsR.getEdge().size(), new Edge(initialNode.getId(), i));
                aux = dfsR.getEdge().size();
                if (graphAux.getEdge().isEmpty() != true) {
                    for (int j = 0; j < graphAux.getEdge().size(); j++) {
                        dfsR.getEdge().put(aux + j, graphAux.getEdge().get(j));
                    }
                }
            }
        }
        return dfsR;
    }

    public static Graph DFS_I(Graph graph, Node initialNode) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Node> nodeAux = new HashMap();
        HashMap<Integer, Edge> edgeAux = new HashMap();
        Graph dfsI = new Graph(nodeAux, edgeAux);

        boolean[][] adjMatrix = new boolean[graph.getNode().size()][graph.getNode().size()];
        boolean isVisited;
        int j, x = 0;
        for (int i = 0; i < graph.getEdge().size(); i++) {
            adjMatrix[graph.getEdge().get(i).getId1()][graph.getEdge().get(i).getId2()] = true;
            adjMatrix[graph.getEdge().get(i).getId2()][graph.getEdge().get(i).getId1()] = true;
        }
        stack.push(graph.getNode().get(0).getId());
        graph.getNode().get(0).setVisited(true);
        dfsI.getNode().put(x, new Node(graph.getNode().get(initialNode.getId())));

        while (stack.isEmpty() == false) {
            j = stack.peek();
            isVisited = false;
            for (int i = 0; i < graph.getNode().size(); i++) {
                if (adjMatrix[j][i] == true && graph.getNode().get(i).getVisited() == false) {
                    graph.getNode().get(i).setVisited(true);
                    dfsI.getEdge().put(x, new Edge(j, i));
                    x++;
                    dfsI.getNode().put(x, new Node(graph.getNode().get(i)));
                    stack.push(i);
                    isVisited = true;
                    i = graph.getNode().size();
                }
                if (i == graph.getNode().size() - 1 && isVisited == false) {
                    stack.pop();
                }
            }
        }
        for (int i = 0; i < graph.getNode().size(); i++) {
            graph.getNode().get(i).setVisited(false);
        }
        return dfsI;
    }

    public static Graph Dijsktra(Graph graph, Node initialNode) {
        for (int i = 0; i < graph.getNodes().size(); i++) {
            graph.getNodes().get(i).setW(Double.POSITIVE_INFINITY);
        }
        double mtzEd[][] = new double[graph.getNodes().size()][graph.getNodes().size()];
        for (int i = 0; i < graph.getNodes().size(); i++) {
            for (int j = 0; j < graph.getNodes().size(); j++) {
                mtzEd[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for (int i = 0; i < graph.getEdges().size(); i++) {
            mtzEd[graph.getEdges().get(i).getId1()][graph.getEdges().get(i).getId2()] = graph.getEdges().get(i).getP();
            mtzEd[graph.getEdges().get(i).getId2()][graph.getEdges().get(i).getId1()] = graph.getEdges().get(i).getP();
        }
        ArrayList<Integer> nodesFound = new ArrayList<>();
        HashMap<Integer, Edge> edges = new HashMap();
        int numA = 0;
        nodesFound.add(graph.getNodes().get(initialNode.getId()).getId());
        graph.getNodes().get(initialNode.getId()).setF(true);
        graph.getNodes().get(initialNode.getId()).setW(0);

        double aux = 0;
        int index1 = 0, index2 = 0;

        while (aux != Double.POSITIVE_INFINITY) {
            aux = Double.POSITIVE_INFINITY;
            for (int i = 0; i < nodesFound.size(); i++) {
                for (int j = 0; j < graph.getNodes().size(); j++) {
                    if (graph.getNodes().get(j).getF() != true && mtzEd[nodesFound.get(i)][j] != Double.POSITIVE_INFINITY) {
                        if ((graph.getNodes().get(nodesFound.get(i)).getW() + mtzEd[nodesFound.get(i)][j]) < aux) {
                            graph.getNodes().get(j).setW(graph.getNodes().get(nodesFound.get(i)).getW() + mtzEd[nodesFound.get(i)][j]);
                            aux = graph.getNodes().get(j).getW();
                            index1 = nodesFound.get(i);
                            index2 = j;
                        }
                    }
                }
            }
            if (aux != Double.POSITIVE_INFINITY) {
                graph.getNodes().get(index2).setF(true);
                nodesFound.add(index2);
                edges.put(numA, new Edge(index1, index2, mtzEd[index1][index2]));
                numA++;
            }
        }
        HashMap<Integer, Node> nodesD = new HashMap();

        for (int i = 0; i < nodesFound.size(); i++) {
            nodesD.put(i, new Node(graph.getNodes().get(nodesFound.get(i))));
        }
        Graph graphDijsktra = new Graph(nodesD, edges);

        return graphDijsktra;
    }
    
    public static Graph Kruskal_D(Graph graph){  
        int numEdges = 0;
        double total = 0;
        PriorityQueue<Edge> edgePriority = new PriorityQueue<>();
        HashMap<Integer, Edge> mtzExp = new HashMap();
        HashMap<Integer, Node> node = new HashMap();
        ArrayList<Integer> listAux = new ArrayList<>();
        
        for (int i = 0; i < graph.getEdges().size(); i++) {
            edgePriority.add(graph.getEdges().get(i));
        }       
        for (int i = 0; i < graph.getEdges().size(); i++) {
            if (listAux.contains(edgePriority.peek().getId1()) && listAux.contains(edgePriority.peek().getId2())) {
                Graph layer1 = new Graph(graph.getNodes(), mtzExp);
                Graph layer2 = DFS_I(layer1, layer1.getNodes().get(edgePriority.peek().getId1()));
                int aux = 0;
                for (int j = 0; j < layer2.getNodes().size(); j++) {
                    if (layer2.getNodes().get(j).getId() == edgePriority.peek().getId2()) {
                        aux = 1;
                    }
                }
                if (aux == 0) {
                    total = total + edgePriority.peek().getP();
                    mtzExp.put(numEdges, edgePriority.poll());
                    numEdges++;
                } else {
                    edgePriority.poll();
                }
            } else {
                if (listAux.contains(edgePriority.peek().getId1()) == false) {
                    listAux.add(edgePriority.peek().getId1());
                }
                if (listAux.contains(edgePriority.peek().getId2()) == false) {
                    listAux.add(edgePriority.peek().getId2());
                }
                total = total + edgePriority.peek().getP();
                mtzExp.put(numEdges, edgePriority.poll());
                numEdges++;
            }
            if (mtzExp.size() == graph.getNodes().size() - 1) {
                i = graph.getEdges().size();
            }
        }
        for (int i = 0; i < listAux.size(); i++) {
            node.put(i, graph.getNodes().get(listAux.get(i)));
        }
        
        Graph graphKruskalD = new Graph(node, mtzExp);
        graphKruskalD.setMinExpansionKruskalD(total);
        return graphKruskalD;
        } 
    
    public static Graph Kruskal_I(Graph graph){  
        int numEdges = 0;
        double total = 0;
        PriorityQueue<Edge> edgePriority = new PriorityQueue<>();
        HashMap<Integer, Edge> mtzExp = new HashMap();
        HashMap<Integer, Node> node = new HashMap();
        ArrayList<Integer> listAux = new ArrayList<>();
        
        for (int i = 0; i < graph.getEdges().size(); i++) {
            edgePriority.add(graph.getEdges().get(i));
        }
       
        for (int i = 0; i < graph.getEdges().size(); i++) {
            if (listAux.contains(edgePriority.peek().getId1()) && listAux.contains(edgePriority.peek().getId2())) {
                Graph layer1 = new Graph(graph.getNodes(), mtzExp);
                Graph layer2 = DFS_I(layer1, layer1.getNodes().get(edgePriority.peek().getId1()));
                int flag = 0;
                for (int j = 0; j < layer2.getNodes().size(); j++) {
                    if (layer2.getNodes().get(j).getId() == edgePriority.peek().getId2()) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    total = total + edgePriority.peek().getP();
                    mtzExp.put(numEdges, edgePriority.poll());
                    numEdges++;
                } else {
                    edgePriority.poll();
                }
            } else {
                if (listAux.contains(edgePriority.peek().getId1()) == false) {
                    listAux.add(edgePriority.peek().getId1());
                }
                if (listAux.contains(edgePriority.peek().getId2()) == false) {
                    listAux.add(edgePriority.peek().getId2());
                }
                total = total + edgePriority.peek().getP();
                mtzExp.put(numEdges, edgePriority.poll());
                numEdges++;
            }
            if (mtzExp.size() == graph.getNodes().size() - 1) {
                i = graph.getEdges().size();
            }
        }
        for (int i = 0; i < listAux.size(); i++) {
            node.put(i, graph.getNodes().get(listAux.get(i)));
        }
       
        Graph graphKruskalI = new Graph(node, mtzExp);
        graphKruskalI.setMinExpansionKruskalI(total);
        return graphKruskalI;
    }
    
    public static Graph Prim(Graph graph){
        Edge EdgMtz[][] = new Edge[graph.getNodes().size()][graph.getNodes().size()];
        ArrayList<Integer> nodesFound = new ArrayList<>();
        HashMap<Integer, Edge> EdgZ = new HashMap();
        int numEdg = 0;
        
        for (int i = 0; i < graph.getEdges().size(); i++) {
            EdgMtz[graph.getEdges().get(i).getId1()][graph.getEdges().get(i).getId2()] = graph.getEdges().get(i);
            EdgMtz[graph.getEdges().get(i).getId2()][graph.getEdges().get(i).getId1()] = graph.getEdges().get(i);
        }
        
        nodesFound.add(graph.getNodes().get(0).getId());
        graph.getNodes().get(0).setF(true);
        PriorityQueue<Edge> pqEdges = new PriorityQueue<>();
        double em = 0;
        for (int z = 0; z < graph.getEdges().size(); z++) {
            for (int i = 0; i < nodesFound.size(); i++) {
                for (int j = 0; j < graph.getNodes().size(); j++) {
                    if (EdgMtz[nodesFound.get(i)][j] != null && graph.getNodes().get(j).getF() == false) {
                        pqEdges.add(EdgMtz[nodesFound.get(i)][j]);
                    }
                }
            }
            if (pqEdges.isEmpty() == false) {
                if (nodesFound.contains(pqEdges.peek().getId1())) {
                    graph.getNodes().get(pqEdges.peek().getId2()).setF(true);
                    nodesFound.add(pqEdges.peek().getId2());
                    EdgZ.put(numEdg, EdgMtz[pqEdges.peek().getId1()][pqEdges.peek().getId2()]);
                    numEdg++;
                    em = em + pqEdges.peek().getP();
                    pqEdges.clear();
                } else {
                    graph.getNodes().get(pqEdges.peek().getId1()).setF(true);
                    nodesFound.add(pqEdges.peek().getId1());
                    EdgZ.put(numEdg, EdgMtz[pqEdges.peek().getId1()][pqEdges.peek().getId2()]);
                    numEdg++;
                    em = em + pqEdges.peek().getP();
                    pqEdges.clear();
                }
            }
            if (EdgZ.size() == graph.getNodes().size() - 1) {
                break;
            }
        }
        
        Graph graphPrim = new Graph(graph.getNodes(), EdgZ);
        graphPrim.setMinExpansionPrim(em);
        return graphPrim;
    }

    public void generateAlgSearchFile(String algName, Graph graph) {
        File f = new File(algName + ".gv");
        String struct = "graph G {\n";

        Iterator<HashMap.Entry<Integer, Node>> iterator1 = graph.getNodes().entrySet().iterator();
        while (iterator1.hasNext()) {
            struct += iterator1.next().getValue().id + ";\n";
        }
        Iterator<HashMap.Entry<Integer, Edge>> iterator2 = graph.getEdges().entrySet().iterator();
        Iterator<HashMap.Entry<Integer, Edge>> iterator3 = graph.getEdges().entrySet().iterator();
        while (iterator2.hasNext()) {
            struct += iterator2.next().getValue().getId1() + "--" + iterator3.next().getValue().getId2() + ";\n";
        }
        PrintWriter pw;
        try {
            pw = new PrintWriter(f);
            pw.write(struct);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
