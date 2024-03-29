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
        Graph gKruskalD, gKruskalI, gPrim;
               
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
                graphErdos.randomEdgeValues(0, 50);
                
                gKruskalD = Kruskal_D(graphErdos);
                gKruskalI = Kruskal_I(graphErdos);
                gPrim = Prim(graphErdos);
                
                graph.generateGraphFile("Erdos_N" + numNodes + "_E" + numEdges+"(KruskalDir)", gKruskalD);
                graph.generateGraphFile("Erdos_N" + numNodes + "_E" + numEdges+"(KruskalInv)", gKruskalI);
                graph.generateGraphFile("Erdos_N" + numNodes + "_E" + numEdges+"(KruskalPrim)", gPrim);
                
                System.out.println("Peso Total del Árbol de Expanción Mínima Kruskal Dir: "+gKruskalD.getMinExpansionKruskalD());
                System.out.println("Peso Total del Árbol de Expanción Mínima Kruskal Inv: "+gKruskalI.getMinExpansionKruskalI());
                System.out.println("Peso Total del Árbol de Expanción Mínima PRIM: "+gPrim.getMinExpansionPrim());
                
                //graph.generateGraphFile("Erdos_N" + numNodes + "_E" + numEdges+"(Dijsktra)", Dijsktra(graphErdos, graphErdos.getNodes().get(initialNode)));
                //graph.generateAlgSearchFile("ErdosBFS_N" + numNodes + "_E" + numEdges, BFS(graphErdos, graphErdos.getNodes().get(initialNode)));
                //graph.generateAlgSearchFile("ErdosDFS_R_N" + numNodes + "_E" + numEdges, DFS_R(graphErdos, graphErdos.getNodes().get(initialNode)));
                //graph.generateAlgSearchFile("ErdosDFS_I_N" + numNodes + "_E" + numEdges, DFS_I(graphErdos, graphErdos.getNodes().get(initialNode)));
                
                
                break;
            case "Gilbert":
                numNodes = Integer.parseInt(JOptionPane.showInputDialog(null, "Número de nodos: "));
                probability = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese la probabilidad: "));
                initialNode = Integer.parseInt(JOptionPane.showInputDialog(null, "Nodo inicial: "));
                graph = new GraphAlgorithm();
                Graph graphGilbert = genGilbert(numNodes, probability, false, false);
                graph.generateGraphFile("Gilbert_N" + numNodes + "_P", graphGilbert);
                graphGilbert.randomEdgeValues(0, 50);
                //graph.generateGraphFile("Gilbert_N" + numNodes + "_P"+"(Dijsktra)", Dijsktra(graphGilbert, graphGilbert.getNodes().get(initialNode)));
                
                gKruskalD = Kruskal_D(graphGilbert);
                gKruskalI = Kruskal_I(graphGilbert);
                gPrim = Prim(graphGilbert);
                
                graph.generateGraphFile("GilbertBFS_N" + numNodes + "_P"+"(KruskalDir)", gKruskalD);
                graph.generateGraphFile("GilbertBFS_N" + numNodes + "_P"+"(KruskalInv)", gKruskalI);
                graph.generateGraphFile("GilbertBFS_N" + numNodes + "_P"+"(KruskalPrim)", gPrim);
                
                System.out.println("Peso Total del Árbol de Expanción Mínima Kruskal Dir: "+gKruskalD.getMinExpansionKruskalD());
                System.out.println("Peso Total del Árbol de Expanción Mínima Kruskal Inv: "+gKruskalI.getMinExpansionKruskalI());
                System.out.println("Peso Total del Árbol de Expanción Mínima PRIM: "+gPrim.getMinExpansionPrim());

                //graph.generateAlgSearchFile("GilbertBFS_N" + numNodes + "_P", BFS(graphGilbert, graphGilbert.getNodes().get(initialNode)));
                //graph.generateAlgSearchFile("GilbertDFS_R_N" + numNodes + "_P", DFS_R(graphGilbert, graphGilbert.getNodes().get(initialNode)));
                //graph.generateAlgSearchFile("GilbertDFS_I_N" + numNodes + "_P", DFS_I(graphGilbert, graphGilbert.getNodes().get(initialNode)));

                break;
            case "Geográfico Simple":
                numNodes = Integer.parseInt(JOptionPane.showInputDialog(null, "Número de nodos: "));
                connectivity = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese la probabilidad: "));
                initialNode = Integer.parseInt(JOptionPane.showInputDialog(null, "Nodo inicial: "));
                graph = new GraphAlgorithm();
                Graph graphGeograf = genGeografico(numNodes, connectivity, false, false);
                graph.generateGraphFile("Geografico_N" + numNodes + "_P", graphGeograf);
                graphGeograf.randomEdgeValues(0, 50);
                //graph.generateGraphFile("Geografico_N" + numNodes + "_P"+"(Dijsktra)", Dijsktra(graphGeograf, graphGeograf.getNodes().get(initialNode)));
                
                gKruskalD = Kruskal_D(graphGeograf);
                gKruskalI = Kruskal_I(graphGeograf);
                gPrim = Prim(graphGeograf);

                graph.generateGraphFile("GeograficoBFS_N" + numNodes + "_P"+"(KruskalDir)", gKruskalD);
                graph.generateGraphFile("GeograficoBFS_N" + numNodes + "_P"+"(KruskalInv)", gKruskalI);
                graph.generateGraphFile("GeograficoBFS_N" + numNodes + "_P"+"(KruskalPrim)", gPrim);
                
                System.out.println("Peso Total del Árbol de Expanción Mínima Kruskal Dir: "+gKruskalD.getMinExpansionKruskalD());
                System.out.println("Peso Total del Árbol de Expanción Mínima Kruskal Inv: "+gKruskalI.getMinExpansionKruskalI());
                System.out.println("Peso Total del Árbol de Expanción Mínima PRIM: "+gPrim.getMinExpansionPrim());
                
                //graph.generateAlgSearchFile("GeograficoBFS_N" + numNodes + "_P", BFS(graphGeograf, graphGeograf.getNodes().get(initialNode)));
                //graph.generateAlgSearchFile("GeograficoDFS_R_N" + numNodes + "_P", DFS_R(graphGeograf, graphGeograf.getNodes().get(initialNode)));
                //graph.generateAlgSearchFile("GeograficoDFS_I_N" + numNodes + "_P", DFS_I(graphGeograf, graphGeograf.getNodes().get(initialNode)));

                break;
            case "BarBabási-Albert":
                numNodes = Integer.parseInt(JOptionPane.showInputDialog(null, "Número de nodos: "));
                coordenade = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese grado de conexión: "));
                initialNode = Integer.parseInt(JOptionPane.showInputDialog(null, "Nodo inicial: "));
                graph = new GraphAlgorithm();
                Graph graphBarabasiAlbert = genBarabasiAlbert(numNodes, coordenade, false, false);
                graph.generateGraphFile("Barabasi_N" + numNodes + "_G" + coordenade, graphBarabasiAlbert);
                graphBarabasiAlbert.randomEdgeValues(0, 50);
                //graph.generateGraphFile("Barabasi_N" + numNodes + "_G"+coordenade+"(Dijsktra)", Dijsktra(graphBarabasiAlbert, graphBarabasiAlbert.getNodes().get(initialNode)));
                
                gKruskalD = Kruskal_D(graphBarabasiAlbert);
                gKruskalI = Kruskal_I(graphBarabasiAlbert);
                gPrim = Prim(graphBarabasiAlbert);

                graph.generateGraphFile("BarabasiBFS_N" + numNodes + "_G" + coordenade+"(KruskalDir)", gKruskalD);
                graph.generateGraphFile("BarabasiBFS_N" + numNodes + "_G" + coordenade+"(KruskalInv)", gKruskalI);
                graph.generateGraphFile("BarabasiBFS_N" + numNodes + "_G" + coordenade+"(KruskalPrim)", gPrim);
                
                System.out.println("Peso Total del Árbol de Expanción Mínima Kruskal Dir: "+gKruskalD.getMinExpansionKruskalD());
                System.out.println("Peso Total del Árbol de Expanción Mínima Kruskal Inv: "+gKruskalI.getMinExpansionKruskalI());
                System.out.println("Peso Total del Árbol de Expanción Mínima PRIM: "+gPrim.getMinExpansionPrim());

                //graph.generateAlgSearchFile("BarabasiBFS_N" + numNodes + "_G" + coordenade, BFS(graphBarabasiAlbert, graphBarabasiAlbert.getNodes().get(initialNode)));
                //graph.generateAlgSearchFile("BarabasiDFS_R_N" + numNodes + "_G" + coordenade, DFS_R(graphBarabasiAlbert, graphBarabasiAlbert.getNodes().get(initialNode)));
                //graph.generateAlgSearchFile("BarabasiDFS_I_N" + numNodes + "_G" + coordenade, DFS_I(graphBarabasiAlbert, graphBarabasiAlbert.getNodes().get(initialNode)));

                break;
            default:
                break;
        }
    }
}
