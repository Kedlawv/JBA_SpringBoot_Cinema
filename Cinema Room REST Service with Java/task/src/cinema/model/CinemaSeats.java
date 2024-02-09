package cinema.model;

import java.util.ArrayList;
import java.util.List;

public class CinemaSeats {
    private int rows;
    private int columns;
    private List<Seat> seats;

    public CinemaSeats(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new ArrayList<>();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
