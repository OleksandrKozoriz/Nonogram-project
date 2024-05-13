package sk.tuke.nonogram.core;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import sk.tuke.nonogram.consoleui.Coordinates;


public class Map {
    private final int[][] horizontalField;
    private final int[][] verticalField;
    private final Tile[][] pictureField;
    private final int colsNumber;
    private final int rowsNumber;
    private final int scorePoints;

    public Map(String fileName) throws FileNotFoundException {
        //Get map from Json file
        String currentDir = System.getProperty("user.dir");
        FileReader reader = new FileReader(currentDir + "/src/main/java/sk/tuke/nonogram/maps" + fileName);
        Gson gson = new Gson();
        jsonMap map = gson.fromJson(reader, jsonMap.class);
        horizontalField = map.horizontalArr;
        verticalField = map.verticalArr;
        scorePoints = map.scorePoints;
        //----------------------
        //Get array size
        colsNumber = horizontalField[0].length;
        rowsNumber = verticalField.length;
        //--------------
        pictureField = new Tile[rowsNumber][colsNumber];

        for(int rows = 0; rows < rowsNumber; rows++){
            for (int cols = 0; cols < colsNumber; cols++){
                pictureField[rows][cols] = new Tile(TileState.WHITE);
            }
        }
    }

    public void changeTilesState(Coordinates coordinates){
        if( coordinates == null ){
            System.out.println("coordinates invalid");
            return;
        }

        if( coordinates.getXFrom() == coordinates.getXTo() ){
            int row = coordinates.getXTo();
            for (int cols = coordinates.getYFrom(); cols <= coordinates.getYTo(); cols++){
                pictureField[row][cols].setState(coordinates.getState());
            }
        }else if( coordinates.getYFrom() == coordinates.getYTo() ){
            int col = coordinates.getYTo();
            for (int rows = coordinates.getXFrom(); rows <= coordinates.getXTo(); rows++){
                pictureField[rows][col].setState(coordinates.getState());
            }
        }else{
            System.out.println("x or y coordinate must be the same");
        }
    }

    public boolean isSolved(){
        boolean solved = true;

        for (int rows = 0; rows < rowsNumber; rows++){

            int cols = colsNumber-1;
            int[] verticalCell = new int[verticalField[0].length];
            int verticalCellIndex = verticalField[0].length - 1;

            while (cols >= 0){
                if (pictureField[rows][cols].getState() == TileState.COLORED){
                    verticalCell[verticalCellIndex]++;
                    if (cols - 1 < 0){

                    }else if (pictureField[rows][cols-1].getState() != TileState.COLORED){
                        verticalCellIndex-=1;
                    }
                    if (verticalCellIndex < 0){
                        break;
                    }
                }
                cols--;
            }
            for (cols = 0; cols < verticalCell.length; cols++){
                if( verticalCell[cols] != verticalField[rows][cols] ) {
                    solved = false;
                }
            }
        }
        return solved;
    }

    public int[][] getHorizontalField() {
        return horizontalField;
    }

    public int[][] getVerticalField() {
        return verticalField;
    }

    public Tile[][] getPictureField() {
        return pictureField;
    }

    public int getColsNumber() {
        return colsNumber;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getScorePoints() {
        return scorePoints;
    }

    static class jsonMap{
        public String name;
        public int[][] horizontalArr;
        public int[][] verticalArr;
        public int scorePoints;
    }
}
