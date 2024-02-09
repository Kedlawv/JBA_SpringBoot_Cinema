package cinema.repo;

import cinema.exception.WrongTokenException;
import cinema.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TicketStaticRepo {

    private List<Ticket> soldTickets;

    public TicketStaticRepo() {
        this.soldTickets = new ArrayList<>();
    }

    public void saveTicket(Ticket ticket) {
        soldTickets.add(ticket);
    }

    public Ticket deleteTicket(UUID uuid) {
        Ticket ticket = this.soldTickets.stream()
                .filter(t -> t.getToken().equals(uuid))
                .findFirst()
                .orElseThrow(WrongTokenException::new);
        soldTickets.remove(ticket);
        ticket.clearToken();
        return ticket;
    }

    public List<Ticket> getSoldTickets() {
        return this.soldTickets;
    }
 }
