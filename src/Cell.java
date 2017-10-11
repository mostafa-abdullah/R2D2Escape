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

    @Override
    public String toString() {
        if(this == ROCK)
            return "*";
        if(this == EMPTY_PRESSURE_PAD)
            return "_";
        if(this == OCCUPIED_PRESSURE_PAD)
            return "+";
        if(this == EMPTY)
            return ".";
        if(this == TELEPORT)
            return "|";
        if(this == OBSTACLE)
            return "#";
        return "M";
    }
}