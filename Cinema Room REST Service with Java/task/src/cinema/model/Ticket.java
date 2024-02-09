package cinema.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

public class Ticket {
    private Seat ticket;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID token;

    public Ticket(Seat seat) {
        this.ticket = seat;
        this.token = UUID.randomUUID();
    }

    public Seat getTicket() {
        return ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void clearToken() {
        this.token = null;
    }
}
