package cinema;

import java.util.UUID;

public record Token(UUID token, Seats ticket) {
}
