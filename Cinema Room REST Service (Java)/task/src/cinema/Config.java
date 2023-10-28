package cinema;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan
public class Config {
    @Value("${cinema.rows}")
    int totalRows;
    @Value("${cinema.columns}")
    int totalColumns;
    private final List<Seats> seatsList = new ArrayList<>();

    @Bean
    public Cinema cinema() {
        return new Cinema("IdeaCinema", totalRows, totalColumns, seatsList);
    }

}
