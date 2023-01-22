package src;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    public static String checkWinner(Box[] _boxes){
        String winner = "";
        String playerOne = "x";
        String playerTwo = "o";

        if((Objects.equals(_boxes[0].player, playerOne) && Objects.equals(_boxes[1].player, playerOne) && Objects.equals(_boxes[2].player, playerOne)) || (Objects.equals(_boxes[3].player, playerOne) && Objects.equals(_boxes[4].player, playerOne) && Objects.equals(_boxes[5].player, playerOne)) || (Objects.equals(_boxes[6].player, playerOne) && Objects.equals(_boxes[7].player, playerOne) && Objects.equals(_boxes[8].player, playerOne)) || (Objects.equals(_boxes[0].player, playerOne) && Objects.equals(_boxes[4].player, playerOne) && Objects.equals(_boxes[8].player, playerOne)) || (Objects.equals(_boxes[2].player, playerOne) && Objects.equals(_boxes[4].player, playerOne) && Objects.equals(_boxes[6].player, playerOne)) || (Objects.equals(_boxes[0].player, playerOne) && Objects.equals(_boxes[3].player, playerOne) && Objects.equals(_boxes[6].player, playerOne)) || (Objects.equals(_boxes[1].player, playerOne) && Objects.equals(_boxes[4].player, playerOne) && Objects.equals(_boxes[7].player, playerOne)) || (Objects.equals(_boxes[2].player, playerOne) && Objects.equals(_boxes[5].player, playerOne) && Objects.equals(_boxes[8].player, playerOne))){
          winner = "Player One";
        }
        if((Objects.equals(_boxes[0].player, playerTwo) && Objects.equals(_boxes[1].player, playerTwo) && Objects.equals(_boxes[2].player, playerTwo)) || (Objects.equals(_boxes[3].player, playerTwo) && Objects.equals(_boxes[4].player, playerTwo) && Objects.equals(_boxes[5].player, playerTwo)) || (Objects.equals(_boxes[6].player, playerTwo) && Objects.equals(_boxes[7].player, playerTwo) && Objects.equals(_boxes[8].player, playerTwo)) || (Objects.equals(_boxes[0].player, playerTwo) && Objects.equals(_boxes[4].player, playerTwo) && Objects.equals(_boxes[8].player, playerTwo)) || (Objects.equals(_boxes[2].player, playerTwo) && Objects.equals(_boxes[4].player, playerTwo) && Objects.equals(_boxes[6].player, playerTwo)) || (Objects.equals(_boxes[0].player, playerTwo) && Objects.equals(_boxes[3].player, playerTwo) && Objects.equals(_boxes[6].player, playerTwo)) || (Objects.equals(_boxes[1].player, playerTwo) && Objects.equals(_boxes[4].player, playerTwo) && Objects.equals(_boxes[7].player, playerTwo)) || (Objects.equals(_boxes[2].player, playerTwo) && Objects.equals(_boxes[5].player, playerTwo) && Objects.equals(_boxes[8].player, playerTwo))){
            winner = "Player Two";
        }

        return winner;
    }

    public static boolean checkAvailableBox(String _chosenBox, Box[] _boxes) {
        boolean boxIsAvailable = true;

        int boxIndex = 0;

        while(boxIsAvailable && boxIndex < _boxes.length){
            if(Objects.equals(_boxes[boxIndex].position, _chosenBox) && !Objects.equals(_boxes[boxIndex].player, " ")){
                boxIsAvailable = false;
            }
            boxIndex++;
        }

        return boxIsAvailable;
    }

    public static Box[] createBoxes(){
        String[] boxesCoords = {"11", "12", "13", "21", "22", "23", "31", "32", "33"};

        Box[] boxes = new Box[9];

        for (int i = 0; i < boxesCoords.length; i++) {
            Box box = new Box(boxesCoords[i]);
            box.player = " ";
            boxes[i] = box;
        }

        return boxes;
    }

    public static void drawBoard(Box[] _boxes){
        System.out.print("-------\n");
        System.out.print("|" + _boxes[0].player + "|" + _boxes[1].player + "|" + _boxes[2].player +"|\n");
        System.out.print("-------\n");
        System.out.print("|" + _boxes[3].player + "|" + _boxes[4].player + "|" + _boxes[5].player +"|\n");
        System.out.print("-------\n");
        System.out.print("|" + _boxes[6].player + "|" + _boxes[7].player + "|" + _boxes[8].player +"|\n");
        System.out.print("-------\n");
    }

    public static void main(String[] args){
        String title = "Tres en Raya";
        String winner = "";
        Scanner scanner= new Scanner(System.in);
        int movementsCounter = 0;
        String playerOne = "x";
        String playerTwo = "o";
        int turn = 1;
        Box[] boxes;

        System.out.print("Welcome to " + title + "\n");

        boxes = createBoxes();

        drawBoard(boxes);

        System.out.print("Let's play\n");

        while(Objects.equals(winner, "")){
            System.out.print("Introduce your move: ");
            String chosenBox = scanner.nextLine();
            boolean boxIsAvailable = checkAvailableBox(chosenBox, boxes);
            if(boxIsAvailable) {
                System.out.print("You have chosen the " + chosenBox + " box\n");
                boolean boxNotMatched = true;
                int boxIndex = 0;
                while (boxNotMatched && boxIndex < boxes.length) {
                    if (Objects.equals(boxes[boxIndex].position, chosenBox)) {
                        if (turn == 1) {
                            boxes[boxIndex].player = playerOne;
                        }
                        if (turn == 2) {
                            boxes[boxIndex].player = playerTwo;
                        }
                        winner = checkWinner(boxes);
                        boxNotMatched = false;
                    } else {
                        boxIndex++;
                    }
                }
                drawBoard(boxes);
                if (turn == 1) {
                    turn++;
                } else if (turn == 2) {
                    turn--;
                }
                movementsCounter++;
                if (movementsCounter > 8 && Objects.equals(winner, "")) {
                    winner = "Draw";
                }
            }else {
                System.out.print("That box is already taken. Chose another one.\n");
            }
        }

        System.out.print("Winner: " + winner);
    }
}
