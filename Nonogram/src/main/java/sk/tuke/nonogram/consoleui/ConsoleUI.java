package sk.tuke.nonogram.consoleui;



import org.antlr.v4.runtime.misc.NotNull;
import sk.tuke.nonogram.core.Map;
import sk.tuke.nonogram.core.Tile;
import sk.tuke.nonogram.core.TileState;
import sk.tuke.nonogram.entity.Comment;
import sk.tuke.nonogram.entity.Rating;
import sk.tuke.nonogram.entity.Score;
import sk.tuke.nonogram.service.CommentServiceRestClient;
import sk.tuke.nonogram.service.RatingServiceRestClient;
import sk.tuke.nonogram.service.ScoreServiceRestClient;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

public class ConsoleUI {
    private final Map map;
    // ANSI escape code constants for text colors and background colors
    String RESET = "\u001B[0m";
    String BLACK_BG = "\u001B[40m";
    String WHITE_BG = "\u001B[47m";

    public ConsoleUI(String fileName) throws FileNotFoundException {
        map = new Map(fileName);
        Scanner scanner = new Scanner(System.in);
    }

    public void gameLoop( Scanner scanner ){
//        while(!map.isSolved()){
//            Coordinates coordinates;
//
//            //Clear screen
//            System.out.print("\033[H\033[2J");
//            System.out.flush();
//
//            printMap();
//            coordinates = parseInput(scanner);
//            map.changeTilesState(coordinates);
//        }
//        printMap();
//        addGame(scanner);
//        System.out.println("YOU WIN!!");
    }


    private void printMap(){
        int[][] horizontalField = map.getHorizontalField();
        int[][] verticalField = map.getVerticalField();
        Tile[][] pictureField = map.getPictureField();
        int cols = map.getColsNumber();
        int rows = map.getRowsNumber();

        for (int horizontalFieldRows=0; horizontalFieldRows < horizontalField.length; horizontalFieldRows++) {
            for (int verticalFieldCols=0; verticalFieldCols < verticalField[1].length; verticalFieldCols++){
                System.out.print("   ");
            }
            for (int horizontalFieldCols=0; horizontalFieldCols < cols; horizontalFieldCols++) {
                System.out.print("|" + horizontalField[horizontalFieldRows][horizontalFieldCols] + " ");
            }
            System.out.println();
        }
        for (int verticalFieldRows=0; verticalFieldRows < rows; verticalFieldRows++){
            for (int verticalFieldCols=0; verticalFieldCols < verticalField[verticalFieldRows].length; verticalFieldCols++){
                System.out.print(" " + verticalField[verticalFieldRows][verticalFieldCols] + " ");
            }
            for (int pictureCols=0; pictureCols < cols; pictureCols++){
                if(pictureField[verticalFieldRows][pictureCols].getState() == TileState.WHITE){
                    System.out.print("|" + WHITE_BG + "  " + RESET);
                } else if (pictureField[verticalFieldRows][pictureCols].getState() == TileState.COLORED) {
                    System.out.print("|" + BLACK_BG + "  " + RESET);
                } else if (pictureField[verticalFieldRows][pictureCols].getState() == TileState.CROSSED) {
                    System.out.print("|" + WHITE_BG + "XX" + RESET);
                }
            }
            System.out.println();
        }
    }

    private Coordinates parseInput(@NotNull Scanner scanner ) throws ArrayIndexOutOfBoundsException {
        System.out.println("press 1 to select XX");
        System.out.println("press 2 to select " + BLACK_BG + "  " + RESET);
        System.out.println("press 3 to select " + WHITE_BG + "  " + RESET);
        System.out.println("Example: 1,2-1,4;2");

        String input = scanner.nextLine();

        try {
            String[] numbers = input.split(";");
            String[] coordinatesString = numbers[0].split("-");

            String[] coordinatesFrom = coordinatesString[0].split(",");
            String[] coordinatesTo = coordinatesString[1].split(",");
            int xFrom = Integer.parseInt(coordinatesFrom[0]);
            int yFrom = Integer.parseInt(coordinatesFrom[1]);

            int xTo = Integer.parseInt(coordinatesTo[0]);
            int yTo = Integer.parseInt(coordinatesTo[1]);
            int stateNum = Integer.parseInt(numbers[1]);

            TileState state = switch (stateNum) {
                case 1 -> TileState.CROSSED;
                case 2 -> TileState.COLORED;
                default -> TileState.WHITE;
            };
            return new Coordinates( xFrom, yFrom, xTo, yTo, state);
        }catch (Exception ArrayOutOfBounds){
            Coordinates ret = parseInputSingle(input);
//            System.out.println("incorrect input");
            return ret;
        }
    }

    private Coordinates parseInputSingle(String input) throws ArrayIndexOutOfBoundsException {
        try {
            String[] numbers = input.split(";");

            String[] coordinates = numbers[0].split(",");

            int xFrom = Integer.parseInt(coordinates[0]);
            int yFrom = Integer.parseInt(coordinates[1]);
            int xTo = xFrom;
            int yTo = yFrom;

            int stateNum = Integer.parseInt(numbers[1]);

            TileState state = switch (stateNum) {
                case 1 -> TileState.CROSSED;
                case 2 -> TileState.COLORED;
                default -> TileState.WHITE;
            };
            return new Coordinates( xFrom, yFrom, xTo, yTo, state);
        }catch (Exception ArrayOutOfBounds){
            System.out.println("incorrect input");
            return null;
        }
    }

    private void addGame(Scanner scanner){
        String nickName;
        System.out.println("Do you want to save your game? (y/n)");

        while (true){
            String ans = scanner.nextLine();
            if(ans.equalsIgnoreCase("y")){
                System.out.println("enter your nickname:");
                nickName = scanner.nextLine();

                addScore(scanner, nickName);
                addRating(scanner, nickName);
                addComment(scanner, nickName);
                break;
            }else if(ans.equalsIgnoreCase("n")) {
                break;
            }else{
                System.out.println("Invalid input");
            }
        }
    }

    private void addRating(Scanner scanner, String nickName){
        System.out.println("How would you rate this game?");
        Integer ratingValue = null;

        while (ratingValue == null || ratingValue < 0){
            System.out.println("enter non-zero value");
            try {
                ratingValue = scanner.nextInt();
            }catch (Exception E){
                ratingValue = null;
                scanner.nextLine();
                System.out.println("incorrect input");
            }
        }

        Rating rating = new Rating(nickName, "nonogram", ratingValue, new Date());
        RatingServiceRestClient ratingClient = new RatingServiceRestClient();
        ratingClient.setRating(rating);
    }

    private void addScore(Scanner scanner, String nickName){
        int scoreValue = map.getScorePoints();
        Score score = new Score("nonogram", nickName, scoreValue, new Date());
        ScoreServiceRestClient scoreClient = new ScoreServiceRestClient();
        scoreClient.addScore(score);
    }

    private void addComment(Scanner scanner, String nickName){
        System.out.println("add your comment to your game");
        scanner.nextLine();
        String commentText = scanner.nextLine();
        Comment comment = new Comment(nickName, "nonogram", commentText, new Date());
        CommentServiceRestClient commentClient = new CommentServiceRestClient();
        commentClient.addComment(comment);
    }
}