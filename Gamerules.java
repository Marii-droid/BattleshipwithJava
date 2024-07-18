package battleship;

import java.util.Scanner;

public class Gamerules {

    public static boolean isOutOfBounds(CoordinatesShip ship) {
        if ((ship.firstInt < 1 || ship.firstInt > 10 || ship.secondInt < 1 || ship.secondInt > 10) ||
                (ship.firstCharacter > 'J' || ship.secondCharacter > 'J')) {
            return true;
        } else if (ship.firstCharacter != ship.secondCharacter && ship.firstInt != ship.secondInt) {
            return true;
        }
        return false;
    }

    public static boolean isIncorrectLength(CoordinatesShip coordinatesShip, Battleships ship) {
        return CoordinatesShip.calculateLength(coordinatesShip) != ship.cells;
    }

    public static boolean isAdjacent(CoordinatesShip coordinatesShip, Field field) {
        for (int i = coordinatesShip.firstCharacter - 1; i <= coordinatesShip.secondCharacter + 1; i++) {
            for (int j = coordinatesShip.firstInt - 1; j <= coordinatesShip.secondInt + 1; j++) {
                int row = i - 'A';
                int col = j - 1;
                if (row >= 0 && col >= 0 && row < 10 && col < 10) {
                    if (field.field[row][col] == Field.SHIP) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isSunkenShip(Coordinate coordinate, Field field) {
        int initialRow = coordinate.row;
        int initialCol = coordinate.col;

        while(initialCol < field.field.length - 1) {
            initialCol++;
            char rightSide = field.field[initialRow][initialCol];
            if (rightSide == Field.SHIP) {
                return false;
            } else if (rightSide == Field.WATER) {
                break;
            } else if (rightSide == Field.HIT) {
                continue;
            }
        }
        initialCol = coordinate.col;
        while(initialCol > 0) {
            initialCol--;
            char leftSide = field.field[initialRow][initialCol];
            if (leftSide == Field.SHIP) {
                return false;
            } else if (leftSide == Field.WATER) {
                break;
            } else if (leftSide == Field.HIT) {
                continue;
            }
        }
        while(initialRow > 0) {
            initialRow--;
            char topSide = field.field[initialRow][coordinate.col];
            if (topSide == Field.SHIP) {
                return false;
            } else if (topSide == Field.WATER) {
                break;
            } else if (topSide == Field.HIT) {
                continue;
            }
        }
        initialRow = coordinate.row;
        while(initialRow < field.field[0].length - 1) {
            initialRow++;
            char bottomSide = field.field[initialRow][coordinate.col];
            if (bottomSide == Field.SHIP) {
                return false;
            } else if (bottomSide == Field.WATER) {
                break;
            } else if (bottomSide == Field.HIT) {
                continue;
            }
        }
        return true;
    }

    public static boolean pressEnter(Scanner scanner, boolean isPlayerOne) {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        return !isPlayerOne;
    }
}
