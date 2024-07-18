package battleship;

public class CoordinatesShip {

    char firstCharacter;
    char secondCharacter;
    int firstInt;
    int secondInt;
    Battleships shipType;

    CoordinatesShip(Battleships ship, String[] coordinates) {
        String firstCoordinate = coordinates[0];
        String secondCoordinate = coordinates[1];

        firstCharacter = firstCoordinate.charAt(0);
        firstInt = Integer.parseInt(firstCoordinate.substring(1));

        secondCharacter = secondCoordinate.charAt(0);
        secondInt = Integer.parseInt(secondCoordinate.substring(1));

        boolean isReverse = firstCharacter > secondCharacter || firstInt > secondInt;
        if (isReverse) {
            char tempChar = firstCharacter;
            firstCharacter = secondCharacter;
            secondCharacter = tempChar;

            int tempInt = firstInt;
            firstInt = secondInt;
            secondInt = tempInt;

        }
    }

    public static int calculateLength(CoordinatesShip ship) {

        char firstCharacter = ship.firstCharacter;
        int firstInt = ship.firstInt;

        char secondCharacter = ship.secondCharacter;
        int secondInt = ship.secondInt;

        int lengthCharacter = (secondCharacter - firstCharacter);
        int lengthInt = (secondInt - firstInt);

        // length ships
        if ((lengthCharacter > lengthInt && lengthInt == 0)) {
            return (secondCharacter - firstCharacter) + 1; //vertical down
        } else if ((lengthCharacter < lengthInt && lengthInt == 0)) {
            return (firstCharacter - secondCharacter) + 1; // vertical up
        } else if ((lengthInt > lengthCharacter && lengthCharacter == 0)) {
            return (secondInt - firstInt) + 1;
        } else {
            return (firstInt - secondInt) + 1;
        }
    }
}