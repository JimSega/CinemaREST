package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seats {
    @JsonIgnore
    private int id;
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean free;

    public Seats() {

    }

    public Seats(int id, int row, int column, boolean free, int price) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.free = free;
        this.price = price;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean isFree() {
        return free;
    }
}
