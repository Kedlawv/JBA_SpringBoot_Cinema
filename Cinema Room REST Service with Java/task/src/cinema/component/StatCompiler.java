package cinema.component;

import cinema.model.CinemaStats;
import cinema.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatCompiler {

    public CinemaStats calculateStats(List<Ticket> tickets) {
        CinemaStats cinemaStats = new CinemaStats();
        int income = tickets.stream()
                .mapToInt(ticket -> ticket.getTicket().getPrice())
                .sum();

        int available = 81 - tickets.size();

        int purchased = tickets.size();

        cinemaStats.setIncome(income);
        cinemaStats.setAvailable(available);
        cinemaStats.setPurchased(purchased);

        return cinemaStats;
    }
}
