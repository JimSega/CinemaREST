package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Cinema {
    @Value("${password}")
    String passwordSecret;
    @Value("${price.before4}")
    int priceBefore4;
    @Value("${price.after4}")
    int priceAfter4;
    @JsonIgnore
    private final Map<UUID, Seats> mapUUID = new ConcurrentHashMap<>();
    @JsonIgnore
    private final String name;
    private int rows;
    private int columns;
    private List<Seats> seatsList;
    public Cinema (String name, int rows, int columns, List<Seats> seatsList) {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
        this.seatsList = seatsList;
    }

    @PostConstruct
    public void initSeatsList() {
        int o = 1;
        for(int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                if(i <= 4) {
                    seatsList.add(new Seats(o++, i, j, true, priceBefore4));
                } else {
                    seatsList.add(new Seats(o++, i, j, true, priceAfter4));
                }
            }

        }
    }

    @PreDestroy
    public void destroySeatsList() {
        seatsList.clear();
    }
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
    public List<Seats> getSeats() {
        return seatsList;
    }

    public void setSeats(List<Seats> seatsList) {
        this.seatsList = seatsList;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public CinemaDTO getAvailableSeats() {
        return new CinemaDTO(rows, columns, seatsList.stream().filter(Seats::isFree).toList());
        }

    public Map<UUID, Seats> getMapUUID() {
        return mapUUID;
    }
}
