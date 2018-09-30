package net.nanoteck137.tictactoe.game;

import org.joml.*;

public class Player {
    private int index;
    private Vector4f color;

    public Player(int index, Vector4f color) {
        this.index = index;
        this.color = color;
    }

    public int getIndex() {
        return index;
    }

    public Vector4f getColor() {
        return color;
    }
}