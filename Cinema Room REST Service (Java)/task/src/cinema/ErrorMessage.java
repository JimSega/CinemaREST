package cinema;

public enum ErrorMessage {
    OUT_OF_BOUNDS("The number of a row or a column is out of bounds!"),
    HAS_BEEN_PURCHASED("The ticket has been already purchased!");
    final String error;
    ErrorMessage(String error) {
        this.error = error;
    }
    @Override
    public String toString() {
        return this.error;
    }

}
