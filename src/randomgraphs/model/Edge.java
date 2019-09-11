package randomgraphs.model;

/**
 *
 * @author xorez
 */
public class Edge implements Comparable<Edge> {

    private int id;
    private int id1;
    private int id2;
    private double w;
    private boolean f;

    public Edge(int id1, int id2) {
        this.id1 = id1;
        this.id2 = id2;
        this.w = 0;
    }

    public Edge(int id1, int id2, double p) {
        this.id1 = id1;
        this.id2 = id2;
        this.w = p;
    }

    public Edge(Edge edge) {
        this.id1 = edge.getId1();
        this.id2 = edge.getId2();
        this.w = edge.getP();
    }

    public Edge() {
    }

    public int getId() {
        return this.id;
    }

    public int getId1() {
        return this.id1;
    }

    public int getId2() {
        return this.id2;
    }

    public double getP() {
        return this.w;
    }

    public void setP(double p) {
        this.w = p;
    }

    public void setId(int keyNodo) {
        this.id = keyNodo;
    }

    public void setId1(int keyNodo) {
        this.id1 = keyNodo;
    }

    public void setId2(int keyNodo) {
        this.id2 = keyNodo;
    }

    public void setF(boolean a) {
        this.f = a;
    }

    public boolean getF() {
        return this.f;
    }

    public void copy(Edge edge) {
        this.id1 = edge.getId1();
        this.id2 = edge.getId2();
        this.w = edge.getP();
    }

    @Override
    public int compareTo(Edge edge) {
        if (this.w > edge.getP()) {
            return 1;
        } else if (this.w < edge.getP()) {
            return -1;
        } else {
            return 0;
        }
    }
}
