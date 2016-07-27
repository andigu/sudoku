package ds.nodes;

public class ColumnHeadNode extends Node {
    private int size = 0;
    //private int position = -1; <- for debugging purposes

    public ColumnHeadNode() {
        super();
        //this.position = position; <- for debugging purposes
    }

    public ColumnHeadNode getHead() {
        return this;
    }

    public int getSize() {
        return this.size;
    }

    public void decreaseSize() {
        this.size--;
    }

    public void increaseSize() {
        this.size++;
    }
}
