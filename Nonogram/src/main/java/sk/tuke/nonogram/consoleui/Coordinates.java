package sk.tuke.nonogram.consoleui;


import sk.tuke.nonogram.core.TileState;

public class Coordinates {
    private final int XFrom;
    private final int YFrom;
    private final int XTo;
    private final int YTo;
    private final TileState state;


    public Coordinates(int XFrom, int YFrom, int XTo, int YTo, TileState state) {
        this.XFrom = XFrom;
        this.YFrom = YFrom;
        this.XTo = XTo;
        this.YTo = YTo;
        this.state = state;
    }

    public int getXFrom() {
        return XFrom;
    }

    public int getYFrom() {
        return YFrom;
    }

    public int getXTo() {
        return XTo;
    }

    public int getYTo() {
        return YTo;
    }

    public TileState getState() {
        return state;
    }
}
