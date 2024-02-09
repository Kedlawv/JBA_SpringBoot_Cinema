package cinema.controller;

import cinema.component.StatCompiler;
import cinema.exception.AuthException;
import cinema.exception.SeatLocationOutOfBoundsException;
import cinema.exception.SeatNotAvailableException;
import cinema.model.CinemaSeats;
import cinema.model.CinemaStats;
import cinema.model.Seat;
import cinema.model.Ticket;
import cinema.repo.CinemaStaticRepo;
import cinema.repo.TicketStaticRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CinemaController {

    private final CinemaStaticRepo cinemaStaticRepo;
    private final TicketStaticRepo ticketStaticRepo;
    private final StatCompiler statCompiler;

    public CinemaController(CinemaStaticRepo cinemaStaticRepo, TicketStaticRepo ticketStaticRepo, StatCompiler statCompiler) {
        this.cinemaStaticRepo = cinemaStaticRepo;
        this.ticketStaticRepo = ticketStaticRepo;
        this.statCompiler = statCompiler;
    }

    @GetMapping("/seats")
    public CinemaSeats getCinemaSeats() {
        return cinemaStaticRepo.getCinemaSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody Seat seat) {
        Optional<Seat> optionalOfSeat = cinemaStaticRepo.getSeat(seat.getRow(), seat.getColumn());

        if (optionalOfSeat.isEmpty()) {
            throw new SeatLocationOutOfBoundsException("The number of a row or a column is out of bounds!");

        } else if (!optionalOfSeat.get().isAvailable()) {
            throw new SeatNotAvailableException("The ticket has been already purchased!");

        } else {
            Seat bookedSeat = optionalOfSeat.get();
            bookedSeat.setAvailable(false);
            Ticket ticket = new Ticket(bookedSeat);
            ticketStaticRepo.saveTicket(ticket);
            return ResponseEntity.ok(ticket);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<Ticket> returnTicket(@RequestBody Map<String,String> tokenJson){
        UUID tokenId = UUID.fromString(tokenJson.get("token"));
        Ticket ticket = ticketStaticRepo.deleteTicket(tokenId);
        ticket.getTicket().setAvailable(true);
        return ResponseEntity.ok().body(ticket);
    }

    @GetMapping("/stats")
    public ResponseEntity<CinemaStats> getStats(@RequestParam String password) {
        if (!"super_secret".equals(password)) {
            throw new AuthException("The password is wrong!");
        }

        return ResponseEntity.ok()
                .body(statCompiler.calculateStats(ticketStaticRepo.getSoldTickets()));
    }
}
