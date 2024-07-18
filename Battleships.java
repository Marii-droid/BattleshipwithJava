package battleship;

public enum Battleships {
    AIRCRAFT (5, "Aircraft Carrier"),
    BATTLESHIPS (4, "Battleships"),
    SUBMARINE (3, "Submarine"),
    CRUISER (3, "Cruiser"),
    DESTROYER (2, "Destroyer"),;

    final int cells;
    final String name;

    Battleships (int cells, String name) {
        this.cells = cells;
        this.name = name;
    }

    public int getCells() {
        return cells;
    }
    public String getName() {
        return name;
    }
}
