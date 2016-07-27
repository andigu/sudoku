package ds;

import ds.matrix.Matrix;
import ds.nodes.ColumnHeadNode;
import ds.nodes.Node;


class ToroidalList {
    private ColumnHeadNode root = new ColumnHeadNode();

    ToroidalList(Matrix matrix) {
        ColumnHeadNode currentColumn = root;
        // Generate column heads
        for (int i = 0; i < matrix.getNumColumns(); i++) {
            currentColumn.setRight(new ColumnHeadNode());
            currentColumn.getRight().setLeft(currentColumn);
            currentColumn = (ColumnHeadNode) currentColumn.getRight();
        }
        root.setLeft(currentColumn);
        currentColumn.setRight(root);

        // Generate actual grid
        for (int i = 0; i < matrix.getNumRows(); i++) {
            currentColumn = (ColumnHeadNode) root.getRight();
            Node firstNode = null;
            Node lastCreatedNode = null;
            for (int j = 0; j < matrix.getNumColumns(); j++) {
                if (matrix.getValAt(new int[]{i, j})) {
                    Node currentNode = currentColumn;
                    while (currentNode.getDown() != null) { // Get to the bottom of the current row
                        currentNode = currentNode.getDown();
                    }
                    currentNode.setDown(new Node(new int[]{i, j}));
                    if (firstNode == null) {
                        firstNode = currentNode.getDown(); // Remember the first Node in the column to link first and last
                    }
                    currentNode.getDown().setUp(currentNode);
                    currentNode.getDown().setLeft(lastCreatedNode);
                    currentNode.getDown().setHead(currentColumn);
                    if (lastCreatedNode != null) {
                        currentNode.getDown().getLeft().setRight(currentNode.getDown());
                    }
                    lastCreatedNode = currentNode.getDown();
                    currentColumn.increaseSize();
                }
                currentColumn = (ColumnHeadNode) currentColumn.getRight();
            }
            if (lastCreatedNode != null) { // Link first and last item
                lastCreatedNode.setRight(firstNode);
                firstNode.setLeft(lastCreatedNode);
            }
        }

        // Link bottom nodes to their column heads
        currentColumn = (ColumnHeadNode) root.getRight();
        for (int i = 0; i < matrix.getNumColumns(); i++) {
            Node currentNode = currentColumn;
            while (currentNode.getDown() != null) {
                currentNode = currentNode.getDown();
            }
            currentNode.setDown(currentColumn);
            currentColumn.setUp(currentNode);
            currentColumn = (ColumnHeadNode) currentColumn.getRight();
        }
    }

    ColumnHeadNode getRoot() {
        return root;
    }

    void cover(ColumnHeadNode columnHead) {
        columnHead.removeHorizontally();
        Node currentRow = columnHead.getDown();
        while(currentRow != columnHead){
            Node currentNode = currentRow.getRight();
            while (currentNode != currentRow) {
                currentNode.removeVertically();
                currentNode.getHead().decreaseSize();
                currentNode = currentNode.getRight();
            }
            currentRow = currentRow.getDown();
        }
    }

    void uncover(ColumnHeadNode columnHead) {
        Node currentRow = columnHead.getUp();
        while (currentRow != columnHead) {
            Node currentNode = currentRow.getLeft();
            while (currentNode != currentRow) {
                currentNode.getHead().increaseSize();
                currentNode.reinsertVertically();
                currentNode = currentNode.getLeft();
            }
            currentRow = currentRow.getUp();
        }
        columnHead.reinsertHorizontally();
    }

    ColumnHeadNode chooseNextColumn() {
        ColumnHeadNode currentColumn = (ColumnHeadNode)root.getRight();
        ColumnHeadNode smallest = currentColumn;
        while (currentColumn.getRight() != root) {
            currentColumn = (ColumnHeadNode)currentColumn.getRight();
            if (currentColumn.getSize() < smallest.getSize()) {
                smallest = currentColumn;
            }
        }
        return smallest;
    }

}

