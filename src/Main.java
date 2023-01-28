package src;
import java.util.Objects;
import java.util.Scanner;

public class Main {
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

        boxes = Utils.createBoxes();

        Utils.drawBoard(boxes);

        System.out.print("Let's play\n");

        while(Objects.equals(winner, "")){
            System.out.print("Introduce your move: ");
            String chosenBox = scanner.nextLine();
            boolean boxIsAvailable = Utils.checkAvailableBox(chosenBox, boxes);
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
                        winner = Utils.checkWinner(boxes);
                        boxNotMatched = false;
                    } else {
                        boxIndex++;
                    }
                }
                Utils.drawBoard(boxes);
                if (turn == 1) {
                    turn++;
                } else if (turn == 2) {
                    turn--;
                }
                movementsCounter++;
                if (movementsCounter > 8 && Objects.equals(winner, "")) {
                    winner = "Draw";
                }
            } else {
                System.out.print("That box is already taken. Chose another one.\n");
            }
        }

        System.out.print("Winner: " + winner);
    }
}
