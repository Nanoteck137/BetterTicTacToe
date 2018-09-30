package net.nanoteck137.game;

import org.joml.*;

public class Cell {
    private int index;

    public Vector4f color;

    public int playerIndex;
    public boolean selected;


    public Cell(int index) {
        this.index = index;
        this.color = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.playerIndex = -1;
        this.selected = false;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return Integer.toString(index);
    }

    public boolean isValid(int playerIndex) {
        return selected && playerIndex == this.playerIndex;
    }
}