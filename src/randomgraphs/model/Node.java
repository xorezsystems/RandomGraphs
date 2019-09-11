package randomgraphs.model;

/**
 *
 * @author xorez
 */
public class Node {

    public int id;
    public int grade;
    public double x;
    public double y;
    public boolean visited;
    private boolean F;
    private double w;
    public int connected;

    public Node() {
        this.grade = 0;
        this.x = 0;
        this.y = 0;
        this.connected = 0;
        this.F = false;
        this.w = 0;
    }

    public Node(Node v1) {
        this.grade = v1.getCoor();
        this.id = v1.getId();
        this.x = v1.getX();
        this.y = v1.getY();
        this.connected = v1.getConnected();
        this.F = v1.getF();
        this.w = v1.getW();
    }

    public Node(int keyRandom) {
        this.id = keyRandom;
        this.grade = 0;
        this.x = 0;
        this.y = 0;
        this.connected = 0;
        this.F = false;
        this.w = 0;
    }

    public Node(int keyRandom, double x1, double y1) {
        this.id = keyRandom;
        this.grade = 0;
        this.x = x1;
        this.y = y1;
        this.F = false;
        this.connected = 0;
        this.w = 0;
    }

    public Node(int keyRandom, int con, int gra, double x1, double y1, boolean g, double p) {
        this.id = keyRandom;
        this.connected = con;
        this.grade = gra;
        this.x = x1;
        this.y = y1;
        this.F = g;
        this.w = p;
    }

    public void colocarrect(Node n1) {
        this.grade = n1.getCoor();
        this.id = n1.getId();
        this.x = n1.getX();
        this.y = n1.getX();
    }

    public void copy(Node n1) {
        this.grade = n1.getCoor();
        this.id = n1.getId();
        this.x = n1.getX();
        this.y = n1.getY();
        this.connected = n1.getConnected();
        this.F = n1.getF();
        this.w = n1.getW();
    }

    public int getId() {
        return this.id;
    }

    public void aumCoor(int i) {
        this.grade = this.grade + i;
    }

    public void setCoor(int i) {
        this.grade = i;
    }

    public int getCoor() {
        return this.grade;
    }

    public void set(int idAzar) {
        this.id = idAzar;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x1) {
        this.x = x1;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y1) {
        this.y = y1;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public void connect() {
        this.connected = 1;
    }

    public int getConnected() {
        return this.connected;
    }

    public void setF(boolean a) {
        this.F = a;
    }

    public boolean getF() {
        return this.F;
    }

    public void setW(double p) {
        this.w = p;
    }

    public double getW() {
        return this.w;
    }
}
