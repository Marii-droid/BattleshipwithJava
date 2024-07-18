package battleship;

public class Coordinate {
    int row;
    int col;
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Coordinate(String coordinate) {
        char firstCharacter = coordinate.charAt(0);
        int firstInt = Integer.parseInt(coordinate.substring(1));
        row = firstCharacter -'A';
        col = firstInt - 1;
    }
}
