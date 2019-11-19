package randomgraphs.controller;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
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
}
