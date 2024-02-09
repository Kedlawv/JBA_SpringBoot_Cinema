package cinema.repo;

import cinema.model.CinemaSeats;
import cinema.model.Seat;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class CinemaStaticRepo {

    private CinemaSeats cinemaSeats;

    public CinemaStaticRepo(){
        this.cinemaSeats = initCinemaSeats();
    }

    private CinemaSeats initCinemaSeats() {
        CinemaSeats cinemaSeats = new CinemaSeats(9, 9);
        for (int row = 1; row <= cinemaSeats.getRows(); row++) {
            for(int column = 1; column <= cinemaSeats.getColumns(); column++){
                int price = row <= 4 ? 10 : 8;
                cinemaSeats.getSeats().add(new Seat(row, column, price));
            }
        }

        return cinemaSeats;
    }

    public CinemaSeats getCinemaSeats() {
        return cinemaSeats;
    }

    public Optional<Seat> getSeat(int row, int column) {
        Optional<Seat> first = this.cinemaSeats.getSeats().stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findFirst();

        return first;

    }
}
