package PrisonSearch;

public enum Cell {
    ROCK,
    EMPTY_PRESSURE_PAD,
    PRESSURE_PAD_ROCK,
    PRESSURE_PAD_ME,
    EMPTY,
    TELEPORT,
    OBSTACLE,
    ME;

    boolean isValidEnd() {
        return this == PRESSURE_PAD_ROCK || this == EMPTY || this == TELEPORT || this == OBSTACLE;
    }

    @Override
    public String toString() {
        if(this == ROCK)
            return "*";
        if(this == EMPTY_PRESSURE_PAD)
            return "_";
        if(this == PRESSURE_PAD_ROCK)
            return "=";
        if(this == EMPTY)
            return ".";
        if(this == TELEPORT)
            return "|";
        if(this == OBSTACLE)
            return "#";
        if(this == PRESSURE_PAD_ME)
            return "+";
        return "M";
    }
}