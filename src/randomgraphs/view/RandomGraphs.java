
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
       
        float  probability;
        float connectivity;
        double coordenade;
        String[] nameAlgorithms = {"Erdös y Rényi","Gilbert","Geográfico Simple","BarBabási-Albert"};
        
        String output = (String) JOptionPane.showInputDialog(null, "Seleccione un algoritmo de generación de grafos:",
                "Grafos", 
                JOptionPane.INFORMATION_MESSAGE,
                null,
                nameAlgorithms,
                nameAlgorithms[0]);
        switch (output){
            case "Erdös y Rényi":
                numNodes=Integer.parseInt(JOptionPane.showInputDialog(null, "Número de nodos: "));
                numEdges=Integer.parseInt(JOptionPane.showInputDialog(null, "Número de aristas: "));
                graph= new GraphAlgorithm();
                graph.generateGraphFile("Erdos_N"+numNodes+"_E"+numEdges, genErdosRenyi(numNodes, numEdges, false, false));
                
                
                
                break;
            case "Gilbert":
                 numNodes=Integer.parseInt(JOptionPane.showInputDialog(null, "Número de nodos: "));
                probability=Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese la probabilidad: "));
                graph= new GraphAlgorithm();
                graph.generateGraphFile("Gilbert_N"+numNodes+"_P", genGilbert(numNodes, probability, false, false));
                
                break;
            case "Geográfico Simple":
                 numNodes=Integer.parseInt(JOptionPane.showInputDialog(null, "Número de nodos: "));
                connectivity=Float.parseFloat(JOptionPane.showInputDialog(null, "Ingrese la probabilidad: "));
                graph= new GraphAlgorithm();
                graph.generateGraphFile("Geografico_N"+numNodes+"_P", genGeografico(numNodes, connectivity, false, false));
                
                break;
            case "BarBabási-Albert":
                 numNodes=Integer.parseInt(JOptionPane.showInputDialog(null, "Número de nodos: "));
                coordenade=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese grado de conexión: "));
                graph= new GraphAlgorithm();
                graph.generateGraphFile("Barabasi_N"+numNodes+"_G"+coordenade, genBarabasiAlbert(numNodes, coordenade, false, false));
                
                break;
        default:
            break;
        }
    }
    
}
