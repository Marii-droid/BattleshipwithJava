package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playerOne = true;

        Field playerOneField = new Field(1);
        playerOneField.startGame();
        playerOneField.printOpenField(); //place ship player 1
        for (Battleships battleships : Battleships.values()) { //player 1
            playerOneField.placeShip(battleships);
            System.out.println();
            playerOneField.printOpenField();
        }
        playerOne = Gamerules.pressEnter(scanner, playerOne);

        Field playerTwoField = new Field(2);
        playerTwoField.startGame();
        playerTwoField.printOpenField(); //place ship player 2
        for (Battleships battleships : Battleships.values()) { //player 2
            playerTwoField.placeShip(battleships);
            System.out.println();
            playerTwoField.printOpenField();
            System.out.println();
        }
        playerOne = Gamerules.pressEnter(scanner, playerOne);
        System.out.println();

        //player 1 loop
        while (!playerOneField.allShipsDestroyed() || !playerTwoField.allShipsDestroyed()) {
            if (playerOne) {
                try {
                    playerTwoField.printHiddenField();
                    System.out.println("---------------------");
                    playerOneField.printOpenField();
                    System.out.println();
                    playerTwoField.takeShot();
                    playerOne = Gamerules.pressEnter(scanner, playerOne);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (!playerOne) {
                try {
                    playerOneField.printHiddenField();
                    System.out.println("---------------------");
                    playerTwoField.printOpenField();
                    System.out.println();
                    playerOneField.takeShot();
                    playerOne = Gamerules.pressEnter(scanner, playerOne);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}