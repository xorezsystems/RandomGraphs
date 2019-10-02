package randomgraphs.view;

import javax.swing.JOptionPane;
import randomgraphs.controller.GraphAlgorithm;
import static randomgraphs.controller.GraphAlgorithm.*;
import randomgraphs.model.Graph;

/**
 *
 * @author xorez
 */
public class RandomGraphs {

    public static void main(String[] args) {
        GraphAlgorithm graph;
        int op;
        int numNodes;
        int numEdges;
        int initialNode;
        float probability;
        float connectivity;
        double coordenade;
        String[] nameAlgorithms = {"Erdös y Rényi", "Gilbert", "Geográfico Simple", "BarBabási-Albert"};

        String output = (String) JOptionPane.showInputDialog(null, "Seleccione un algoritmo de generación de grafos:",
                "Grafos",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                nameAlgorithms,
                nameAlgorithms[0]);
        switch (output) {
            case "Erdös y Rényi":
                numNodes = Integer.parseInt(JOptionPane.showInputDialog(null, "Número de nodos: "));
                numEdges = Integer.parseInt(JOptionPane.showInputDialog(null, "Número de aristas: "));
                initialNode = Integer.parseInt(JOptionPane.showInputDialog(null, "Nodo inicial: "));
                graph = new GraphAlgorithm();
                Graph graphErdos = genErdosRenyi(numNodes, numEdges, false, false);
                graph.generateGraphFile("Erdos_N" + numNodes + "_E" + numEdges, graphErdos);
                graph.generateAlgSearchFile("ErdosBFS_N" + numNodes + "_E" + numEdges, BFS(graphErdos, graphErdos.getNodes().get(initialNode)));
                graph.generateAlgSearchFile("ErdosDFS_R_N" + numNodes + "_E" + numEdges, DFS_R(graphErdos, graphErdos.getNodes().get(initialNode)));
          
                graph.generateAlgSearchFile("ErdosDFS_I_N" + numNodes + "_E" + numEdges, DFS_I(graphErdos, graphErdos.getNodes().get(initialNode)));

                break;
            case "Gilbert":
                numNodes = Integer.parseInt(JOptionPane.showInputDialog(null, "Número de nodos: "));
                probability = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese la probabilidad: "));
                initialNode = Integer.parseInt(JOptionPane.showInputDialog(null, "Nodo inicial: "));
                graph = new GraphAlgorithm();
                Graph graphGilbert = genGilbert(numNodes, probability, false, false);
                graph.generateGraphFile("Gilbert_N" + numNodes + "_P", graphGilbert);
                graph.generateAlgSearchFile("GilbertBFS_N" + numNodes + "_P", BFS(graphGilbert, graphGilbert.getNodes().get(initialNode)));
                graph.generateAlgSearchFile("GilbertDFS_R_N" + numNodes + "_P", DFS_R(graphGilbert, graphGilbert.getNodes().get(initialNode)));
                graph.generateAlgSearchFile("GilbertDFS_I_N" + numNodes + "_P", DFS_I(graphGilbert, graphGilbert.getNodes().get(initialNode)));

                break;
            case "Geográfico Simple":
                numNodes = Integer.parseInt(JOptionPane.showInputDialog(null, "Número de nodos: "));
                connectivity = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese la probabilidad: "));
                initialNode = Integer.parseInt(JOptionPane.showInputDialog(null, "Nodo inicial: "));
                graph = new GraphAlgorithm();
                Graph graphGeograf = genGeografico(numNodes, connectivity, false, false);
                graph.generateGraphFile("Geografico_N" + numNodes + "_P", graphGeograf);
                graph.generateAlgSearchFile("GeograficoBFS_N" + numNodes + "_P", BFS(graphGeograf, graphGeograf.getNodes().get(initialNode)));
                graph.generateAlgSearchFile("GeograficoDFS_R_N" + numNodes + "_P", DFS_R(graphGeograf, graphGeograf.getNodes().get(initialNode)));
                graph.generateAlgSearchFile("GeograficoDFS_I_N" + numNodes + "_P", DFS_I(graphGeograf, graphGeograf.getNodes().get(initialNode)));

                break;
            case "BarBabási-Albert":
                numNodes = Integer.parseInt(JOptionPane.showInputDialog(null, "Número de nodos: "));
                coordenade = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese grado de conexión: "));
                initialNode = Integer.parseInt(JOptionPane.showInputDialog(null, "Nodo inicial: "));
                graph = new GraphAlgorithm();
                Graph graphBarabasiAlbert = genBarabasiAlbert(numNodes, coordenade, false, false);
                graph.generateGraphFile("Barabasi_N" + numNodes + "_G" + coordenade, graphBarabasiAlbert);
                graph.generateAlgSearchFile("BarabasiBFS_N" + numNodes + "_G" + coordenade, BFS(graphBarabasiAlbert, graphBarabasiAlbert.getNodes().get(initialNode)));
                graph.generateAlgSearchFile("BarabasiDFS_R_N" + numNodes + "_G" + coordenade, DFS_R(graphBarabasiAlbert, graphBarabasiAlbert.getNodes().get(initialNode)));
                graph.generateAlgSearchFile("BarabasiDFS_I_N" + numNodes + "_G" + coordenade, DFS_I(graphBarabasiAlbert, graphBarabasiAlbert.getNodes().get(initialNode)));

                break;
            default:
                break;
        }
    }
}
