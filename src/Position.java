public class Position {

    int row;
    int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public double distanceTo(Position other) {
        int dr = this.row - other.row;
        int dc = this.col - other.col;
        return Math.sqrt(dr*dr + dc*dc);
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "[" + row + "," + col + "]";
    }
}
