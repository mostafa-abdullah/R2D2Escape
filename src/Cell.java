/**
 * Created by mostafa on 10/11/17.
 */
public enum Cell {
    ROCK,
    EMPTY_PRESSURE_PAD,
    OCCUPIED_PRESSURE_PAD,
    EMPTY,
    TELEPORT,
    OBSTACLE,
    ME;

    boolean isValidEnd() {
        return this == OCCUPIED_PRESSURE_PAD || this == EMPTY || this == TELEPORT;
    }
}