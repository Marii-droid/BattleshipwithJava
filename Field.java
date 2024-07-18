package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Field {
    final static int SIZE = 10;
    final static char WATER = '~';
    final static char SHIP = 'O';
    final static char HIT = 'X';
    final static char MISS = 'M';
    int placedShip = Battleships.values().length;
    int playerNumber;

    public Field(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    static Scanner sc = new Scanner(System.in);
    char[][] field = createField();

    public void startGame() {
        System.out.printf("Player %d, place your ships on the game field\n", playerNumber);
        System.out.println();
    }

    public void takeShot() {
        //input user
        System.out.printf("Player %d, it's your turn:\n", playerNumber);
        Coordinate userShot = new Coordinate(sc.nextLine());
        boolean isSunk = false;

        while (invalidShot(userShot)) {
            System.out.println("\nError! You entered the wrong coordinates! \n Try again:\n");
            userShot = new Coordinate(sc.nextLine());
        }
        boolean isShip = isAShip(userShot);
        boolean isHit = isAHit(userShot);
        if (isShip) {
            field[userShot.row][userShot.col] = HIT;
            isSunk = Gamerules.isSunkenShip(userShot, this);
        } else if (isHit) {
            field[userShot.row][userShot.col] = HIT;
        } else {
            field[userShot.row][userShot.col] = MISS;
        }

        if (isSunk) {
            placedShip--;
            if (placedShip == 0) {
                System.out.println("\nYou sank the last ship. You won. Congratulations!\n");
            } else {
                System.out.println("\nYou sank a ship! Specify a new target:\n");
            }
        } else if (isShip) {
            System.out.println("\nYou hit a ship! Try again:\n");
        } else {
            System.out.println("\nYou missed! Try again:\n");
        }
    }
    boolean allShipsDestroyed(){
        return placedShip == 0;
    }

    private boolean invalidShot(Coordinate userShot) {
        if (userShot.row < 0 || userShot.row >= SIZE || userShot.col < 0 || userShot.col >= SIZE) {
            return true;
        }
        return false;
    }

    public boolean isAShip(Coordinate inputCoordinate) {
        return field[inputCoordinate.row][inputCoordinate.col] == SHIP;
    }

    private boolean isAHit(Coordinate inputCoordinate){
        return field[inputCoordinate.row][inputCoordinate.col] == HIT;
    }

    public void printOpenField() {

        System.out.print(" ");
        for (int column = 1; column <= SIZE; column++) {
            System.out.print(" " + column);
        }
        System.out.println();

        for (int row = 0; row < SIZE; row++) {
            System.out.printf("%c ", 'A' + row);
            for (int column = 0; column < SIZE; column++) {
                char position = field[row][column];
                System.out.print(position + " ");
            }
            System.out.println();
        }
    }

    public void printHiddenField() {
        System.out.print(" ");
        for (int column = 1; column <= SIZE; column++) {
            System.out.print(" " + column);
        }
        System.out.println();

        for (int row = 0; row < SIZE; row++) {
            System.out.printf("%c ", 'A' + row);
            for (int column = 0; column < SIZE; column++) {
                char position = field[row][column];
                if (position == SHIP) {
                    position = WATER;
                }
                System.out.print(position + " ");
            }
            System.out.println();
        }
    }

    private char[][] createField() {
        char[][] field = new char[SIZE][SIZE];
        for (char[] row : field) {
            Arrays.fill(row, WATER);
        }
        return field;
    }

    public void placeShip(Battleships ship) {
        CoordinatesShip coordinatesShip = locationShip(ship);

        for (int row = coordinatesShip.firstCharacter; row <= coordinatesShip.secondCharacter; row++) {
            for (int column = coordinatesShip.firstInt; column <= coordinatesShip.secondInt; column++) {
                int[] location = {row - 'A', column - 1};
                field[location[0]][location[1]] = this.SHIP;
            }
        }
    }

    public CoordinatesShip locationShip(Battleships battleships) {
        System.out.println();
        System.out.println("Please enter the coordinates of the %s (%d cells):".formatted(battleships.getName(), battleships.getCells()));
        while (true) {
            String[] coordinates = sc.nextLine().split(" ");
            CoordinatesShip coordinatesShip = new CoordinatesShip(battleships, coordinates);

            if (Gamerules.isOutOfBounds(coordinatesShip)) {
                System.out.println("Error! Wrong ship location! Try again:\n");
                continue;
            }
            if (Gamerules.isIncorrectLength(coordinatesShip, battleships)) {
                System.out.println("Error! Wrong length! Try again:\n");
                continue;
            }
            if (Gamerules.isAdjacent(coordinatesShip, this)) {
                System.out.println("Error! Too close to adjacent ship! Try again:\n");
                continue;
            }
            return coordinatesShip;
        }
    }
}
