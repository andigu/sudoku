package ds.nodes;

public class Node {
    private Node left;
    private Node right;
    private Node up;
    private Node down;
    private ColumnHeadNode head;
    private int[] location; // for debugging purposes only; serves no real use

    Node() {
        this.left = null;
        this.right = null;
        this.up = null;
        this.down = null;
        this.head = null;
    }

    public Node(int[] location) {
        this.left = null;
        this.right = null;
        this.up = null;
        this.down = null;
        this.head = null;
        this.location = location;
    }

    public void setLeft(Node leftNode) {
        this.left = leftNode;
    }

    public void setRight(Node rightNode) {
        this.right = rightNode;
    }

    public void setUp(Node upNode) {
        this.up = upNode;
    }

    public void setDown(Node downNode) {
        this.down = downNode;
    }

    public void setHead(ColumnHeadNode head) {
        this.head = head;
    }

    public void removeHorizontally() {
        this.left.setRight(this.right);
        this.right.setLeft(this.left);
    }

    public void removeVertically() {
        this.up.setDown(this.down);
        this.down.setUp(this.up);
    }

    public void reinsertHorizontally() {
        this.left.setRight(this);
        this.right.setLeft(this);
    }

    public void reinsertVertically() {
        this.up.setDown(this);
        this.down.setUp(this);
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public Node getUp() {
        return this.up;
    }

    public Node getDown() {
        return this.down;
    }

    public int[] getLocation() {
        return this.location;
    }

    public ColumnHeadNode getHead() {
        return this.head;
    }
}
