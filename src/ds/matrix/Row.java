package ds.matrix;



class Row {
    private boolean[] contents;
    private int[] location = new int[2];
    private int value = -1;

    Row(int size, int value, int row, int column) {
        contents = new boolean[size];
        this.value = value + 1;
        location[0] = row;
        location[1] = column;
    }

    int[] getLocation() {
        return location;
    }

    int getValue() {
        return value;
    }

    boolean getValAt(int index) {
        return contents[index];
    }

    void fill(int index) {
        contents[index] = true;
    }

}
