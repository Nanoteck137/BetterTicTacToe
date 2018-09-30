package net.nanoteck137.game;

import java.lang.Math;

import org.joml.*;

public class Board {

    private static final int BOARD_CELLS = 3;
    private static final int PLAYER_COUNT = 2;

    private Cell[] cells;
    
    private Player[] players;
    private int currentPlayer = 0;
    
    private boolean reset = false;

    private int playerWon = -1;

    public Board() {
        players = new Player[PLAYER_COUNT];
        players[0] = new Player(0, new Vector4f(1.0f, 0.0f, 0.0f, 1.0f));
        players[1] = new Player(1, new Vector4f(0.0f, 0.0f, 1.0f, 1.0f));

        cells = new Cell[BOARD_CELLS * BOARD_CELLS];

        for(int i = 0; i < BOARD_CELLS; i++) {
            for(int j = 0; j < BOARD_CELLS; j++) {
                int index =i + j * BOARD_CELLS;
                Cell cell = new Cell(i + (j * BOARD_CELLS));
                cells[index] = cell;
            }
        }
    }

    public void update(Matrix4f matrix, Input input) {
        if(input.isButtonPressed(0)) {
			Vector2f mousePos = input.getMousePosition();
			
			Vector3f test = new Vector3f(mousePos.x, 800 -mousePos.y, 0.0f);
			Vector3f test2 = new Vector3f();
			//TODO: Change the viewport input to the actual window size
			matrix.unproject(test, new int[] { 0, 0, 800, 800 }, test2);
			
			int x = (int)Math.floor(test2.x) / 3;
			int y = (int)Math.floor(test2.y) / 3;
        	
            Cell cell = cells[x + y * BOARD_CELLS];
            if(!cell.selected) {
                cell.color = players[currentPlayer].getColor();

                cell.selected = true;
                cell.playerIndex = currentPlayer;
             
                checkBoard();
                
                currentPlayer++;
                currentPlayer %= 2;
            }
        }
    }

    public void render(Renderer renderer) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                Cell cell = cells[i + j * BOARD_CELLS];
                if(reset)
                	cell.color.w = 0.7f;
                renderer.renderRect(i * 3 + 0.05f, j * 3 + 0.05f, 2.9f, 2.9f, cell.color);
            }
        }
    }

    private void checkBoard() {
        for (int i = 0; i < cells.length / BOARD_CELLS; i++) {
            if (cells[i].isValid(currentPlayer) &&
                cells[i + BOARD_CELLS].isValid(currentPlayer) &&
                cells[i + (BOARD_CELLS * 2)].isValid(currentPlayer))
            {
                reset = true;
                playerWon = currentPlayer;
                return;
            }

            if (cells[i * 3].isValid(currentPlayer) &&
                cells[i * 3 + 1].isValid(currentPlayer) &&
                cells[i * 3 + 2].isValid(currentPlayer))
            {
                reset = true;
                playerWon = currentPlayer;
                return;
            }

            if (i == 0) {
                if (cells[i].isValid(currentPlayer) &&
                    cells[i + 4].isValid(currentPlayer) &&
                    cells[i + 4 * 2].isValid(currentPlayer))
                {
                    reset = true;
                    playerWon = currentPlayer;
                    return;
                }
            }

            if (i == 2) {
                if (cells[i].isValid(currentPlayer) &&
                    cells[i + 2].isValid(currentPlayer) &&
                    cells[i + (2 * 2)].isValid(currentPlayer))
                {
                    reset = true;
                    playerWon = currentPlayer;
                    return;
                }
            }
        }

        int numSelected = 0;
        for(int i = 0; i < cells.length; i++) {
            if(cells[i].selected)
                numSelected++;
        }

        if(numSelected == cells.length) {
            reset = true;
            System.out.println("Nobody wins");
            playerWon = -2;
            return;
        }
    }

    public boolean needReset() {
        return reset;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getPlayerWon() {
        return playerWon;
    }

}